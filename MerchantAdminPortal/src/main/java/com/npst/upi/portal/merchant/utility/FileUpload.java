package com.npst.upi.portal.merchant.utility;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;

public class FileUpload {
	public static  File rename(MultipartFile file,String path) 

	{
		String  fileName=file.getOriginalFilename();

		System.out.println(fileName);

		// fileName= System.DateTime.Now.ToString("_ddMMyyhhmmss") + file.Extension; 
		String extension = "";
		try { 
		extension=fileName.substring((fileName.lastIndexOf('.')));
		} catch (Exception e) {
			return null;
		}
	UUID uuid = UUID.randomUUID();
		
	 String  name = uuid.toString();

		System.out.println("time  String = " +name);
		fileName=name+extension;

		System.out.println(fileName);

		/*String filePath = rootPath + "/" + fileName;*/
		String filePath = fileName;

		 /*System.out.println(filePath);*/
		File   storeFile = new File(filePath);
		
		
	 



		return storeFile;
	}
	
	
	
	public static File uploadFile(MultipartFile file,String path) throws Exception
	{
		
		 
				 byte[] bytes = file.getBytes();

				
				    
				 File dir = new File( path );
					if (!dir.exists())
						dir.mkdirs();
					
				 

				 // Create the file on server
				 File serverFile =FileUpload.rename(file,path);
				 
				 
			     
				 
		 
				      BufferedOutputStream stream = new BufferedOutputStream(
						 new FileOutputStream(serverFile));
				       stream.write(bytes);
				      stream.close();
		     return  serverFile;
				      
		   
		
	}
	
	public static File saveUserPic(String base64String,HttpServletRequest request) throws IOException
	{
		 BASE64Decoder decoder = new BASE64Decoder();
	        byte[] decodedBytes = decoder.decodeBuffer(base64String);
	       // log.debug("Decoded upload data : " + decodedBytes.length);
	       // String uploadFile = "/tmp/test.png";
	        //log.debug("File save path : " + uploadFile);
	 
	    	UUID uuid = UUID.randomUUID();
			
	   	 String  name = uuid.toString();

	   		System.out.println("time  String = " +name);
	   	String fileName=name+".jpeg";

	    	System.out.println(fileName);
	   	    String rootPath=request.getSession().getServletContext().getRealPath("/resources/StoreImages");
	   		String filePath = rootPath + "/" + fileName;


	   		 System.out.println(filePath);
	   		File  serverFile = new File(filePath);
	   		
	      
	         BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
	         if (image == null) {
	             // loger.error("Buffered Image is null");
	          }
	         
	         else{
	         File f = new File(filePath);
	 
	         // write the image
	          ImageIO.write(image, "jpeg", f);
	         }
		
		return serverFile;
		
	}
	
	/*public static File uploadFile1(String base64String,String path) throws Exception
	{
		
		BASE64Decoder decoder = new BASE64Decoder();
        byte[] decodedBytes = decoder.decodeBuffer(base64String);
        
		 File dir = new File(path);
			if (!dir.exists())
				dir.mkdirs();
        
			 BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
	         if (image == null) {
	             // log.error("Buffered Image is null");
	          }
	         File f = new File(path);
	 
	         // write the image
	          ImageIO.write(image, "jpeg", f);
			
			
		
		return 
	}
	*/
	
	public static File uploadFiles(MultipartFile file) throws Exception
	{
		
		 
				 byte[] bytes = file.getBytes();

				
				    
				 /*File dir = new File( path );
					if (!dir.exists())
						dir.mkdirs();*/
					
				 

				 // Create the file on server
				 File serverFile =FileUpload.renames(file);
				 
				 
			     
				 
		 
				      BufferedOutputStream stream = new BufferedOutputStream(
						 new FileOutputStream(serverFile));
				       stream.write(bytes);
				      stream.close();
		     return  serverFile;
				      
		   
		
	}
	
	public static  File renames(MultipartFile file) 

	{


		String  fileName=file.getOriginalFilename();

		System.out.println(fileName);

		// fileName= System.DateTime.Now.ToString("_ddMMyyhhmmss") + file.Extension; 
		String extension = "";
		try { 
		extension=fileName.substring((fileName.lastIndexOf('.')));
		} catch (Exception e) {
			return null;
		}
	/*UUID uuid = UUID.randomUUID();
		
	 String  name = uuid.toString();

		System.out.println("time  String = " +name);*/
		fileName=extension;

		System.out.println(fileName);

		String filePath = fileName;


		 System.out.println(filePath);
		File   storeFile = new File(filePath);
		return storeFile;
	}
	
}
