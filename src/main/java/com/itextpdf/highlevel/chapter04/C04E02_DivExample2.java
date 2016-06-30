/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter04;

import com.itextpdf.highlevel.util.CsvTo2DList;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C04E02_DivExample2 {
    
    public static final String SRC = "src/main/resources/data/jekyll_hyde.csv";
    public static final String DEST = "results/chapter04/jekyll_hyde_overviewV2.pdf";
       
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C04E02_DivExample2().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf);
        List<List<String>> resultSet = CsvTo2DList.convert(SRC, "|");
        resultSet.remove(0);
        for (List<String> record : resultSet) {
            Div div = new Div().setKeepTogether(true);
            String url = String.format(
                "http://www.imdb.com/title/tt%s", record.get(0));
            Link movie = new Link(record.get(2), PdfAction.createURI(url));
            div.add(new Paragraph(movie.setFontSize(14)))
                .add(new Paragraph(String.format(
                    "Directed by %s (%s, %s)",
                    record.get(3), record.get(4), record.get(1))));
            File file = new File(String.format(
                "src/main/resources/img/%s.jpg", record.get(0)));
            if (file.exists()) {
                Image img = new Image(ImageDataFactory.create(file.getPath()));
                img.scaleToFit(10000, 120);
                div.add(img);
            }
            document.add(div);
        }
        document.close();
    }
    
}
