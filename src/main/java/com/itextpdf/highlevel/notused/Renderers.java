/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.notused;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.renderer.DocumentRenderer;

import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class Renderers {
    public static final String DEST = "results/notused/renderers.pdf";
    public static final String TEXT =
        "This is a long sentence. We will render this paragraph in different "
        + "ways. We'll use the default renderer as well as a column renderer. "
        + "This is some more text to make the paragraph longer. We want it "
        + "to span multiple lines. An example with a short paragraph isn't "
        + "useful for us.";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new Renderers().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        
        // Initialize document
        Document document = new Document(pdf);
        
        Paragraph p = new Paragraph().add(TEXT);
        document.add(p);
        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        
        //Set column parameters
        float offSet = 36;
        float gutter = 23;
        float columnWidth = (PageSize.A4.getWidth() - offSet * 2) / 2 - gutter;
        float columnHeight = PageSize.A4.getHeight() - offSet * 2;

        //Define column areas
        Rectangle[] columns = {
            new Rectangle(offSet, offSet, columnWidth, columnHeight),
            new Rectangle(offSet + columnWidth + gutter, offSet, columnWidth, columnHeight)};
        document.setRenderer(new ColumnDocumentRenderer(document, columns)); 
        document.add(new AreaBreak(AreaBreakType.LAST_PAGE));

        for (int i = 0; i < 10; i++)
            document.add(p);
        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        
        document.setRenderer(new DocumentRenderer(document)); 
        document.add(new AreaBreak(AreaBreakType.LAST_PAGE));
        document.add(p);
        
        //Close document
        document.close();
        
    }

}
