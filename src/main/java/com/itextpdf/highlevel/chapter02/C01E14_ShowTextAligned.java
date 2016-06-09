/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter02;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.licensekey.LicenseKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author iText
 */
public class C01E14_ShowTextAligned {
    public static final String DEST = "results/chapter02/showtextaligned.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C01E14_ShowTextAligned().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        LicenseKey.loadLicenseFile(new FileInputStream("src/main/resources/license/itextkey-typography.xml"));
        //Initialize PDF writer
        OutputStream fos = new FileOutputStream(dest);
        PdfWriter writer = new PdfWriter(fos);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
        
        // Initialize document
        Document document = new Document(pdf);
        document.showTextAligned("AWAY AGAIN", 36, 806, TextAlignment.LEFT);
        document.showTextAlignedKerned("AWAY AGAIN", 36, 790, TextAlignment.LEFT, VerticalAlignment.BOTTOM, 0);
        document.close();
    }
}
