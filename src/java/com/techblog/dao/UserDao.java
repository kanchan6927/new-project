/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techblog.dao;

import com.techblog.entities.RegUser;
import java.sql.*;

public class UserDao {

    private Connection con;

    public UserDao(Connection con) {

        this.con = con;
    }

    // method to insert user to database
    public boolean saveUser(RegUser reguser) {

        boolean f = false;

        try {
            // user ---> database

            String query = "insert into registration_form(userName ,email,password,gender,about) values (?,?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, reguser.getName());
            pstmt.setString(2, reguser.getEmail());
            pstmt.setString(3, reguser.getPassword());
            pstmt.setString(4, reguser.getGender());
            pstmt.setString(5, reguser.getAbout());

            pstmt.executeUpdate();
            f = true;

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return f;
    }

    // get user by email and password
    public RegUser getUserByEmailAndPassword(String email, String password) {

        RegUser reguser = null;

        try {
            String query = "select * from registration_form where email=? and password = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet set = pstmt.executeQuery();
            if (set.next()) {

                reguser = new RegUser();
                // data from db 

                String name = set.getString("userName");

                // set to user object 
                reguser.setName(name);
                reguser.setId(set.getInt("id"));
                reguser.setPassword(set.getString("password"));
                reguser.setEmail(set.getString("email"));
                reguser.setGender(set.getString("gender"));
                reguser.setAbout(set.getString("about"));
                reguser.setDateTime(set.getTimestamp("rdate"));
                reguser.setProfile(set.getString("profile"));

            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return reguser;
    }

    // update user from profile
    public boolean updateUser(RegUser reguser) {

        boolean f = false;
        try {

            String Query = "update registration_form set userName = ? ,email = ?,password=?, about =? ,profile = ? where id = ?";

            PreparedStatement pstmt = con.prepareStatement(Query);

            pstmt.setString(1, reguser.getName());
            pstmt.setString(2, reguser.getEmail());
            pstmt.setString(3, reguser.getPassword());
            pstmt.setString(4, reguser.getAbout());
            pstmt.setString(5, reguser.getProfile());
            pstmt.setInt(6, reguser.getId());

            pstmt.executeUpdate();
            f = true;

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return f;
    }

    public RegUser getUserByUserId(int userId) {

        RegUser reguser = null;

        try {

            String query = "select * from registration_form where id = ?";

            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setInt(1, userId);

            ResultSet set = pstmt.executeQuery();

            if (set.next()) {

                reguser = new RegUser();
                // data from db 

                String name = set.getString("userName");

                // set to user object 
                reguser.setName(name);
                reguser.setId(set.getInt("id"));
                reguser.setPassword(set.getString("password"));
                reguser.setEmail(set.getString("email"));
                reguser.setGender(set.getString("gender"));
                reguser.setAbout(set.getString("about"));
                reguser.setDateTime(set.getTimestamp("rdate"));
                reguser.setProfile(set.getString("profile"));

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return reguser;
    }

}
