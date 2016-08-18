/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 building blocks
 */
package com.itextpdf.highlevel.notused.appendix;

import com.itextpdf.io.font.otf.GlyphLine;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.splitting.DefaultSplitCharacters;
import com.itextpdf.layout.splitting.ISplitCharacters;

import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class DocumentLayoutMethods {
    
    public static final String DEST = "results/appendix/document_layout_methods.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new DocumentLayoutMethods().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);
        Paragraph p;
        p = new Paragraph("Testing layout methods");
        document.add(p);
        document.setTextAlignment(TextAlignment.CENTER);
        p = new Paragraph("Testing layout methods");
        document.add(p);
        p = new Paragraph();
        for (int i = 0; i < 6; i++)
            p.add("singing supercalifragilisticexpialidocious ");
        document.add(p);
        document.setHyphenation(new HyphenationConfig("en", "uk", 3, 3));
        document.add(p);
        document.setTextAlignment(TextAlignment.JUSTIFIED);
        document.add(p);
        document.setHyphenation(null);
        document.setSplitCharacters(new ISplitCharacters() {
            public boolean isSplitCharacter(GlyphLine text, int glyphPos) {
                if (!text.get(glyphPos).hasValidUnicode()) {
                    return false;
                }
                int charCode = text.get(glyphPos).getUnicode();
                return (charCode < ' ' || charCode == 'i');
            }
            
        });
        document.add(p);
        document.setSplitCharacters(new DefaultSplitCharacters());
        document.setTextAlignment(TextAlignment.LEFT);
        document.add(p);
        document.setWordSpacing(10);
        document.add(p);
        document.setCharacterSpacing(5);
        document.add(p);
        //Close document
        document.close();
    }
}
