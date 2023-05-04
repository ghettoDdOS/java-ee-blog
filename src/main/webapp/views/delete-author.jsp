<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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
    <title>Авторы</title>

    <link
      rel="stylesheet"
      href="css/bootstrap.min.css"
    />
  </head>
  <body class="d-flex flex-column h-100">
    <jsp:include page="/jspf/header.jsp" />

    <main class="flex-grow-1">
      <div class="container-fluid">
        <div class="row my-2">
          <div class="col-12 col-lg-8">
            <h1 class="bg-info p-2 rounded text-center">Список авторов</h1>
            <div class="w-100 h-100 overflow-auto">
              <table class="table bg-secondary py-3 rounded">
                <thead>
                  <tr>
                    <th scope="col">Код</th>
                    <th scope="col">ФИО</th>
                    <th scope="col">E-mail</th>
                    <th scope="col">Дата регистрации</th>
                    <th scope="col">Редактировать</th>
                    <th scope="col">Удалить</th>
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
                      <td class="text-center" width="20">
                        <a
                          href='<c:url value="/edit-author?id=${author.getId()}" />'
                          role="button"
                          class="btn btn-outline-primary"
                        >
                          <img
                            alt="Редактировать"
                            src="images/icon-edit.png"
                            width="20"
                          />
                        </a>
                      </td>
                      <td class="text-center" width="20">
                        <a
                          href='<c:url value="/delete-author?id=${author.getId()}"/>'
                          role="button"
                          class="btn btn-outline-primary"
                        >
                          <img
                            alt="Удалить"
                            src="images/icon-delete.png"
                            width="20"
                          />
                        </a>
                      </td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
          <div class="col-12 col-lg-4">
            <h1 class="bg-info p-2 rounded text-center">Редактирование автора</h1>
            <form
              method="POST"
              action=""
              class="bg-secondary p-3 rounded"
            >
              <div class="form-group">
                <label for="id">Код автора</label>
                <input
                  type="text"
                  class="form-control"
                  id="id"
                  name="id"
                  readonly
                  value="${authorsDelete[0].getId()}"
                />
              </div>
              <div class="form-group">
                <label for="fullName">ФИО</label>
                <input
                  type="text"
                  class="form-control"
                  id="fullName"
                  name="fullName"
                  readonly
                  value="${authorsDelete[0].getFullName()}"
                />
              </div>
              <div class="form-group">
                <label for="email">E-mail</label>
                <input
                  type="email"
                  class="form-control"
                  id="email"
                  name="email"
                  readonly
                  value="${authorsDelete[0].getEmail()}"
                />
              </div>
              <div class="form-group">
                <label for="createdAt">Дата регистрации</label>
                <input
                  type="text"
                  class="form-control"
                  id="createdAt"
                  name="createdAt"
                  readonly
                  value="${authorsDelete[0].getCreatedAt()}"
                />
              </div>
              <button
                type="submit"
                class="btn btn-primary"
              >
                Удалить
              </button>
              <a
                href='<c:url value="/authors" />'
                role="button"
                class="btn btn-secondary"
              >
                Отменить/Возврат
              </a>
            </form>
          </div>
        </div>
      </div>
    </main>

    <jsp:include page="/jspf/footer.jsp" />
  </body>
</html>
