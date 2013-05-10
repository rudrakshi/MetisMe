package com.metisme.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.Part;
import java.sql.*;

public class Images {
    
     public static String getFilename(Part part,String path) 
    {
	    
            for (String cd : part.getHeader("content-disposition").split(";")) 
            {
	        if (cd.trim().startsWith("filename")) 
                {
	            String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
    }
     
     public static boolean uploadImage(String path,int id,Part filePart,String filename) throws IOException
    {
        File file=new File(path+"//images//user"+id);
        if(!(file.isDirectory()))
        {
            file.mkdir();
            System.out.println(path);
        }
        path=path+"//images//user"+id;
        //System.out.println(path);
	InputStream filecontent = filePart.getInputStream();
        OutputStream out = new FileOutputStream(new File(path+"//"+"img"+filename));
	int read = 0;
	byte[] bytes = new byte[1024];	 
	while ((read = filecontent.read(bytes)) != -1) 
        {
            out.write(bytes, 0, read);
	}	 
	filecontent.close();
	out.flush();
	out.close();
        changeUimg(path,filename,id);
        return true;
    }
     
     public static void changeUimg(String p,String f,int u)
     {
         try
        {
            Connection con=DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement("update user set img=? where uid=?");
            pst.setString(1, p+"//"+"img"+f);
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
