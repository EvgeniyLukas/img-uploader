package com.lukas.imguploader.entity;

import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "comment", schema = "mysocial")
public class Comment {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id", nullable = false)
  private Long id;
  @Basic
  @Column(name = "created_date", nullable = true)
  private LocalDateTime createdDate;
  @Basic
  @Column(name = "message", nullable = false, length = -1, columnDefinition = "text")
  private String message;
  @Basic
  @Column(name = "user_id", nullable = false)
  private Long userId;
  @Basic
  @Column(name = "username", nullable = false, length = 255)
  private String username;
  @Basic
  @Column(name = "post_id", nullable = true)
  private Long postId;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "post_id", referencedColumnName = "id")
  private Post postByPostId;

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

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Comment that = (Comment) o;

    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    }
    if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) {
      return false;
    }
    if (message != null ? !message.equals(that.message) : that.message != null) {
      return false;
    }
    if (userId != null ? !userId.equals(that.userId) : that.userId != null) {
      return false;
    }
    if (username != null ? !username.equals(that.username) : that.username != null) {
      return false;
    }
    if (postId != null ? !postId.equals(that.postId) : that.postId != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
    result = 31 * result + (message != null ? message.hashCode() : 0);
    result = 31 * result + (userId != null ? userId.hashCode() : 0);
    result = 31 * result + (username != null ? username.hashCode() : 0);
    result = 31 * result + (postId != null ? postId.hashCode() : 0);
    return result;
  }

  public Post getPostByPostId() {
    return postByPostId;
  }

  public void setPostByPostId(Post postByPostId) {
    this.postByPostId = postByPostId;
  }
}
