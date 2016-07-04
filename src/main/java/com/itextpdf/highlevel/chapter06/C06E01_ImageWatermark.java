/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter06;

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
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.property.TextAlignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C06E01_ImageWatermark {
    public static final String SRC = "src/main/resources/txt/jekyll_hyde.txt";
    public static final String IMG = "src/main/resources/img/3132614.jpg";
    public static final String DEST = "results/chapter06/jekyll_hydeV1.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C06E01_ImageWatermark().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Image img = new Image(ImageDataFactory.create(IMG));
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE,
                new TransparentImage(img));
        // Initialize document
        Document document = new Document(pdf);
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        document.setTextAlignment(TextAlignment.JUSTIFIED)
            .setHyphenation(new HyphenationConfig("en", "uk", 3, 3));
        
        BufferedReader br = new BufferedReader(new FileReader(SRC));
        String line;
        Div div = new Div();
        while ((line = br.readLine()) != null) {
            document.add(new Paragraph(line)
                .setFont(bold).setFontSize(12)
                .setMarginBottom(0)
                .setKeepWithNext(true));
            div = new Div()
                .setFont(font).setFontSize(11)
                .setMarginBottom(18);
            while ((line = br.readLine()) != null) {
                div.add(
                    new Paragraph(line)
                        .setMarginBottom(0)
                        .setFirstLineIndent(36)
                        .setMultipliedLeading(1.2f)
                );
                if (line.isEmpty()) {
                    document.add(div);
                    break;
                }
            }
        }
        document.add(div);

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
            PdfDocument pdfDoc = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            Rectangle pageSize = page.getPageSize();
            PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
            pdfCanvas.saveState().setExtGState(gState);
            Canvas canvas = new Canvas(pdfCanvas, pdfDoc, page.getPageSize());
            canvas.add(img.scaleAbsolute(pageSize.getWidth(), pageSize.getHeight()));
            pdfCanvas.restoreState();
            pdfCanvas.release();
        }
    }
}
