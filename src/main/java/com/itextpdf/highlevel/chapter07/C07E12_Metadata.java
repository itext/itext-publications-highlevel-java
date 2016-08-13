/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 building blocks
 */
package com.itextpdf.highlevel.chapter07;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfDocumentInfo;
import com.itextpdf.kernel.pdf.PdfVersion;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.test.annotations.WrapToTest;
import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class C07E12_Metadata {
    
    public static final String DEST = "results/chapter07/metadata.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C07E12_Metadata().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(
            new PdfWriter(dest,
                new WriterProperties()
                    .addXmpMetadata()
                    .setPdfVersion(PdfVersion.PDF_1_6)));
        PdfDocumentInfo info = pdf.getDocumentInfo();
        info.setTitle("The Strange Case of Dr. Jekyll and Mr. Hyde");
        info.setAuthor("Robert Louis Stevenson");
        info.setSubject("A novel");
        info.setKeywords("Dr. Jekyll, Mr. Hyde");
        info.setCreator("A simple tutorial example");
        Document document = new Document(pdf);
        document.add(new Paragraph("Mr. Jekyl and Mr. Hyde"));
        document.close();
    }
}
