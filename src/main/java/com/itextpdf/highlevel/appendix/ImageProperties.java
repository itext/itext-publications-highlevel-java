/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.appendix;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class ImageProperties {
    
    public static final String TEST1 = "src/main/resources/img/test/amb.jb2";
    public static final String TEST2 = "src/main/resources/img/test/butterfly.bmp";
    public static final String TEST3 = "src/main/resources/img/test/hitchcock.gif";
    public static final String TEST4 = "src/main/resources/img/test/hitchcock.png";
    public static final String TEST5 = "src/main/resources/img/test/info.png";
    public static final String TEST6 = "src/main/resources/img/test/marbles.tif";
    
    public static final String DEST = "results/appendix/image_properties.pdf";
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ImageProperties().createPdf(DEST);
    }
    
    
    public void createPdf(String dest) throws IOException {
        OutputStream fos = new FileOutputStream(dest);
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdf = new PdfDocument(writer);
        
        Document document = new Document(pdf);
        
        Image img1 = new Image(ImageDataFactory.create(TEST1));
        img1.scaleToFit(100, 100).setDestination("Top");
        document.add(img1);
        Image img2 = new Image(ImageDataFactory.create(TEST2));
        img2.setHeight(300);
        document.add(img2);
        Image img3 = new Image(ImageDataFactory.create(TEST3));
        img3.scaleToFit(100, 100);
        img3.setBackgroundColor(Color.BLUE);
        document.add(img3);
        Image img4 = new Image(ImageDataFactory.create(TEST4));
        img4.scaleToFit(100, 100);
        img4.setBackgroundColor(Color.RED);
        document.add(img4);
        Image img5 = new Image(ImageDataFactory.create(TEST5));
        img5.scaleToFit(50, 50);
        Style style = new Style();
        style.setBorderRight(new SolidBorder(2));
        img5.addStyle(style);
        img5.setUnderline();
        document.add(img5);
        Image img6 = new Image(ImageDataFactory.create(TEST6));
        PdfAction top = PdfAction.createGoTo("Top");
        img6.scaleToFit(100, 100).setAction(top);
        document.add(img6);
        
        document.close();
    }
}
