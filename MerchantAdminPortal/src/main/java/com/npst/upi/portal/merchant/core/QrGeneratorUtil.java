/**
 * 
 */
package com.npst.upi.portal.merchant.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.border.Border;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.npst.upi.portal.merchant.entity.Merchants;
import com.npst.upi.portal.merchant.utility.Utility;
import com.npst.upi.portal.merchant.constant.Constants;;

@Component
public class QrGeneratorUtil {
	public static String BANK_LOGO=Utility.getProperty("BANK_LOGO");
	public static String BHIM_LOGO=Utility.getProperty("BHIM_LOGO");
	public static String COSMOS_LOGO=Utility.getProperty("COSMOS_LOGO");
	public static String FILE_NAME=Utility.getProperty("FILE_NAME");
	public static String SCAN_PAY=Utility.getProperty("SCAN_PAY");
	public static String TIMEPAY=Utility.getProperty("TIMEPAY");
	public static String FILE_PATH=Utility.getProperty("FILE_PATH");

	public static String PDF_FILE_PATH=Utility.getProperty("PDF_FILE_PATH");

	@Value("${qrcode.width:175}")
	private String qrwidth;

	@Value("${qrcode.height:175}")
	private String qrheight;

	@Value("${qrcode.bbcode.font.size:30}")
	private String qrfontsize;

