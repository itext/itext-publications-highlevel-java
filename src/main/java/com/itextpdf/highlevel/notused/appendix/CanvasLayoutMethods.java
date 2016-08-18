/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itextpdf.highlevel.notused.appendix;

import com.itextpdf.io.font.otf.GlyphLine;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.splitting.DefaultSplitCharacters;
import com.itextpdf.layout.splitting.ISplitCharacters;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author iText
 */
public class CanvasLayoutMethods {
    
    public static final String DEST = "results/appendix/canvas_layout_methods.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CanvasLayoutMethods().createPdf(DEST);
    }
    
    
    public void createPdf(String dest) throws IOException {
        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        
        PdfPage page = pdf.addNewPage();
        PdfCanvas pdfCanvas = new PdfCanvas(page);
        Rectangle rectangle = new Rectangle(36, 36, 523, 770);
        Canvas canvas = new Canvas(pdfCanvas, pdf, rectangle);
        Paragraph p;
        p = new Paragraph("Testing layout methods");
        canvas.add(p);
        canvas.setTextAlignment(TextAlignment.CENTER);
        p = new Paragraph("Testing layout methods");
        canvas.add(p);
        p = new Paragraph();
        for (int i = 0; i < 6; i++)
            p.add("singing supercalifragilisticexpialidocious ");
        canvas.add(p);
        canvas.setHyphenation(new HyphenationConfig("en", "uk", 3, 3));
        canvas.add(p);
        canvas.setTextAlignment(TextAlignment.JUSTIFIED);
        canvas.add(p);
        canvas.setHyphenation(null);
        canvas.setSplitCharacters(new ISplitCharacters() {
            public boolean isSplitCharacter(GlyphLine text, int glyphPos) {
                if (!text.get(glyphPos).hasValidUnicode()) {
                    return false;
                }
                int charCode = text.get(glyphPos).getUnicode();
                return (charCode < ' ' || charCode == 'i');
            }
            
        });
        canvas.add(p);
        canvas.setSplitCharacters(new DefaultSplitCharacters());
        canvas.setTextAlignment(TextAlignment.LEFT);
        canvas.add(p);
        canvas.setWordSpacing(10);
        canvas.add(p);
        canvas.setCharacterSpacing(5);
        canvas.add(p);
        //Close document
        pdf.close();
    }
}
