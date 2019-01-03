package top.gendseo.books.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.gendseo.books.dao.BooksDao;

/**
 * 后端 API 接口为 /BooksApi/*
 * 主要定义四种方法，CRUD
 * GET -> 查询
 * POST -> 增，删，改
 */
@WebServlet("/BooksApi/*")
public class BooksApi extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public BooksApi() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
    try {
      String booksjson = BooksDao.Query();
      PrintWriter out = response.getWriter();
      out.write(booksjson);
      out.flush();
      out.close();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/plain");
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");

    if (request.getRequestURI().equals("/JavaWebBooks/BooksApi/DELETE")) {
      System.out.println("delete");
      String Bid = request.getParameter("Bid");
      String result;
      try {
        result = BooksDao.DELETE(Bid);
        PrintWriter out = response.getWriter();
        out.write(result);
        out.flush();
        out.close();
      } catch (ClassNotFoundException | SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }

    if (request.getRequestURI().equals("/JavaWebBooks/BooksApi/UPDATE")) {
      System.out.println("update");

      BufferedReader reader = request.getReader();
      String json = reader.readLine();
      System.out.println(json);
      reader.close();
      String result;
      try {
        result = BooksDao.UPDATE(json);
        PrintWriter out = response.getWriter();
        out.write(result);
        out.flush();
        out.close();
      } catch (ClassNotFoundException | SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    if (request.getRequestURI().equals("/JavaWebBooks/BooksApi/INSERT")) {
      System.out.println("insert");

      BufferedReader reader = request.getReader();
      String json = reader.readLine();
      System.out.println(json);
      reader.close();
      String result;
      try {
        result = BooksDao.INSERT(json);
        PrintWriter out = response.getWriter();
        out.write(result);
        out.flush();
        out.close();
      } catch (ClassNotFoundException | SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}
