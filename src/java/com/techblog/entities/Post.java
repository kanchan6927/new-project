/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techblog.entities;

import java.sql.*;

public class Post {

    private int pid;
    private String ptitle;
    private String pcontent;
    private String pcode;
    private String ppic;
    private Timestamp pdate;
    private int catId;
    private int userId;

    public Post() {
    }


    public Post(int pid, String ptitle, String pcontent, String pcode, String ppic, Timestamp pdate, int catId, int userId) {
        this.pid = pid;
        this.ptitle = ptitle;
        this.pcontent = pcontent;
        this.pcode = pcode;
        this.ppic = ppic;
        this.pdate = pdate;
        this.catId = catId;
        this.userId = userId;
    }

    public Post(String ptitle, String pcontent, String pcode, String ppic, Timestamp pdate, int catId, int userId) {

        this.ptitle = ptitle;
        this.pcontent = pcontent;
        this.pcode = pcode;
        this.ppic = ppic;
        this.pdate = pdate;
        this.catId = catId;
        this.userId = userId;
    }

    public void setPid(int pid) {
        this.pid = pid;

    }

    public int getPid() {
        return pid;

    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;

    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPcontent(String pcontent) {
        this.pcontent = pcontent;
    }

    public String getPcontent() {

        return pcontent;
    }

    public void setPcode(String pcode) {

        this.pcode = pcode;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPpic(String ppic) {

        this.ppic = ppic;
    }

    public String GetPpic() {
        return ppic;
    }

    public void setPdate(Timestamp pdate) {

        this.pdate = pdate;
    }

    public Timestamp getPdate() {

        return pdate;
    }

    public void setCatid(int catId) {
        this.catId = catId;
    }

    public int getCatid() {

        return catId;
    }

    public void setUserid(int userId) {
        this.userId = userId;
    }

    public int getUserId() {

        return userId;
    }

}
