package com.tx.porygon.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OpenPDF
 */
public class OpenPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpenPDF() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("purchaseId")!=null){
			//TestG.generatePDF(request.getParameter("purchaseId"));
			 	File f = new File("/images/"+request.getParameter("purchaseId")+".pdf");
			    if(!f.exists()){
			    	response.sendError(404, "Etiqueta não existe");
			    	return;
			    }
			    
			    String fileName = URLEncoder.encode(request.getParameter("purchaseId"), "UTF-8");
			    fileName = URLDecoder.decode(fileName, "ISO8859_1");
			    response.setContentType("application/pdf"); 
			    response.setHeader("Content-disposition", "inline; filename="+ fileName+ ".pdf");
			    File pdfFile = new File("/images/"+request.getParameter("purchaseId")+".pdf");
			    response.setContentLength((int) pdfFile.length());
			    FileInputStream in = new FileInputStream("/images/"+request.getParameter("purchaseId")+".pdf");
			    
			    byte[] buffer = new byte[4096];
			    int length;
			    while ((length = in.read(buffer)) > 0){
			    	response.getOutputStream().write(buffer, 0, length);
			    }
			    in.close();
			    
			    response.getOutputStream().flush();
			    response.getOutputStream().close();
		}
		else{
			response.sendError(404, "Order não encontrado");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
