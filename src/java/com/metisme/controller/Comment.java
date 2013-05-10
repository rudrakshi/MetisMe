package com.metisme.controller;

import java.io.IOException;
import com.metisme.model.Messages;
import com.metisme.beans.UserBean;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Comment extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try{
           String lk=request.getParameter("like");
           int m=Integer.parseInt(request.getParameter("mid"));
           HttpSession session=request.getSession();
           UserBean ub=(UserBean)session.getAttribute("user");
           int u=ub.getUid();
           
           Messages msg=new Messages();

           if(lk.equalsIgnoreCase("Like"))
           {
               msg.addLike(m,u);
           }
           else
           {
               msg.removeLike(m,u);
           }
           response.sendRedirect("Home");
       }catch(NullPointerException e)
       {
           response.sendRedirect("Home");
       }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        HttpSession session=request.getSession();
        UserBean ub=(UserBean)session.getAttribute("user");
        int u=ub.getUid();
        String com=request.getParameter("comment");
        int m=Integer.parseInt(request.getParameter("mid"));
        
        Messages msg=new Messages();
        msg.addComment(u,m,com);
        
        response.sendRedirect("Home");
        }
        catch(NullPointerException n)
        {
            response.sendRedirect("Home");
        }
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
