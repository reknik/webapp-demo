package net.gsdgroup.demo.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Main", urlPatterns = {"/Main"})
public class MainServlet extends HttpServlet {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  /**
   * Forwards GET request to the associated jsp page.
   */
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Main.jsp");
    dispatcher.include(req, resp);
  }

}
