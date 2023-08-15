package com.itextpdf.highlevel.chapter05;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.borders.DottedBorder;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;
import com.itextpdf.layout.renderer.TableRenderer;

import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C05E06_CellBorders {

    private class RoundedCornersCellRenderer extends CellRenderer {
        public RoundedCornersCellRenderer(Cell modelElement) {
            super(modelElement);
        }

        @Override
        public void drawBorder(DrawContext drawContext) {
            Rectangle occupiedAreaBBox = getOccupiedAreaBBox();
            UnitValue[] margins = getMargins();
            Rectangle rectangle = applyMargins(occupiedAreaBBox, margins, false);
            PdfCanvas canvas = drawContext.getCanvas();
            canvas.roundRectangle(rectangle.getX() + 1, rectangle.getY() + 1,
                    rectangle.getWidth() - 2, rectangle.getHeight() - 2, 5).stroke();
            super.drawBorder(drawContext);
        }

        @Override
        protected Rectangle applyMargins(Rectangle rect, UnitValue[] margins, boolean reverse) {
            return rect.<Rectangle>applyMargins(margins[0].getValue(), margins[1].getValue(), margins[2].getValue(), margins[3].getValue(), reverse);
        }
    }

    private class RoundedCornersTableRenderer extends TableRenderer {

        public RoundedCornersTableRenderer(Table modelElement) {
            super(modelElement);
        }



        @Override
        protected void drawBorders(DrawContext drawContext) {
            Rectangle occupiedAreaBBox = getOccupiedAreaBBox();
            UnitValue[] margins = getMargins();
            Rectangle rectangle = applyMargins(occupiedAreaBBox, margins, false);
            PdfCanvas canvas = drawContext.getCanvas();
            canvas.roundRectangle(rectangle.getX() + 1, rectangle.getY() + 1,
                    rectangle.getWidth() - 2, rectangle.getHeight() -2, 5).stroke();
            super.drawBorder(drawContext);
        }

    }

    private class RoundedCornersCell extends Cell {
        public RoundedCornersCell() {
            super();
            setBorder(Border.NO_BORDER);
            setMargin(2);
        }

        public RoundedCornersCell(int rowspan, int colspan) {
            super(rowspan, colspan);
            setBorder(Border.NO_BORDER);
            setMargin(2);
            setVerticalAlignment(VerticalAlignment.MIDDLE);
        }

        @Override
        protected IRenderer makeNewRenderer() {
            return new C05E06_CellBorders.RoundedCornersCellRenderer(this);
        }
    }

    public static final String DEST = "results/chapter05/cell_borders.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E06_CellBorders().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);

        Table table1 = new Table(UnitValue.createPercentArray(new float[] { 2, 1, 1 }));
        table1.setWidth(UnitValue.createPercentValue(80));
        table1.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table1.addCell(new Cell(1, 3).add(new Paragraph("Cell with colspan 3")).setPadding(10).setMargin(5).setBorder(new DashedBorder
                (0.5f)));
        table1.addCell(new Cell(2, 1).add(new Paragraph("Cell with rowspan 2")).setMarginTop(5).setMarginBottom(5).setBorderBottom
                (new DottedBorder(0.5f)).setBorderLeft(new DottedBorder(0.5f)));
        table1.addCell(new Cell().add(new Paragraph("row 1; cell 1")).setBorder(new DottedBorder(ColorConstants.ORANGE, 0.5f)));
        table1.addCell(new Cell().add(new Paragraph("row 1; cell 2")));
        table1.addCell(new Cell().add(new Paragraph("row 2; cell 1")).setMargin(10).setBorderBottom(new SolidBorder(2)));
        table1.addCell(new Cell().add(new Paragraph("row 2; cell 2")).setPadding(10).setBorderBottom(new SolidBorder(2)));
        document.add(table1);
        Table table2 = new Table(UnitValue.createPercentArray(new float[] { 2, 1, 1 }));
        table2.setMarginTop(10);
        table2.setBorder(new SolidBorder(1));
        table2.setWidth(UnitValue.createPercentValue(80));
        table2.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table2.addCell(new Cell(1, 3).add(new Paragraph("Cell with colspan 3")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell(2, 1).add(new Paragraph("Cell with rowspan 2")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().add(new Paragraph("row 1; cell 1")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().add(new Paragraph("row 1; cell 2")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().add(new Paragraph("row 2; cell 1")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().add(new Paragraph("row 2; cell 2")).setBorder(Border.NO_BORDER));
        document.add(table2);
        Table table3 = new Table(UnitValue.createPercentArray(new float[] { 2, 1, 1 }));
        table3.setMarginTop(10);
        table3.setWidth(UnitValue.createPercentValue(80));
        table3.setHorizontalAlignment(HorizontalAlignment.CENTER);
        Cell cell = new C05E06_CellBorders.RoundedCornersCell(1, 3).add(new Paragraph("Cell with colspan 3"));
        table3.addCell(cell);
        cell = new C05E06_CellBorders.RoundedCornersCell(2, 1).add(new Paragraph("Cell with rowspan 2"));
        table3.addCell(cell);
        cell = new C05E06_CellBorders.RoundedCornersCell().add(new Paragraph("row 1; cell 1"));
        table3.addCell(cell);
        cell = new C05E06_CellBorders.RoundedCornersCell().add(new Paragraph("row 1; cell 2"));
        table3.addCell(cell);
        cell = new C05E06_CellBorders.RoundedCornersCell().add(new Paragraph("row 2; cell 1"));
        table3.addCell(cell);
        cell = new C05E06_CellBorders.RoundedCornersCell().add(new Paragraph("row 2; cell 2"));
        table3.addCell(cell);
        table3.setNextRenderer(new RoundedCornersTableRenderer(table3));
        document.add(table3);
        document.close();
    }
}
