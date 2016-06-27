/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.appendix;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.border.DottedBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author iText
 */
public class CellProperties {
    
    public static final String DEST = "results/appendix/cell_properties.pdf";
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CellProperties().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        // Initialize PDF writer and PDF document
        OutputStream fos = new FileOutputStream(dest);
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdf = new PdfDocument(writer);
        
        // Initialize document
        Document document = new Document(pdf);
        Table table = new Table(2);
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        table.addCell(new Cell().add("Test 1").setHeight(50).setWidthPercent(20));
        Style style = new Style();
        style.setBackgroundColor(Color.YELLOW);
        table.addCell(new Cell().setBorder(new DottedBorder(5)).add("Test 2").addStyle(style));
        table.addCell(new Cell().add("Test 3").setVerticalAlignment(VerticalAlignment.BOTTOM));
        table.addCell(new Cell().add(ParagraphProperties.getNewParagraphInstance()).setHyphenation(new HyphenationConfig("en", "uk", 3, 3)));
        table.addCell(new Cell().add("Rotated").setRotationAngle(Math.PI / 18).setFont(font).setFontSize(8).setFontColor(Color.RED));
        table.addCell(new Cell().add("Centered").setTextAlignment(TextAlignment.CENTER));
        document.add(table);
        document.close();
    }
}
