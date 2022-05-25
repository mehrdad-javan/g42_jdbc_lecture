package se.lexicon;

import se.lexicon.db.MySQLConnection;
import se.lexicon.model.City;

import java.sql.*;

public class JdbcCrudDemo {

  public static void main(String[] args) {
    //findAllCities();
    //findCityById();
    //findCityByName();
    //findCityByNameAndCountryCode("Imperatriz", "BRA");
    deleteCityById(299);
  }


  public static void findAllCities() {
    String selectAllCitiesQuery = "select id, name, population from city";
    Connection connection = null;
    Statement statement = null;
    try {
      connection = MySQLConnection.getConnection();
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(selectAllCitiesQuery);
      while (resultSet.next()){
        System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name") + " " + resultSet.getInt("population"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (statement != null) statement.close();
        if (connection != null) connection.close();
      }catch (SQLException e){
        e.printStackTrace();
      }
    }
  }

  public static void findCityById(){
    // step 1: define query
    // step 2: define connection
    // step 3: create Statement or PreparedStatement
    // step 4: execute
    // step 5 close resources and done
    int id = 299;
    String selectQuery = "select * from city where id = " + id;

    try(
            Connection connection= MySQLConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            ){
      while (resultSet.next()){
        System.out.println(resultSet.getInt("id"));
        System.out.println(resultSet.getString("name"));
        System.out.println(resultSet.getString("CountryCode"));
        System.out.println(resultSet.getString("District"));
        System.out.println(resultSet.getInt("Population"));
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
  }

  public static void findCityByName(){
    String name = "Ambon";
    String query = "select * from city where name like ?";
    try {
      Connection connection = MySQLConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, name);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        System.out.println(resultSet.getString("name"));
        System.out.println(resultSet.getString("countryCode"));
      }
    }catch (SQLException exceptionReferenceName){
      exceptionReferenceName.printStackTrace();
    }
    // todo: do not forget to close the resources

  }

  public static void findCityByNameAndCountryCode(String cityName, String countryCode){
    String query = "select * from city where name = ? and countryCode = ?";
    try {
      Connection connection = MySQLConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, cityName);
      preparedStatement.setString(2, countryCode);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        System.out.println(resultSet.getInt("id"));
        System.out.println(resultSet.getString("name"));
        System.out.println(resultSet.getString("countryCode"));
        System.out.println(resultSet.getString("District"));
        System.out.println(resultSet.getInt("Population"));
      }
    }catch (SQLException exceptionReferenceName){
      exceptionReferenceName.printStackTrace();
    }
    // todo: do not forget to close the resources

  }

  public static void deleteCityById(int id){
    Connection connection = MySQLConnection.getConnection();
    try {
      PreparedStatement preparedStatement = connection.prepareStatement("delete from city where id = ?");
      preparedStatement.setInt(1, id);
      int result = preparedStatement.executeUpdate();
      System.out.println("result = " + result);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // update city by id and change the city name


}
