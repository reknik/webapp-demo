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

@WebServlet(name = "AddProduct", urlPatterns = {"/AddProduct"})
public class AddProductServlet extends HttpServlet {

  /**
   * Servlet that adds product to the database.
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
   * Gets product based on input fields.
   *
   * @param request, not null
   * @return ProductBean
   */
  private ProductBean getProductBean(HttpServletRequest request) {
    ProductBean product = new ProductBean();
    String value = request.getParameter("ValueAdd");
    String expiry = request.getParameter("ExpiryAdd");
    String category = request.getParameter("CategoryAdd");
    String discount = request.getParameter("DiscountAdd");
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
   * POST operation to add product to database.
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    String name = request.getParameter("NameAdd");

    // Verifies if required name is present
    // Else goes back to original page with no-name warning
    if (name == null || name.equals("")) {
      session.setAttribute("noName", true);
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AddProduct.jsp");
      dispatcher.include(request, response);
      return;
    }
    ProductBean product = getProductBean(request);
    product.setName(name);
    try {
      if (productDao.add(product, (String) session.getAttribute("email"))) {
        session.setAttribute("productSuccess", true);
        session.setAttribute("productId", product.getId());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/GetProducts");
        dispatcher.forward(request, response);
        return;
      } else {
        session.setAttribute("productFailure", true);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AddProduct.jsp");
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
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AddProduct.jsp");
    dispatcher.forward(request, response);
  }
}
