package com.itextpdf.highlevel.chapter05;

import java.io.File;
import java.io.IOException;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;
/**
 * @author Bruno Lowagie (iText Software)
 */
public class C05E06_CellBorders3 {

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
            canvas.roundRectangle(rectangle.getX(), rectangle.getY(),
                rectangle.getWidth(), rectangle.getHeight(), 5).stroke();
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
            setBorder(Border.NO_BORDER);
            setMargin(2);
        }

        public RoundedCornersCell(int rowspan, int colspan) {
            super(rowspan, colspan);
            setBorder(Border.NO_BORDER);
            setVerticalAlignment(VerticalAlignment.MIDDLE);
            setMargin(5);
        }

        @Override
        protected IRenderer makeNewRenderer() {
            return new RoundedCornersCellRenderer(this);
        }
    }

    public static final String DEST = "results/chapter05/cell_borders3.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E06_CellBorders3().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);

        Table table = new Table(UnitValue.createPercentArray(new float[]{2, 1, 1}));
        table.setWidth(UnitValue.createPercentValue(80))
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
        document.add(table);

        document.close();
    }
}
