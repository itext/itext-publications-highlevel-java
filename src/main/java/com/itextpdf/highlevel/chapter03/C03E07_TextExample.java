/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter03;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.test.annotations.WrapToTest;

import java.io.File;
import java.io.IOException;

@WrapToTest
public class C03E07_TextExample {
    
    public static final String DEST = "results/chapter03/jekyll_hyde_text.pdf";
       
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C03E07_TextExample().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf);
        Text t1 = new Text("The Strange Case of ");
        Text t2 = new Text("Dr. Jekyll").setTextRise(5);
        Text t3 = new Text(" and ").setHorizontalScaling(2);
        Text t4 = new Text("Mr. Hyde").setSkew(10, 45);
        document.add(new Paragraph(t1).add(t2).add(t3).add(t4));
        document.close();
    }
}
