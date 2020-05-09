package com.itextpdf.highlevel.chapter02;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C02E04_CanvasReturn {

    public static final String DEST = "results/chapter02/canvas_return.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C02E04_CanvasReturn().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        PdfPage page = pdf.addNewPage();
        PdfCanvas pdfCanvas = new PdfCanvas(page);
        Rectangle rectangle = new Rectangle(36, 650, 100, 100);
        pdfCanvas.rectangle(rectangle);
        pdfCanvas.stroke();
        Canvas canvas1 = new Canvas(pdfCanvas, rectangle);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
        Text title = new Text("The Strange Case of Dr. Jekyll and Mr. Hyde").setFont(bold);
        Text author = new Text("Robert Louis Stevenson").setFont(font);
        Paragraph p = new Paragraph().add(title).add(" by ").add(author);
        canvas1.add(p);
        canvas1.close();

        PdfPage page2 = pdf.addNewPage();
        PdfCanvas pdfCanvas2 = new PdfCanvas(page2);
        Canvas canvas2 = new Canvas(pdfCanvas2, rectangle);
        canvas2.add(new Paragraph("Dr. Jekyll and Mr. Hyde"));
        canvas2.close();

        PdfPage page1 = pdf.getFirstPage();
        PdfCanvas pdfCanvas1 = new PdfCanvas(
            page1.newContentStreamBefore(), page1.getResources(), pdf);
        rectangle = new Rectangle(100, 700, 100, 100);
        pdfCanvas1.saveState()
                .setFillColor(ColorConstants.CYAN)
                .rectangle(rectangle)
                .fill()
                .restoreState();
        Canvas canvas = new Canvas(pdfCanvas1, rectangle);
        canvas.add(new Paragraph("Dr. Jekyll and Mr. Hyde"));
        canvas.close();

        //Close document
        pdf.close();
    }
}
