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
public class C06E05_RemoteGoto {
    
    public static final String DEST = "results/chapter06/jekyll_hyde_remote.pdf";
    
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C06E05_RemoteGoto().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf);
        Link link1 = new Link("Strange Case of Dr. Jekyll and Mr. Hyde",
            PdfAction.createGoToR(
                new File(C06E04_TOC_GoToNamed.DEST).getName(), 1, true));
        Link link2 = new Link("table of contents",
            PdfAction.createGoToR(
                new File(C06E04_TOC_GoToNamed.DEST).getName(), "toc", true));
        Paragraph p = new Paragraph()
            .add("Read the amazing horror story ")
            .add(link1.setFontColor(Color.BLUE))
            .add(" or, if you're too afraid to start reading the story, read the ")
            .add(link2.setFontColor(Color.BLUE))
            .add(".");
        document.add(p);
        document.close();
    }
}
