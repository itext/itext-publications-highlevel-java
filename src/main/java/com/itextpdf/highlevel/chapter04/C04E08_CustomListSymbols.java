package com.itextpdf.highlevel.chapter04;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.ListNumberingType;
import com.itextpdf.layout.properties.ListSymbolAlignment;
import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C04E08_CustomListSymbols {

    public static final String DEST = "results/chapter04/custom_list_symbols.pdf";
    public static final String INFO = "src/main/resources/img/test/info.png";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C04E08_CustomListSymbols().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        PageSize pagesize = PageSize.A6.rotate();
        Document document = new Document(pdf, pagesize);
        //Set column parameters
        float offSet = 36;
        float gutter = 23;
        float columnWidth = (pagesize.getWidth() - offSet * 2) / 2 - gutter;
        float columnHeight = pagesize.getHeight() - offSet * 2;

        //Define column areas
        Rectangle[] columns = {
            new Rectangle(offSet, offSet, columnWidth, columnHeight),
            new Rectangle(offSet + columnWidth + gutter, offSet, columnWidth, columnHeight)};
        document.setRenderer(new ColumnDocumentRenderer(document, columns));

        List list = new List();
        list.setListSymbol("\u2022");
        list.add("Dr. Jekyll");
        list.add("Mr. Hyde");
        document.add(list);

        list = new List();
        PdfFont font = PdfFontFactory.createFont(StandardFonts.ZAPFDINGBATS);
        list.setListSymbol(new Text("*").setFont(font).setFontColor(ColorConstants.ORANGE));
        list.setSymbolIndent(10);
        list.add("Dr. Jekyll");
        list.add("Mr. Hyde");
        document.add(list);

        Image info = new Image(ImageDataFactory.create(INFO));
        info.scaleAbsolute(12, 12);
        list = new List().setSymbolIndent(3);
        list.setListSymbol(info);
        list.add("Dr. Jekyll");
        list.add("Mr. Hyde");
        document.add(list);

        list = new List();
        list.setListSymbol(ListNumberingType.ENGLISH_LOWER);
        list.setPostSymbolText("- ");
        list.add("Dr. Jekyll");
        list.add("Mr. Hyde");
        document.add(list);

        list = new List(ListNumberingType.DECIMAL);
        list.setPreSymbolText("Part ");
        list.setPostSymbolText(": ");
        list.add("Dr. Jekyll");
        list.add("Mr. Hyde");
        document.add(list);

        list = new List(ListNumberingType.DECIMAL);
        list.setItemStartIndex(5);
        list.add("Dr. Jekyll");
        list.add("Mr. Hyde");
        document.add(list);

        list = new List(ListNumberingType.ROMAN_LOWER);
        list.setListSymbolAlignment(ListSymbolAlignment.LEFT);
        for (int i = 0; i < 6; i++) {
            list.add("Dr. Jekyll");
            list.add("Mr. Hyde");
        }
        document.add(list);
        //Close document
        document.close();
    }
}
