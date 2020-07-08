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
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.io.File;
import java.io.IOException;

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
        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        Style style = new Style();
        style.setBackgroundColor(ColorConstants.YELLOW).setTextAlignment(TextAlignment.CENTER);
        document.add(createNewList().addStyle(style).setWidth(300).setHorizontalAlignment(HorizontalAlignment.CENTER).setDestination("Top"));
        document.add(createNewList().setRotationAngle(Math.PI / 18).setFont(font).setFontSize(8).setFontColor(ColorConstants.RED));
        document.add(createNewList().setHyphenation(new HyphenationConfig("en", "uk", 3, 3)).setBorder(new SolidBorder(0.5f)).setKeepWithNext(true));
        document.add(createNewList().setKeepTogether(true).setHeight(200));
        document.add(createNewList().setWidth(UnitValue.createPercentValue(50)));
        document.add(createNewList().setRelativePosition(10, 10, 50, 10));
        document.add(createNewList());
        document.add(new AreaBreak());
        document.add(createNewList().setFixedPosition(100, 400, 350).setAction(PdfAction.createGoTo("Top")));
        document.add(createNewList().setBackgroundColor(ColorConstants.YELLOW).setMarginBottom(10));
        document.add(createNewList().setBackgroundColor(ColorConstants.LIGHT_GRAY).setPaddingLeft(20).setPaddingRight(50));
        document.add(createNewList().setBackgroundColor(ColorConstants.YELLOW));
        document.add(createNewList().setBackgroundColor(ColorConstants.LIGHT_GRAY));
        document.add(createNewList().setBackgroundColor(ColorConstants.YELLOW));
        document.add(createNewList().setBackgroundColor(ColorConstants.LIGHT_GRAY).setMargin(50).setPadding(30));
        document.add(createNewList().setBackgroundColor(ColorConstants.YELLOW));
        document.add(createNewList().setBackgroundColor(ColorConstants.LIGHT_GRAY));
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
