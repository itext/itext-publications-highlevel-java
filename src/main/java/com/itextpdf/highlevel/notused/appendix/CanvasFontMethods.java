package com.itextpdf.highlevel.notused.appendix;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.Property;

import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class CanvasFontMethods {

    public static final String DEST = "results/appendix/canvas_font_methods.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CanvasFontMethods().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        PdfPage page = pdf.addNewPage();
        PdfCanvas pdfCanvas = new PdfCanvas(page);
        Rectangle rectangle = new Rectangle(36, 36, 523, 770);
        Canvas canvas = new Canvas(pdfCanvas, rectangle);
        Paragraph p;
        p = new Paragraph("Testing font methods");
        canvas.add(p);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        canvas.setFont(font);
        p = new Paragraph("Testing font methods: changed font");
        canvas.add(p);
        canvas.setFontSize(18);
        p = new Paragraph("Testing font methods: changed font size");
        canvas.add(p);
        canvas.setFontColor(ColorConstants.BLUE);
        p = new Paragraph("Testing font methods: changed color");
        canvas.add(p);
        canvas.setBold();
        p = new Paragraph("Testing font methods: to bold");
        canvas.add(p);
        canvas.setItalic();
        p = new Paragraph("Testing font methods: to italic");
        canvas.add(p);
        canvas.setProperty(Property.BOLD_SIMULATION, false);
        canvas.setProperty(Property.ITALIC_SIMULATION, false);
        canvas.setProperty(Property.FONT_COLOR, null);
        p = new Paragraph("Testing font methods: resetting style and color");
        canvas.add(p);
        canvas.setLineThrough();
        p = new Paragraph("Testing font methods: line through (default)");
        canvas.add(p);
        canvas.setProperty(Property.UNDERLINE, null);
        canvas.setUnderline();
        p = new Paragraph("Testing font methods: underline (default)");
        canvas.add(p);
        canvas.setProperty(Property.UNDERLINE, null);
        canvas.setUnderline(2, 4);
        canvas.setUnderline(ColorConstants.BLUE, 5, 0.1f, 2, -0.5f, PdfCanvasConstants.LineCapStyle.ROUND);
        p = new Paragraph("Testing font methods: underline (custom)");
        canvas.add(p);
        canvas.setProperty(Property.UNDERLINE, null);
        canvas.setTextRenderingMode(PdfCanvasConstants.TextRenderingMode.STROKE);
        p = new Paragraph("Testing font methods: change text rendering mode");
        canvas.add(p);
        canvas.setStrokeWidth(0.1f);
        canvas.setStrokeColor(ColorConstants.BLUE);
        p = new Paragraph("Testing font methods: change stroke width and color");
        canvas.add(p);
        //Close document
        pdf.close();
    }
}
