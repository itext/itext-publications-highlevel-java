/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 building blocks
 */
package com.itextpdf.highlevel.chapter07;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfDocumentInfo;
import com.itextpdf.kernel.pdf.PdfViewerPreferences;
import com.itextpdf.kernel.pdf.PdfViewerPreferences.PdfViewerPreferencesConstants;
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
public class C07E10_PrinterPreferences {
    
    public static final String DEST = "results/chapter07/printerpreferences.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C07E10_PrinterPreferences().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        PdfViewerPreferences preferences = new PdfViewerPreferences();
        preferences.setPrintScaling(PdfViewerPreferencesConstants.NONE);
        preferences.setNumCopies(5);
        pdf.getCatalog().setViewerPreferences(preferences);
        PdfDocumentInfo info = pdf.getDocumentInfo();
        info.setTitle("A Strange Case");
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.add(new Paragraph("Mr. Jekyl and Mr. Hyde"));
        document.close();
    }
}
