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
import domain.Blog;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete-blog")
public class DeleteBlogServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  ConnectionProperty prop;
  String select_all_blogs = "SELECT id, title, content, created_at, id_author FROM blog ORDER BY id ASC";
  String select_all_authors = "SELECT id, full_name, email, created_at FROM author ORDER BY id ASC";
  String select_blog_ById = "SELECT id, title, content, created_at, id_author FROM blog WHERE id = ?";
  String delete_blog = "DELETE FROM blog WHERE id = ?";

  ArrayList<Author> authors = new ArrayList<Author>();
  ArrayList<Blog> blogs = new ArrayList<Blog>();
  ArrayList<Blog> deleteBlogs = new ArrayList<Blog>();
  String userPath;

  public DeleteBlogServlet() throws FileNotFoundException, IOException {
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

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");

    EmpConnBuilder builder = new EmpConnBuilder();

    try (Connection conn = builder.getConnection()) {
      String strId = request.getParameter("id");
      Long idBlogSelected = null;
      if (strId != null) {
        idBlogSelected = Long.parseLong(strId);
      }
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
      stmt = conn.createStatement();
      Long idAuthor;
      rs = stmt.executeQuery(select_all_blogs);
      if (rs != null) {
        blogs.clear();
        while (rs.next()) {
          idAuthor = rs.getLong("id_author");
          blogs.add(new Blog(
              rs.getLong("id"),
              rs.getString("title"),
              rs.getString("content"),
              idAuthor,
              FindById(idAuthor, authors),
              rs.getTimestamp("created_at").toLocalDateTime()));
        }
        rs.close();
        request.setAttribute("blogs", blogs);
      } else {
        System.out.println("Ошибка загрузки blog");
      }
      try (PreparedStatement preparedStatement = conn.prepareStatement(select_blog_ById)) {
        preparedStatement.setLong(1, idBlogSelected);
        rs = preparedStatement.executeQuery();
        if (rs != null) {
          deleteBlogs.clear();
          while (rs.next()) {
            deleteBlogs.add(new Blog(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getLong("id_author"),
                FindById(rs.getLong("id_author"), authors),
                rs.getTimestamp("created_at").toLocalDateTime()));
          }
          rs.close();
          request.setAttribute("blogsDelete", deleteBlogs);
        } else {
          System.out.println("Ошибка загрузки author");
        }
      } catch (Exception e) {

        System.out.println(e);
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    RequestDispatcher view = getServletContext().getRequestDispatcher("/views/delete-blog.jsp");
    view.forward(request, response);
  }

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)

      throws ServletException, IOException {
    EmpConnBuilder builder = new EmpConnBuilder();
    try (Connection conn = builder.getConnection()) {
      String strId = request.getParameter("id");
      Long id = null;
      if (strId != null) {
        id = Long.parseLong(strId);
      }
      try (PreparedStatement preparedStatement = conn.prepareStatement(delete_blog)) {
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
