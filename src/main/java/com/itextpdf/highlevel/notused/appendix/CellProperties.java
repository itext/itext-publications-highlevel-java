/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.notused.appendix;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.border.DottedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.File;
import java.io.IOException;

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
        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);
        Table table = new Table(2);
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        table.addCell(new Cell().add("Test 1").setHeight(50).setDestination("Top"));
        Style style = new Style();
        style.setBackgroundColor(Color.YELLOW);
        table.addCell(new Cell().setBorder(new DottedBorder(5)).add("Test 2").addStyle(style).setRelativePosition(10, 10, 50, 10));
        table.addCell(new Cell().add("Test 3").setVerticalAlignment(VerticalAlignment.BOTTOM));
        table.addCell(new Cell().add(ParagraphProperties.getNewParagraphInstance()).setHyphenation(new HyphenationConfig("en", "uk", 3, 3)));
        table.addCell(new Cell().add("Rotated").setRotationAngle(Math.PI / 18).setFont(font).setFontSize(8).setFontColor(Color.RED));
        table.addCell(new Cell().add("Centered").setTextAlignment(TextAlignment.CENTER).setAction(PdfAction.createGoTo("Top")));
        table.addCell(new Cell().add("Test 5").setBackgroundColor(Color.BLUE));
        table.addCell(new Cell().add(ParagraphProperties.getNewParagraphInstance()).setBackgroundColor(Color.RED).setPaddingLeft(20).setPaddingRight(50));
        table.addCell(new Cell().add("Test 7").setBackgroundColor(Color.RED));
        table.addCell(new Cell().add("Test 8").setBackgroundColor(Color.BLUE).setMarginBottom(10));
        table.addCell(new Cell().add("Test 9").setBackgroundColor(Color.BLUE));
        table.addCell(new Cell().add("Test 10").setBackgroundColor(Color.RED));
        table.addCell(new Cell().add(ParagraphProperties.getNewParagraphInstance()).setBackgroundColor(Color.RED).setMargin(50).setPadding(30));
        table.addCell(new Cell().add("Test 12").setBackgroundColor(Color.BLUE));
        
        document.add(table);
        SolidBorder border = new SolidBorder(Color.RED, 2);
        Cell cell = new Cell().add("Test").setFixedPosition(100, 400, 350).setBorder(border).setBackgroundColor(Color.BLUE).setHeight(100).setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(cell);
        document.close();
    }
}
