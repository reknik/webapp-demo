package net.gsdgroup.demo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Logout", urlPatterns = {"/Logout"})
public class LogoutServlet extends HttpServlet {

  /**
   * Servlet that logs out user
   */
  private static final long serialVersionUID = 1L;

  /**
   * GET operation that sets 'email' attribute to null and forwards to login page
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    session.setAttribute("email", null);
    getServletContext().getRequestDispatcher("/Login.jsp").include(request, response);
  }
}
