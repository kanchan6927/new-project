/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techblog.entities;

import java.sql.*;

public class RegUser {

    private int id;
    private String name;
    private String email;
    private String password;
    private String gender;
    private Timestamp dateTime;
    private String about;
    private String profile;

    public RegUser(int id, String name, String password, String email, String gender, Timestamp dateTime, String about) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.dateTime = dateTime;
        this.about = about;

    }

    public RegUser() {

    }

    public RegUser(int id, String name) {

    }

    public RegUser(String name, String password, String email, String gender, String about) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.about = about;

    }

    public RegUser(String name) {

        this.name = name;
    }

    public RegUser(int id) {

        this.id = id;
    }

    public RegUser(String name, String profile) {

        this.name = name;
        this.profile = profile;
    }

    // getters and setters 
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAbout() {
        return about;
    }

    public void setProfile(String profile) {

        this.profile = profile;

    }

    public String getProfile() {

        return profile;

    }

}
