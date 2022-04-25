package net.gsdgroup.demo.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.gsdgroup.demo.beans.LoginBean;
import net.gsdgroup.demo.dao.LoginDao;
import net.gsdgroup.logging.Logger;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

  /**
   * Servlet that logs in user if validated by DAO
   */
  private static final long serialVersionUID = 1L;
  private LoginDao loginDao;

  /**
   * Initializes required LoginDao.
   */
  public void init() {
    loginDao = new LoginDao();
  }

  /**
   * POST operation that forwards if login validates, else goes back with warning.
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    LoginBean loginBean = new LoginBean();
    loginBean.setEmail(email);
    loginBean.setPassword(password);

    try {
      if (loginDao.validate(loginBean)) {
        session.setAttribute("email", email);
        session.setAttribute("loginFailed", false);
        RequestDispatcher dispatcher = getServletContext()
            .getRequestDispatcher("/GetProducts");
        dispatcher.forward(request, response);
      } else {
        session.setAttribute("loginFailed", true);
        RequestDispatcher dispatcher = getServletContext()
            .getRequestDispatcher("/Login.jsp");
        dispatcher.include(request, response);
      }
    } catch (ClassNotFoundException e) {
      Logger.warn(e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Forwards GET request to the associated jsp page.
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    getServletContext().getRequestDispatcher("/Login.jsp").include(request, response);
  }
}
