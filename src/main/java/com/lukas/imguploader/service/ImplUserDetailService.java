package com.lukas.imguploader.service;

import com.lukas.imguploader.entity.User;
import com.lukas.imguploader.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ImplUserDetailService implements UserDetailsService {


  private UserRepository userRepository;

  @Autowired
  public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    User user = userRepository.findUserByEmail(username)
        .orElseThrow(
            () -> new UsernameNotFoundException("Username not found with username: " + username));

    return build(user);
  }

  public User loadUserById(Long id) {
    return userRepository.findUserById(id).orElse(null);
  }


  public static User build(User user) {
    List<GrantedAuthority> authorities = user.getUserRolesById().stream()
        .map(role -> new SimpleGrantedAuthority(role.name()))
        .collect(Collectors.toList());

    return new User(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getPassword(),
        authorities);
  }

}
