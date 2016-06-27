/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.notused.appendix;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author iText
 */
public class ListProperties {
    
    public static final String DEST = "results/appendix/list_properties.pdf";
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ListProperties().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        // Initialize PDF writer and PDF document
        OutputStream fos = new FileOutputStream(dest);
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdf = new PdfDocument(writer);
        
        // Initialize document
        Document document = new Document(pdf);
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        Style style = new Style();
        style.setBackgroundColor(Color.YELLOW).setTextAlignment(TextAlignment.CENTER);
        document.add(createNewList().addStyle(style).setWidth(300).setHorizontalAlignment(HorizontalAlignment.CENTER).setDestination("Top"));
        document.add(createNewList().setRotationAngle(Math.PI / 18).setFont(font).setFontSize(8).setFontColor(Color.RED));
        document.add(createNewList().setHyphenation(new HyphenationConfig("en", "uk", 3, 3)).setBorder(new SolidBorder(0.5f)).setKeepWithNext(true));
        document.add(createNewList().setKeepTogether(true).setHeight(200));
        document.add(createNewList().setWidthPercent(50));
        document.add(createNewList().setRelativePosition(10, 10, 50, 10));
        document.add(createNewList());
        document.add(new AreaBreak());
        document.add(createNewList().setFixedPosition(100, 400, 350).setAction(PdfAction.createGoTo("Top")));
        document.add(createNewList().setBackgroundColor(Color.YELLOW).setMarginBottom(10));
        document.add(createNewList().setBackgroundColor(Color.LIGHT_GRAY).setPaddingLeft(20).setPaddingRight(50));
        document.add(createNewList().setBackgroundColor(Color.YELLOW));
        document.add(createNewList().setBackgroundColor(Color.LIGHT_GRAY));
        document.add(createNewList().setBackgroundColor(Color.YELLOW));
        document.add(createNewList().setBackgroundColor(Color.LIGHT_GRAY).setMargin(50).setPadding(30));
        document.add(createNewList().setBackgroundColor(Color.YELLOW));
        document.add(createNewList().setBackgroundColor(Color.LIGHT_GRAY));
        document.close();
    }
    
    public static List createNewList() {
        List list = new List();
        list.add("item 1");
        list.add("item 2");
        list.add("item 3");
        list.add("item 4");
        list.add("item 5");
        list.add("item 6");
        list.add("This is a long text snippet that "
                + "will be used and reused to test paragraph "
                + "properties. This paragraph should take "
                + "more than one line. We'll change different "
                + "properties and then look at the effect "
                + "when we add the paragraph to the document.");
        return list;
    }
}
