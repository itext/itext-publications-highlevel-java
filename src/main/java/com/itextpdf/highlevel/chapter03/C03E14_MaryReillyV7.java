/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter03;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.property.UnitValue;
import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C03E14_MaryReillyV7 {
    public static final String SRC = "src/main/resources/pdfs/jekyll_hyde.pdf";
    public static final String MARY = "src/main/resources/img/0117002.jpg";
    public static final String DEST = "results/chapter03/mary_reilly_V7.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C03E14_MaryReillyV7().manipulatePdf(SRC, DEST);
    }
    
    public void manipulatePdf(String src, String dest) throws IOException {
        PdfReader reader = new PdfReader(src);
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdfDoc = new PdfDocument(reader, writer);
        Document document = new Document(pdfDoc);
        Image img = new Image(ImageDataFactory.create(MARY));
        img.setFixedPosition(1, 350, 750, UnitValue.createPointValue(50));
        document.add(img);
        document.close();
    }
}
