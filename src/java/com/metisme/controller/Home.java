package com.metisme.controller;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.metisme.model.Messages;
import java.util.*;
import com.metisme.beans.*;
import javax.servlet.RequestDispatcher;

public class Home extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

       @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           try{
       HttpSession session=request.getSession();
       UserBean ub=(UserBean)session.getAttribute("user");
       int u=ub.getUid();
       
       Messages m=new Messages();
       List<MessageBean> mb=m.getMessages(u);
       
       RequestDispatcher rd=request.getRequestDispatcher("Home.jsp");
       request.setAttribute("Messages",mb);
       rd.forward(request,response);
           }catch(NullPointerException e)
           {
               response.sendRedirect("Login.jsp");
           }
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        String msg=request.getParameter("msg");
        HttpSession session=request.getSession();
        UserBean ub=(UserBean)session.getAttribute("user");
        int u=ub.getUid();
        if(msg!=null)
        {
            Messages m=new Messages();
            int a=m.addMessage(msg,u);
            if(a==1)
                response.sendRedirect("Home");
           
        }
        else
        response.sendRedirect("CreateMsg.jsp");
         }catch(NullPointerException e)
           {
               response.sendRedirect("Login.jsp");
           }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
