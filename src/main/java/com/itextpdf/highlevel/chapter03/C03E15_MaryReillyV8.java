package com.itextpdf.highlevel.chapter03;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.UnitValue;
import java.io.File;
import java.io.IOException;

public class C03E15_MaryReillyV8 {
    
    public static final String MARY = "src/main/resources/img/0117002.jpg";
    public static final String DEST = "results/chapter03/mary_reilly_V8.pdf";
       
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C03E15_MaryReillyV8().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf);
        Paragraph p = new Paragraph(
            "Mary Reilly is a maid in the household of Dr. Jekyll: ");
        document.add(p);
        Image img = new Image(ImageDataFactory.create(MARY));
        img.setHorizontalAlignment(HorizontalAlignment.CENTER);
        img.setWidth(UnitValue.createPercentValue(80));
        document.add(img);
        document.close();
    }
    
}
