/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter04;

import com.itextpdf.highlevel.util.CsvTo2DList;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C04E03_LineSeparatorExample {
    
    public static final String SRC = "src/main/resources/data/jekyll_hyde.csv";
    public static final String DEST = "results/chapter04/jekyll_hyde_overviewV3.pdf";
       
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C04E03_LineSeparatorExample().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf);
        SolidLine line = new SolidLine(1f);
        line.setColor(Color.RED);
        LineSeparator ls = new LineSeparator(line);
        ls.setWidthPercent(50);
        ls.setMarginTop(5);
        List<List<String>> resultSet = CsvTo2DList.convert(SRC, "|");
        resultSet.remove(0);
        for (List<String> record : resultSet) {
            String url = String.format(
                "http://www.imdb.com/title/tt%s", record.get(0));
            Link movie = new Link(record.get(2), PdfAction.createURI(url));
            Div div = new Div()
                .setKeepTogether(true)
                .setBorderLeft(new SolidBorder(2))
                .setPaddingLeft(3)
                .setMarginBottom(10)
                .add(new Paragraph(movie.setFontSize(14f)))
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
            div.add(ls);
            document.add(div);
        }
        document.close();
    }
    
}
