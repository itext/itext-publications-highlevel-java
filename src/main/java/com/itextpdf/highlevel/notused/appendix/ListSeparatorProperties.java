package com.itextpdf.highlevel.notused.appendix;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.io.File;
import java.io.IOException;

/**
 * @author iText
 */
public class ListSeparatorProperties {

    public static final String DEST = "results/appendix/separator_properties.pdf";
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ListSeparatorProperties().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);
        Style style = new Style();
        style.setBackgroundColor(ColorConstants.YELLOW);
        document.add(createNewSeparator().addStyle(style).setDestination("Top"));
        document.add(new Paragraph("test"));
        document.add(createNewSeparator().setWidth(300).setHorizontalAlignment(HorizontalAlignment.CENTER));
        document.add(createNewSeparator().setMargin(10).setVerticalAlignment(VerticalAlignment.BOTTOM).setBorder(new SolidBorder(0.5f)));
        document.add(createNewSeparator().setMargin(10).setWidth(300));
        document.add(createNewSeparator().setMargin(10).setRelativePosition(10, 10, 50, 10));
        document.add(createNewSeparator().setMargin(10).setWidth(UnitValue.createPercentValue(50)));
        document.add(createNewSeparator().setMargin(10).setWidth(50).setAction(PdfAction.createGoTo("Top")));
        document.add(createNewSeparator().setFixedPosition(100, 200, 350));
        document.add(new AreaBreak());
        document.add(createNewSeparator().setBackgroundColor(ColorConstants.YELLOW).setMarginBottom(10));
        document.add(createNewSeparator().setBackgroundColor(ColorConstants.LIGHT_GRAY).setPaddingLeft(20).setPaddingRight(50));
        document.add(createNewSeparator().setBackgroundColor(ColorConstants.YELLOW));
        document.add(createNewSeparator().setBackgroundColor(ColorConstants.LIGHT_GRAY).setMarginBottom(50));
        document.add(createNewSeparator().setBackgroundColor(ColorConstants.YELLOW));
        document.add(createNewSeparator().setBackgroundColor(ColorConstants.LIGHT_GRAY).setMargin(50).setPadding(30));
        document.add(createNewSeparator().setBackgroundColor(ColorConstants.YELLOW));
        document.add(createNewSeparator().setBackgroundColor(ColorConstants.LIGHT_GRAY));
        document.close();
    }

    public static LineSeparator createNewSeparator() {
        return new LineSeparator(new DottedLine());
    }
}
