/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 building blocks
 */
package com.itextpdf.highlevel.chapter03;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.wmf.WmfImageData;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.test.annotations.WrapToTest;

import java.io.File;
import java.io.IOException;

/**
 * @author iText
 */
@WrapToTest
public class C03E20_XObjectTypes {
    public static final String WMF = "src/main/resources/img/test/butterfly.wmf";
    public static final String SRC = "src/main/resources/pdfs/jekyll_hyde.pdf";
    
    public static final String DEST = "results/chapter03/xobject_types.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C03E20_XObjectTypes().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf);
        PdfFormXObject xObject1 = new PdfFormXObject(new WmfImageData(WMF), pdf);
        Image img1 = new Image(xObject1);
        document.add(img1);
        PdfReader reader = new PdfReader(SRC);
        PdfDocument existing = new PdfDocument(reader);
        PdfPage page = existing.getPage(1);
        PdfFormXObject xObject2 = page.copyAsFormXObject(pdf);
        Image img2 = new Image(xObject2);
        img2.scaleToFit(400, 400);
        document.add(img2);
        document.close();
    }
}
