/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 building blocks
 */
package com.itextpdf.highlevel.chapter06;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.test.annotations.WrapToTest;
import java.io.File;
import java.io.IOException;

/**
 * @author iText
 */
@WrapToTest
public class C06E06_JavaScript {
    
    public static final String DEST = "results/chapter06/jekyll_hyde_javascript.pdf";
    
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C06E06_JavaScript().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf);
        Link link = new Link("here",
            PdfAction.createJavaScript("app.alert('Boo!');"));
        Paragraph p = new Paragraph()
            .add("Click ")
            .add(link.setFontColor(Color.BLUE))
            .add(" if you want to be scared.");
        document.add(p);
        document.close();
    }
}
