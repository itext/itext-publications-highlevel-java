/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter03;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author iText
 */
public class C03E14_ImageTypes {
    public static final String TEST1 = "src/main/resources/img/test/amb.jb2";
    public static final String TEST2 = "src/main/resources/img/test/butterfly.bmp";
    public static final String TEST3 = "src/main/resources/img/test/hitchcock.gif";
    public static final String TEST4 = "src/main/resources/img/test/hitchcock.png";
    public static final String TEST5 = "src/main/resources/img/test/info.png";
    public static final String TEST6 = "src/main/resources/img/test/map.jp2";
    public static final String TEST7 = "src/main/resources/img/test/marbles.tif";
    
    public static final String DEST = "results/chapter03/image_types.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C03E14_ImageTypes().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(
            new PdfWriter(new FileOutputStream(dest)));
        Document document = new Document(pdf);
        Image img1 = new Image(ImageDataFactory.create(TEST1));
        document.add(img1);
        Image img2 = new Image(ImageDataFactory.create(TEST2));
        document.add(img2);
        Image img3 = new Image(ImageDataFactory.create(TEST3));
        img3.setBackgroundColor(Color.LIGHT_GRAY);
        document.add(img3);
        Image img4 = new Image(ImageDataFactory.create(TEST4));
        document.add(img4);
        Image img5 = new Image(ImageDataFactory.create(TEST5));
        img5.setBorderLeft(new SolidBorder(2));
        document.add(img5);
        Image img6 = new Image(ImageDataFactory.create(TEST6));
        document.add(img6);
        Image img7 = new Image(ImageDataFactory.create(TEST7));
        document.add(img7);
        document.close();
    }
}
