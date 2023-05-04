package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import dao.ConnectionProperty;
import dao.EmpConnBuilder;
import domain.Author;
import domain.Blog;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BlogsServlet
 */
@WebServlet("/blogs")
public class BlogsServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  ConnectionProperty prop;
  String select_all_blogs = "SELECT id, title, content, created_at, id_author FROM blog ORDER BY id ASC";
  String select_all_authors = "SELECT id, full_name, email, created_at FROM author ORDER BY id ASC";
  String insert_blog = "INSERT INTO blog (title, content, created_at, id_author) VALUES(?,?,?,?)";
  ArrayList<Author> authors = new ArrayList<Author>();
  ArrayList<Blog> blogs = new ArrayList<Blog>();
  String userPath;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public BlogsServlet() throws FileNotFoundException, IOException {
    super();
    prop = new ConnectionProperty();
  }

  // Поиск автора по id
  private Author FindById(Long id, ArrayList<Author> authors) {
    if (authors != null) {
      for (Author a : authors) {
        if ((a.getId()).equals(id)) {
          return a;
        }
      }
    } else {
      return null;
    }
    return null;
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    response.setContentType("text/html");

    EmpConnBuilder builder = new EmpConnBuilder();

    try (Connection conn = builder.getConnection()) {
      // Загрузка всех авторов
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

      // Загрузка всех блогов
      long id;
      stmt = conn.createStatement();
      rs = stmt.executeQuery(select_all_blogs);
      if (rs != null) {
        blogs.clear();
        while (rs.next()) {
          id = rs.getLong("id_author");
          blogs.add(new Blog(
              rs.getLong("id"),
              rs.getString("title"),
              rs.getString("content"),
              id,
              FindById(id, authors),
              rs.getTimestamp("created_at").toLocalDateTime()));
        }
        rs.close();
        request.setAttribute("blogs", blogs);
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    RequestDispatcher view = getServletContext().getRequestDispatcher("/views/blogs.jsp");
    view.forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request,
   *      HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    EmpConnBuilder builder = new EmpConnBuilder();
    try (Connection conn = builder.getConnection()) {
      String title = request.getParameter("title");
      String content = request.getParameter("content");
      Long idAuthor = Long.parseLong(request.getParameter("id_author"));
      PreparedStatement preparedStatement = conn.prepareStatement(insert_blog);
      preparedStatement.setString(1, title);
      preparedStatement.setString(2, content);
      preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
      preparedStatement.setLong(4, idAuthor);
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      System.out.println(e);
      RequestDispatcher view = getServletContext().getRequestDispatcher("/views/blogs.jsp");
      view.forward(request, response);
    }
    doGet(request, response);
  }
}
