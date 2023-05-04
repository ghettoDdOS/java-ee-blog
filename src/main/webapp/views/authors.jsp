<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Author"%>


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
    <jsp:include page="/jspf/header.jsp" />
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
                <th scope="col">Дата регистраци</th>
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
                  <td>${author.getCreatedAt()}</td>
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
                <label for="fullName">ФИО </label>
                <input
                  type="text"
                  name="fullName"
                />
              </p>
              <p>
                <label for="email">E-mail </label>
                <input
                  type="text"
                  name="email"
                />
              </p>
              <p>
                <button type="submit">Добавить</button>
              </p>
            </form>
          </div>
        </article>
      </section>
    </div>
    <jsp:include page="/jspf/footer.jsp" />
  </body>
</html>
