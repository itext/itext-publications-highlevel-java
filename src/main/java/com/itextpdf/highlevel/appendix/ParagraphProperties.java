/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.appendix;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class ParagraphProperties {
    
    public static final String DEST = "results/appendix/paragraph_properties.pdf";
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ParagraphProperties().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        // Initialize PDF writer and PDF document
        OutputStream fos = new FileOutputStream(dest);
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdf = new PdfDocument(writer);
        
        // Initialize document
        Document document = new Document(pdf);
        Paragraph p;
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        Style style = new Style();
        style.setBackgroundColor(Color.YELLOW);
        p = getNewParagraphInstance().addStyle(style).setBorder(new SolidBorder(0.5f));
        document.add(p);
        p = getNewParagraphInstance();
        p.setBackgroundColor(Color.GRAY).setWidth(150).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
        document.add(p);
        document.add(getNewParagraphInstance().setRotationAngle(Math.PI / 18));
        document.add(getNewParagraphInstance().setWidth(150).setHyphenation(new HyphenationConfig("en", "uk", 3, 3)));
        document.add(getNewParagraphInstance().setHeight(120).setVerticalAlignment(VerticalAlignment.BOTTOM).setBackgroundColor(Color.YELLOW));
        document.add(getNewParagraphInstance().setWidthPercent(80).setFont(font).setFontSize(8).setFontColor(Color.RED));
        document.close();
    }
    
    public static Paragraph getNewParagraphInstance() {
        return new Paragraph("This is a long paragraph that "
                + "will be used and reused to test paragraph "
                + "properties. This paragraph should take "
                + "more than one line. We'll change different "
                + "properties and then look at the effect "
                + "when we add the paragraph to the document.");
    }
}
