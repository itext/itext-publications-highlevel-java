package com.itextpdf.highlevel.chapter05;

import com.itextpdf.highlevel.util.CsvTo2DList;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author iText
 */
public class C05E08_JekyllHydeTableV1 {
    
    public static final String SRC = "src/main/resources/data/jekyll_hyde.csv";
    public static final String DEST = "results/chapter05/jekyll_hyde_table1.pdf";
       
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E08_JekyllHydeTableV1().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        
        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        Table table = new Table(UnitValue.createPercentArray(new float[]{3, 2, 14, 9, 4, 3}));
        table.setWidth(UnitValue.createPercentValue(100));
        List<List<String>> resultSet = CsvTo2DList.convert(SRC, "|");
        List<String> header = resultSet.remove(0);
        for (String field : header) {
            table.addHeaderCell(field);
        }
        Cell cell = new Cell(1, 6).add(new Paragraph("Continued on next page..."));
        table.addFooterCell(cell)
            .setSkipLastFooter(true);
        for (List<String> record : resultSet) {
            for (String field : record) {
                table.addCell(field);
            }
        }
        document.add(table);
        document.close();
    }
}
