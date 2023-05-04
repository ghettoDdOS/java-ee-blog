package domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс для данных блога
 */
public class Blog {
  // Идентификатор блога
  private Long id;

  // Заголовок
  private String title;

  // Содержание
  private String content;

  // Дата создания
  private LocalDateTime createdAt;

  // Внешний ключ -ссылка на сущность Author
  private Long idAuthor;

  // Навигационное свойства - ссылка на автора
  private Author author;

  public Blog() {
  }

  public Blog(String title, String content, Author author) {
    this.title = title;
    this.content = content;
    this.author = author;
  }

  public Blog(String title, String content, Long idAuthor, Author author) {
    this.title = title;
    this.content = content;
    this.idAuthor = idAuthor;
    this.author = author;
  }

  public Blog(Long id,
      String title,
      String content,
      Long idAuthor,
      Author author) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.idAuthor = idAuthor;
    this.author = author;
  }

  public Blog(Long id,
      String title,
      String content,
      Long idAuthor,
      Author author,
      LocalDateTime createdAt) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.idAuthor = idAuthor;
    this.author = author;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getCreatedAt() {
    return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
  }

  public Timestamp getCreatedAtTimestamp() {
    return Timestamp.valueOf(createdAt);
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Long getIdAuthor() {
    return idAuthor;
  }

  public void setIdAuthor(Long idAuthor) {
    this.idAuthor = idAuthor;
  }

  public String getAuthor() {
    return author.getFullName();
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  @Override
  public String toString() {
    return "Blog [id=" + id
        + ", title=" + title
        + ", content=" + content
        + ", createdAt=" + createdAt
        + ", idAuthor=" + idAuthor
        + ", author=" + author + "]";
  }
}
