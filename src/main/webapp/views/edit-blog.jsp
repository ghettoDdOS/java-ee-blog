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
    <title>Блоги</title>

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
            <h1 class="bg-info p-2 rounded text-center">Список блогов</h1>
            <div class="w-100 h-100 overflow-auto">
              <table class="table bg-secondary py-3 rounded">
                <thead>
                  <tr>
                    <th scope="col">Код</th>
                    <th scope="col">Заголовок</th>
                    <th scope="col">Содержание</th>
                    <th scope="col">Автор</th>
                    <th scope="col">Дата создания</th>
                    <th scope="col">Редактировать</th>
                    <th scope="col">Удалить</th>
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
                      <td
                        class="text-center"
                        width="20"
                      >
                        <a
                          href='<c:url value="/edit-blog?id=${blog.getId()}" />'
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
                      <td
                        class="text-center"
                        width="20"
                      >
                        <a
                          href='<c:url value="/delete-blog?id=${blog.getId()}"/>'
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
            <h1 class="bg-info p-2 rounded text-center">Редактировать блог</h1>
            <form
              method="POST"
              action=""
              class="bg-secondary p-3 rounded"
            >
              <div class="form-group">
                <label for="id">Код блога</label>
                <input
                  type="text"
                  class="form-control"
                  id="id"
                  name="id"
                  readonly
                  value="${blogsEdit[0].getId()}"
                />
              </div>
              <div class="form-group">
                <label for="title">Заголовок</label>
                <input
                  type="text"
                  class="form-control"
                  id="title"
                  name="title"
                  required
                  value="${blogsEdit[0].getTitle()}"
                />
              </div>
              <div class="form-group">
                <label for="content">Содержание</label>
                <textarea
                  class="form-control"
                  name="content"
                  id="content"
                  rows="3"
                  required
                >${blogsEdit[0].getContent()}</textarea>
              </div>
              <div class="form-group">
                <label for="id_author">Автор</label>
                <select
                  class="form-control"
                  id="id_author"
                  name="id_author"
                  required
                  value="${blogsEdit[0].getIdAuthor()}"
                >
                  <option disabled>Выберите автора</option>
                  <c:forEach
                    var="author"
                    items="${authors}"
                  >
                    <option value="${author.getId()}">
                      ${author.getFullName()}
                    </option>
                  </c:forEach>
                </select>
              </div>
              <div class="form-group">
                <label for="createdAt">Дата создания</label>
                <input
                  type="text"
                  class="form-control"
                  id="createdAt"
                  name="createdAt"
                  readonly
                  value="${blogsEdit[0].getCreatedAt()}"
                />
              </div>
              <button
                type="submit"
                class="btn btn-primary"
              >
                Редактировать
              </button>
              <a
                href='<c:url value="/blogs" />'
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
