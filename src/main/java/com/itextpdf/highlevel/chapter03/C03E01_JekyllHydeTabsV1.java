package com.itextpdf.highlevel.chapter03;

import com.itextpdf.highlevel.util.CsvTo2DList;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class C03E01_JekyllHydeTabsV1 {
    
    public static final String SRC = "src/main/resources/data/jekyll_hyde.csv";
    public static final String DEST = "results/chapter03/jekyll_hyde_tabs1.pdf";
       
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C03E01_JekyllHydeTabsV1().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf, PageSize.A4.rotate());
        
        PdfCanvas pdfCanvas = new PdfCanvas(pdf.addNewPage());
        for (int i = 1; i <= 10; i++) {
            pdfCanvas.moveTo(document.getLeftMargin() + i * 50, 0);
            pdfCanvas.lineTo(document.getLeftMargin() + i * 50, 595);
        }
        pdfCanvas.stroke();
        
        List<List<String>> resultSet = CsvTo2DList.convert(SRC, "|");
        for (List<String> record : resultSet) {
            Paragraph p = new Paragraph();
            p.add(record.get(0).trim()).add(new Tab())
                .add(record.get(1).trim()).add(new Tab())
                .add(record.get(2).trim()).add(new Tab())
                .add(record.get(3).trim()).add(new Tab())
                .add(record.get(4).trim()).add(new Tab())
                .add(record.get(5).trim());
            document.add(p);
        }
        document.close();
    }
}
