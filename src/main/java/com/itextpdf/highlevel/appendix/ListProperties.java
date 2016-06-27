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
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.property.HorizontalAlignment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author iText
 */
public class ListProperties {
    
    public static final String DEST = "results/appendix/list_properties.pdf";
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ListProperties().createPdf(DEST);
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
        document.add(createNewList().addStyle(style).setWidth(300).setHorizontalAlignment(HorizontalAlignment.CENTER));
        document.add(createNewList().setHyphenation(new HyphenationConfig("en", "uk", 3, 3)).setBorder(new SolidBorder(0.5f)));
        document.close();
    }
    
    public static List createNewList() {
        List list = new List();
        list.add("item 1");
        list.add("item 2");
        list.add("item 3");
        list.add("This is a long text snippet that "
                + "will be used and reused to test paragraph "
                + "properties. This paragraph should take "
                + "more than one line. We'll change different "
                + "properties and then look at the effect "
                + "when we add the paragraph to the document.");
        return list;
    }
}
