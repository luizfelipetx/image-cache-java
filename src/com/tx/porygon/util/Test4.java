package com.tx.porygon.util;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

public class Test4 {

	
		 public static void convert(InputStream input, OutputStream out) throws DocumentException{   
		        Tidy tidy = new Tidy();           
		        Document doc = tidy.parseDOM(input, null);   
		        ITextRenderer renderer = new ITextRenderer();   
		        renderer.setDocument(doc, null);   
		        renderer.layout();         
		        renderer.createPDF(out);                   
		    }     
		    
		 public static void main(String[] args) throws Exception {

		    	OutputStream os = new FileOutputStream("hello.pdf");;
		    	//convert("<h1 style=\"color:red\">Hello PDF</h1>", os); 
		        convert(new ByteArrayInputStream("<h1 style=\"color:red\">Hello PDF</h1>".getBytes()),os);

		    	os.close();
			}
	}