	public void generateQr(Merchants merchant, final String qrCodeData, final String filePath, final String fileName)
			throws Exception {
		
		
		Document document = null;
		try {
/*			BarcodeQRCode barcodeQRCode = new BarcodeQRCode(qrCodeData,Integer.valueOf(qrwidth),Integer.valueOf(qrheight), null);
			 Image codeQrImage = barcodeQRCode.getImage();
			 Image  image3= Image.getInstance(BHIM_LOGO);
			 Image  image4= Image.getInstance(SCAN_PAY);
			 Image  image2= Image.getInstance(BANK_LOGO);
			 Image  image5= Image.getInstance(COSMOS_LOGO);
			 Image  image6= Image.getInstance(TIMEPAY);
			 Font font = FontFactory.getFont(FontFactory.TIMES_BOLD,7,BaseColor.BLACK);
			 Paragraph pera=new Paragraph(merchant.getMerchantVPA(),font);
		     Paragraph pera2= new Paragraph(merchant.getMerchantOrgName()+"\n");
			 Paragraph pera4=new Paragraph(merchant.getMerchantMobileNo()+"\n");
		     Paragraph pera3= new Paragraph(merchant.getMerchantVPA()+"\n", font2);
			 PdfPTable table = new PdfPTable(1);
			 table.setHorizontalAlignment(Element.ALIGN_CENTER);
			 table.setTotalWidth(152);
		     table.setLockedWidth(true);
		      pera.setAlignment(Element.ALIGN_CENTER);
		      pera2.setAlignment(Element.ALIGN_CENTER);
		      pera4.setAlignment(Element.ALIGN_CENTER);
		      PdfPCell cell = new PdfPCell(codeQrImage, true);
		      PdfPCell cell2 = new PdfPCell(image2, true);
		      PdfPCell cell3 = new PdfPCell(image3, true);
		      PdfPCell cellcos = new PdfPCell(image4, true);
		      PdfPCell cellbank = new PdfPCell(image5, true);
		      PdfPCell celltimepay = new PdfPCell(image6, true);
		      PdfPCell cell4 = new PdfPCell();
		      cell4.addElement(pera);
		      PdfPCell cell6 = new PdfPCell();
		      cell6.addElement(pera2);
		      PdfPCell cell8 = new PdfPCell();
		      cell8.addElement(pera4);
		      cell.setBorder(Rectangle.NO_BORDER);
		      cell2.setBorder(Rectangle.NO_BORDER);
		      cell3.setBorder(Rectangle.NO_BORDER);
		      cell4.setBorder(Rectangle.NO_BORDER);
		      cell6.setBorder(Rectangle.NO_BORDER);
		      cell8.setBorder(Rectangle.NO_BORDER);
		      cellcos.setBorder(Rectangle.NO_BORDER);
		      cellbank.setBorder(Rectangle.NO_BORDER);
		      celltimepay.setBorder(Rectangle.NO_BORDER);
		      table.addCell(cell3);
		      table.addCell(cellbank);
		      table.addCell(cell4);
		      //table.addCell(cell5);
		      table.addCell(cell);
		      table.addCell(cell6);
		      table.addCell(cell8);
		      table.addCell(cellcos);
		      table.addCell(cell2);
		      table.addCell(celltimepay);

		        Rectangle layout = new Rectangle(PageSize.A6);
			    layout.setBackgroundColor(new BaseColor(255,255,255)); //Background color
			    layout.setBorderColor(new BaseColor(68,114,196));  //Border color
			  //  layout.setBorderWidth(20);  
			    layout.setBorderWidthTop(60);
			    layout.setBorderWidthBottom(60);
			    layout.setBorderWidthLeft(20);
			    layout.setBorderWidthRight(20);
			    //layout.TITLE

			    //Border width  
			    
			    layout.setBorder(Rectangle.BOX);
				document= new Document(layout);

		      PdfWriter.getInstance(document, new FileOutputStream(new File(filePath, fileName)));
		      document.open();

		      document.add(table);
		      document.close();*/
			String inputFilePath = PDF_FILE_PATH; // Existing file
	        String outputFilePath = filePath+"/"+fileName; // New file
	        //Image image = Image.getInstance("/home/npst/Downloads/qr.jpg");
	        BarcodeQRCode barcodeQRCode = new BarcodeQRCode(qrCodeData,175,175, null);
			Image image = barcodeQRCode.getImage();
	        OutputStream fos = new FileOutputStream(new File(outputFilePath));
	        PdfReader pdfReader = new PdfReader(inputFilePath);
	        PdfStamper pdfStamper = new PdfStamper(pdfReader, fos);
	        for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
	        	   PdfContentByte pdfContentByte=null;
	        	   
	        	   
	               pdfContentByte = pdfStamper.getOverContent(i);
	               pdfContentByte.beginText();
	           
	               pdfContentByte = pdfStamper.getOverContent(i);
	               image.setAbsolutePosition(56f, 160f);
	               pdfContentByte.addImage(image);
	               
	               
	    
	               BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.WINANSI, BaseFont.EMBEDDED);
	               pdfContentByte.setFontAndSize(bf, 18);
	               pdfContentByte.showTextAligned(PdfContentByte.ALIGN_CENTER,"Page No: " + i,430,15,0);
	               
	               pdfContentByte.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_BOLD,BaseFont.CP1257,BaseFont.EMBEDDED),13);
	               pdfContentByte.setTextMatrix(65, 330); 
	               pdfContentByte.showText(merchant.getMerchantVPA());
	               
	               
	               pdfContentByte.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1257, BaseFont.EMBEDDED),10); 
	              
	               
	               if(merchant.getMerchantOrgName().length()>=17) {
	            	   pdfContentByte.showText(merchant.getMerchantOrgName().substring(0, 15));
	            	   pdfContentByte.setTextMatrix(120, 180);
	               }
	               else {
	            	   
	            	   if(merchant.getMerchantOrgName().length()==15||merchant.getMerchantOrgName().length()==14||merchant.getMerchantOrgName().length()==16) {
	            		   
	            		   pdfContentByte.setTextMatrix(100, 140);
			               pdfContentByte.showText(merchant.getMerchantOrgName());
			               
	            	   }
	            	   
	            	   else if(merchant.getMerchantOrgName().length()==13||merchant.getMerchantOrgName().length()==12) {
	            		   pdfContentByte.setTextMatrix(102, 140);
			               pdfContentByte.showText(merchant.getMerchantOrgName());   
	            	   }
	            	   
	            	   else if(merchant.getMerchantOrgName().length()==11||merchant.getMerchantOrgName().length()==10) {
	            		   pdfContentByte.setTextMatrix(108, 140);
			               pdfContentByte.showText(merchant.getMerchantOrgName());   
	            	   }
	            	   
	            	   else if(merchant.getMerchantOrgName().length()==9||merchant.getMerchantOrgName().length()==8) {
	            		   pdfContentByte.setTextMatrix(112, 140);
			               pdfContentByte.showText(merchant.getMerchantOrgName());   
	            	   }
	            	   
	            	   else if(merchant.getMerchantOrgName().length()==7||merchant.getMerchantOrgName().length()==6) {
	            		   pdfContentByte.setTextMatrix(114, 140);
			               pdfContentByte.showText(merchant.getMerchantOrgName());   
	            	   }
	            	   
	            	   else if(merchant.getMerchantOrgName().length()==5||merchant.getMerchantOrgName().length()==4) {
	            		   pdfContentByte.setTextMatrix(120, 140);
			               pdfContentByte.showText(merchant.getMerchantOrgName());   
	            	   }
	            	   
	            	   else if(merchant.getMerchantOrgName().length()==3||merchant.getMerchantOrgName().length()==2) {
	            		   pdfContentByte.setTextMatrix(126, 140);
			               pdfContentByte.showText(merchant.getMerchantOrgName());   
	            	   }
	            	   
		            	
	               }
	               pdfContentByte.setTextMatrix(162, 190);
	               pdfContentByte.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1257, BaseFont.EMBEDDED),10); 
	               pdfContentByte.showText(merchant.getMerchantMobileNo());
	               pdfContentByte.endText();
	        }
	        pdfStamper.close();
		  
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (document != null)
				document.close();
		}

	}

/*	public String generateFilePath(String branchcode, String qrtype) throws Exception {
		StringBuilder builder = new StringBuilder(FILE_PATH);	
		builder.append("/");
		builder.append(branchcode);
		builder.append("/");
		builder.append(qrtype);
		Path path = Paths.get(builder.toString());
		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}
		return path.toString();
	}*/
	
	public String generateFilePath(Merchants merchant) throws Exception {
		StringBuilder builder = new StringBuilder(FILE_PATH);
		builder.append("/");
		builder.append(merchant.getBranchCode());
		builder.append("/");
		builder.append("UQR");
		builder.append("/");
		builder.append(merchant.getBranchCode()+"_"+merchant.getMerchantAccountNo());
		Path path = Paths.get(builder.toString());
		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}
		return path.toString();
	}

	public String generateFileName(final Merchants dto) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append(dto.getBranchCode());
		builder.append("_");
		builder.append(dto.getMerchantAccountNo());
		builder.append(Constants.FILE_TYPE);
		return builder.toString();
	}
	
	

}
