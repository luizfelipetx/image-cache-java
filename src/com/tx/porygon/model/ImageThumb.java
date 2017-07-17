package com.tx.porygon.model;

import static com.tx.porygon.util.Util.geraMD5;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.imgscalr.Scalr;

import com.jhlabs.image.GaussianFilter;
import com.tx.porygon.config.Config;

public class ImageThumb extends Config {

	
	public static BufferedImage create(HttpServletRequest request){
		
		String h =request.getParameter("h");
		String w = request.getParameter("w");
		String b = request.getParameter("blur");
		String m = request.getParameter("mask");
		String url = 
				request.getParameter("src");
		Boolean blur = false;
		Boolean mask = false;
		
		if(b!=null){
			if(Boolean.valueOf(b))
				blur = Boolean.valueOf(b);
			System.out.println(blur);
		}
		if(m!=null){
			if(Boolean.valueOf(m))
				mask = Boolean.valueOf(m);
			System.out.println(mask);
		}
		if(url!=null)
			if(url.contains("https"))
				url = url.replace("https", "http");
		
		try {
			return generateImage(url,h,w,blur,mask);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static BufferedImage generateImage(String u,String h , String w,Boolean blur,Boolean mask) throws NoSuchAlgorithmException 
	{			
				try 
				{
					  String  urls = u;
					  //gerando o hash para salvar a imagem
		              
					  // Using linux ->String filename = "/mnt/img/"+geraMD5(urls+ "&h="+h+"&w="+w) +".jpg";
					  
					  String blurString = "";
					  if(blur){
						  blurString = "&blur="+blur;
					  }
					  
					  String filename = IMG_PATH_DIR + geraMD5(urls+ "&h="+h+"&w="+w+blurString) +".jpg";
		              // if you run this in apache with Eclipse directly you can see the files in folder img-cache inside tmp files of apache eclipse
					  //if you run this on apache tomcat outsite eclipse , you can see images in /ROOT/timthumb/img-cache/...
					 
		              File f = new File(filename);
		              if(f.exists() && !f.isDirectory()) 
		              { 
		                 //ja existe retorna do cache
		            	  BufferedImage img = ImageIO.read(new File(filename));
		            	  return img;
		              }else{
		            	  
		            	  URL url = new URL(urls);
		            	  //http://files.parsetfss.com/2e9999bc-b4df-4590-b714-82691067b0f8/tfss-00003ea0-be98-4106-b5b4-019fbcd4995f-file
			              BufferedImage img = ImageIO.read(url);
	            		  BufferedImageOp[] bufferOp = null;

		            	  //nao exite gera e retorna
		            	  BufferedImage thumbnail =null;
		            	  
		            	  		//tem altura nao tem largura
			            	  if(h!=null && w==null)
			            	  {
			            		  //soment heigth
		            			  Double d = new Double(h);
			            		  Integer h1 = d.intValue();
			            		  //usa altura como parametro principal de controle
			            		  thumbnail = Scalr.resize(img, Scalr.Mode.FIT_TO_HEIGHT, h1.intValue(),  bufferOp);
			            		  //grava no cache de disco
			            		  thumbnail = escreveNoDisco(thumbnail, filename,blur);
			            		  return thumbnail;
				            	  
			            	  }else if(h==null && w!=null){
			            		  
			            		  //somente width
			            		  Integer w1 =new Integer ( w);
			            		  //usa width como parametro width
			            		  thumbnail = Scalr.resize(img, Scalr.Mode.FIT_TO_WIDTH, w1.intValue(),  bufferOp);
			            		  // thumbnail = Scalr.resize(img, w1.intValue(), null);
			            		  thumbnail =escreveNoDisco(thumbnail, filename,blur);
			            		  return thumbnail;
			            		  
			            	  }else if(h!=null && w!=null)
			            	  {
			            		  //com heigth e width
			            		 
			            		  Integer h1 =new Integer ( h);
			            		  Integer w1 =new Integer ( w);
			            	
			            		  //BufferedImage scaledImg = Scalr.crop(img, 202, 302, null);
			            		  BufferedImage scaleDown = null;
			            		  
			            		  if(img.getHeight()>img.getWidth())
			            		  {
			            			  scaleDown=  Scalr.resize(img, Scalr.Mode.FIT_TO_WIDTH,h1,bufferOp);
			            		  }else{
			            			  scaleDown= Scalr.resize(img, Scalr.Mode.FIT_TO_HEIGHT,w1,bufferOp);
			            		  }
			            		  //caso o tamanho da imagem passado exceda o tamanho orignal da imagem , o tamanho dolveido sera proporcional
			            		  //System.out.print(scaleDown.getHeight()); System.out.print(">");  System.out.print(h1.intValue());
			            		 // System.out.println();
			            		  
			            		 if(scaleDown.getHeight()< h1.intValue()){
			            			  h1 = scaleDown.getHeight();
			            		 }
			            		  
			            		 if(scaleDown.getWidth()< w1.intValue())
			            			  w1 = scaleDown.getWidth();

			            		  //System.out.println(h1);
			            		  BufferedImage crop = Scalr.crop(scaleDown, scaleDown.getWidth()/2-(w1/2), 0,  w1, h1, bufferOp);
			            		  
			            		  //(img, Mode.AUTOMATIC , img.getHeight()-img.getHeight()*2 ,img.getWidth()-img.getWidth()*2);
			            		  // BufferedImage out = img.getSubimage( (img.getWidth()/2)-(600/2), 80,10, 600);
			            		  // BufferedImage crop = Scalr.crop(scaledImg, wi.intValue(),he.intValue(), null);
			            		  
			            		  crop = escreveNoDisco(crop, filename,blur);
			            		  return crop;

			            	  }else{
			            		  //return original
			            		  //thumbnail = Scalr.resize(img,400, null);
			            		  thumbnail = Scalr.resize(img, Scalr.Mode.FIT_TO_HEIGHT, 400,  bufferOp);
			            		  thumbnail = escreveNoDisco(thumbnail, filename,blur);
			            		  
			            		  return thumbnail;
			            	  }
		              }    
			         
		          } catch (IOException e) {
		              System.out.println("Unable to retrieve Image!!!");
		          }
				 System.out.println("Unable to retrieve Image!!!");
				return null;
	}
	
	private static BufferedImage escreveNoDisco(BufferedImage buffer,String name,Boolean blur)
	{	
        try 
        {	
        	File outputfile = new File(name);
        	
        	if(blur)
        	{	
        	   	    //BufferedImage image = ImageIO.read(outputfile);
        		    float[] matrix = new float[2025];
        			for (int i = 0; i < 900; i++)
        				matrix[i] = 1.0f/900.0f;
        		    
        			BufferedImageOp op = new GaussianFilter( 40);
        		    BufferedImage blurredImage = null;
        			blurredImage = op.filter(buffer, blurredImage);
        			ImageIO.write(blurredImage, "jpg", outputfile);
        			return blurredImage;
        	}
        		
        	ImageIO.write(buffer, "jpg", outputfile);
        	return buffer;
        	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	
}
