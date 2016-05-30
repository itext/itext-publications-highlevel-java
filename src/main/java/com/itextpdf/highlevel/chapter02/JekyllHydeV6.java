/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
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
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.TextAlignment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class JekyllHydeV6 {
    public static final String SRC = "src/main/resources/txt/jekyll_hyde.txt";
    public static final String DEST = "results/chapter02/jekyll_hyde_v6.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new JekyllHydeV6().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF writer
        OutputStream fos = new FileOutputStream(dest);
        PdfWriter writer = new PdfWriter(fos);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
        
        // Initialize document
        Document document = new Document(pdf, PageSize.A4);
        
        //Set column parameters
        float offSet = 27;
        float gutter = 18;
        float columnWidth = (PageSize.A4.getWidth() - offSet * 2) / 3 - gutter;
        float columnHeight = PageSize.A4.getHeight() - offSet * 2;

        //Define column areas
        Rectangle[] columns = {new Rectangle(offSet + gutter * 0.5f, offSet, columnWidth, columnHeight),
                new Rectangle(offSet + columnWidth + gutter * 1.5f, offSet, columnWidth, columnHeight),
                new Rectangle(offSet + columnWidth * 2 + gutter * 2.5f, offSet, columnWidth, columnHeight)};
        document.setRenderer(new ColumnDocumentRenderer(document, columns));    
        
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        document.setTextAlignment(TextAlignment.JUSTIFIED)
            .setHyphenation(new HyphenationConfig("en", "uk", 3, 3));
        
        BufferedReader br = new BufferedReader(new FileReader(SRC));
        boolean chapter = false;
        Div div = new Div();
        AreaBreak areaBreak = new AreaBreak(AreaBreakType.NEXT_AREA);
        String line;
        while ((line = br.readLine()) != null) {
            div = new Div()
                .setFont(font).setFontSize(11)
                .setMarginBottom(18);
            div.add(new Paragraph(line)
                .setFont(bold).setFontSize(12)
                .setMarginBottom(0)
            );
            while ((line = br.readLine()) != null) {
                div.add(
                    new Paragraph(line)
                        .setMarginBottom(0)
                        .setFirstLineIndent(36)
                );
                if (line.isEmpty()) {
                    document.add(div);
                    if (chapter) {
                        document.add(areaBreak);
                    }
                    div = new Div();
                    chapter = true;
                    break;
                }
            }
        }
        document.add(div);

        //Close document
        document.close();
        
    }

}
