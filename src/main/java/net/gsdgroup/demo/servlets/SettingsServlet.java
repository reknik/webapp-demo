package net.gsdgroup.demo.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Settings", urlPatterns = {"/Settings"})
public class SettingsServlet extends HttpServlet {
  /**
   *
   */
  private static final long serialVersionUID = 224838538409337632L;

  /**
   * Forwards GET request to the associated jsp page.
   */
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getSession(false).setAttribute("lang", req.getParameter("lang") != null ? req.getParameter("lang") : req.getSession().getAttribute("lang"));
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Settings.jsp");
    dispatcher.include(req, resp);
  }
}
