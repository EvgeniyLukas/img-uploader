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
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "user", schema = "mysocial")
public class User {

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
  @CollectionTable(name="user_role",
  joinColumns = @JoinColumn(name = "user_id"))
  private Set<ERole> userRolesById;

  @Transient
  private Collection<? extends GrantedAuthority> authorities;

  @PrePersist
  public void onCreate() {
    this.createdDate = LocalDateTime.now();
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

  public void setBio(String bio) {
    this.bio = bio;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
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
