/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 building blocks
 */
package com.itextpdf.highlevel.chapter05;

import com.itextpdf.highlevel.util.CsvTo2DList;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.test.annotations.WrapToTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author iText
 */
@WrapToTest
public class C05E10_JekyllHydeTableV3 {
    
    public static final String SRC = "src/main/resources/data/jekyll_hyde.csv";
    public static final String DEST = "results/chapter05/jekyll_hyde_table3.pdf";
       
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E10_JekyllHydeTableV3().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        
        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        Table table = new Table(new float[]{3, 2, 14, 9, 4, 3});
        table.setWidthPercent(100);
        List<List<String>> resultSet = CsvTo2DList.convert(SRC, "|");
        List<String> header = resultSet.remove(0);
        for (String field : header) {
            table.addHeaderCell(field);
        }
        Cell cell;
        for (List<String> record : resultSet) {
            cell = new Cell();
            File file = new File(String.format(
                "src/main/resources/img/%s.jpg", record.get(0)));
            if (file.exists()) {
                Image img = new Image(ImageDataFactory.create(file.getPath()));
                img.setAutoScaleWidth(true);
                cell.add(img);
            }
            else {
                cell.add(record.get(0));
            }
            table.addCell(cell);
            table.addCell(record.get(1));
            table.addCell(record.get(2));
            table.addCell(record.get(3));
            table.addCell(record.get(4));
            table.addCell(record.get(5));
        }
        document.add(table);
        document.close();
    }
}
