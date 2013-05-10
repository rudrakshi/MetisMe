package com.metisme.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.metisme.beans.*;
import com.metisme.model.User;
import java.util.*;

public class Follow extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession session=request.getSession();
       UserBean ub=(UserBean)session.getAttribute("user");
       int uid=ub.getUid();
       User u=new User();
       List<UserBean> list=u.getFollowed(uid);
       if(list!=null)
       {
           RequestDispatcher rd=request.getRequestDispatcher("FollowUser.jsp");
           request.setAttribute("Users", list);
           rd.forward(request, response);
       }
       
       else
           response.sendRedirect("Login.jsp");
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        UserBean ub=(UserBean)session.getAttribute("user");
        int u=ub.getUid();
        int f=Integer.parseInt(request.getParameter("uid"));
        User us=new User();
        us.followUser(u,f);
        
        response.sendRedirect("Follow");
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
