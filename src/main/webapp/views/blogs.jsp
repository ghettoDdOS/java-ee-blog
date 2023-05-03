<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ page import="domain.Author"%>
<%@ page import="domain.Blog"%>

<%
Author a1 = new Author(
  1l,
  "Аверченко Аркадий Тимофеевич",
  "test1@test.ru"
);
Author a2 = new Author(
  2l,
  "Аксаков Сергей Тимофеевич",
  "test2@test.ru"
);
Author a3= new Author(
  3l,
  "Астафьев Виктор Петрович",
  "test3@test.ru"
);
Author a4 = new Author(
  4l,
  "Булгаков Михаил Афанасьевич",
  "test4@test.ru"
);
Author[] authors = new Author[]{a1, a2, a3,a4};

pageContext.setAttribute("authors", authors);

Blog b1 = new Blog(
  "Блог первый",
  "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
  1l,
  a1
);
Blog b2 = new Blog(
  "Блог второй",
  "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
  2l,
  a2
);
Blog b3 = new Blog(
  "Блог третий",
  "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
  3l,
  a3
);
Blog b4 = new Blog(
  "Блог четвертый",
  "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
  4l,
  a4
);

Blog[] blogs = new Blog[]{b1, b2, b3, b4};

pageContext.setAttribute("blogs", blogs);
%>

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
                  <option disabled>Выберите должность</option>
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
