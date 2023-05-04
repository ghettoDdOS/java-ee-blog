package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.ConnectionProperty;
import dao.EmpConnBuilder;
import domain.Author;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete-author")
public class DeleteAuthorServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  ConnectionProperty prop;
  String select_all_authors = "SELECT id, full_name, email, created_at FROM author ORDER BY id ASC";
  String select_author_ById = "SELECT id, full_name, email, created_at FROM author WHERE id = ?";

  String delete_author = "DELETE FROM author WHERE id = ?";
  ArrayList<Author> authors = new ArrayList<Author>();
  ArrayList<Author> deleteAuthors = new ArrayList<Author>();
  String userPath;

  public DeleteAuthorServlet() throws FileNotFoundException, IOException {
    super();
    prop = new ConnectionProperty();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");

    EmpConnBuilder builder = new EmpConnBuilder();

    // Загрузка всех авторов
    try (Connection conn = builder.getConnection()) {
      String strId = request.getParameter("id");
      Long id = null;
      if (strId != null) {
        id = Long.parseLong(strId);
      }

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(select_all_authors);
      if (rs != null) {
        authors.clear();
        while (rs.next()) {
          authors.add(new Author(
              rs.getLong("id"),
              rs.getString("full_name"),
              rs.getString("email"),
              rs.getTimestamp("created_at").toLocalDateTime()));
        }
        rs.close();
        request.setAttribute("authors", authors);
      } else {
        System.out.println("Ошибка загрузки author");
      }
      try (PreparedStatement preparedStatement = conn.prepareStatement(select_author_ById)) {
        preparedStatement.setLong(1, id);
        rs = preparedStatement.executeQuery();
        if (rs != null) {
          deleteAuthors.clear();
          while (rs.next()) {
            deleteAuthors.add(new Author(
                rs.getLong("id"),
                rs.getString("full_name"),
                rs.getString("email"),
                rs.getTimestamp("created_at").toLocalDateTime()));
          }
          rs.close();
          request.setAttribute("authorsDelete", deleteAuthors);
        } else {
          System.out.println("Ошибка загрузки author");

        }
      } catch (Exception e) {
        System.out.println(e);
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    RequestDispatcher view = getServletContext().getRequestDispatcher("/views/delete-author.jsp");
    view.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    EmpConnBuilder builder = new EmpConnBuilder();

    try (Connection conn = builder.getConnection()) {
      Long id = Long.parseLong(request.getParameter("id"));
      try (PreparedStatement preparedStatement = conn.prepareStatement(delete_author)) {
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
      } catch (Exception e) {
        System.out.println(e);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    doGet(request, response);
  }
}
