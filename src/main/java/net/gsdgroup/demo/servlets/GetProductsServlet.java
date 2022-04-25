package net.gsdgroup.demo.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.gsdgroup.demo.SearchStatementBuilder;
import net.gsdgroup.demo.dao.ProductDao;
import net.gsdgroup.logging.Logger;

@WebServlet(name = "GetProducts", urlPatterns = {"/GetProducts"})
public class GetProductsServlet extends HttpServlet {

  /**
   * Servlet that gets products from database
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
   * Gets values of input fields and builds the query based on them.
   *
   * @param request, not null
   * @return query String
   */
  private String getOptions(HttpServletRequest request) {
    SearchStatementBuilder search;
    HttpSession session = request.getSession();
    String name = request.getParameter("Name");
    String lowerValue = request.getParameter("LowerValue");
    String higherValue = request.getParameter("HigherValue");
    String lowerExpiry = request.getParameter("LowerExpiry");
    String higherExpiry = request.getParameter("HigherExpiry");
    String category = request.getParameter("Category");
    String lowerDiscount = request.getParameter("LowerDiscount");
    String higherDiscount = request.getParameter("HigherDiscount");
    String sort = request.getParameter("Sort");
    Object userOnly = request.getParameter("UserOnly");

    if (userOnly != null) {
      search = new SearchStatementBuilder((String) session.getAttribute("email"));
    } else {
      search = new SearchStatementBuilder();
    }
    session.setAttribute("userOnly", userOnly);
    if (name != null && !name.equals("")) {
      search.addName(name);

    }
    session.setAttribute("name", name);
    if (lowerValue != null && !lowerValue.equals("")) {
      search.addLowerValue(lowerValue);

    }
    session.setAttribute("lowerValue", lowerValue);
    if (higherValue != null && !higherValue.equals("")) {
      search.addHigherValue(higherValue);

    }
    session.setAttribute("higherValue", higherValue);
    if (lowerExpiry != null && !lowerExpiry.equals("")) {
      search.addLowerExpiry(lowerExpiry);

    }
    session.setAttribute("lowerExpiry", lowerExpiry);
    if (higherExpiry != null && !higherExpiry.equals("")) {
      search.addHigherExpiry(higherExpiry);

    }
    session.setAttribute("higherExpiry", higherExpiry);
    if (category != null && !category.equals("")) {
      search.addCategory(category);
    }
    session.setAttribute("category", category);
    if (lowerDiscount != null && !lowerDiscount.equals("")) {
      search.addLowerDiscount(lowerDiscount);

    }
    session.setAttribute("lowerDiscount", lowerDiscount);
    if (higherDiscount != null && !higherDiscount.equals("")) {
      search.addHigherDiscount(higherDiscount);

    }
    session.setAttribute("higherDiscount", higherDiscount);
    if (sort != null && !sort.equals("")) {
		if (sort.contains("Asc")) {
			search.orderAscending(sort.substring(0, sort.length() - 3));
		} else {
			search.orderDescending(sort.substring(0, sort.length() - 4));
		}
    }
    session.setAttribute("sort", sort);
    return search.toString();

  }

  /**
   * POST operation to set the product list session attribute.
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    String options = getOptions(request);
    try {

      session.setAttribute("products", productDao.getProducts(options));
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Main.jsp");
      dispatcher.forward(request, response);
    } catch (ClassNotFoundException e) {
      Logger.warn(e.getMessage());
      e.printStackTrace();
    }

  }
}
