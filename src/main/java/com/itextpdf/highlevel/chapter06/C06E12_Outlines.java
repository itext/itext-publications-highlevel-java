/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 building blocks
 */
package com.itextpdf.highlevel.chapter06;

import com.itextpdf.highlevel.util.CsvTo2DList;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfOutline;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.test.annotations.WrapToTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class C06E12_Outlines {
    
    public static final String SRC = "src/main/resources/data/jekyll_hyde.csv";
    public static final String DEST = "results/chapter06/jekyll_hyde_outlines.pdf";
       
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C06E12_Outlines().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        pdf.addNewPage();
        pdf.getCatalog().setPageMode(PdfName.UseOutlines);
        PdfOutline root = pdf.getOutlines(false);
        List<List<String>> resultSet = CsvTo2DList.convert(SRC, "|");
        resultSet.remove(0);
        for (List<String> record : resultSet) {
            PdfOutline movie = root.addOutline(record.get(2));
            PdfOutline imdb = movie.addOutline("Link to IMDB");
            imdb.setColor(Color.BLUE);
            imdb.setStyle(PdfOutline.FLAG_BOLD);
            String url = String.format(
                "http://www.imdb.com/title/tt%s", record.get(0));
            imdb.addAction(PdfAction.createURI(url));
            PdfOutline info = movie.addOutline("More info:");
            info.setOpen(false);
            info.setStyle(PdfOutline.FLAG_ITALIC);
            PdfOutline director = info.addOutline("Directed by " + record.get(3));
            director.setColor(Color.RED);
            PdfOutline place = info.addOutline("Produced in " + record.get(4));
            place.setColor(Color.MAGENTA);
            PdfOutline year = info.addOutline("Released in " + record.get(1));
            year.setColor(Color.DARK_GRAY);
        }
        pdf.close();
    }
    
}
