package com.lukas.imguploader.repository;

import com.lukas.imguploader.entity.Post;
import com.lukas.imguploader.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

  List<Post> findAllByUserOrderByCreatedDateDesc(User user);

  List<Post> findAllByOrderByCreatedDateDesc();

  Optional<Post> findPostByIdAndUser(Long id, User user);

}
