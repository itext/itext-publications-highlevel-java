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
import com.itextpdf.layout.border.DottedBorder;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author iText
 */
public class TableProperties {
    
    public static final String DEST = "results/appendix/table_properties.pdf";
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TableProperties().createPdf(DEST);
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
        document.add(createNewTable().addStyle(style).setWidth(300).setHorizontalAlignment(HorizontalAlignment.CENTER)).setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(createNewTable().setBorder(new DottedBorder(5)).setHyphenation(new HyphenationConfig("en", "uk", 3, 3)));
        document.add(createNewTable().setRotationAngle(30));
        document.add(createNewTable());
        document.close();
    }
    
    public Table createNewTable() {
        Table table = new Table(2);
        table.addCell("test1");
        table.addCell("test2");
        table.addCell("test3");
        table.addCell("This is a long text snippet that "
                + "will be used and reused to test paragraph "
                + "properties. This paragraph should take "
                + "more than one line. We'll change different "
                + "properties and then look at the effect "
                + "when we add the paragraph to the document.");
        return table;
    }
}
