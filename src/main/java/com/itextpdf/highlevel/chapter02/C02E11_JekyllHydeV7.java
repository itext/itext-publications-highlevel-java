package com.itextpdf.highlevel.chapter02;

import com.itextpdf.io.font.constants.StandardFonts;
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
import com.itextpdf.layout.layout.RootLayoutArea;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.renderer.DocumentRenderer;
import com.itextpdf.layout.renderer.IRenderer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C02E11_JekyllHydeV7 {
    
    class MyColumnRenderer extends DocumentRenderer {

        protected int nextAreaNumber;
        protected final Rectangle[] columns;
        protected int currentAreaNumber;
        protected Set<Integer> moveColumn = new HashSet<>();
        
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
            return (currentArea = new RootLayoutArea(currentArea.getPageNumber(), columns[nextAreaNumber++ % columns.length].clone()));
        }
    
        @Override
        protected PageSize addNewPage(PageSize customPageSize) {
            if (currentAreaNumber != nextAreaNumber
                && currentAreaNumber % columns.length != 0)
                moveColumn.add(document.getPdfDocument().getNumberOfPages());
            return super.addNewPage(customPageSize);
        }

        @Override
        protected void flushSingleRenderer(IRenderer resultRenderer) {
            int pageNum = resultRenderer.getOccupiedArea().getPageNumber();
            if (moveColumn.contains(pageNum)) {
                resultRenderer.move(columns[0].getWidth() / 2, 0);
            }
            super.flushSingleRenderer(resultRenderer);
        }
    
    }
    
    public static final String SRC = "src/main/resources/txt/jekyll_hyde.txt";
    public static final String DEST = "results/chapter02/jekyll_hyde_v7.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C02E11_JekyllHydeV7().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        
        // Initialize document
        Document document = new Document(pdf);
        
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
        
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        document.setTextAlignment(TextAlignment.JUSTIFIED)
            .setFont(font)
            .setHyphenation(new HyphenationConfig("en", null, 3, 3));
        
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
        br.close();
        renderer.flush();
        document.close();
        
    }

}
