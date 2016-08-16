/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 building blocks
 */
package com.itextpdf.highlevel.chapter07;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfNumber;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.test.annotations.WrapToTest;
import java.io.File;
import java.io.IOException;

/**
 * @author iText
 */
@WrapToTest
public class C07E01_EventHandlers {
    
    public static final String DEST = "results/chapter07/jekyll_hyde_page_orientation.pdf";
    
    public static final PdfNumber PORTRAIT = new PdfNumber(0);
    public static final PdfNumber LANDSCAPE = new PdfNumber(90);
    public static final PdfNumber INVERTEDPORTRAIT = new PdfNumber(180);
    public static final PdfNumber SEASCAPE = new PdfNumber(270);
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C07E01_EventHandlers().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        pdf.getCatalog().setPageLayout(PdfName.TwoColumnLeft);
        PageRotationEventHandler eventHandler = new PageRotationEventHandler();
        pdf.addEventHandler(PdfDocumentEvent.START_PAGE, eventHandler);
        Document document = new Document(pdf, PageSize.A8);
        document.add(new Paragraph("Dr. Jekyll"));
        eventHandler.setRotation(INVERTEDPORTRAIT);
        document.add(new AreaBreak());
        document.add(new Paragraph("Mr. Hyde"));
        eventHandler.setRotation(LANDSCAPE);
        document.add(new AreaBreak());
        document.add(new Paragraph("Dr. Jekyll"));
        eventHandler.setRotation(SEASCAPE);
        document.add(new AreaBreak());
        document.add(new Paragraph("Mr. Hyde"));
        document.close();
    }
    
    protected class PageRotationEventHandler implements IEventHandler {
        protected PdfNumber rotation = PORTRAIT;
 
        public void setRotation(PdfNumber orientation) {
            this.rotation = orientation;
        }
 
        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            docEvent.getPage().put(PdfName.Rotate, rotation);
        }
    }
}
