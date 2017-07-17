package com.tx.porygon.config;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {
	public static final Properties properties = new Properties();
	private final static Logger log = Logger.getLogger(Config.class.getName());
	public static boolean loaded=false;
		
//		static 
//		{
//			if(!loaded) {
//				InputStream in = Config.class.getResourceAsStream("config.properties");
//		        try 
//		        {
//		        	properties.load(in);
//				} catch (IOException e)
//				{
//		
//			}
//		}
		
		public final static String IMG_PATH_DIR = "/images/";
		public final static String IMG_PATH_DIR_TRACKING = "/trackings/";
		public final static String IMG_PATH_DIR_MATRIX = "/matrix/";
}