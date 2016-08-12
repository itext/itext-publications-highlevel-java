/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 building blocks
 */
package com.itextpdf.highlevel.chapter05;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C05E16_LargeTable {
    
    public static final String DEST = "results/chapter05/large_table.pdf";
       
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E16_LargeTable().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        
        // Initialize document
        Document document = new Document(pdf);
        Table table = new Table(3, true);
        table.addHeaderCell("Table header 1");
        table.addHeaderCell("Table header 2");
        table.addHeaderCell("Table header 3");
        table.addFooterCell("Table footer 1");
        table.addFooterCell("Table footer 2");
        table.addFooterCell("Table footer 3");
        document.add(table);
        for (int i = 0; i < 1000; i++) {
            table.addCell(String.format("Row %s; column 1", i + 1));
            table.addCell(String.format("Row %s; column 2", i + 1));
            table.addCell(String.format("Row %s; column 3", i + 1));
            if (i %50 == 0) {
                table.flush();
            }
        }
        table.complete();
        document.close();
    }
}
