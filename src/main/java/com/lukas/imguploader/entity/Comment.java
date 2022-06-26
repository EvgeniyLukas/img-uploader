package com.lukas.imguploader.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Comment comment = (Comment) o;
    return id != null && Objects.equals(id, comment.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
