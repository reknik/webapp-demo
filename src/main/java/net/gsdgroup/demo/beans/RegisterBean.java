package net.gsdgroup.demo.beans;

public class RegisterBean {
  private String email;
  private String password;
  private String passwordC;

  /**
   * @return email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets email
   *
   * @param email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets password
   *
   * @param password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return password confirmation
   */
  public String getPasswordC() {
    return passwordC;
  }

  /**
   * Sets password confirmation
   *
   * @param passwordC
   */
  public void setPasswordC(String passwordC) {
    this.passwordC = passwordC;
  }
}
