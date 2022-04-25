package net.gsdgroup.demo.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.gsdgroup.demo.beans.RegisterBean;
import net.gsdgroup.demo.dao.RegisterDao;
import net.gsdgroup.logging.Logger;

@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class RegisterServlet extends HttpServlet {

  /**
   * Servlet that registers users.
   */
  private static final long serialVersionUID = 1L;
  private RegisterDao registerDao;

  /**
   * Initializes required RegisterDao.
   */
  public void init() {
    registerDao = new RegisterDao();
  }

  /**
   * POST operation that registers users if input is valid.
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Register.jsp");

    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String passwordC = request.getParameter("passwordC");

    RegisterBean registerBean = new RegisterBean();
    registerBean.setEmail(email);
    registerBean.setPassword(password);
    registerBean.setPasswordC(passwordC);

    //Verifies if email field is empty, returns back to original page with warning
    if (email == null || email.length() < 5) {
      session.setAttribute("badEmail", true);
      dispatcher.include(request, response);
      return;
    }

    //Verifies if email field is empty or same as email, returns back to original page with warning
    if (password == null || password.length() < 5 || email.equals(password)) {
      session.setAttribute("badPassword", true);
      dispatcher.include(request, response);
      return;
    }

    //Verifies if passwords don't match, returns back to original page with warning
    if (!password.equals(passwordC)) {
      session.setAttribute("samePassword", true);
      dispatcher.include(request, response);
      return;
    }

    try {

      if (registerDao.validate(registerBean)) {
        session.setAttribute("registerSuccess", true);
        dispatcher.include(request, response);
        return;
      } else {
        session.setAttribute("registerFailure", true);
        dispatcher.include(request, response);
        return;
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
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Register.jsp");
    dispatcher.include(request, response);
  }
}
