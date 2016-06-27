/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.appendix;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author iText
 */
public class ListSeparatorProperties {
    
    public static final String DEST = "results/appendix/separator_properties.pdf";
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ListSeparatorProperties().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        // Initialize PDF writer and PDF document
        OutputStream fos = new FileOutputStream(dest);
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdf = new PdfDocument(writer);
        
        // Initialize document
        Document document = new Document(pdf);
        Style style = new Style();
        style.setBackgroundColor(Color.YELLOW);
        document.add(createNewSeparator().addStyle(style));
        document.add(new Paragraph("test"));
        document.add(createNewSeparator().setWidth(300).setHorizontalAlignment(HorizontalAlignment.CENTER));
        document.add(createNewSeparator().setMargin(10).setVerticalAlignment(VerticalAlignment.BOTTOM).setBorder(new SolidBorder(0.5f)));
        document.add(createNewSeparator().setMargin(10).setWidth(300));
        document.add(createNewSeparator().setMargin(10));
        document.add(createNewSeparator().setMargin(10).setWidthPercent(50));
        document.add(createNewSeparator().setMargin(10).setWidth(50));
        document.close();
    }
    
    public static LineSeparator createNewSeparator() {
        return new LineSeparator(new DottedLine());
    }
}
