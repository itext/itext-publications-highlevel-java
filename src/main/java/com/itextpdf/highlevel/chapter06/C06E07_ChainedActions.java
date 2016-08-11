/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter06;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.IOException;

/**
 * @author iText
 */
public class C06E07_ChainedActions {
    
    public static final String DEST = "results/chapter06/jekyll_hyde_chained.pdf";
    
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C06E07_ChainedActions().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf);
        PdfAction action = PdfAction.createJavaScript("app.alert('Boo');");
        action.next(PdfAction.createGoToR(
                new File(C06E04_TOC_GoToNamed.DEST).getName(), 1, true));
        Link link = new Link("here", action);
        Paragraph p = new Paragraph()
            .add("Click ")
            .add(link.setFontColor(Color.BLUE))
            .add(" if you want to be scared.");
        document.add(p);
        document.close();
    }
}
