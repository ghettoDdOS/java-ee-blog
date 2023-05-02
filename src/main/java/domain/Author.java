package domain;

import java.util.Date;

/**
 * Класс данных об авторах
 */
public class Author {
  // Идентификатор автора
  private Long id;
  // Фио автора
  private String fullName;
  // E-mail
  private String email;
  // Дата регистрации
  private Date createdAt;

  public Author() {
  }

  public Author(String fullName, String email) {
    this.fullName = fullName;
    this.email = email;
  }

  public Author(Long id, String fullName, String email) {
    this.id = id;
    this.fullName = fullName;
    this.email = email;
  }

  public Author(Long id, String fullName, String email, Date createdAt) {
    this.id = id;
    this.fullName = fullName;
    this.email = email;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return "Author [id=" + id
        + ", fullName="
        + fullName + ", email="
        + email + ", createdAt="
        + createdAt + "]";
  }
}
