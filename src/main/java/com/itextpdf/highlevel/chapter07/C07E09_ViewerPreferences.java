/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 building blocks
 */
package com.itextpdf.highlevel.chapter07;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfDocumentInfo;
import com.itextpdf.kernel.pdf.PdfViewerPreferences;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.test.annotations.WrapToTest;
import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class C07E09_ViewerPreferences {
    
    public static final String DEST = "results/chapter07/viewerpreferences.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C07E09_ViewerPreferences().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        PdfViewerPreferences preferences = new PdfViewerPreferences();
        preferences.setFitWindow(true);
        preferences.setHideMenubar(true);
        preferences.setHideToolbar(true);
        preferences.setHideWindowUI(true);
        preferences.setCenterWindow(true);
        preferences.setDisplayDocTitle(true);
        pdf.getCatalog().setViewerPreferences(preferences);
        PdfDocumentInfo info = pdf.getDocumentInfo();
        info.setTitle("A Strange Case");
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.add(new Paragraph("Mr. Jekyl and Mr. Hyde"));
        document.close();
    }
}
