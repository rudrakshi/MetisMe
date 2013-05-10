package com.metisme.beans;

import java.util.*;

public class MessageBean {
    private int mid,like;
    private String content;
    private String uname;
    private String uimg;
    private String status;
    private List<CommentBean> comment;
    
    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUimg() {
        return uimg;
    }

    public void setUimg(String uimg) {
        this.uimg = uimg;
    }

    public List<CommentBean> getComment() {
        return comment;
    }

   public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setComment(List<CommentBean> comment) {
        this.comment = comment;
    }
    
    
}
