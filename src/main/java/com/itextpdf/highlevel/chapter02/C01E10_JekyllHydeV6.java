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
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.layout.LayoutResult;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.renderer.DocumentRenderer;
import com.itextpdf.layout.renderer.IRenderer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C01E10_JekyllHydeV6 {
    
    class MyColumnRenderer extends DocumentRenderer {

        private int currentAreaNumber;
        private int nextAreaNumber;
        private Rectangle[] columns;
        protected Set<Integer> moveColumn = new HashSet<Integer>();
        
        public MyColumnRenderer(Document document, Rectangle[] columns) {
            super(document, false);
            this.columns = columns;
        }

        @Override
        protected LayoutArea updateCurrentArea(LayoutResult overflowResult) {
            if (overflowResult != null && overflowResult.getAreaBreak() != null && overflowResult.getAreaBreak().getType() != AreaBreakType.NEXT_AREA) {
                nextAreaNumber = 0;
            }
            if (nextAreaNumber % columns.length == 0) {
                super.updateCurrentArea(overflowResult);
            }
            currentAreaNumber = nextAreaNumber + 1;
            return (currentArea = new LayoutArea(currentPageNumber, columns[nextAreaNumber++ % columns.length].clone()));
        }
    
        @Override
        protected PageSize addNewPage(PageSize customPageSize) {
            if (currentAreaNumber != nextAreaNumber
                && currentAreaNumber % columns.length != 0)
                moveColumn.add(currentPageNumber - 1);
            return super.addNewPage(customPageSize); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        protected void flushSingleRenderer(IRenderer resultRenderer) {
            int pageNum = resultRenderer.getOccupiedArea().getPageNumber();
            if (moveColumn.contains(pageNum)) {
                resultRenderer.move(columns[0].getWidth() / 2, 0);
            }
            super.flushSingleRenderer(resultRenderer); //To change body of generated methods, choose Tools | Templates.
        }
    
    }
    
    public static final String SRC = "src/main/resources/txt/jekyll_hyde.txt";
    public static final String DEST = "results/chapter02/jekyll_hyde_v6.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C01E10_JekyllHydeV6().createPdf(DEST);
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
        float offSet = 36;
        float gutter = 23;
        float columnWidth = (PageSize.A4.getWidth() - offSet * 2) / 2 - gutter;
        float columnHeight = PageSize.A4.getHeight() - offSet * 2;

        //Define column areas
        Rectangle[] columns = {
            new Rectangle(offSet, offSet, columnWidth, columnHeight),
            new Rectangle(offSet + columnWidth + gutter, offSet, columnWidth, columnHeight)};
        DocumentRenderer renderer = new MyColumnRenderer(document, columns);
        document.setRenderer(renderer);
        
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        document.setTextAlignment(TextAlignment.JUSTIFIED)
            .setFont(font)
            .setHyphenation(new HyphenationConfig("en", "uk", 3, 3));
        
        BufferedReader br = new BufferedReader(new FileReader(SRC));
        String line;
        Paragraph p;
        boolean title = true;
        AreaBreak nextPage = new AreaBreak(AreaBreakType.NEXT_PAGE);
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
        renderer.flush();
        document.close();
        
    }

}
