package com.tx.porygon.core;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tx.porygon.enums.TypeRequest;
import com.tx.porygon.interfaces.Constants;
import com.tx.porygon.model.Barcode;
import com.tx.porygon.model.ImageThumb;

/**
 * Felipe teixeira
 */

public class ImageCacheServlet extends HttpServlet implements Constants
{
	private static final long serialVersionUID = 1L;
	
    public ImageCacheServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		TypeRequest typeRequest = TypeRequest.getTypeRequest(type);
		
		switch (typeRequest) {
		
		case CREATE_LABEL:
			
			//PDFProcessor.generatePDF(request);
			response.setContentType("application/json");
			response.getWriter().println("{\"result\":\"sucesso\"}");
			
			break;
		
		case CREATE_BAR_CODE:
			BufferedImage image =  Barcode.create(request);
			response.setContentType("image/jpeg");
			OutputStream out2 = response.getOutputStream();
			ImageIO.write(image, "jpg", out2);
			out2.close();
			break;
		case CREATE_BARCODE_MATRIX:
			
			BufferedImage bufferedImageMatrix = Barcode.createMatrix(request);
			
			response.setContentType("image/jpeg");
			OutputStream outMatrix = response.getOutputStream();
			ImageIO.write(bufferedImageMatrix, "jpg", outMatrix);
			outMatrix.close();
			break;
		default:
			try{
				BufferedImage normalImage = ImageThumb.create(request); 
				response.setContentType("image/jpeg");
				OutputStream out3 = response.getOutputStream();
				ImageIO.write(normalImage, "jpg", out3);
				out3.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}
}