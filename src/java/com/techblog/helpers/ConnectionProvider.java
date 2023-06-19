/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techblog.helpers;

import java.sql.*;

public class ConnectionProvider {

    private static Connection con;

    public static Connection getConnection() {

        try {
            
           if(con == null){
           
            // load driver
          
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // create connection 
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/techblog","root","root123");
            
           
           
           }
            

        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return con;
    }

}
