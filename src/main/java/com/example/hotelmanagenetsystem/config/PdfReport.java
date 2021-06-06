package com.example.hotelmanagenetsystem.config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.hotelmanagenetsystem.model.Bookings;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


@Component
public class PdfReport {
	public static ByteArrayInputStream authorPdfViews(List<Bookings> authors){

	    ByteArrayOutputStream out=new ByteArrayOutputStream();

	    Document document=new Document();


	    try {

	      PdfPTable table = new PdfPTable(5);
	      table.setWidthPercentage(80);
	      table.setWidths(new int[]{1,3,3,3,3});


	      PdfPCell hcell;
	      Font font= FontFactory.getFont(FontFactory.HELVETICA);

	      hcell=new PdfPCell(new Phrase("ID",font));
	      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	      table.addCell(hcell);

	      hcell=new PdfPCell(new Phrase("BookingNumber",font));
	      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	      table.addCell(hcell);


	      hcell=new PdfPCell(new Phrase("Checkin",font));
	      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	      table.addCell(hcell);

	      hcell=new PdfPCell(new Phrase("Checkout",font));
	      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	      table.addCell(hcell);

	      hcell=new PdfPCell(new Phrase("Name",font));
	      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	      table.addCell(hcell);

	      for(Bookings author:authors){
	        PdfPCell cell;

	        cell=new PdfPCell(new Phrase(author.getId().toString()));
	        cell.setPaddingLeft(5);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        table.addCell(cell);

	        cell=new PdfPCell(new Phrase(String.valueOf(author.getBookingNumber())));
	        cell.setPaddingLeft(5);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        table.addCell(cell);


	        cell=new PdfPCell(new Phrase(author.getFromStart()));
	        cell.setPaddingLeft(5);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        table.addCell(cell);


	        cell=new PdfPCell(new Phrase(author.getToEnd()));
	        cell.setPaddingLeft(5);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        table.addCell(cell);


	        cell=new PdfPCell(new Phrase(String.valueOf(author.getUserProfile().getFirstName())));
	        cell.setPaddingLeft(5);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        table.addCell(cell);
	        
	       


	      }

	      PdfWriter.getInstance(document,out);
	      document.open();

	      document.add(table);

	      document.close();


	    }catch (Exception e){
	      e.printStackTrace();
	    }

	    return  new ByteArrayInputStream(out.toByteArray());
	  }
}
