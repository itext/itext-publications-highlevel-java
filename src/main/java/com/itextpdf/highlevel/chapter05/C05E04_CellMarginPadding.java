/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter05;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C05E04_CellMarginPadding {
    
    public static final String DEST = "results/chapter05/cell_margin_padding.pdf";
       
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E04_CellMarginPadding().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        
        // Initialize document
        Document document = new Document(pdf);
        Table table = new Table(new float[]{2, 1, 1});
        table.setBackgroundColor(Color.ORANGE);
        table.setWidthPercent(80);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table.addCell(
            new Cell(1, 3).add("Cell with colspan 3")
                .setPadding(10).setMargin(5).setBackgroundColor(Color.GREEN));
        table.addCell(new Cell(2, 1).add("Cell with rowspan 2")
            .setMarginTop(5).setMarginBottom(5).setPaddingLeft(30)
            .setFontColor(Color.WHITE).setBackgroundColor(Color.BLUE));
        table.addCell(new Cell().add("row 1; cell 1")
            .setFontColor(Color.WHITE).setBackgroundColor(Color.RED));
        table.addCell(new Cell().add("row 1; cell 2"));
        table.addCell(new Cell().add("row 2; cell 1").setMargin(10)
            .setFontColor(Color.WHITE).setBackgroundColor(Color.RED));
        table.addCell(new Cell().add("row 2; cell 2").setPadding(10)
            .setFontColor(Color.WHITE).setBackgroundColor(Color.RED));
        document.add(table);
        document.close();
    }
}
