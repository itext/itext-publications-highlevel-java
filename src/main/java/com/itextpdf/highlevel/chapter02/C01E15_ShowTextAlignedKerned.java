/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter02;

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
import java.io.OutputStream;

/**
 * @author iText
 */
public class C01E15_ShowTextAlignedKerned {
    public static final String DEST = "results/chapter02/showtextalignedkerned.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C01E15_ShowTextAlignedKerned().createPdf(DEST);
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
        document.showTextAligned("The Strange Case of Dr. Jekyll and Mr. Hyde", 36, 806, TextAlignment.LEFT);
        document.showTextAlignedKerned("The Strange Case of Dr. Jekyll and Mr. Hyde", 36, 790, TextAlignment.LEFT, VerticalAlignment.BOTTOM, 0);
        document.showTextAligned("AWAY AGAIN", 36, 774, TextAlignment.LEFT);
        document.showTextAlignedKerned("AWAY AGAIN", 36, 758, TextAlignment.LEFT, VerticalAlignment.BOTTOM, 0);
        
        document.close();
    }
}
