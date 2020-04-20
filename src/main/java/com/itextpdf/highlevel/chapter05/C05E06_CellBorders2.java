package com.itextpdf.highlevel.chapter05;

import java.io.File;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
/**
 * @author Bruno Lowagie (iText Software)
 */
public class C05E06_CellBorders2 {

    public static final String DEST = "results/chapter05/cell_borders2.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E06_CellBorders2().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);

        Table table = new Table(UnitValue.createPercentArray(new float[]{2, 1, 1}));
        table.setBorder(new SolidBorder(3))
            .setWidth(UnitValue.createPercentValue(80))
            .setHorizontalAlignment(HorizontalAlignment.CENTER)
            .setTextAlignment(TextAlignment.CENTER);
        table.addCell(new Cell(1, 3)
            .add(new Paragraph("Cell with colspan 3"))
            .setVerticalAlignment(VerticalAlignment.MIDDLE)
            .setBorder(Border.NO_BORDER));
        table.addCell(new Cell(2, 1)
            .add(new Paragraph("Cell with rowspan 2"))
            .setVerticalAlignment(VerticalAlignment.MIDDLE)
            .setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("row 1; cell 1")));
        table.addCell(new Cell().add(new Paragraph("row 1; cell 2")));
        table.addCell(new Cell().add(new Paragraph("row 2; cell 1")));
        table.addCell(new Cell().add(new Paragraph("row 2; cell 2")));
        document.add(table);

        document.close();
    }
}
