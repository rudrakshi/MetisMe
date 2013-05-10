package com.metisme.controller;

import java.io.IOException;
import com.metisme.model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.metisme.beans.UserBean;

public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session=request.getSession();
         session.removeAttribute("userID");
         session.removeAttribute("userName");
         session.invalidate();
         response.sendRedirect("Login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            String unm=request.getParameter("unm");
            String pwd=request.getParameter("pwd");
            
            User u=new User();
               UserBean a=u.checkUser(unm,pwd);
               if(a!=null)
               {
                   HttpSession session=request.getSession();
                   session.setAttribute("user",a);
                   response.sendRedirect("Home");
               }
               else
                   response.sendRedirect("Login.jsp");
       
        }catch(NullPointerException e)
        {
            response.sendRedirect("Login.jsp");
        }catch(Exception e)
        {
            System.out.println(e);
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
