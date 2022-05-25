package se.lexicon;

import se.lexicon.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDemo {

  public static void main(String[] args) {
    List<City> cities = new ArrayList<>();
    try {
      // jdbc:mysql://[host][:port]/[database] [?propertyName1][=propertyValue1][&propertyName2][=propertyValue2]...
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "root");
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("select id, name from city");
      while (resultSet.next()) {
        //System.out.println("ID: " + resultSet.getInt("id") + "  " + "Name: " + resultSet.getString("name"));
        cities.add(new City(resultSet.getInt("id"), resultSet.getString("name")));
      }

      System.out.println(cities.size());
      System.out.println("------------");
      System.out.println(cities);
    } catch (SQLException e) {
      System.out.println("Connection failed!" + e.getMessage());
    }

  }


}
