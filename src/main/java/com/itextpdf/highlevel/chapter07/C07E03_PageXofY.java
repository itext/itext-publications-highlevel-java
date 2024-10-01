package com.itextpdf.highlevel.chapter07;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.event.AbstractPdfDocumentEvent;
import com.itextpdf.kernel.pdf.event.AbstractPdfDocumentEventHandler;
import com.itextpdf.kernel.pdf.event.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.properties.TextAlignment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C07E03_PageXofY {
    public static final String SRC = "src/main/resources/txt/jekyll_hyde.txt";
    public static final String DEST = "results/chapter07/jekyll_hydeV2.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C07E03_PageXofY().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        pdf.addEventHandler(PdfDocumentEvent.START_PAGE, new Header("The Strange Case of Dr. Jekyll and Mr. Hyde"));
        PageXofY event = new PageXofY();
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, event);
        // Initialize document
        Document document = new Document(pdf);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
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
        event.writeTotal(pdf);
        //Close document
        document.close();
    }

    protected static class Header extends AbstractPdfDocumentEventHandler {
        String header;
        
        public Header(String header) {
            this.header = header;
        }
        
        @Override
        public void onAcceptedEvent(AbstractPdfDocumentEvent event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdf = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            if (pdf.getPageNumber(page) == 1) return;
            Rectangle pageSize = page.getPageSize();
            PdfCanvas pdfCanvas = new PdfCanvas(
                page.getLastContentStream(), page.getResources(), pdf);
            Canvas canvas = new Canvas(pdfCanvas, pageSize);
            canvas.showTextAligned(header,
                pageSize.getWidth() / 2,
                pageSize.getTop() - 30, TextAlignment.CENTER);
        }
    }
    
    protected static class PageXofY extends AbstractPdfDocumentEventHandler {
        protected PdfFormXObject placeholder;
        protected float side = 20;
        protected float x = 300;
        protected float y = 25;
        protected float space = 4.5f;
        protected float descent = 3;
        
        public PageXofY() {
            placeholder = new PdfFormXObject(new Rectangle(0, 0, side, side));
        }
        
        @Override
        public void onAcceptedEvent(AbstractPdfDocumentEvent event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdf = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            int pageNumber = pdf.getPageNumber(page);
            Rectangle pageSize = page.getPageSize();
            PdfCanvas pdfCanvas = new PdfCanvas(
                page.getLastContentStream(), page.getResources(), pdf);
            Canvas canvas = new Canvas(pdfCanvas, pageSize);
            Paragraph p = new Paragraph()
                .add("Page ").add(String.valueOf(pageNumber)).add(" of");
            canvas.showTextAligned(p, x, y, TextAlignment.RIGHT);
            pdfCanvas.addXObjectAt(placeholder, x + space, y - descent);
            pdfCanvas.release();
        }
        
        public void writeTotal(PdfDocument pdf) {
            Canvas canvas = new Canvas(placeholder, pdf);
            canvas.showTextAligned(String.valueOf(pdf.getNumberOfPages()),
                0, descent, TextAlignment.LEFT);
        }
    }
}
