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

public class Change extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try{
            HttpSession session=request.getSession();
            UserBean ub=(UserBean)session.getAttribute("user");
            int u=ub.getUid();
            System.out.println("uid: "+u);
            String path = request.getRealPath("");       
            System.out.println("path: "+path);
                Part filePart = request.getPart("img"); // Retrieves <input type="file" name="file">
                System.out.println("got filepart"+filePart);
                String filename = Images.getFilename(filePart,path);
                if(Images.uploadImage(path,u,filePart,filename))
                {                        
                        ub.setUimg(path);
                        session.setAttribute("user", ub);
                	request.setAttribute("result","Images Uploaded Successfully");
                   
                }                
                else
                {
                	request.setAttribute("result","Images Can Not Uploaded, Please Try Again... !!");
                }
                RequestDispatcher rd=request.getRequestDispatcher("Home");
           
            rd.forward(request, response);
        }catch(NullPointerException e)
           {
               response.sendRedirect("Home");
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
