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

@WebServlet(name = "UpdateProduct", urlPatterns = {"/UpdateProduct"})
public class UpdateProductServlet extends HttpServlet {

  /**
   * Servlet that updates product in database.
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
   * Gets values of input fields and modifies product based on them.
   *
   * @param request, not null
   * @param product, not null
   * @return modified product
   */
  private ProductBean getProductBean(HttpServletRequest request, ProductBean product) {
    String name = request.getParameter("Name");
    String value = request.getParameter("Value");
    String expiry = request.getParameter("Expiry");
    String category = request.getParameter("Category");
    String discount = request.getParameter("Discount");

	  if (name != null && !name.equals("")) {
		  product.setName(name);
	  }
	  if (value != null && !value.equals("")) {
		  product.setValue(Double.valueOf(value));
	  }
	  if (expiry != null && !expiry.equals("")) {
		  product.setExpiry(expiry);
	  }
	  if (discount != null && !discount.equals("")) {
		  product.setDiscount(Integer.valueOf(discount));
	  }
	  if (category != null && !category.equals("")) {
		  product.setCategory(category);
	  }
    return product;
  }

  /**
   * POST operation that updates product.
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession session = request.getSession();
    if (request.getParameter("productUpdated") != null) {
      session.setAttribute("productId", request.getParameter("productId"));
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UpdateProduct.jsp");
      dispatcher.include(request, response);
      return;
    }
    if (session.getAttribute("productId") == null) {
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/GetProducts");
      dispatcher.forward(request, response);
    }
    String id = (String) session.getAttribute("productId");
    ProductBean product = getProductBean(request, productDao.find(id));
    try {
      if (productDao.update(product)) {
        session.setAttribute("productSuccess", true);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/GetProducts");
        dispatcher.forward(request, response);
        return;
      } else {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UpdateProduct");
        session.setAttribute("productFailure", true);
        dispatcher.include(request, response);
        ;
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
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UpdateProduct.jsp");
    dispatcher.include(request, response);
  }
}
