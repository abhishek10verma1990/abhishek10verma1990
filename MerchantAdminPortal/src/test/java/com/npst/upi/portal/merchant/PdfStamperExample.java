package com.npst.upi.portal.merchant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PdfStamperExample {

	public static void main(String[] args) throws Exception {
		 
        String inputFilePath = "/home/npst/Downloads/jpg2pdf.pdf"; // Existing file
        String outputFilePath = "/home/npst/Downloads/jpg2pdf3.pdf"; // New file
        Image image = Image.getInstance("/home/npst/Downloads/qr.jpg");
        OutputStream fos = new FileOutputStream(new File(outputFilePath));
        PdfReader pdfReader = new PdfReader(inputFilePath);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, fos);
        for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
        	   PdfContentByte pdfContentByte=null;
               pdfContentByte = pdfStamper.getOverContent(i);
               pdfContentByte.beginText();
               pdfContentByte.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_BOLD,BaseFont.CP1257,BaseFont.EMBEDDED),10);
               pdfContentByte.setTextMatrix(100, 340); 
               pdfContentByte.showText("9971345344.timepay@commos");
               pdfContentByte = pdfStamper.getOverContent(i);
               image.setAbsolutePosition(80f, 170f);
               pdfContentByte.addImage(image);
               BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.WINANSI, BaseFont.EMBEDDED);
               pdfContentByte.setFontAndSize(bf, 18);
               pdfContentByte.showTextAligned(PdfContentByte.ALIGN_LEFT,"Page No: " + i,430,15,0);
               pdfContentByte.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1257, BaseFont.EMBEDDED),10); 
               pdfContentByte.setTextMatrix(130, 150); 
               pdfContentByte.showText("Shahane Jawellers");
               pdfContentByte.setTextMatrix(140, 170);
               pdfContentByte.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1257, BaseFont.EMBEDDED),10); 
               pdfContentByte.showText("9971345344");
               pdfContentByte.endText();
        }
        pdfStamper.close();
}
}
  
  