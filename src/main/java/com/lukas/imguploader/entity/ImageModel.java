package com.lukas.imguploader.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Arrays;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "image_model", schema = "mysocial")
public class ImageModel {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id", nullable = false)
  private Long id;
  @Basic
  @Lob
  @Column(name = "image_bytes", nullable = true, columnDefinition = "LONGBLOB")
  private byte[] imageBytes;
  @Basic
  @Column(name = "name", nullable = false, length = 255)
  private String name;
  @Basic
  //@Column(name = "post_id", nullable = true)
  @JsonIgnore
  private Long postId;
  @Basic
  //@Column(name = "user_id", nullable = true)
  @JsonIgnore
  private Long userId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public byte[] getImageBytes() {
    return imageBytes;
  }

  public void setImageBytes(byte[] imageBytes) {
    this.imageBytes = imageBytes;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ImageModel that = (ImageModel) o;

    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    }
    if (!Arrays.equals(imageBytes, that.imageBytes)) {
      return false;
    }
    if (name != null ? !name.equals(that.name) : that.name != null) {
      return false;
    }
    if (postId != null ? !postId.equals(that.postId) : that.postId != null) {
      return false;
    }
    if (userId != null ? !userId.equals(that.userId) : that.userId != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + Arrays.hashCode(imageBytes);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (postId != null ? postId.hashCode() : 0);
    result = 31 * result + (userId != null ? userId.hashCode() : 0);
    return result;
  }
}
