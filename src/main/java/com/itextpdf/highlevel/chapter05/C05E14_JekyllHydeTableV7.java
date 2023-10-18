package com.itextpdf.highlevel.chapter05;

import com.itextpdf.highlevel.util.CsvTo2DList;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class C05E14_JekyllHydeTableV7 {

    public static final String SRC = "src/main/resources/data/jekyll_hyde.csv";
    public static final String DEST = "results/chapter05/jekyll_hyde_table7.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E14_JekyllHydeTableV7().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        Table table = new Table(UnitValue.createPercentArray(new float[]{3, 2, 14, 9, 4, 3}));
        table.setWidth(UnitValue.createPercentValue(100));
        List<List<String>> resultSet = CsvTo2DList.convert(SRC, "|");
        List<String> header = resultSet.remove(0);
        for (String field : header) {
            table.addHeaderCell(field);
        }
        for (List<String> record : resultSet) {
            table.addCell(record.get(0));
            table.addCell(record.get(1));
            Cell cell = new Cell().add(new Paragraph(record.get(2)));
            cell.setNextRenderer(new RunlengthRenderer(cell, record.get(5)));
            table.addCell(cell);
            table.addCell(record.get(3));
            table.addCell(record.get(4));
            table.addCell(record.get(5));
        }
        document.add(table);
        document.close();
    }

    private class RunlengthRenderer extends CellRenderer {
        private int runlength;

        public RunlengthRenderer(Cell modelElement, String duration) {
            super(modelElement);
            if (duration.trim().isEmpty()) runlength = 0;
            else runlength = Integer.parseInt(duration);
        }

        @Override
        public IRenderer getNextRenderer() {
            return new RunlengthRenderer((Cell) getModelElement(), String.valueOf(runlength));
        }

        @Override
        public void drawBackground(DrawContext drawContext) {
            if (runlength == 0) return;
            PdfCanvas canvas = drawContext.getCanvas();
            canvas.saveState();
            if (runlength < 90) {
                canvas.setFillColor(ColorConstants.GREEN);
            } else if (runlength > 240) {
                runlength = 240;
                canvas.setFillColor(ColorConstants.RED);
            } else {
                canvas.setFillColor(ColorConstants.ORANGE);
            }
            Rectangle rect = getOccupiedAreaBBox();
            canvas.rectangle(rect.getLeft(), rect.getBottom(),
                    rect.getWidth() * runlength / 240, rect.getHeight());
            canvas.fill();
            canvas.restoreState();
            super.drawBackground(drawContext);
        }
    }
}
