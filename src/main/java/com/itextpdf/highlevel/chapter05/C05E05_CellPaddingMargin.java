package com.itextpdf.highlevel.chapter05;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.IRenderer;
import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C05E05_CellPaddingMargin {

    public static final String DEST = "results/chapter05/cell_margin_padding.pdf";

    private class MarginCellRenderer extends CellRenderer {
        public MarginCellRenderer(Cell modelElement) {
            super(modelElement);
        }

        @Override
        public IRenderer getNextRenderer() {
        	return new MarginCellRenderer((Cell)getModelElement());
        }

        @Override
        protected Rectangle applyMargins(Rectangle rect, UnitValue[] margins, boolean reverse) {
            return rect.<Rectangle>applyMargins(margins[0].getValue(), margins[1].getValue(), margins[2].getValue(), margins[3].getValue(), reverse);
        }
    }

    private class MarginCell extends Cell {
        public MarginCell() {
            super();
        }

        public MarginCell(int rowspan, int colspan) {
            super(rowspan, colspan);
        }

        @Override
        protected IRenderer makeNewRenderer() {
            return new MarginCellRenderer(this);
        }
    }

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E05_CellPaddingMargin().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);
        Table table = new Table(UnitValue.createPercentArray(new float[]{2, 1, 1}));
        table.setBackgroundColor(ColorConstants.ORANGE);
        table.setWidth(UnitValue.createPercentValue(80));
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table.addCell(
            new MarginCell(1, 3).add(new Paragraph("Cell with colspan 3"))
                .setPadding(10).setMargin(5).setBackgroundColor(ColorConstants.GREEN));
        table.addCell(new MarginCell(2, 1).add(new Paragraph("Cell with rowspan 2"))
            .setMarginTop(5).setMarginBottom(5).setPaddingLeft(30)
            .setFontColor(ColorConstants.WHITE).setBackgroundColor(ColorConstants.BLUE));
        table.addCell(new MarginCell().add(new Paragraph("row 1; cell 1"))
            .setFontColor(ColorConstants.WHITE).setBackgroundColor(ColorConstants.RED));
        table.addCell(new MarginCell().add(new Paragraph("row 1; cell 2")));
        table.addCell(new MarginCell().add(new Paragraph("row 2; cell 1")).setMargin(10)
            .setFontColor(ColorConstants.WHITE).setBackgroundColor(ColorConstants.RED));
        table.addCell(new MarginCell().add(new Paragraph("row 2; cell 2")).setPadding(10)
            .setFontColor(ColorConstants.WHITE).setBackgroundColor(ColorConstants.RED));
        document.add(table);
        document.close();
    }
}
