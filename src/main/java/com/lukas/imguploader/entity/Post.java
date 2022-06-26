package com.lukas.imguploader.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.hibernate.Hibernate;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "post", schema = "mysocial")
public class Post {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id", nullable = false)
  private Long id;
  @Basic
  @Column(name = "caption", nullable = true, length = 255)
  private String caption;
  @Basic
  @Column(name = "created_date", nullable = true, updatable = false)
  private LocalDateTime createdDate;
  @Basic
  @Column(name = "likes", nullable = true)
  private Integer likes;
  @Basic
  @Column(name = "location", nullable = true, length = 255)
  private String location;
  @Basic
  @Column(name = "title", nullable = true, length = 255)
  private String title;
//  @Basic
//  @Column(name = "user_id", nullable = true)
//  private Long userId;
  @OneToMany(mappedBy = "post", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  private List<Comment> commentsById;
  @ManyToOne(fetch = FetchType.LAZY)
  @Exclude
  private User user;
  @Column
  @ElementCollection(targetClass = String.class)
  private Set<String> likedUsers = new HashSet<>();

  @PrePersist
  public void onCreate() {
    this.createdDate = LocalDateTime.now();
  }


  public Collection<Comment> getCommentsById() {
    return commentsById;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Post post = (Post) o;
    return id != null && Objects.equals(id, post.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
