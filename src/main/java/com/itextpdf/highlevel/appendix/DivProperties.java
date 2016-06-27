/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.appendix;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.property.HorizontalAlignment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author iText
 */
public class DivProperties {
    
    public static final String DEST = "results/appendix/div_properties.pdf";
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new DivProperties().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        // Initialize PDF writer and PDF document
        OutputStream fos = new FileOutputStream(dest);
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdf = new PdfDocument(writer);
        
        // Initialize document
        Document document = new Document(pdf);
        Style style = new Style();
        style.setBackgroundColor(Color.YELLOW).setBorder(new SolidBorder(0.5f));
        document.add(createNewDiv().addStyle(style).setWidth(350).setHorizontalAlignment(HorizontalAlignment.CENTER));
        document.add(createNewDiv().setWidth(350).setHyphenation(new HyphenationConfig("en", "uk", 3, 3)));
        document.close();
    }
    
    public static Div createNewDiv() {
        Div div = new Div();
        div.add(ParagraphProperties.getNewParagraphInstance());
        div.add(ListSeparatorProperties.createNewSeparator());
        div.add(ListProperties.createNewList());
        return div;
    }
}
