package com.itextpdf.highlevel.chapter07;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceCmyk;
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
public class C07E02_TextWatermark {
    public static final String SRC = "src/main/resources/txt/jekyll_hyde.txt";
    public static final String DEST = "results/chapter07/jekyll_hydeV1.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C07E02_TextWatermark().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE,
                new TextWatermark());
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

        //Close document
        document.close();
    }
    protected class TextWatermark implements IEventHandler {
        
        Color lime, blue;
        PdfFont helvetica;

        protected TextWatermark() throws IOException {
            helvetica = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            lime = new DeviceCmyk(0.208f, 0, 0.584f, 0);
            blue = new DeviceCmyk(0.445f, 0.0546f, 0, 0.0667f);
        }
        
        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdf = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            int pageNumber = pdf.getPageNumber(page);
            Rectangle pageSize = page.getPageSize();
            PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdf);
            pdfCanvas.saveState()
                .setFillColor(pageNumber % 2 == 1 ? lime : blue)
                .rectangle(pageSize.getLeft(), pageSize.getBottom(),
                    pageSize.getWidth(), pageSize.getHeight())
                .fill().restoreState();
            if (pageNumber > 1) {
                pdfCanvas.beginText()
                    .setFontAndSize(helvetica, 10)
                    .moveText(pageSize.getWidth() / 2 - 120, pageSize.getTop() - 20)
                    .showText("The Strange Case of Dr. Jekyll and Mr. Hyde")
                    .moveText(120, -pageSize.getTop() + 40)
                    .showText(String.valueOf(pageNumber))
                    .endText();
            }
            pdfCanvas.release();
        }
    }
}
