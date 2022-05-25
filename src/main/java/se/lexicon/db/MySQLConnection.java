package se.lexicon.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

  private static final String URL = "jdbc:mysql://localhost:3306/world";
  private static final String USER = "root";
  private static final String PASS = "root";

  public static Connection getConnection() {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(URL, USER, PASS);
    } catch (SQLException e) {
      System.out.println("Connection Failed!");
      System.out.println("SQLException: " + e.getMessage());
    }

    return connection;
  }


}
