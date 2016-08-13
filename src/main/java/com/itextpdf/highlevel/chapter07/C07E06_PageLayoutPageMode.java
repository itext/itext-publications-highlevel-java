/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 building blocks
 */
package com.itextpdf.highlevel.chapter07;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C07E06_PageLayoutPageMode {
    
    public static final String DEST = "results/chapter07/page_mode_page_layout.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C07E06_PageLayoutPageMode().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        pdf.getCatalog().setPageLayout(PdfName.TwoColumnRight);
        pdf.getCatalog().setPageMode(PdfName.UseThumbs);
        Document document = new Document(pdf, PageSize.A8);
        document.add(new Paragraph("Mr. Jekyl"));
        document.add(new AreaBreak());
        document.add(new Paragraph("Mr. Hyde"));
        document.close();
    }
}
