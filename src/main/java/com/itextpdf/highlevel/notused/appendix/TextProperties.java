/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 building blocks
 */
package com.itextpdf.highlevel.notused.appendix;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class TextProperties {
    
    public static final String DEST = "results/appendix/text_properties.pdf";
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TextProperties().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);
        Style style = new Style().setBold().setDestination("Top");
        Text t1 = new Text("Test").addStyle(style);
        document.add(new Paragraph(t1));
        Text t2 = new Text("Test").setBorder(new SolidBorder(0.5f));
        document.add(new Paragraph(t2));
        Text t3 = new Text("Test").setBorderLeft(new SolidBorder(0.5f)).setBackgroundColor(Color.LIGHT_GRAY);
        document.add(new Paragraph(t3));
        Text t4 = new Text("AWAY AGAIN").setCharacterSpacing(10);
        document.add(new Paragraph(t4));
        Text t5 = new Text("AWAY AGAIN").setWordSpacing(10);
        document.add(new Paragraph(t5));
        Text t6 = new Text("AWAY AGAIN").setRelativePosition(-10, 50, 0, 0);
        document.add(new Paragraph(t6));
        PdfAction top = PdfAction.createGoTo("Top");
        Text t7 = new Text("go to top").setAction(top);
        document.add(new Paragraph(t7));
        document.close();
    }
}
