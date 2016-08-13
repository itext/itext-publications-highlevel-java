/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 building blocks
 */
package com.itextpdf.highlevel.chapter07;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.test.annotations.WrapToTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class C07E13_Compressed {
    public static final String SRC = "src/main/resources/txt/jekyll_hyde.txt";
    public static final String IMG = "src/main/resources/img/3132614.jpg";
    public static final String DEST = "results/chapter07/jekyll_hyde_compressed.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C07E13_Compressed().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest,
            new WriterProperties().setFullCompressionMode(true)));
        Image img = new Image(ImageDataFactory.create(IMG));
        IEventHandler handler = new TransparentImage(img);
        pdf.addEventHandler(PdfDocumentEvent.START_PAGE, handler);
        // Initialize document
        Document document = new Document(pdf);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        document.setTextAlignment(TextAlignment.JUSTIFIED)
            .setHyphenation(new HyphenationConfig("en", "uk", 3, 3));

        BufferedReader br = new BufferedReader(new FileReader(SRC));
        String name, line;
        Paragraph p;
        boolean title = true;
        int counter = 0;
        List<SimpleEntry<String,SimpleEntry<String, Integer>>> toc = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            p = new Paragraph(line);
            p.setKeepTogether(true);
            if (title) {
                name = String.format("title%02d", counter++);
                p.setFont(bold).setFontSize(12)
                    .setKeepWithNext(true)
                    .setDestination(name);
                title = false;
                document.add(p);
                toc.add(new SimpleEntry(name, new SimpleEntry(line, pdf.getNumberOfPages())));
            }
            else {
                p.setFirstLineIndent(36);
                if (line.isEmpty()) {
                    p.setMarginBottom(12);
                    title = true;
                }
                else {
                    p.setMarginBottom(0);
                }
                document.add(p);
            }
        }
        
        pdf.removeEventHandler(PdfDocumentEvent.START_PAGE, handler);
        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        
        p = new Paragraph().setFont(bold)
            .add("Table of Contents").setDestination("toc");
        document.add(p);
        
        toc.remove(0);
        List<TabStop> tabstops = new ArrayList();
        tabstops.add(new TabStop(580, TabAlignment.RIGHT, new DottedLine()));
        for (SimpleEntry<String, SimpleEntry<String, Integer>> entry : toc) {
            SimpleEntry<String, Integer> text = entry.getValue();
            p = new Paragraph()
                .addTabStops(tabstops)
                .add(text.getKey())
                .add(new Tab())
                .add(String.valueOf(text.getValue()))
                .setAction(PdfAction.createGoTo(entry.getKey()));
            document.add(p);
        }

        //Close document
        document.close();
    }
    protected class TransparentImage implements IEventHandler {

        protected PdfExtGState gState;
        protected Image img;
        
        public TransparentImage(Image img) {
            this.img = img;
            gState = new PdfExtGState().setFillOpacity(0.2f);
        }
        
        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdf = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            Rectangle pageSize = page.getPageSize();
            PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdf);
            pdfCanvas.saveState().setExtGState(gState);
            Canvas canvas = new Canvas(pdfCanvas, pdf, page.getPageSize());
            canvas.add(img.scaleAbsolute(pageSize.getWidth(), pageSize.getHeight()));
            pdfCanvas.restoreState();
            pdfCanvas.release();
        }
    }
}
