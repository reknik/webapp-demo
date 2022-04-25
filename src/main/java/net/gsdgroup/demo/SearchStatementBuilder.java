package net.gsdgroup.demo;

public class SearchStatementBuilder {
  /**
   * StringBuilder that selects * from products table (and user associated with
   * them) by default.
   */
  private StringBuilder builder = new StringBuilder(
      "SELECT P.product_ID,P.product_name,P.product_value,P.product_expiry,P.product_category,P.product_discount,U.email FROM"
          + " products P INNER JOIN products_connector PC ON PC.product_ID=P.product_ID INNER JOIN"
          + " users U ON PC.user_ID=U.user_ID WHERE ");

  /**
   * Class constructor
   */
  public SearchStatementBuilder() {

  }

  /**
   * Class constructor that selects products with specific user.
   *
   * @param email, not null
   */
  public SearchStatementBuilder(String email) {
    builder.append("U.email='" + email + "' AND ");
  }

  /**
   * Appends minimum value criteria to the current query String.
   *
   * @param value, not null
   */
  public SearchStatementBuilder addLowerValue(String value) {
    builder.append("P.product_value > " + value + " AND ");
    return this;
  }

  /**
   * Appends maximum value criteria to the current query String.
   *
   * @param value, not null
   */
  public SearchStatementBuilder addHigherValue(String value) {
    builder.append("P.product_value < " + value + " AND ");
    return this;
  }

  /**
   * Appends minimum discount criteria to the current query String.
   *
   * @param discount, not null
   */
  public SearchStatementBuilder addLowerDiscount(String discount) {
    builder.append("P.product_discount > " + discount + " AND ");
    return this;
  }

  /**
   * Appends maximum discount criteria to the current query String.
   *
   * @param discount, not null
   */
  public SearchStatementBuilder addHigherDiscount(String discount) {
    builder.append("P.product_discount < " + discount + " AND ");
    return this;
  }

  /**
   * Appends minimum expiry criteria to the current query String.
   *
   * @param expiry, not null
   */
  public SearchStatementBuilder addLowerExpiry(String expiry) {
    builder.append("P.product_expiry > '" + expiry + "' AND ");
    return this;
  }

  /**
   * Appends maximum expiry criteria to the current query String.
   *
   * @param expiry, not null
   */
  public SearchStatementBuilder addHigherExpiry(String expiry) {
    builder.append("P.product_expiry < '" + expiry + "' AND ");
    return this;
  }

  /**
   * Appends name (like) criteria to the current query String.
   *
   * @param name, not null
   */
  public SearchStatementBuilder addName(String name) {
    builder.append("P.product_name LIKE '%" + name + "%' AND ");
    return this;
  }

  /**
   * Appends category criteria to the current query String.
   *
   * @param category, not null
   */
  public SearchStatementBuilder addCategory(String category) {
    builder.append("P.product_category = '" + category + "' AND ");
    return this;
  }

  /**
   * Appends ID criteria to the current query String.
   *
   * @param option, not null
   */

  public SearchStatementBuilder addId(String id) {
    builder.append("P.product_ID = '" + id + "' AND ");
    return this;
  }

  /**
   * Appends order by ascending to the current query String based on option.
   *
   * @param option, not null
   */
  public void orderAscending(String option) {
	  if (builder.substring(builder.length() - 4, builder.length()).equals("AND ")) {
		  builder.delete(builder.length() - 4, builder.length());
	  } else {
		  builder.delete(builder.length() - 6, builder.length());
	  }
    builder.append("ORDER BY " + option + " ASC ;");
  }

  /**
   * Appends order by descending to the current query String based on option.
   *
   * @param option, not null
   */
  public void orderDescending(String option) {
	  if (builder.substring(builder.length() - 4, builder.length()).equals("AND ")) {
		  builder.delete(builder.length() - 4, builder.length());
	  } else {
		  builder.delete(builder.length() - 6, builder.length());
	  }
    builder.append("ORDER BY " + option + " DESC ;");
  }

  /**
   * Returns query String. Based on ending deletes last query word.
   */
  public String toString() {
	  if (builder.substring(builder.length() - 4, builder.length()).equals("AND ")) {
		  return builder.delete(builder.length() - 4, builder.length()).append(";").toString();
	  } else if (builder.substring(builder.length() - 6, builder.length()).equals("WHERE ")) {
		  return builder.delete(builder.length() - 6, builder.length()).append(";").toString();
	  } else {
		  return builder.toString();
	  }
  }
}
