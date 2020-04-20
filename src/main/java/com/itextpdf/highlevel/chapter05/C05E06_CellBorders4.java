package com.itextpdf.highlevel.chapter05;

import java.io.File;
import java.io.IOException;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;
import com.itextpdf.layout.renderer.TableRenderer;
/**
 * @author Bruno Lowagie (iText Software)
 */
public class C05E06_CellBorders4 {

    private class RoundedCornersTableRenderer extends TableRenderer {

        public RoundedCornersTableRenderer(Table modelElement) {
            super(modelElement);
        }

        @Override
        public IRenderer getNextRenderer() {
            return new RoundedCornersTableRenderer((Table)getModelElement());
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
                    rectangle.getWidth() - 2, rectangle.getHeight() -2, 5).stroke();
            super.drawBorder(drawContext);
        }

        @Override
        public IRenderer getNextRenderer() {
            return new RoundedCornersCellRenderer((Cell)getModelElement());
        }

        @Override
        protected Rectangle applyMargins(Rectangle rect, UnitValue[] margins, boolean reverse) {
            return rect.<Rectangle>applyMargins(margins[0].getValue(), margins[1].getValue(), margins[2].getValue(), margins[3].getValue(), reverse);
        }
    }

    private class RoundedCornersCell extends Cell {
        public RoundedCornersCell() {
            super();
            setMargin(2);
        }

        public RoundedCornersCell(int rowspan, int colspan) {
            super(rowspan, colspan);
            setMargin(2);
        }

        @Override
        protected IRenderer makeNewRenderer() {
            return new RoundedCornersCellRenderer(this);
        }
    }

    public static final String DEST = "results/chapter05/cell_borders4.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E06_CellBorders4().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);

        Table table = new Table(UnitValue.createPercentArray(new float[]{2, 1, 1}))
            .setWidth(UnitValue.createPercentValue(80))
            .setHorizontalAlignment(HorizontalAlignment.CENTER)
            .setTextAlignment(TextAlignment.CENTER);
        Cell cell = new RoundedCornersCell(1, 3)
            .add(new Paragraph("Cell with colspan 3"));
        table.addCell(cell);
        cell = new RoundedCornersCell(2, 1)
            .add(new Paragraph("Cell with rowspan 2"));
        table.addCell(cell);
        cell = new RoundedCornersCell()
            .add(new Paragraph("row 1; cell 1"));
        table.addCell(cell);
        cell = new RoundedCornersCell()
            .add(new Paragraph("row 1; cell 2"));
        table.addCell(cell);
        cell = new RoundedCornersCell()
            .add(new Paragraph("row 2; cell 1"));
        table.addCell(cell);
        cell = new RoundedCornersCell()
            .add(new Paragraph("row 2; cell 2"));
        table.addCell(cell);
        table.setNextRenderer(new RoundedCornersTableRenderer(table));
        document.add(table);

        document.close();
    }
}
