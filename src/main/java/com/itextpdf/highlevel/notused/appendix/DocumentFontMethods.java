package com.itextpdf.highlevel.notused.appendix;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.Property;

import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class DocumentFontMethods {

    public static final String DEST = "results/appendix/document_font_methods.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new DocumentFontMethods().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);
        Paragraph p;
        p = new Paragraph("Testing font methods");
        document.add(p);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        document.setFont(font);
        p = new Paragraph("Testing font methods: changed font");
        document.add(p);
        document.setFontSize(18);
        p = new Paragraph("Testing font methods: changed font size");
        document.add(p);
        document.setFontColor(ColorConstants.BLUE);
        p = new Paragraph("Testing font methods: changed color");
        document.add(p);
        // We don't suggest usage of simulateBold() method to reach text thickness since the result is written with the usual
        // rather than the bold font: we only emulate "thickness". It's recommended to use an actual bold font instead.
        document.simulateBold();
        p = new Paragraph("Testing font methods: simulated bold");
        document.add(p);
        // We don't suggest usage of simulateItalic() method to reach text obliquity since the result is written with the usual
        // rather than the italic font: we only emulate "obliquity". It's recommended to use an actual italic font instead.
        document.simulateItalic();
        p = new Paragraph("Testing font methods: simulated italic");
        document.add(p);
        document.setProperty(Property.BOLD_SIMULATION, false);
        document.setProperty(Property.ITALIC_SIMULATION, false);
        document.setProperty(Property.FONT_COLOR, null);
        p = new Paragraph("Testing font methods: resetting style and color");
        document.add(p);
        document.setLineThrough();
        p = new Paragraph("Testing font methods: line through (default)");
        document.add(p);
        document.setProperty(Property.UNDERLINE, null);
        document.setUnderline();
        p = new Paragraph("Testing font methods: underline (default)");
        document.add(p);
        document.setProperty(Property.UNDERLINE, null);
        document.setUnderline(2, 4);
        document.setUnderline(ColorConstants.BLUE, 5, 0.1f, 2, -0.5f, PdfCanvasConstants.LineCapStyle.ROUND);
        p = new Paragraph("Testing font methods: underline (custom)");
        document.add(p);
        document.setProperty(Property.UNDERLINE, null);
        document.setTextRenderingMode(PdfCanvasConstants.TextRenderingMode.STROKE);
        p = new Paragraph("Testing font methods: change text rendering mode");
        document.add(p);
        document.setStrokeWidth(0.1f);
        document.setStrokeColor(ColorConstants.BLUE);
        p = new Paragraph("Testing font methods: change stroke width and color");
        document.add(p);
        //Close document
        document.close();
    }
}
