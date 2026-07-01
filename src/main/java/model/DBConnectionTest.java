/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.DBConnection;
import java.sql.Connection;

public class DBConnectionTest {
    public static void main(String[] args) {
        System.out.println("Starting DBConnection test...");

        try {
            System.out.println("Step 1: Loading driver class...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully.");

            System.out.println("Step 2: Preparing connection parameters...");
            String dbURL = "jdbc:mysql://localhost:3307/sgas";
            String dbUsername = "root";
            String dbPassword = "admin";
            System.out.println("URL: " + dbURL);
            System.out.println("Username: " + dbUsername);
            // Don’t print password in real logs, but for testing you can confirm it’s set
            System.out.println("Password set (hidden).");

            System.out.println("Step 3: Attempting connection...");
            Connection conn = DBConnection.initializeDatabase();
            System.out.println("Connection established successfully: " + conn);

            System.out.println("Step 4: Closing connection...");
            conn.close();
            System.out.println("Connection closed successfully.");

        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: Driver class not found.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERROR: Could not connect to database.");
            e.printStackTrace();
        }

        System.out.println("DBConnection test finished.");
    }
}
