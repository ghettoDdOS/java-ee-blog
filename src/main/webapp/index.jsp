<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html
  lang="ru"
  class="h-100"
>
  <head>
    <meta charset="UTF-8" />
    <meta
      http-equiv="X-UA-Compatible"
      content="IE=edge"
    />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0"
    />
    <title>Главная страница</title>

    <link
      rel="stylesheet"
      href="css/bootstrap.min.css"
    />
  </head>
  <body class="d-flex flex-column h-100">
    <jsp:include page="jspf/header.jsp" />

    <main class="flex-grow-1">
      <div class="container-fluid">
        <h2>Функции системы</h2>
        <div class="list-group">
          <a
            href="authors"
            class="list-group-item list-group-item-action"
          >
            Авторы
          </a>
          <a
            href="blogs"
            class="list-group-item list-group-item-action"
          >
            Блоги
          </a>
        </div>
      </div>
    </main>

    <jsp:include page="jspf/footer.jsp" />
  </body>
</html>
