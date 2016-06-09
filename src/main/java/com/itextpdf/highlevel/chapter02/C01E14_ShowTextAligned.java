/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter02;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author iText
 */
public class C01E14_ShowTextAligned {
    public static final String DEST = "results/chapter02/showtextaligned.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C01E14_ShowTextAligned().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF writer
        OutputStream fos = new FileOutputStream(dest);
        PdfWriter writer = new PdfWriter(fos);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
        
        // Initialize document
        Document document = new Document(pdf);
        Paragraph title = new Paragraph("The Strange Case of Dr. Jekyll and Mr. Hyde");
        document.showTextAligned(title, 36, 806, TextAlignment.LEFT);
        Paragraph author = new Paragraph("by Robert Louis Stevenson");
        document.showTextAligned(author, 36, 806, TextAlignment.LEFT, VerticalAlignment.TOP);
        document.showTextAligned("Jekyll", 300, 800, TextAlignment.CENTER, 0.5f * (float)Math.PI);
        document.showTextAligned("Hyde", 300, 800, TextAlignment.CENTER, -0.5f * (float)Math.PI);
        document.showTextAligned("Jekyll", 350, 800, TextAlignment.CENTER, VerticalAlignment.TOP, 0.5f * (float)Math.PI);
        document.showTextAligned("Hyde", 350, 800, TextAlignment.CENTER, VerticalAlignment.TOP, -0.5f * (float)Math.PI);
        document.showTextAligned("Jekyll", 400, 800, TextAlignment.CENTER, VerticalAlignment.MIDDLE, 0.5f * (float)Math.PI);
        document.showTextAligned("Hyde", 400, 800, TextAlignment.CENTER, VerticalAlignment.MIDDLE, -0.5f * (float)Math.PI);
        
        document.close();
    }
}
