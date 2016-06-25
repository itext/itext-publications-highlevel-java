/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter03;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class C03E11_MaryReillyV4 {
    
    public static final String MARY = "src/main/resources/img/0117002.jpg";
    public static final String DEST = "results/chapter03/mary_reilly_V4.pdf";
       
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C03E11_MaryReillyV4().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(
            new PdfWriter(new FileOutputStream(dest)));
        Document document = new Document(pdf);
        Paragraph p = new Paragraph(
            "Mary Reilly is a maid in the household of Dr. Jekyll: ");
        document.add(p);
        Image img = new Image(ImageDataFactory.create(MARY), 320, 750, 50);
        document.add(img);
        document.close();
    }
    
}
