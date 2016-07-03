/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter05;

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
public class C05E03_CellAlignment {
    
    public static final String DEST = "results/chapter05/cell_alignment.pdf";
       
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E03_CellAlignment().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        
        // Initialize document
        Document document = new Document(pdf);
        Table table = new Table(new float[]{2, 1, 1});
        table.setWidthPercent(80);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table.setTextAlignment(TextAlignment.CENTER);
        table.addCell(new Cell(1, 3).add("Cell with colspan 3"));
        table.addCell(new Cell(2, 1).add("Cell with rowspan 2")
            .setTextAlignment(TextAlignment.RIGHT));
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        Cell cell = new Cell()
            .add(new Paragraph("Left").setTextAlignment(TextAlignment.LEFT))
            .add(new Paragraph("Center"))
            .add(new Paragraph("Right").setTextAlignment(TextAlignment.RIGHT));
        table.addCell(cell);
        cell = new Cell().add("Middle")
            .setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        cell = new Cell().add("Bottom")
            .setVerticalAlignment(VerticalAlignment.BOTTOM);
        table.addCell(cell);
        document.add(table);
        document.close();
    }
}
