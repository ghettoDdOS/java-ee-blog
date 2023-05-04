<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ page import="domain.Author"%>
<%@ page import="domain.Blog"%>

<!DOCTYPE html>
<html>
  <link
    rel="stylesheet"
    type="text/css"
    href="css/style.css"
  />
  <meta
    http-equiv="Content-Type"
    content="text/html"
    charset="UTF-8"
  />
  <title>Блоги</title>
  <head>
    <meta charset="UTF-8" />
    <title>Блоги</title>
  </head>
  <body>
    <jsp:include page="/jspf/header.jsp" />
    <div id="main">
      <aside class="leftAside">
        <h3>Список блогов</h3>
        <table>
          <thead>
            <tr>
              <th>Код</th>
              <th>Заголовок</th>
              <th>Содержание</th>
              <th>Автор</th>
              <th>Дата создания</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach
              var="blog"
              items="${blogs}"
            >
              <tr>
                <td>${blog.getId()}</td>
                <td>${blog.getTitle()}</td>
                <td>${blog.getContent()}</td>
                <td>${blog.getAuthor()}</td>
                <td>${blog.getCreatedAt()}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </aside>
      <section>
        <article>
          <h3>Создать блог</h3>
          <div class="text-article">
            <form
              method="POST"
              action=""
            >
              <p>
                <label for="title">Заголовок</label>
                <input
                  type="text"
                  name="title"
                />
              </p>
              <p>
                <label for="content">Содержание</label>
                <input
                  type="text"
                  name="content"
                />
              </p>
              <p>
                <label for="author">Автор</label>
                <select>
                  <option disabled>Выберите автора</option>
                  <c:forEach
                    var="author"
                    items="${authors}"
                  >
                    <option value="${author}">${author.getFullName()}</option>
                  </c:forEach>
                </select>
              </p>
            </form>

            <p>
              <button type="submit">Добавить</button>
            </p>
          </div>
        </article>
      </section>
    </div>
    <jsp:include page="/jspf/footer.jsp" />
  </body>
</html>
