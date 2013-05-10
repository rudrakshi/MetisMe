package com.metisme.model;

import java.util.*;
import com.metisme.beans.*;
import java.sql.*;

public class Messages {
    
    public int addMessage(String msg, int u)
    {
        int a=0;
        try
        {
            Connection con=DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement("insert into message(mcontent,uid) values(?,?)");
            pst.setString(1, msg);
            pst.setInt(2, u);
           
            a=pst.executeUpdate();
            if(a==1)
                con.commit();
            else
                con.rollback();
        }catch(Exception e)
        {
            a=200;
            System.out.println(e);
        }
        return a;
    }
    
    public List<MessageBean> getMessages(int u)
    {
        List<MessageBean> messages=new ArrayList<MessageBean>();
        try
        {
            Connection con=DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement("select f_uid from follow where p_uid=?");
            pst.setInt(1, u);
           
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                
                int f=rs.getInt("f_uid");
                pst=con.prepareStatement("select m.mid,m.mcontent,u.uname,u.img from message m,user u where m.uid=u.uid and m.uid=?");
                pst.setInt(1, f);
                ResultSet rs1=pst.executeQuery();
                while(rs1.next())
                {
                    MessageBean mb=new MessageBean();
                    mb.setMid(rs1.getInt(1));
                    mb.setContent(rs1.getString(2));
                    mb.setUname(rs1.getString(3));
                    mb.setUimg(rs1.getString(4));
                    mb.setLike(getLikeCount(mb.getMid(),con));
                    mb.setComment(getComments(con,mb.getMid()));
                    mb.setStatus(getLikeStatus(mb.getMid(),con,u));
                    messages.add(mb);
                }
                                
            }
            
        }catch(Exception e)
        {
             System.out.println(e);
            messages=null;
        }
        return messages;
        
    }
    
    String getLikeStatus(int m, Connection con, int u)
    {
        String status="Unlike";
        try
        {
            PreparedStatement pst=con.prepareStatement("select count(*) from likes where uid=? and mid=?");
            pst.setInt(1, u);
            pst.setInt(2, m);
            ResultSet rs1=pst.executeQuery();
            if(rs1.next())
                if(rs1.getInt(1)>0)
                    status="Like";
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return status;
    }
    
    int getLikeCount(int m, Connection con)
    {
        try
        {
            PreparedStatement pst=con.prepareStatement("select count(mid) from likes where mid=?");
            pst.setInt(1, m);
            ResultSet rslike=pst.executeQuery();
            if(rslike.next())
                return rslike.getInt(1);
            else
                return 0;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return 0;
    }
    
    List<CommentBean> getComments(Connection con,int m)
    {
        List<CommentBean> cb=new ArrayList<CommentBean>();
        try
        {
            PreparedStatement pst=con.prepareStatement("select comment,uid,time from comment where mid=?");
            pst.setInt(1, m);
            ResultSet rscomm=pst.executeQuery();
                      
            while(rscomm.next())
            {
                CommentBean c=new CommentBean();
                c.setComment(rscomm.getString("comment"));
                
                pst=con.prepareStatement("select uname from user where uid=?");
                pst.setInt(1, rscomm.getInt("uid"));
                ResultSet rs=pst.executeQuery();
                if(rs.next())
                    c.setUname(rs.getString("uname"));
            
                c.setTime(rscomm.getDate("time"));

                cb.add(c);
            }
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return cb;
    }
    
    public void addComment(int u,int m,String comment)
    {
        try
        {
            Connection con=DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement("insert into comment(mid,comment,uid) values(?,?,?)");
            pst.setInt(1, m);
            pst.setInt(3, u);
            pst.setString(2, comment);
           
            int a=pst.executeUpdate();
            if(a==1)
                con.commit();
            else
                con.rollback();
            
            sendMail(m,u);
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    private void sendMail(int m,int u)
    {
        try
        {
            Connection con=DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement("select email from user where uid in (select f.p_uid from message m,follow f where f.f_uid=m.uid and m.uid=?)");
            pst.setInt(1, u);
           
            ResultSet rs=pst.executeQuery(); 
            String msg="Hi, There is a comment on the post you follow";
            while(rs.next())
            {
                Mail.sendMail(rs.getString("email"), msg);
            }
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void addLike(int m,int u)
    {
        try
        {
            Connection con=DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement("insert into likes values(?,?)");
            pst.setInt(1, m);
            pst.setInt(2, u);
                      
            int a=pst.executeUpdate();
            if(a==1)
                con.commit();
            else
                con.rollback();
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void removeLike(int m,int u)
    {
        try
        {
            Connection con=DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement("delete from likes where mid=? and uid=?");
            pst.setInt(1, m);
            pst.setInt(2, u);
           
            int a=pst.executeUpdate();
            if(a==1)
                con.commit();
            else
                con.rollback();
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
