/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter02;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class JekyllHydeV1 {
    public static final String SRC = "src/main/resources/txt/jekyll_hyde.txt";
    public static final String DEST = "results/chapter01/jekyll_hyde_v1.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new JekyllHydeV1().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF writer
        OutputStream fos = new FileOutputStream(dest);
        PdfWriter writer = new PdfWriter(fos);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
        
        // Initialize document
        Document document = new Document(pdf);

        BufferedReader br = new BufferedReader(new FileReader(SRC));
        String line;
        while ((line = br.readLine()) != null) {
            document.add(new Paragraph(line));
        }

        //Close document
        document.close();
    }

}
