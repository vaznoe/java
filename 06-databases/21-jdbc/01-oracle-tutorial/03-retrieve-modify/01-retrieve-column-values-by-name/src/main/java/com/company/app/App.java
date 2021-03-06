package com.company.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App
{
    public static void main( String[] args ) throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oraclejbdc", "root", "abc");
        Statement statement = connection.createStatement();

        String query = "SELECT COF_NAME, SUP_ID, PRICE, SALES, TOTAL FROM COFFEES";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            String coffeeName = resultSet.getString("COF_NAME");
            int supplierID = resultSet.getInt("SUP_ID");
            float price = resultSet.getFloat("PRICE");
            int sales = resultSet.getInt("SALES");
            int total = resultSet.getInt("TOTAL");
            System.out.println(coffeeName + "\t" + supplierID +
                    "\t" + price + "\t" + sales +
                    "\t" + total);
        }

        statement.close();
        connection.close();
    }
}
/*
output:
Colombian	101	7.99	0	0
Colombian_Decaf	101	8.99	0	0
Espresso	150	9.99	0	0
French_Roast	49	8.99	0	0
French_Roast_Decaf	49	9.99	0	0
 */
