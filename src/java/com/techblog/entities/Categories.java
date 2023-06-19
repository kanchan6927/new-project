/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techblog.entities;

public class Categories {

    private int cid;
    private String name;
    private String description;

    public Categories(int cid, String name, String description) {

        this.cid = cid;
        this.name = name;
        this.description = description;

    }

    public Categories() {
    }

    public Categories(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setCid(int cid) {
        this.cid = cid;

    }

    public int getCid() {
        return cid;

    }

    public void setName(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
