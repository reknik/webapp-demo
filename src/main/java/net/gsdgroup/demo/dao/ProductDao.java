package net.gsdgroup.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import net.gsdgroup.demo.SearchStatementBuilder;
import net.gsdgroup.demo.beans.ProductBean;
import net.gsdgroup.logging.Logger;

public class ProductDao {
  /**
   * Gets products from database based on options query string.
   *
   * @param options, not null
   * @return list of ProductBean
   * @throws ClassNotFoundException
   */
  public List<ProductBean> getProducts(String options) throws ClassNotFoundException {
    List<ProductBean> products = new ArrayList<>();
    Class.forName("com.mysql.cj.jdbc.Driver");
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp_demo", "root",
        "Stefanel12"); PreparedStatement preparedStatement = connection.prepareStatement(options)) {
      Logger.info(preparedStatement.toString());
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        ProductBean product = new ProductBean();
        product.setId(rs.getInt("product_ID"));
        product.setName(rs.getString("product_name"));
        product.setValue(rs.getDouble("product_value"));
        product.setExpiry(
            (rs.getDate("product_expiry") != null) ? rs.getDate("product_expiry").toString() : null);
        product.setCategory(rs.getString("product_category"));
        product.setDiscount(rs.getInt("product_discount"));
        product.setOwner(rs.getString("email"));
        products.add(product);
      }
      return products;
    } catch (SQLException e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
    }
    return null;

  }

  /**
   * Adds product to the database. First adds it in products table, then in
   * products_connector based on generated product_ID.
   *
   * @param product, not null
   * @param email,   not null
   * @return if query succeeded
   * @throws ClassNotFoundException
   */
  public boolean add(ProductBean product, String email) throws ClassNotFoundException {
    boolean status = false;
    Class.forName("com.mysql.cj.jdbc.Driver");
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp_demo", "root",
        "Stefanel12");
         PreparedStatement addStatement = connection.prepareStatement(
             "insert into products(product_name,product_value,product_expiry,product_category,product_discount)"
                 + " values(?,?,?,?,?)",
             Statement.RETURN_GENERATED_KEYS);) {

      addStatement.setString(1, product.getName());
      addStatement.setDouble(2, product.getValue());
      addStatement.setString(3, product.getExpiry());
      addStatement.setString(4, product.getCategory());
      addStatement.setInt(5, product.getDiscount());
      Logger.info(addStatement.toString());
      addStatement.executeUpdate();
      status = true;
      try (ResultSet generated_id = addStatement.getGeneratedKeys()) {
        if (generated_id.next()) {
          int product_id = generated_id.getInt(1);
          PreparedStatement getUserIdStatement = connection
              .prepareStatement("SELECT user_ID FROM users WHERE email=? ");
          getUserIdStatement.setString(1, email);
          Logger.info(getUserIdStatement.toString());
          ResultSet users = getUserIdStatement.executeQuery();
          if (users.next()) {
            int user_id = users.getInt(1);
            getUserIdStatement.close();
            PreparedStatement connectorStatement = connection
                .prepareStatement("INSERT INTO products_connector(product_ID,user_ID) VALUES(?,?)");
            connectorStatement.setInt(1, product_id);
            connectorStatement.setInt(2, user_id);
            connectorStatement.executeUpdate();
            connectorStatement.close();
          } else {
            Logger.warn("Couldnt add to products_connector table");
          }
          users.close();
          getUserIdStatement.close();
        }
      }
    } catch (SQLException e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
    }
    return status;
  }

  /**
   * Finds specific product in database based on product_ID. Calls
   * {@link #getProducts(String) getProducts} with constructed query string.
   *
   * @param ID, not null
   * @return Product
   */
  public ProductBean find(String ID) {
    try {
      List<ProductBean> products = this.getProducts(new SearchStatementBuilder().addId(ID).toString());
		if (products != null && products.size() > 0) {
			return products.get(0);
		} else {
			return null;
		}
    } catch (ClassNotFoundException e) {

      Logger.warn(e.getMessage());
      e.printStackTrace();

    }
    return null;

  }

  /**
   * Verifies if product is owned by user.
   *
   * @param ID,    not null
   * @param email, not null
   * @return if product is owned by user
   */
  public boolean isOwner(String ID, String email) {
    try {
      List<ProductBean> products = this.getProducts(new SearchStatementBuilder(email).addId(ID).toString());
		if (products != null && products.size() > 0) {
			return true;
		} else {
			return false;
		}
    } catch (ClassNotFoundException e) {

      Logger.warn(e.getMessage());
      e.printStackTrace();

    }
    return false;
  }

  /**
   * Updates product in database.
   *
   * @param product, not null
   * @return if query succeeded
   * @throws ClassNotFoundException
   */
  public boolean update(ProductBean product) throws ClassNotFoundException {
    boolean status = false;
    Class.forName("com.mysql.cj.jdbc.Driver");
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp_demo", "root",
        "Stefanel12");
         PreparedStatement updateStatement = connection.prepareStatement(
             "UPDATE products SET product_name=?,product_value=?,product_expiry=?,product_category=?,product_discount=? WHERE product_ID ="
                 + product.getId());) {

      updateStatement.setString(1, product.getName());
      updateStatement.setDouble(2, product.getValue());
      updateStatement.setString(3, product.getExpiry());
      updateStatement.setString(4, product.getCategory());
      updateStatement.setInt(5, product.getDiscount());
      Logger.info(updateStatement.toString());
      updateStatement.executeUpdate();
      status = true;
    } catch (SQLException e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
    }
    return status;
  }

  /**
   * Deletes product in database.
   *
   * @param product, not null
   * @return is query succeeded
   * @throws ClassNotFoundException
   */
  public boolean delete(ProductBean product) throws ClassNotFoundException {
    boolean status = false;
    Class.forName("com.mysql.cj.jdbc.Driver");
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp_demo", "root",
        "Stefanel12");
         PreparedStatement deleteStatement = connection
             .prepareStatement("DELETE FROM products WHERE product_ID =" + product.getId());) {
      Logger.info(deleteStatement.toString());
      deleteStatement.executeUpdate();
      status = true;
    } catch (SQLException e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
    }
    return status;
  }
}
