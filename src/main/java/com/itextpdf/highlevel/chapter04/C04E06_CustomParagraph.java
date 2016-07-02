/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter04;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.ParagraphRenderer;
import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C04E06_CustomParagraph {
    
    class MyParagraphRenderer extends ParagraphRenderer {

        public MyParagraphRenderer(Paragraph modelElement) {
            super(modelElement);
        }

        @Override
        public void drawBackground(DrawContext drawContext) {
            Rectangle bBox = getOccupiedAreaBBox();
            Rectangle bgArea = applyMargins(bBox, false);
            drawContext.getCanvas().saveState().setFillColor(Color.ORANGE)
                .roundRectangle((double)bgArea.getX(), (double)bgArea.getY(),
                    (double)bgArea.getWidth(), (double)bgArea.getHeight(), 5)
                .fill().restoreState();
        }
    }
    
    public static final String DEST = "results/chapter04/custom_paragraph.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C04E06_CustomParagraph().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        
        // Initialize document
        Document document = new Document(pdf);
        
        Paragraph p1 = new Paragraph(
            "The Strange Case of Dr. Jekyll and Mr. Hyde");
        p1.setBackgroundColor(Color.ORANGE);
        document.add(p1);
        
        Paragraph p2 = new Paragraph(
            "The Strange Case of Dr. Jekyll and Mr. Hyde");
        p2.setNextRenderer(new MyParagraphRenderer(p2));
        document.add(p2);
        
        document.close();
    }
}
