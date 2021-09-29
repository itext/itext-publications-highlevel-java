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
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.io.File;
import java.io.IOException;

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
        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);
        Paragraph p;
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        Style style = new Style();
        style.setBackgroundColor(ColorConstants.YELLOW);
        p = getNewParagraphInstance().addStyle(style).setBorder(new SolidBorder(0.5f)).setDestination("Top");
        document.add(p);
        p = getNewParagraphInstance();
        p.setBackgroundColor(ColorConstants.GRAY).setWidth(150).setHorizontalAlignment(HorizontalAlignment.CENTER).setTextAlignment(TextAlignment.CENTER);
        document.add(p);
        document.add(getNewParagraphInstance().setRotationAngle(Math.PI / 18));
        document.add(getNewParagraphInstance().setWidth(150).setHyphenation(new HyphenationConfig("en", "uk", 3, 3)));
        document.add(getNewParagraphInstance().setHeight(120).setVerticalAlignment(VerticalAlignment.BOTTOM).setBackgroundColor(ColorConstants.YELLOW).setRelativePosition(10, 10, 50, 10));
        document.add(getNewParagraphInstance().setWidth(UnitValue.createPercentValue(80)).setFont(font).setFontSize(8).setFontColor(ColorConstants.RED));
        document.add(new AreaBreak());
        document.add(getNewParagraphInstance().setFixedPosition(100, 400, 350).setAction(PdfAction.createGoTo("Top")));
        document.add(new AreaBreak());
        document.add(getNewParagraphInstance().setBackgroundColor(ColorConstants.YELLOW).setMarginBottom(10));
        document.add(getNewParagraphInstance().setBackgroundColor(ColorConstants.LIGHT_GRAY).setPaddingLeft(20).setPaddingRight(50));
        document.add(getNewParagraphInstance().setBackgroundColor(ColorConstants.YELLOW));
        document.add(getNewParagraphInstance().setBackgroundColor(ColorConstants.LIGHT_GRAY));
        document.add(getNewParagraphInstance().setBackgroundColor(ColorConstants.YELLOW));
        document.add(getNewParagraphInstance().setBackgroundColor(ColorConstants.LIGHT_GRAY).setMargin(50).setPadding(30));
        document.add(getNewParagraphInstance().setBackgroundColor(ColorConstants.YELLOW));
        document.add(getNewParagraphInstance().setBackgroundColor(ColorConstants.LIGHT_GRAY));
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
