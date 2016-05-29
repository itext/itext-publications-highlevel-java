/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter02;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class CanvasExample {
    
    public static final String DEST = "results/chapter02/canvas_example.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CanvasExample().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF writer
        FileOutputStream fos = new FileOutputStream(dest);
        PdfWriter writer = new PdfWriter(fos);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
        
        PdfPage page1 = pdf.addNewPage();
        PdfCanvas pdfCanvas1 = new PdfCanvas(page1);
        Rectangle rectangle = new Rectangle(36, 700, 100, 100);
        pdfCanvas1.rectangle(rectangle);
        pdfCanvas1.stroke();
        Canvas canvas1 = new Canvas(pdfCanvas1, pdf, rectangle);
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
        Text title = new Text("The Strange Case of Dr. Jekyll and Mr. Hyde").setFont(bold);
        Text author = new Text("Robert Louis Stevenson").setFont(font);
        Paragraph p = new Paragraph().add(title).add(" by ").add(author);
        canvas1.add(p);
        
        PdfPage page2 = pdf.addNewPage();
        PdfCanvas pdfCanvas2 = new PdfCanvas(page2);
        rectangle = new Rectangle(100, 700, 100, 100);
        Canvas canvas2 = new Canvas(pdfCanvas2, pdf, rectangle);
        canvas2.add(new Paragraph("Dr. Jekyll and Mr. Hyde"));
        
        PdfPage page = pdf.getFirstPage();
        PdfCanvas pdfCanvas = new PdfCanvas(
            page.newContentStreamBefore(), page.getResources(), pdf);
        rectangle = new Rectangle(50, 710, 100, 100);
        pdfCanvas.saveState()
                .setFillColor(Color.CYAN)
                .rectangle(rectangle)
                .fill()
                .restoreState();
        Canvas canvas = new Canvas(pdfCanvas, pdf, rectangle);
        canvas.add(new Paragraph("Dr. Jekyll and Mr. Hyde"));
        
        //Close document
        pdf.close();
    }
}
