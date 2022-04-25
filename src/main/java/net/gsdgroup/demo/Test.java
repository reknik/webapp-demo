package net.gsdgroup.demo;

public class Test {
  public static void main(String... args) {
    StringBuilder builder = new StringBuilder("Select * from users where Name=ceva AND ");
    System.out.print(builder.substring(builder.length() - 4, builder.length()));
  }
}
