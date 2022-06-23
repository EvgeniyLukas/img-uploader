package com.lukas.imguploader.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@ToString
@Table(name = "user", schema = "mysocial")
public class User implements UserDetails {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id", nullable = false)
  private Long id;
  @Basic
  @Column(name = "bio", columnDefinition = "text", length = -1)
  private String bio;
  @Basic
  @Column(name = "created_date", nullable = true, updatable = false)
  @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
  private LocalDateTime createdDate;
  @Basic
  @Column(name = "email", unique = true, length = 255)
  private String email;
  @Basic
  @Column(name = "lastname", nullable = false, length = 255)
  private String lastname;
  @Basic
  @Column(name = "name", nullable = false, length = 255)
  private String name;
  @Basic
  @Column(name = "password", nullable = true, length = 3000)
  private String password;
  @Basic
  @Column(name = "username", unique = true, length = 255)
  private String username;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @Exclude
  private List<Post> postsById;

  @ElementCollection(targetClass = ERole.class)
  @CollectionTable(name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"))
  private Set<ERole> userRolesById;

  @Transient
  private Collection<? extends GrantedAuthority> authorities;


  public User() {
  }

  public User(
      Long id,
      String email,
      String password,
      String username,
      Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.username = username;
    this.authorities = authorities;
  }

  @PrePersist
  public void onCreate() {
    this.createdDate = LocalDateTime.now();
  }


  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBio() {
    return bio;
  }

  public String getEmail() {
    return email;
  }


  public String getLastname() {
    return lastname;
  }


  public String getName() {
    return name;
  }


  public String getUsername() {
    return username;
  }


  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public Collection<Post> getPostsById() {
    return postsById;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    User user = (User) o;
    return id != null && Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
