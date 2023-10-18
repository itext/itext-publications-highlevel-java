package com.itextpdf.highlevel.chapter06;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.navigation.PdfDestination;
import com.itextpdf.kernel.pdf.navigation.PdfExplicitDestination;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.AreaBreakType;
import java.io.File;
import java.io.IOException;

public class C06E08_ExplicitDestinations {
    public static final String DEST = "results/chapter06/jekyll_hyde_explicit.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C06E08_ExplicitDestinations().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf);
        // here we need to add pages to the document beforehand, because we'll need to get their instances for destination creation
        pdf.addNewPage();
        pdf.addNewPage();
        
        PdfDestination jekyll = PdfExplicitDestination.createFitH(pdf.getPage(1), 416);
        PdfDestination hyde = PdfExplicitDestination.createXYZ(pdf.getPage(1), 150, 516, 2);
        PdfDestination jekyll2 = PdfExplicitDestination.createFitR(pdf.getPage(2), 50, 380, 130, 440);
        document.add(new Paragraph()
                .add(new Link("Link to Dr. Jekyll", jekyll)));
        document.add(new Paragraph()
                .add(new Link("Link to Mr. Hyde", hyde)));
        document.add(new Paragraph()
                .add(new Link("Link to Dr. Jekyll on page 2", jekyll2)));
        document.add(new Paragraph()
            .setFixedPosition(50, 400, 80)
            .add("Dr. Jekyll"));
        document.add(new Paragraph()
            .setFixedPosition(150, 500, 80)
            .add("Mr. Hyde"));
        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        document.add(new Paragraph()
            .setFixedPosition(50, 400, 80)
            .add("Dr. Jekyll on page 2"));
        
        document.close();
    }
    
}
