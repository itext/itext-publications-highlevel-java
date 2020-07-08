package com.itextpdf.highlevel.notused.appendix;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.DottedBorder;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.File;
import java.io.IOException;

/**
 * @author iText
 */
public class CellProperties {

    public static final String DEST = "results/appendix/cell_properties.pdf";
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CellProperties().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);
        Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        table.addCell(new Cell().add(new Paragraph("Test 1")).setHeight(50).setDestination("Top"));
        Style style = new Style();
        style.setBackgroundColor(ColorConstants.YELLOW);
        table.addCell(new Cell().setBorder(new DottedBorder(5)).add(new Paragraph("Test 2")).addStyle(style).setRelativePosition(10, 10, 50, 10));
        table.addCell(new Cell().add(new Paragraph("Test 3")).setVerticalAlignment(VerticalAlignment.BOTTOM));
        table.addCell(new Cell().add(ParagraphProperties.getNewParagraphInstance()).setHyphenation(new HyphenationConfig("en", "uk", 3, 3)));
        table.addCell(new Cell().add(new Paragraph("Rotated")).setRotationAngle(Math.PI / 18).setFont(font).setFontSize(8).setFontColor(ColorConstants.RED));
        table.addCell(new Cell().add(new Paragraph("Centered")).setTextAlignment(TextAlignment.CENTER).setAction(PdfAction.createGoTo("Top")));
        table.addCell(new Cell().add(new Paragraph("Test 5")).setBackgroundColor(ColorConstants.BLUE));
        table.addCell(new Cell().add(ParagraphProperties.getNewParagraphInstance()).setBackgroundColor(ColorConstants.RED).setPaddingLeft(20).setPaddingRight(50));
        table.addCell(new Cell().add(new Paragraph("Test 7")).setBackgroundColor(ColorConstants.RED));
        table.addCell(new Cell().add(new Paragraph("Test 8")).setBackgroundColor(ColorConstants.BLUE).setMarginBottom(10));
        table.addCell(new Cell().add(new Paragraph("Test 9")).setBackgroundColor(ColorConstants.BLUE));
        table.addCell(new Cell().add(new Paragraph("Test 10")).setBackgroundColor(ColorConstants.RED));
        table.addCell(new Cell().add(ParagraphProperties.getNewParagraphInstance()).setBackgroundColor(ColorConstants.RED).setMargin(50).setPadding(30));
        table.addCell(new Cell().add(new Paragraph("Test 12")).setBackgroundColor(ColorConstants.BLUE));

        document.add(table);
        SolidBorder border = new SolidBorder(ColorConstants.RED, 2);
        Cell cell = new Cell().add(new Paragraph("Test")).setFixedPosition(100, 400, 350).setBorder(border).setBackgroundColor(ColorConstants.BLUE).setHeight(100).setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(cell);
        document.close();
    }
}
