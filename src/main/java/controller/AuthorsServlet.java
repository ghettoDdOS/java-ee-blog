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

/**
 * Servlet implementation class AuthorsServlet
 */
@WebServlet("/authors")
public class AuthorsServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  ConnectionProperty prop;
  String select_all_authors = "SELECT id, full_name, email, created_at FROM author";
  String insert_author = "INSERT INTO author (full_name, email, created_at) VALUES(?, ?, ?)";
  ArrayList<Author> authors = new ArrayList<Author>();
  String userPath;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public AuthorsServlet() throws FileNotFoundException, IOException {
    super();
    prop = new ConnectionProperty();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");

    EmpConnBuilder builder = new EmpConnBuilder();

    // Загрузка всех авторов
    try (Connection conn = builder.getConnection()) {
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
        RequestDispatcher view = getServletContext().getRequestDispatcher("/views/authors.jsp");
        view.forward(request, response);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request,
   *      HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    EmpConnBuilder builder = new EmpConnBuilder();
    try (Connection conn = builder.getConnection()) {
      String fullName = request.getParameter("fullName");
      String email = request.getParameter("email");

      Author newAuthor = new Author(fullName, email);
      try (PreparedStatement preparedStatement = conn.prepareStatement(insert_author)) {
        preparedStatement.setString(
            1, newAuthor.getFullName());
        preparedStatement.setString(
            2, newAuthor.getEmail());
        preparedStatement.setTimestamp(
            3, newAuthor.getCreatedAtTimestamp());
        preparedStatement.executeUpdate();
      } catch (Exception e) {
        System.out.println(e);
      }
    } catch (Exception e) {
      System.out.println(e);
      RequestDispatcher view = getServletContext().getRequestDispatcher("/views/authors.jsp");
      view.forward(request, response);
    }
    doGet(request, response);
  }
}
