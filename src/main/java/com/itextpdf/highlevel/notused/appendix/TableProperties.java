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
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.io.File;
import java.io.IOException;

/**
 * @author iText
 */
public class TableProperties {

    public static final String DEST = "results/appendix/table_properties.pdf";
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TableProperties().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        Style style = new Style();
        style.setBackgroundColor(ColorConstants.YELLOW);
        document.add(createNewTable().addStyle(style).setDestination("Top").setWidth(300).setHorizontalAlignment(HorizontalAlignment.CENTER)).setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(createNewTable().setBorder(new DottedBorder(5)).setHyphenation(new HyphenationConfig("en", "uk", 3, 3)));
        document.add(createNewTable().setTextAlignment(TextAlignment.CENTER));
        document.add(ListSeparatorProperties.createNewSeparator().setMargin(10).setWidth(300).setKeepWithNext(true));
        document.add(createNewTable().setKeepTogether(true).setWidth(UnitValue.createPercentValue(90)));
        document.add(createNewTable());
        document.add(createNewTable().setRelativePosition(10, 10, 50, 10));
        document.add(createNewTable());
        document.add(new AreaBreak());
        document.add(createNewTable().setFixedPosition(100, 400, 350).setAction(PdfAction.createGoTo("Top")));
        document.add(new AreaBreak());
        document.add(createNewTable().setBackgroundColor(ColorConstants.YELLOW).setMarginBottom(10));
        document.add(createNewTable().setBackgroundColor(ColorConstants.LIGHT_GRAY).setPaddingLeft(20).setPaddingRight(50));
        document.add(createNewTable().setBackgroundColor(ColorConstants.YELLOW));
        document.add(createNewTable().setBackgroundColor(ColorConstants.LIGHT_GRAY));
        document.add(createNewTable().setBackgroundColor(ColorConstants.YELLOW));
        document.add(createNewTable().setBackgroundColor(ColorConstants.LIGHT_GRAY).setMargin(50).setPadding(30));
        document.add(createNewTable().setBackgroundColor(ColorConstants.YELLOW));
        document.add(createNewTable().setBackgroundColor(ColorConstants.LIGHT_GRAY));
        document.close();
    }

    public Table createNewTable() {
        Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
        table.addCell("test1");
        table.addCell("test2");
        table.addCell("test3");
        table.addCell("test4");
        table.addCell("test5");
        table.addCell("test6");
        table.addCell("test7");
        table.addCell("This is a long text snippet that "
                + "will be used and reused to test paragraph "
                + "properties. This paragraph should take "
                + "more than one line. We'll change different "
                + "properties and then look at the effect "
                + "when we add the paragraph to the document.");
        return table;
    }
}
