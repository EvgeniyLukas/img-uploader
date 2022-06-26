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
  @ManyToOne(fetch = FetchType.EAGER)
  private Post post;


//  @Basic
//  @Column(name = "post_id", nullable = true)
//  private Long postId;

//  @ManyToOne(fetch = FetchType.EAGER)
//  @JoinColumn(name = "post_id", referencedColumnName = "id")
//  private Post postByPostId;

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



}
