package net.gsdgroup.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.gsdgroup.demo.beans.RegisterBean;
import net.gsdgroup.logging.Logger;

public class RegisterDao {
  /**
   * Registers user in database if email not used.
   *
   * @param registerBean
   * @return if query succeeded
   * @throws ClassNotFoundException
   */
  public boolean validate(RegisterBean registerBean) throws ClassNotFoundException {
    boolean status = false;
    Class.forName("com.mysql.cj.jdbc.Driver");
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp_demo", "root",
        "Stefanel12");
         PreparedStatement preparedStatement = connection
             .prepareStatement("select * from users where email = ?");
         PreparedStatement registerStatement = connection
             .prepareStatement("insert into users(email,password) values(?,?)");) {
      preparedStatement.setString(1, registerBean.getEmail());
      Logger.info(preparedStatement.toString());
      ResultSet rs = preparedStatement.executeQuery();
      status = rs.next();
      if (!status) {
        registerStatement.setString(1, registerBean.getEmail());
        registerStatement.setString(2, registerBean.getPassword());
        Logger.info(registerStatement.toString());
        registerStatement.executeUpdate();
        status = true;
      } else {
        return false;
      }
    } catch (SQLException e) {
      Logger.warn(e.getMessage());
      e.printStackTrace();
    }
    return status;
  }
}
