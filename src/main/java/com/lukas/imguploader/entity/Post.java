package com.lukas.imguploader.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
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
  private User user;
  @Column
  @ElementCollection(targetClass = String.class)
  private Set<String> likedUsers = new HashSet<>();

  @PrePersist
  public void onCreate() {
    this.createdDate = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public String getCaption() {
    return caption;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public Integer getLikes() {
    return likes;
  }

  public String getLocation() {
    return location;
  }


  public String getTitle() {
    return title;
  }




  public Collection<Comment> getCommentsById() {
    return commentsById;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Post that = (Post) o;

    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    }
    if (caption != null ? !caption.equals(that.caption) : that.caption != null) {
      return false;
    }
    if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) {
      return false;
    }
    if (likes != null ? !likes.equals(that.likes) : that.likes != null) {
      return false;
    }
    if (location != null ? !location.equals(that.location) : that.location != null) {
      return false;
    }
    if (title != null ? !title.equals(that.title) : that.title != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (caption != null ? caption.hashCode() : 0);
    result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
    result = 31 * result + (likes != null ? likes.hashCode() : 0);
    result = 31 * result + (location != null ? location.hashCode() : 0);
    result = 31 * result + (title != null ? title.hashCode() : 0);
    return result;
  }







}
