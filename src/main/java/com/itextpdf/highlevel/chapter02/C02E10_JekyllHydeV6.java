/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 building blocks
 */
package com.itextpdf.highlevel.chapter02;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.renderer.DocumentRenderer;
import com.itextpdf.test.annotations.WrapToTest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class C02E10_JekyllHydeV6 {
    public static final String SRC = "src/main/resources/txt/jekyll_hyde.txt";
    public static final String DEST = "results/chapter02/jekyll_hyde_v6.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C02E10_JekyllHydeV6().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        
        // Initialize document
        Document document = new Document(pdf);
        Paragraph p = new Paragraph()
            .add("Be prepared to read a story about a London lawyer "
            + "named Gabriel John Utterson who investigates strange "
            + "occurrences between his old friend, Dr. Henry Jekyll, "
            + "and the evil Edward Hyde.");
        document.add(p);
        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        
        //Set column parameters
        float offSet = 36;
        float gutter = 23;
        float columnWidth = (PageSize.A4.getWidth() - offSet * 2) / 2 - gutter;
        float columnHeight = PageSize.A4.getHeight() - offSet * 2;

        //Define column areas
        Rectangle[] columns = {
            new Rectangle(offSet, offSet, columnWidth, columnHeight),
            new Rectangle(offSet + columnWidth + gutter, offSet, columnWidth, columnHeight)};
        document.setRenderer(new ColumnDocumentRenderer(document, columns));
        document.add(new AreaBreak(AreaBreakType.LAST_PAGE));   
        
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        document.setTextAlignment(TextAlignment.JUSTIFIED)
            .setFont(font)
            .setHyphenation(new HyphenationConfig("en", "uk", 3, 3));
        
        BufferedReader br = new BufferedReader(new FileReader(SRC));
        String line;
        boolean title = true;
        AreaBreak nextPage = new AreaBreak(AreaBreakType.NEXT_AREA);
        while ((line = br.readLine()) != null) {
            p = new Paragraph(line);
            if (title) {
                p.setFont(bold).setFontSize(12);
                title = false;
            }
            else {
                p.setFirstLineIndent(36);
            }
            if (line.isEmpty()) {
                document.add(nextPage);
                title = true;
            }
            document.add(p);
        }

        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        document.setRenderer(new DocumentRenderer(document)); 
        document.add(new AreaBreak(AreaBreakType.LAST_PAGE));
        p = new Paragraph()
            .add("This was the story about the London lawyer "
            + "named Gabriel John Utterson who investigates strange "
            + "occurrences between his old friend, Dr. Henry Jekyll, "
            + "and the evil Edward Hyde. THE END!");
        document.add(p);
        
        //Close document
        document.close();
        
    }

}
