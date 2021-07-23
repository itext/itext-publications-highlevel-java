package com.itextpdf.highlevel.chapter02;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.renderer.IRenderer;
import com.itextpdf.layout.renderer.TextRenderer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C02E12_JekyllHydeV8 {
    public static final String SRC = "src/main/resources/txt/jekyll_hyde.txt";
    public static final String DEST = "results/chapter02/jekyll_hyde_v8.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C02E12_JekyllHydeV8().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        
        // Initialize document
        Document document = new Document(pdf, PageSize.A4, false);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        document.setTextAlignment(TextAlignment.JUSTIFIED)
            .setHyphenation(new HyphenationConfig("en", null, 3, 3))
            .setFont(font)
            .setFontSize(11);
        
        Text totalPages = new Text("This document has {totalpages} pages.");
        IRenderer renderer = new TextRenderer(totalPages);
        totalPages.setNextRenderer(renderer);
        document.add(new Paragraph(totalPages));
        
        BufferedReader br = new BufferedReader(new FileReader(SRC));
        String line;
        Paragraph p;
        boolean title = true;
        while ((line = br.readLine()) != null) {
            p = new Paragraph(line);
            p.setKeepTogether(true);
            if (title) {
                p.setFont(bold).setFontSize(12);
                title = false;
            }
            else {
                p.setFirstLineIndent(36);
            }
            if (line.isEmpty()) {
                p.setMarginBottom(12);
                title = true;
            }
            else {
                p.setMarginBottom(0);
            }
            document.add(p);
        }
        br.close();
        String total = renderer.toString().replace("{totalpages}",
                String.valueOf(pdf.getNumberOfPages()));
        ((TextRenderer)renderer).setText(total);
        ((Text)renderer.getModelElement()).setNextRenderer(renderer);
        document.relayout();
        
        //Close document
        document.close();
    }

}
