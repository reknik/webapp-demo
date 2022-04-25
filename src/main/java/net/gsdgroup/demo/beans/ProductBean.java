package net.gsdgroup.demo.beans;

public class ProductBean {
  private int id;
  private String name;
  private double value;
  private String expiry;
  private String category;
  private int discount;
  private String Owner;

  /**
   * @return owner of product
   */
  public String getOwner() {
    return Owner;
  }

  /**
   * Sets owner of product
   *
   * @param owner
   */
  public void setOwner(String owner) {
    Owner = owner;
  }

  /**
   * @return id of product
   */
  public int getId() {
    return id;
  }

  /**
   * Sets id of product
   *
   * @param owner
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return name of product
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name of product
   *
   * @param owner
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return value of product
   */
  public double getValue() {
    return value;
  }

  /**
   * Sets value of product
   *
   * @param owner
   */
  public void setValue(double value) {
    this.value = value;
  }

  /**
   * @return expiry date of product
   */
  public String getExpiry() {
    return expiry;
  }

  /**
   * Sets expiry date of product
   *
   * @param owner
   */
  public void setExpiry(String expiry) {
    this.expiry = expiry;
  }

  /**
   * @return category of product
   */
  public String getCategory() {
    return category;
  }

  /**
   * Sets category of product
   *
   * @param owner
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * @return discount% of product
   */
  public int getDiscount() {
    return discount;
  }

  /**
   * Sets owner discount% product
   *
   * @param owner
   */
  public void setDiscount(int discount) {
    this.discount = discount;
  }

}
