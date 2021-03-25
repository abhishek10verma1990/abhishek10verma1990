package com.npst.upi.portal.merchant;



	import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
	import com.itextpdf.text.Image;
	import com.itextpdf.text.pdf.PdfWriter;
	public class AddImageAbsolutePosition {
	    public static void main(String[] args) throws DocumentException, MalformedURLException, IOException {
		Document document = new Document();
	        PdfWriter.getInstance(document, new FileOutputStream("/home/npst/Downloads/jpg2pdf.pdf"));
	        document.open();
	        Image img = Image.getInstance("/home/npst/Downloads/images.png");
	        img.setAbsolutePosition(.5f, .25f);
	        document.add(img);
	        document.close();
	        System.out.println("Done");
	    }
}
