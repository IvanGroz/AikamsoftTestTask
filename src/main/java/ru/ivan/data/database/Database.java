package ru.ivan.data.database;


import java.sql.*;
import java.util.Properties;

public class Database {
  private final String url; // = "jdbc:postgresql://localhost:5432/purchases";
  private final Properties props;
  private Connection connection;

  public Database(String username, String password, Boolean ssl, String url) {
    this.url = url;
    props = new Properties();
    props.setProperty("user", username);
    props.setProperty("password", password);
    props.setProperty("ssl", ssl.toString());
  }

  public void connect() throws SQLException {

    this.connection = DriverManager.getConnection(url, props);

  }

  public ResultSet execute(String sql) throws SQLException {
    Statement statement = connection.createStatement();
    return statement.executeQuery(sql);

  }

  public void disconnect() throws SQLException {
    this.connection.close();
  }
}
