/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter01;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.test.annotations.WrapToTest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class C01E09_ColorRendering {
    
    public static final String DEST = "results/chapter01/color_rendermode.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C01E09_ColorRendering().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        // Initialize PDF writer and PDF document
        OutputStream fos = new FileOutputStream(dest);
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdf = new PdfDocument(writer);
        
        // Initialize document
        Document document = new Document(pdf);
        
        // Add content
        Text title1 = new Text("The Strange Case of ").setFontColor(Color.BLUE);
        Text title2 = new Text("Dr. Jekyll")
                .setStrokeColor(Color.GREEN)
                .setTextRenderingMode(PdfCanvasConstants.TextRenderingMode.FILL_STROKE);
        Text title3 = new Text(" and ");
        Text title4 = new Text("Mr. Hyde")
                .setStrokeColor(Color.RED).setStrokeWidth(0.5f)
                .setTextRenderingMode(PdfCanvasConstants.TextRenderingMode.STROKE);
        Paragraph p = new Paragraph().setFontSize(24)
                .add(title1).add(title2).add(title3).add(title4);
        document.add(p);
        
        //Close document
        document.close();
    }
}
