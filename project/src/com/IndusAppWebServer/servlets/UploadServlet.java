package com.IndusAppWebServer.servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/upload")
@MultipartConfig( fileSizeThreshold = 1024 * 1024, 
maxFileSize = 1024 * 1024 * 5, 
maxRequestSize = 1024 * 1024 * 5 * 5 )
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String FILES_FOLDER = "/resources";
    public String uploadPath;
    public static List<String> catalog = new ArrayList<String>();
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /*
     * Si le dossier de sauvegarde de l'image n'existe pas, on demande sa création.
     */ 
    @Override
    public void init() throws ServletException {
        uploadPath = getServletContext().getRealPath( FILES_FOLDER );
        File uploadDir = new File( uploadPath );
        if ( ! uploadDir.exists() ) uploadDir.mkdir();
    }
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		for ( Part part : request.getParts() ) {
			
            String fileName = getFileName( part );
            String fullPath = uploadPath + File.separator + fileName;
            System.out.println(fullPath);
            catalog.add(fileName);
            part.write( fullPath );
        }
	}

	private String getFileName(Part part) {
		String [] temp = part.getHeader( "content-disposition" ).split(";");
		System.out.println(temp[temp.length-1].substring(11, temp[temp.length-1].length()-1));
        return temp[temp.length-1].substring(11, temp[temp.length-1].length()-1);
	}

}
