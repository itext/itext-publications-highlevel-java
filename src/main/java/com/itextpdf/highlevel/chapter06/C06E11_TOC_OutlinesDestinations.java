package com.itextpdf.highlevel.chapter06;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfOutline;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.navigation.PdfDestination;
import com.itextpdf.kernel.pdf.navigation.PdfExplicitDestination;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.ParagraphRenderer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C06E11_TOC_OutlinesDestinations {
    public static final String SRC = "src/main/resources/txt/jekyll_hyde.txt";
    public static final String DEST = "results/chapter06/jekyll_hyde_outline2.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C06E11_TOC_OutlinesDestinations().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        pdf.getCatalog().setPageMode(PdfName.UseOutlines);
        // Initialize document
        Document document = new Document(pdf);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        document.setTextAlignment(TextAlignment.JUSTIFIED)
            .setHyphenation(new HyphenationConfig("en", "uk", 3, 3))
            .setFont(font)
            .setFontSize(11);
        
        BufferedReader br = new BufferedReader(new FileReader(SRC));
        String line;
        Paragraph p;
        boolean title = true;
        PdfOutline outline = null;
        while ((line = br.readLine()) != null) {
            p = new Paragraph(line);
            p.setKeepTogether(true);
            if (title) {
                outline = createOutline(outline, pdf, line, p);
                p.setFont(bold).setFontSize(12)
                    .setKeepWithNext(true);
                title = false;
                document.add(p);
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
        
        //Close document
        document.close();
    }

    public PdfOutline createOutline(PdfOutline outline, PdfDocument pdf, String title, Paragraph p) {
        if (outline ==  null) {
            outline = pdf.getOutlines(false);
            outline = outline.addOutline(title);
            return outline;
        }
        OutlineRenderer renderer = new OutlineRenderer(p, title, outline);
        p.setNextRenderer(renderer);
        return outline;
    }
       
    protected class OutlineRenderer extends ParagraphRenderer {
        protected PdfOutline parent;
        protected String title;

        public OutlineRenderer(Paragraph modelElement, String title, PdfOutline parent) {
            super(modelElement);
            this.title = title;
            this.parent = parent;
        }

        @Override
        public void draw(DrawContext drawContext) {
            super.draw(drawContext);
            Rectangle rect = getOccupiedAreaBBox();
            PdfDestination dest = PdfExplicitDestination.createFitH(
                    drawContext.getDocument().getLastPage(),
                    rect.getTop());
            PdfOutline outline = parent.addOutline(title);
            outline.addDestination(dest);
        }
    }
}
