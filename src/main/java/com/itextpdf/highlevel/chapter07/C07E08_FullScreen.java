/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 building blocks
 */
package com.itextpdf.highlevel.chapter07;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfViewerPreferences;
import com.itextpdf.kernel.pdf.PdfViewerPreferences.PdfViewerPreferencesConstants;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C07E08_FullScreen {
    
    public static final String DEST = "results/chapter07/fullscreen.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C07E08_FullScreen().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        pdf.getCatalog().setPageMode(PdfName.FullScreen);
        PdfViewerPreferences preferences = new PdfViewerPreferences();
        preferences.setNonFullScreenPageMode(PdfViewerPreferencesConstants.USE_THUMBS);
        pdf.getCatalog().setViewerPreferences(preferences);
        Document document = new Document(pdf, PageSize.A8);
        document.add(new Paragraph("Mr. Jekyl"));
        document.add(new AreaBreak());
        document.add(new Paragraph("Mr. Hyde"));
        document.close();
    }
}
