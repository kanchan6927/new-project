/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techblog.entities;

public class likeStatus {

    private int is_liked;
    private int pid;
    private int userId;
    private String thumClass;
    private String ppic;
    private String likeduserName;
    private int likedUserId;

    public likeStatus(int pid, int userId, int is_liked) {
        this.pid = pid;
        this.userId = userId;
        this.is_liked = is_liked;
    }

    public likeStatus(int pid, int userId, String thumClass) {
        this.pid = pid;
        this.userId = userId;
        this.thumClass = thumClass;
    }

    public likeStatus() {
    }

    public likeStatus(int userId) {

        this.userId = userId;

    }

    public likeStatus(int pid, String ppic,String likeduserName,int likedUserId) {

        this.pid = pid;
        this.ppic = ppic;
        this.likedUserId = likedUserId;
        this.likeduserName = likeduserName;

    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setIs_liked(int is_liked) {

        this.is_liked = is_liked;

    }

    public int getIs_liked() {

        return is_liked;
    }

    public void setThumClass(String thumClass) {

        this.thumClass = thumClass;

    }

    public String getThumClass() {

        return thumClass;
    }

    public void setPpic(String ppic) {
        this.ppic = ppic;

    }

    public String getPpic() {
        return ppic;

    }

    public void setLikeduserName(String likeduserName) {
        this.likeduserName = likeduserName;

    }

    public String getLikeduserName() {
        return likeduserName;

    }

    public void setLikedUserId(int likedUserId) {
        this.likedUserId = likedUserId;

    }

    public int getLikedUserId() {
        return likedUserId;

    }

}
