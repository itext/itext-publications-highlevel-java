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
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.io.File;
import java.io.IOException;

public class DivProperties {

    public static final String DEST = "results/appendix/div_properties.pdf";
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new DivProperties().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);
        Style style = new Style();
        style.setBackgroundColor(ColorConstants.YELLOW).setBorder(new SolidBorder(0.5f));
        document.add(createNewDiv().addStyle(style).setWidth(350).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER).setDestination("Top"));
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        document.add(createNewDiv().setRotationAngle(Math.PI / 18).setFont(font).setFontSize(8).setFontColor(ColorConstants.RED));
        document.add(createNewDiv().setWidth(350).setHyphenation(new HyphenationConfig("en", "uk", 3, 3)).setKeepWithNext(true));
        document.add(createNewDiv().setWidth(UnitValue.createPercentValue(70)).setKeepTogether(true));
        document.add(createNewDiv().setHeight(350).setBackgroundColor(ColorConstants.YELLOW).setAction(PdfAction.createGoTo("Top")).setRelativePosition(10, 10, 50, 10));
        document.add(new AreaBreak());
        document.add(createNewDiv().setFixedPosition(100, 400, 350));
        document.add(new AreaBreak());
        document.add(createNewDiv().setBackgroundColor(ColorConstants.YELLOW).setMarginBottom(10));
        document.add(createNewDiv().setBackgroundColor(ColorConstants.LIGHT_GRAY).setPaddingLeft(20).setPaddingRight(50));
        document.add(createNewDiv().setBackgroundColor(ColorConstants.YELLOW));
        document.add(createNewDiv().setBackgroundColor(ColorConstants.LIGHT_GRAY));
        document.add(createNewDiv().setBackgroundColor(ColorConstants.YELLOW));
        document.add(createNewDiv().setBackgroundColor(ColorConstants.LIGHT_GRAY).setMargin(50).setPadding(30));
        document.add(createNewDiv().setBackgroundColor(ColorConstants.YELLOW));
        document.add(createNewDiv().setBackgroundColor(ColorConstants.LIGHT_GRAY));
        document.close();
    }

    public static Div createNewDiv() {
        Div div = new Div();
        div.add(ParagraphProperties.getNewParagraphInstance());
        div.add(ListSeparatorProperties.createNewSeparator());
        div.add(ListProperties.createNewList());
        return div;
    }
}
