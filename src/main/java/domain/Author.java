package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
  private LocalDateTime createdAt;

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

  public Author(Long id, String fullName, String email, LocalDateTime createdAt) {
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

  public String getCreatedAt() {
    return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
  }

  public void setCreatedAt(LocalDateTime createdAt) {
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
