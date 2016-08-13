/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 building blocks
 */
package com.itextpdf.highlevel.chapter05;

import com.itextpdf.highlevel.util.CsvTo2DList;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.test.annotations.WrapToTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author iText
 */
@WrapToTest
public class C05E11_JekyllHydeTableV4 {
    
    public static final String SRC = "src/main/resources/data/jekyll_hyde.csv";
    public static final String DEST = "results/chapter05/jekyll_hyde_table4.pdf";
       
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E11_JekyllHydeTableV4().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        
        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        Table table = new Table(new float[]{3, 32});
        table.setWidthPercent(100);
        List<List<String>> resultSet = CsvTo2DList.convert(SRC, "|");
        resultSet.remove(0);
        table.addHeaderCell("imdb")
            .addHeaderCell("Information about the movie");
        Cell cell;
        for (List<String> record : resultSet) {
            table.addCell(record.get(0));
            cell = new Cell()
                .add(new Paragraph(record.get(1)))
                .add(new Paragraph(record.get(2)))
                .add(new Paragraph(record.get(3)))
                .add(new Paragraph(record.get(4)))
                .add(new Paragraph(record.get(5)));
            table.addCell(cell);
        }
        document.add(table);
        document.close();
    }
}
