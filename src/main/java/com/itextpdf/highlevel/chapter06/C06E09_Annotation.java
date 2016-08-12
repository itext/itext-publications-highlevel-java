/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 building blocks
 */
package com.itextpdf.highlevel.chapter06;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfLinkAnnotation;
import com.itextpdf.kernel.pdf.navigation.PdfExplicitDestination;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.AreaBreakType;
import java.io.File;
import java.io.IOException;

/**
 * @author iText
 */
public class C06E09_Annotation {
    
    public static final String DEST = "results/chapter06/jekyll_hyde_annotation.pdf";
    
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C06E09_Annotation().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf);
        PdfAction js = PdfAction.createJavaScript("app.alert('Boo!');");
        PdfAnnotation la1 = new PdfLinkAnnotation(new Rectangle(0, 0, 0, 0))
            .setHighlightMode(PdfAnnotation.HIGHLIGHT_INVERT)
            .setAction(js).setBorderStyle(PdfAnnotation.STYLE_UNDERLINE);
        Link link1 = new Link("here", (PdfLinkAnnotation)la1);
        document.add(new Paragraph()
            .add("Click ")
            .add(link1)
            .add(" if you want to be scared."));
        PdfAnnotation la2 = new PdfLinkAnnotation(new Rectangle(0, 0, 0, 0))
            .setDestination(PdfExplicitDestination.createFit(2))
            .setHighlightMode(PdfAnnotation.HIGHLIGHT_PUSH)
            .setBorderStyle(PdfAnnotation.STYLE_INSET);
        Link link2 = new Link("next page", (PdfLinkAnnotation)la2);
        document.add(new Paragraph()
            .add("Go to the ")
            .add(link2)
            .add(" if you're too scared."));
        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        document.add(new Paragraph().add("There, there, everything is OK."));
        document.close();
    }
}
