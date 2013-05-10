
package com.metisme.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.metisme.model.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Part;
import com.metisme.beans.UserBean;

public class Register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
    }

    
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
       String name=request.getParameter("uname");
       String occ=request.getParameter("occ");
       String loc=request.getParameter("loc");
       String email=request.getParameter("email");
       String unm=request.getParameter("unm");
       String pwd=request.getParameter("pwd");
       String cpwd=request.getParameter("cpwd");
        
       if(pwd.equals(cpwd))
       {
           if(name.matches("[A-za-z]+(\\s[A-Za-z]+)") && loc.matches("[A-Za-z]+") && occ.matches("[A-Za-z]+") && email.matches("([A-Za-z]+)([_A-Za-z0-9-\\.]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,4})"))
           {
               User u=new User();
               int a=u.addUser(name,occ,loc,email);
               if(a==1)
               {
                   int b=u.addLogin(email,unm,pwd);
                   if(b==1)
                   {
                       String msg="Dear"+name+", Thank You for registering with MetisMe";
                       Mail.sendMail(email, msg);
                       response.sendRedirect("Login.jsp");
                   }
               }
               else
                   response.sendRedirect("index.jsp");
           }
       }
       
           response.sendRedirect("index.jsp");
       
        }catch(NullPointerException e)
        {
            response.sendRedirect("Home");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
