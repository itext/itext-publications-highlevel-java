package com.itextpdf.highlevel.chapter05;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.UnitValue;
import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C05E07_NestedTable {

    public static final String DEST = "results/chapter05/nested_tables.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E07_NestedTable().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);
        Table table = new Table(new float[]{1, 1})
            .setWidth(UnitValue.createPercentValue(80))
            .setHorizontalAlignment(HorizontalAlignment.CENTER);
        table.addCell(new Cell(1, 2).add(new Paragraph("Cell with colspan 2")));
        table.addCell(new Cell().add(new Paragraph("Cell with rowspan 1")));
        Table inner = new Table(new float[]{1, 1});
        inner.addCell("row 1; cell 1");
        inner.addCell("row 1; cell 2");
        inner.addCell("row 2; cell 1");
        inner.addCell("row 2; cell 2");
        table.addCell(inner);
        document.add(table);

        table = new Table(new float[]{1, 1})
            .setMarginTop(10)
            .setWidth(UnitValue.createPercentValue(80))
            .setHorizontalAlignment(HorizontalAlignment.CENTER);
        table.addCell(new Cell(1, 2).add(new Paragraph("Cell with colspan 2")));
        table.addCell(new Cell().add(new Paragraph("Cell with rowspan 1")));
        inner = new Table(new float[]{1, 1});
        inner.addCell("row 1; cell 1");
        inner.addCell("row 1; cell 2");
        inner.addCell("row 2; cell 1");
        inner.addCell("row 2; cell 2");
        table.addCell(new Cell().add(inner).setPadding(0));
        document.add(table);

        table = new Table(new float[]{1, 1})
            .setMarginTop(10)
            .setWidth(UnitValue.createPercentValue(80))
            .setHorizontalAlignment(HorizontalAlignment.CENTER);
        table.addCell(new Cell(1, 2).add(new Paragraph("Cell with colspan 2")));
        table.addCell(new Cell().add(new Paragraph("Cell with rowspan 1")));
        inner = new Table(new float[]{1, 1})
            .setWidth(UnitValue.createPercentValue(100));
        inner.addCell("row 1; cell 1");
        inner.addCell("row 1; cell 2");
        inner.addCell("row 2; cell 1");
        inner.addCell("row 2; cell 2");
        table.addCell(new Cell().add(inner).setPadding(0));
        document.add(table);

        document.close();
    }
}
