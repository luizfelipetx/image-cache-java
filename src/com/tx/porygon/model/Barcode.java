package com.tx.porygon.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import com.lowagie.text.pdf.BarcodeDatamatrix;
import com.tx.porygon.config.Config;

public class Barcode extends Config {

	public static BufferedImage create(HttpServletRequest request)
	{
		try{
			
			String trackingCode = request.getParameter("tc");
			String fileName = IMG_PATH_DIR_TRACKING  + generateBarUrl(trackingCode);
		
			File f = new File(fileName);
//			if(f.exists() && !f.isDirectory()) {
//				//ja existe retorna do cac
//				System.out.println("Ja existe");
//			}else{
			
				System.out.println("removendo existentes...");
				System.out.println("executando comannd: rm -r /images/"+fileName);
				Runtime.getRuntime().exec("rm -r /images/"+fileName);

				System.out.println("criando...");
				createBeanImage(trackingCode);
				
//			}
			BufferedImage img = ImageIO.read(new File(fileName));
			return img;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public static String joinParameters(HttpServletRequest request,boolean img){
		
		StringBuffer sbf = new StringBuffer();
		
		sbf.append(request.getParameter("idv"));
		sbf.append(request.getParameter("validadorCepDestino"));
		sbf.append(request.getParameter("cepDestino"));
		sbf.append(request.getParameter("complementoCepDestino"));
		sbf.append(request.getParameter("cepOrigem"));
		System.out.println(request.getParameter("cepOrigem"));
		sbf.append(request.getParameter("complementoCepOrigem"));
		System.out.println(request.getParameter("etiqueta"));
		sbf.append(request.getParameter("etiqueta"));
		sbf.append(request.getParameter("sa"));
		System.out.println(request.getParameter("sa"));
		sbf.append(request.getParameter("cp"));
		System.out.println(request.getParameter("cp"));
		sbf.append(request.getParameter("codigoServico"));
		System.out.println(request.getParameter("codigoServico"));
		sbf.append(request.getParameter("agrupamento"));
		sbf.append(request.getParameter("numero"));
		
		sbf.append(request.getParameter("complemento"));
		sbf.append(request.getParameter("valor"));
		sbf.append(request.getParameter("telefone"));
		sbf.append(request.getParameter("latitude"));
		sbf.append(request.getParameter("longitude"));
		//sbf.append("|");
		//sbf.append(request.getParameter("rc"));
		if(img)
			sbf.append(".jpg");
		return sbf.toString();
	}
	
	public static BufferedImage createMatrix(HttpServletRequest request)
	{
		try{
			String fileName = IMG_PATH_DIR_MATRIX  + generateBarUrl(joinParameters(request,true));
			System.out.println(fileName);
			
			File f = new File(fileName);
			if(f.exists() && !f.isDirectory()) {
				//ja existe retorna do cac
				System.out.println("Ja existe");
			}else{
				System.out.println("n Ja existe" + joinParameters(request,false));
				System.out.println(fileName);
				BufferedImage img = Barcode.createDataMatrix(fileName,joinParameters(request,false));
				return img;
			}
			
			BufferedImage img = ImageIO.read(new File(fileName));
			return img;
			
		}catch(Exception e){
		}
		return null;
	}
	
	
	
	private static String generateBarUrl(String trackingCode){
		
		try {
			return MD5.geraMD5(trackingCode)+".jpg";
		} catch (NoSuchAlgorithmException e) {	
			e.printStackTrace();
		}
		return null;
	}
	
	private static BufferedImage createDataMatrix(String filename,String valueMatrix){
		
		try{
			BarcodeDatamatrix barcode = new BarcodeDatamatrix();
			barcode.setOptions(BarcodeDatamatrix.DM_AUTO);

		    barcode.setWidth(32);
		    barcode.setHeight(32);
		    System.out.println(valueMatrix);
		    int returnResult = barcode.generate(valueMatrix);
		    if (returnResult == BarcodeDatamatrix.DM_NO_ERROR)  
		    {
		    	System.out.println("log");
		    	java.awt.Image awtImage = barcode.createAwtImage(Color.BLACK, Color.WHITE);
		    	BufferedImage bImage= new BufferedImage(120, 120, BufferedImage.TYPE_INT_RGB);
		    	Graphics2D g = bImage.createGraphics();
		    	g.drawImage(awtImage.getScaledInstance(120, 120, 0), 0, 0, null);
		    	g.dispose();
		    	ImageIO.write(bImage, "jpg", new File(filename));
		    	return bImage;
	    	}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	private static String createBeanImage(String trackingCode)
	{	
		try
		{
			Code128Bean code1281 = new Code128Bean();
			code1281.setHeight(15f);
			code1281.setModuleWidth(0.3);
			code1281.setQuietZone(10);
			code1281.doQuietZone(true);
			code1281.setMsgPosition(HumanReadablePlacement.HRP_NONE);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
			code1281.generateBarcode(canvas, trackingCode);
			canvas.finish();
	
			//write to png file
			String barUrl = IMG_PATH_DIR_TRACKING  + generateBarUrl(trackingCode);
			System.out.println("Criando em.."+barUrl);
			FileOutputStream fos2 = new FileOutputStream(barUrl);
			fos2.write(baos.toByteArray());
			fos2.flush();
			fos2.close();
			 
			return barUrl;
			
		}catch(Exception e ){	
			e.printStackTrace();
		}
		return null;
	}
}
