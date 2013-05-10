
package com.metisme.model;

import java.sql.*;
import java.util.*;
import com.metisme.beans.UserBean;

public class User {
    
    public List<UserBean> getFollowed(int u)
    {
        List<UserBean> ub=new ArrayList<UserBean>();
        try
        {
            Connection con=DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement("select uid,uname,img from user where uid<>?");
            pst.setInt(1, u);
                       
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                UserBean f=new UserBean();
                
                f.setUid(rs.getInt("uid"));
                f.setUname(rs.getString("uname"));
                f.setUimg(rs.getString("img"));
                f.setStatus(getStatus(u,f.getUid(),con));
                
                ub.add(f);
            }
        }catch(Exception e)
        {
            ub=null;
            System.out.println(e);
        }
        return ub;
    }
    
    private String getStatus(int u, int f, Connection con)
    {
        String status="Not Followed";
        try
        {
            PreparedStatement pst=con.prepareStatement("select f_uid from follow where p_uid=? and f_uid=?");
            pst.setInt(1, u);
            pst.setInt(2, f);

            ResultSet rs1=pst.executeQuery();
            if(rs1.next())
            {
                status="Followed";
            }
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return status;
    }
    
    public UserBean checkUser(String u, String p)
    {
        UserBean a=new UserBean();
        try
        {
           Connection con=DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement("select l.uid,u.img from login l,user u where l.uid=u.uid and username=? and password=?");
            pst.setString(1, u);
            pst.setString(2, p);
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                
                a.setUid(rs.getInt(1));
                a.setUimg(rs.getString(2));
                a.setUname(u);
            }
            
        }catch(Exception e)
        {
            //a=200;
            System.out.println(e);
        }
        return a;
    }
    
    public int addUser(String name,String occ, String loc, String email)
    {
          int a=0;
        try
        {
        Connection con=DBConnection.getConnection();
        PreparedStatement pst=con.prepareStatement("insert into user(uname,occupation,location,email) values(?,?,?,?)");
        pst.setString(1, name);
        pst.setString(2, occ);
        pst.setString(3, loc);
        pst.setString(4, email);
        
        a=pst.executeUpdate();
        if(a==1)
                con.commit();
            else
                con.rollback();
        }
        catch(Exception e)
        {
            a=200;
            System.out.println(e);
        }
         return a;
    }
    
    public void followUser(int u,int f)
    {
        try
        {
            Connection con=DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement("insert into follow values(?,?)");
            pst.setInt(1, u);
            pst.setInt(2, f);
            
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
    
    public int addLogin(String email,String unm, String pwd)
    {
        int a=0;
        try
        {
            Connection con=DBConnection.getConnection();
            PreparedStatement pst1=con.prepareStatement("select uid from user where email=?");
            pst1.setString(1, email);
            ResultSet rs=pst1.executeQuery();
            if(rs.next())
            {
            PreparedStatement pst=con.prepareStatement("insert into login values(?,?,?)");
            pst.setInt(1, rs.getInt("uid"));
            pst.setString(2, unm);
            pst.setString(3, pwd);
          
            a=pst.executeUpdate();
            if(a==1)
                con.commit();
            else
                con.rollback();
            }
            
        }catch(Exception e)
        {
            //a=400;
            System.out.println(e);
        }
        return a;
    }
}
