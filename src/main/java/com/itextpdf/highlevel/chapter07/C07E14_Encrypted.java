/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 building blocks
 */
package com.itextpdf.highlevel.chapter07;

import com.itextpdf.kernel.pdf.EncryptionConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
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
public class C07E14_Encrypted {
    
    public static final String DEST = "results/chapter07/encrypted.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C07E14_Encrypted().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        byte[] user = "It's Hyde".getBytes();
        byte[] owner = "abcdefg".getBytes();
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest,
            new WriterProperties().setStandardEncryption(user, owner,
                EncryptionConstants.ALLOW_PRINTING | EncryptionConstants.ALLOW_ASSEMBLY,
                EncryptionConstants.ENCRYPTION_AES_256)));
        Document document = new Document(pdf);
        document.add(new Paragraph("Mr. Jekyll has a secret: he changes into Mr. Hyde."));
        document.close();
    }
}
