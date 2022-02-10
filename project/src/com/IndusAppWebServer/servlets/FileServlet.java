package com.IndusAppWebServer.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileServlet
 */
@WebServlet("/getFile")
public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String filename = request.getParameter( "filename" );
        if ( filename == null ) return;
        String fullPath = getServletContext().getRealPath(UploadServlet.FILES_FOLDER)
                        + File.separator + filename;
        File srcFile = new File( fullPath );

        //ServletContext ctx = getServletContext();
        InputStream in = new FileInputStream(srcFile);
        //String mimeType = ctx.getMimeType(srcFile.getAbsolutePath());
        response.setContentLength((int)srcFile.length());
        response.setHeader("Content-Disposition", "attachment; filename=\""+filename+"\"");
        ServletOutputStream os = response.getOutputStream();
        byte[] bufferData = new byte[1024];
        int read = 0;
        while((read = in.read(bufferData)) != -1) {
        	os.write(bufferData, 0, read);
        }
        os.flush();
        os.close();
        in.close();
        
        
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
