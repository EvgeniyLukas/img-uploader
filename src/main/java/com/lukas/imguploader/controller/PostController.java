package com.lukas.imguploader.controller;


import com.lukas.imguploader.dto.PostDTO;
import com.lukas.imguploader.dto.UserDTO;
import com.lukas.imguploader.entity.Post;
import com.lukas.imguploader.entity.User;
import com.lukas.imguploader.facade.PostFacade;
import com.lukas.imguploader.payload.response.MessageResponse;
import com.lukas.imguploader.service.PostService;
import com.lukas.imguploader.validations.ResponseErrorValidation;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/post")
@CrossOrigin
public class PostController {

  @Autowired
  private PostFacade postFacade;
  @Autowired
  private PostService postService;
  @Autowired
  private ResponseErrorValidation responseErrorValidation;

  @PostMapping("/create")
  public ResponseEntity<Object> createPost(@Valid @RequestBody PostDTO postDTO,
      BindingResult bindingResult,
      Principal principal) {
    ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
    if (!ObjectUtils.isEmpty(errors)) {
      return errors;
    }

    Post post = postService.createPost(postDTO, principal);
    PostDTO createdPost = postFacade.postToPostDTO(post);

    return new ResponseEntity<>(createdPost, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<PostDTO>> getAllPosts() {
    List<PostDTO> postDTOList = postService.getAllPosts()
        .stream()
        .map(postFacade::postToPostDTO)
        .collect(Collectors.toList());

    return new ResponseEntity<>(postDTOList, HttpStatus.OK);
  }

  //получаем все посты текущего пользователя
  @GetMapping("/user/posts")
  public ResponseEntity<List<PostDTO>> getAllPostsForUser(Principal principal) {
    List<PostDTO> postDTOList = postService.getAllPostForUser(principal)
        .stream()
        .map(postFacade::postToPostDTO)
        .collect(Collectors.toList());

    return new ResponseEntity<>(postDTOList, HttpStatus.OK);
  }

  //работает когда лайкаем пост
  @PostMapping("/{postId}/{username}/like")
  public ResponseEntity<PostDTO> likePost(@PathVariable("postId") String postId,
      @PathVariable("username") String username) {
    Post post = postService.likePost(Long.parseLong(postId), username);
    PostDTO postDTO = postFacade.postToPostDTO(post);

    return new ResponseEntity<>(postDTO, HttpStatus.OK);
  }

  //удалить пост можно только если он принадлежит пользователю
  @PostMapping("/{postId}/delete")
  public ResponseEntity<MessageResponse> deletePost(@PathVariable("postId") String postId,
      Principal principal) {
    postService.deletePost(Long.parseLong(postId), principal);
    return new ResponseEntity<>(new MessageResponse("Post was deleted"), HttpStatus.OK);
  }

  @PostMapping("/update")
  public ResponseEntity<Object> updateUser(@RequestBody Post post,
      Principal principal) {
    Post postUpdate = postService.updatePost(post, principal);
    PostDTO postDTO = postFacade.postToPostDTO(postUpdate);
    return new ResponseEntity<>(postDTO, HttpStatus.OK);
  }
}
