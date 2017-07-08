package com.company.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*
https://www.tutorialspoint.com/hsqldb/hsqldb_update_query.htm
 */
public class App {
  public static void main(String[] args) throws Exception {

    // Registering the HSQLDB JDBC driver
    Class.forName("org.hsqldb.jdbc.JDBCDriver");

    //Creating the connection with HSQLDB
    Connection connection = DriverManager.getConnection(
        "jdbc:hsqldb:hsql://localhost/testdb",
        "SA",
        "");
    Statement statement = connection.createStatement();

    String query = "UPDATE tutorials_tbl SET title = 'C and Data Structures' WHERE id = 101";

    int result = statement.executeUpdate(query);
    connection.commit();

    System.out.println("result=" + result);
    System.out.println(result + " Rows effected");

  }
}
/*
output:
result=1
1 Rows effected
 */
