package net.gsdgroup.demo.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.gsdgroup.demo.beans.ProductBean;
import net.gsdgroup.demo.dao.ProductDao;
import net.gsdgroup.logging.Logger;

@WebServlet(name = "DeleteProduct", urlPatterns = {"/DeleteProduct"})
public class DeleteProductServlet extends HttpServlet {

  /**
   * Servlet that deletes product from the database.
   */

  private static final long serialVersionUID = 1L;
  private ProductDao productDao;

  /**
   * Initializes required ProductDao.
   */
  public void init() {
    productDao = new ProductDao();
  }

  /**
   * POST operation to delete product from database.
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String id;
    HttpSession session = request.getSession(false);
    id = request.getParameter("productId");
    ProductBean product = new ProductBean();
    product.setId(Integer.parseInt(id));
    try {
      if (productDao.delete(product)) {
        session.setAttribute("productSuccess", true);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/GetProducts");
        dispatcher.forward(request, response);
        return;
      } else {
        session.setAttribute("productFailure", true);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Main");
        dispatcher.forward(request, response);
        return;
      }
    } catch (ClassNotFoundException e) {
      Logger.warn(e.getMessage());
      e.printStackTrace();
    }
  }
}
