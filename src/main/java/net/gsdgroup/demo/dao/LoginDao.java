package net.gsdgroup.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.gsdgroup.demo.beans.LoginBean;
import net.gsdgroup.logging.Logger;

public class LoginDao {

  /**
   * Verifies if given LoginBean exists in databases.
   *
   * @param loginBean
   * @return if query succeeded
   * @throws ClassNotFoundException
   */
  public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
    boolean status = false;
    Class.forName("com.mysql.cj.jdbc.Driver");
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp_demo", "root",
        "Stefanel12");
         PreparedStatement preparedStatement = connection
             .prepareStatement("select * from users where email = ? and password = ? ")) {
      preparedStatement.setString(1, loginBean.getEmail());
      preparedStatement.setString(2, loginBean.getPassword());
      Logger.info(preparedStatement.toString());
      ResultSet rs = preparedStatement.executeQuery();
      status = rs.next();
    } catch (SQLException e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
    }
    return status;
  }
}
