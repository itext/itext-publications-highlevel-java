package com.itextpdf.highlevel.chapter05;

import com.itextpdf.highlevel.util.CsvTo2DList;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author iText
 */
public class C05E09_JekyllHydeTableV2 {
    
    public static final String SRC = "src/main/resources/data/jekyll_hyde.csv";
    public static final String DEST = "results/chapter05/jekyll_hyde_table2.pdf";
       
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E09_JekyllHydeTableV2().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf, PageSize.A4.rotate());
        Table table = new Table(UnitValue.createPercentArray(new float[]{3, 2, 14, 9, 4, 3}));
        table.setWidth(UnitValue.createPercentValue(100));
        List<List<String>> resultSet = CsvTo2DList.convert(SRC, "|");
        List<String> header = resultSet.remove(0);
        for (String field : header) {
            table.addHeaderCell(field);
        }
        for (List<String> record : resultSet) {
            for (String field : record) {
                table.addCell(field);
            }
        }
        Table outerTable = new Table(new float[]{1})
            .addHeaderCell("Continued from previous page:")
            .setSkipFirstHeader(true)
            .addCell(new Cell().add(table).setPadding(0));
        document.add(outerTable);
        document.close();
    }
}
