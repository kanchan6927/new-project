/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techblog.dao;

import com.techblog.entities.Categories;
import com.techblog.entities.Post;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDao {

    Connection con;

    public PostDao(Connection con) {
        this.con = con;
    }

    public ArrayList<Categories> getAllCategories() {
        ArrayList<Categories> list = new ArrayList<>();

        try {
  
            String query = "select * from categories";

            Statement stmt = con.createStatement();

            ResultSet set = stmt.executeQuery(query);

            while (set.next()) {
                int cid = set.getInt("cid");
                String name = set.getString("name");
                String description = set.getString("description");

                Categories categories = new Categories(cid, name, description);
                list.add(categories);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;

    }

    public boolean uploadPost(Post p) {

        boolean f = false;

        try {

            String query = "insert into post(ptitle,pcontent,pcode,ppic,catId,userId)values(?,?,?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1, p.getPtitle());
            pstmt.setString(2, p.getPcontent());
            pstmt.setString(3, p.getPcode());
            pstmt.setString(4, p.GetPpic());
            pstmt.setInt(5, p.getCatid());
            pstmt.setInt(6, p.getUserId());

            pstmt.executeUpdate();
            f = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return f;
    }

    // get all the posts 
    public List<Post> getAllPosts() {

        List list = new ArrayList<>();
        //fetch all the post

        try {

            PreparedStatement pstmt = con.prepareStatement("select * from post order by pid desc;");
            ResultSet set = pstmt.executeQuery();
            while (set.next()) {
                int pid = set.getInt("pid");
                String ptitle = set.getString("ptitle");
                String pcontent = set.getString("pcontent");
                String pcode = set.getString("pcode");
                String ppic = set.getString("ppic");
                Timestamp pdate = set.getTimestamp("pdate");
                int catId = set.getInt("catId");
                int userId = set.getInt("userId");
                Post post = new Post(pid, ptitle, pcontent, pcode, ppic, pdate, catId, userId);

                list.add(post);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    // get post by category Id 
    public List<Post> getPostByCatId(int catId) {

        List list = new ArrayList<>();
        //    fetch all post by id

        try {

            PreparedStatement pstmt = con.prepareStatement("select * from post where catId = ?;");
            pstmt.setInt(1, catId);
            ResultSet set = pstmt.executeQuery();
            while (set.next()) {
                int pid = set.getInt("pid");
                String ptitle = set.getString("ptitle");
                String pcontent = set.getString("pcontent");
                String pcode = set.getString("pcode");
                String ppic = set.getString("ppic");
                Timestamp pdate = set.getTimestamp("pdate");

                int userId = set.getInt("userId");
                Post post = new Post(pid, ptitle, pcontent, pcode, ppic, pdate, catId, userId);

                list.add(post);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public Post getPostByPostId(int postId) {

        Post post = null;

        try {

            String q = "select * from post where pid=?";

            PreparedStatement pstmt = this.con.prepareStatement(q);
            pstmt.setInt(1, postId);

            ResultSet set = pstmt.executeQuery();
            if (set.next()) {

                int pid = set.getInt("pid");
                String ptitle = set.getString("ptitle");
                String pcontent = set.getString("pcontent");
                String pcode = set.getString("pcode");
                String ppic = set.getString("ppic");
                Timestamp pdate = set.getTimestamp("pdate");
                int catId = set.getInt("catId");
                int userId = set.getInt("userId");
                post = new Post(pid, ptitle, pcontent, pcode, ppic, pdate, catId, userId);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return post;
    }

    public List<Post> getPostByUserId(int userId) {
        List list = new ArrayList<>();

        try {

            String q = "select * from post where userId = ?;";

            PreparedStatement pstmt = this.con.prepareStatement(q);
            pstmt.setInt(1, userId);

            ResultSet set = pstmt.executeQuery();
            while (set.next()) {

                int pid = set.getInt("pid");
                String ptitle = set.getString("ptitle");
                String pcontent = set.getString("pcontent");
                String pcode = set.getString("pcode");
                String ppic = set.getString("ppic");
                Timestamp pdate = set.getTimestamp("pdate");
                int catId = set.getInt("catId");
//                int userId = set.getInt("userId");
                Post post = new Post(pid, ptitle, pcontent, pcode, ppic, pdate, catId, userId);
                list.add(post);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public boolean deletePost(int userId ,int pid ) {
        boolean f = false;
        String q = "delete from post where userId = ? and pid = ? ;";

        try {
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, pid);
           

            pstmt.executeUpdate();
            f = true;
            pstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return f;
    }

}
