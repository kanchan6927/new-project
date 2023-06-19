/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techblog.dao;

import com.techblog.entities.Post;
import com.techblog.entities.RegUser;
import com.techblog.entities.likeStatus;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LikeDao {

    Connection con;

    public LikeDao(Connection con) {
        this.con = con;

    }

    public boolean insertLike(int pid, int userId, int is_liked) {

        boolean f = false;
        try {

            String q = "insert into likedByUser(pid,userId,is_liked)values(?,?,?);";

            PreparedStatement pstmt = this.con.prepareStatement(q);

            pstmt.setInt(1, pid);
            pstmt.setInt(2, userId);
            pstmt.setInt(3, is_liked);

            pstmt.executeUpdate();
            f = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return f;
    }

    public int countLikeOnPost(int pid) {

        int count = 0;

        try {
            String q = "select count(*) from likedByUser where pid = ?";

            PreparedStatement pstmt = this.con.prepareStatement(q);
            pstmt.setInt(1, pid);

            ResultSet set = pstmt.executeQuery();

            if (set.next()) {
                count = set.getInt("count(*)");
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return count;
    }

    public boolean deleteLike(int pid, int userId, int is_liked) {
        boolean f = false;

        try {
            String query = "DELETE FROM likedbyuser WHERE pid=? AND userId=? AND is_liked = ?;";

            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setInt(1, pid);
            pstmt.setInt(2, userId);
            pstmt.setInt(3, is_liked);
            int result = pstmt.executeUpdate();

            if (result == 1) {
                f = true;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return f;
    }

    public List<RegUser> fetchuserIdofLikedPost(int pid) {

        List<RegUser> list = new ArrayList<>();

        try {

            String q = "select userId from likedbyuser where pid = ?;";

            String query = "select likedbyuser.userId,registration_form.userName,registration_form.profile  from likedbyuser inner join registration_form on likedbyuser.userId = registration_form.id where pid = ?;";

            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setInt(1, pid);

            ResultSet set = pstmt.executeQuery();

            while (set.next()) {
//                int userId = set.getInt("userId");
                String userName = set.getString("userName");
                String profile = set.getString("profile");

//                RegUser reguser = new RegUser(userId);
                RegUser reguser = new RegUser(userName, profile);
                reguser.setName(userName);
                reguser.setProfile(profile);
                list.add(reguser);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public likeStatus fetchthumClass(int pid, int userId) {

        likeStatus likestatus = null;
        try {

            String q = "select thumClass from likedbyuser where pid = ? and userId = ?;";

            PreparedStatement pstmt = con.prepareStatement(q);

            pstmt.setInt(1, pid);
            pstmt.setInt(2, userId);

            ResultSet set = pstmt.executeQuery();

            if (set.next()) {

                String thumClass = set.getString("thumClass");

                likestatus = new likeStatus(pid, userId, thumClass);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return likestatus;
    }

    public likeStatus getIsLikedStatuss(int pid, int userId) {

        likeStatus likestatus = null;

        try {

            String q = "select is_liked from likedbyuser where pid = ? and userId = ?;";

            PreparedStatement pstmt = con.prepareStatement(q);

            pstmt.setInt(1, pid);
            pstmt.setInt(2, userId);

            ResultSet set = pstmt.executeQuery();

            if (set.next()) {
                int isLiked = set.getInt("is_liked");
                likestatus = new likeStatus(pid, userId, isLiked);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return likestatus;
    }

    

    public List<likeStatus> getAllInfoAboutNot(int userId) {

        List<likeStatus> list = new ArrayList<>();

        try {
            String qq = "SELECT p.pid, p.pPic, u.id, u.userName, po.userId AS likedUserId  FROM post p INNER JOIN likedbyuser l ON p.pid = l.pid  INNER JOIN registration_form u ON u.id = l.userId INNER JOIN post po ON po.pid = l.pid WHERE l.userId = ?;";
            PreparedStatement pstmt = con.prepareStatement(qq);
            pstmt.setInt(1, userId);

            ResultSet set = pstmt.executeQuery();
            while (set.next()) {

                int pid = set.getInt("pid");
                String ppic = set.getString("pPic");
                String likeduserName = set.getString("userName");
                int likeUserId = set.getInt("likedUserId");
                likeStatus ls = new likeStatus(pid, ppic, likeduserName, likeUserId);
                ls.setPid(pid);
                ls.setPpic(ppic);
                list.add(ls);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    //...................................... methods which are not used in project   ...........................//
   
    public List<likeStatus> whichUsersPost(int userId) {

        List<likeStatus> list = new ArrayList<>();

        try {
            String query = " SELECT u.id,u.userName FROM post p INNER JOIN likedbyuser l ON p.pid = l.pid INNER JOIN registration_form u ON u.id = l.userId WHERE p.userId = ?;";
            String q = "select userId from post where pid in(select pid from likedbyuser where userId = ?);";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setInt(1, userId);

            ResultSet set = pstmt.executeQuery();
            while (set.next()) {

                int userIdd = set.getInt("userId");
//                String likedUserName = set.getString("name");
                likeStatus ls = new likeStatus(userIdd);
//                ls.setUserId(userIdd);
//                ls.setName(likedUserName);
                list.add(ls);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public List<RegUser> whichUsersLikedPostt(int userId) {

        List<RegUser> list = new ArrayList<>();

        try {
            String q = "SELECT u.id, u.userName FROM registration_form u INNER JOIN likedbyuser l ON u.id = l.userId WHERE l.userId = ?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setInt(1, userId);

            ResultSet set = pstmt.executeQuery();
            while (set.next()) {

                int userIdd = set.getInt("id");
                String likedUserName = set.getString("userName");
                RegUser rs = new RegUser(userIdd, likedUserName);
                rs.setId(userIdd);
                rs.setName(likedUserName);
                list.add(rs);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public List<likeStatus> whichUsersLikedWhichPostt(int userId) {

        List<likeStatus> list = new ArrayList<>();

        try {
            String qq = "select pid,pPic from post where pid in(select pid from likedbyuser where userId = ?)";
            PreparedStatement pstmt = con.prepareStatement(qq);
            pstmt.setInt(1, userId);

            ResultSet set = pstmt.executeQuery();
            while (set.next()) {

                int pid = set.getInt("pid");
                String ppic = set.getString("pPic");
                likeStatus ls = new likeStatus();
                ls.setPid(pid);
                ls.setPpic(ppic);
                list.add(ls);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }
    
    
    
    
    
    public int getIsLikedStatus(int pid, int userId) {

        int isLiked = 0;

        try {

            String q = "select is_liked from likedbyuser where pid = ? and userId = ?;";

            PreparedStatement pstmt = con.prepareStatement(q);

            pstmt.setInt(1, pid);
            pstmt.setInt(2, userId);

            ResultSet set = pstmt.executeQuery();

            if (set.next()) {
                isLiked = set.getInt("is_liked");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isLiked;
    }

    public List<RegUser> fetchAllDetailsDemo() {

        List list = new ArrayList<>();

        try {

            String q = "select userName from registration_form;";

            Statement stmt = con.createStatement();

            ResultSet set = stmt.executeQuery(q);

            while (set.next()) {
                String userName = set.getString("userName");
                RegUser reguser = new RegUser(userName);
                list.add(reguser);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    
    
    
    
}
