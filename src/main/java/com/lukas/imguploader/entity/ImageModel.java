package com.lukas.imguploader.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    ImageModel that = (ImageModel) o;
    return id != null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
