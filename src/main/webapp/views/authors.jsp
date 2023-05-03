<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Author"%>

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

  Author[] authors = new Author[]{a1, a2, a3, a4};

  int length = authors.length;
  pageContext.setAttribute("authors", authors);
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
  <title>Авторы</title>
  <head>
    <meta charset="UTF-8" />
    <title>Авторы</title>
  </head>
  <body>
    <jsp:include page="jspf/header.jsp" />
    <div id="main">
      <section>
        <aside class="leftAside">
          <h3>Список авторов</h3>
          <table>
            <thead>
              <tr>
                <th scope="col">Код</th>
                <th scope="col">ФИО</th>
                <th scope="col">E-mail</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach
                var="author"
                items="${authors}"
              >
                <tr>
                  <td>${author.getId()}</td>
                  <td>${author.getFullName()}</td>
                  <td>${author.getEmail()}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </aside>
      </section>

      <section>
        <article>
          <h3>Автор</h3>
          <div class="text-article">
            <form
              method="POST"
              action=""
            >
              <p>
                <label for="namerole">ФИО </label>
                <input
                  type="text"
                  name="fullName"
                />
              </p>
              <p>
                <label for="namerole">E-mail </label>
                <input
                  type="text"
                  name="email"
                />
              </p>
            </form>
            <p>
              <button type="submit">Добавить</button>
            </p>
          </div>
        </article>
      </section>
    </div>
    <jsp:include page="jspf/footer.jsp" />
  </body>
</html>
