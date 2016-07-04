/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter05;

import com.itextpdf.highlevel.util.CsvTo2DList;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.TableRenderer;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author iText
 */
public class C05E13_JekyllHydeTableV6 {
    
    public static final String SRC = "src/main/resources/data/jekyll_hyde.csv";
    public static final String DEST = "results/chapter05/jekyll_hyde_table6"
            + ".pdf";
       
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E13_JekyllHydeTableV6().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        
        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        List<List<String>> resultSet = CsvTo2DList.convert(SRC, "|");
        List<String> header = resultSet.remove(0);
        Table table = new Table(new float[]{3, 2, 14, 9, 4, 3});
        int nRows = resultSet.size();
        table.setNextRenderer(new AlternatingBackgroundTableRenderer(
            table, new Table.RowRange(0, nRows - 1)));
        table.setWidthPercent(100);
        for (String field : header) {
            table.addHeaderCell(field);
        }
        for (List<String> record : resultSet) {
            for (String field : record) {
                table.addCell(field);
            }
        }
        document.add(table);
        document.close();
    }
}

class AlternatingBackgroundTableRenderer extends TableRenderer {
    private boolean isOdd = true;

    public AlternatingBackgroundTableRenderer(Table modelElement, Table.RowRange rowRange) {
        super(modelElement, rowRange);
    }

    public AlternatingBackgroundTableRenderer(Table modelElement) {
        super(modelElement);
    }

    @Override
    protected TableRenderer[] split(int row, boolean hasContent) {
        return super.split(row, hasContent);
    }

    @Override
    public AlternatingBackgroundTableRenderer getNextRenderer() {
        return new AlternatingBackgroundTableRenderer((Table) modelElement);
    }

    @Override
    public void draw(DrawContext drawContext) {
        for (int i = 0; i < rows.size() && null != rows.get(i) && null != rows.get(i)[0]; i++) {
            CellRenderer[] renderers = rows.get(i);
            Rectangle rect = new Rectangle(renderers[0].getOccupiedArea().getBBox().getLeft(),
                    renderers[0].getOccupiedArea().getBBox().getBottom(),
                    renderers[renderers.length - 1].getOccupiedArea().getBBox().getRight() -
                            renderers[0].getOccupiedArea().getBBox().getLeft(),
                    renderers[0].getOccupiedArea().getBBox().getHeight());
            PdfCanvas canvas = drawContext.getCanvas();
            canvas.saveState();
            if (isOdd) {
                canvas.setFillColor(Color.LIGHT_GRAY);
                isOdd = false;
            } else {
                canvas.setFillColor(Color.YELLOW);
                isOdd = true;
            }
            canvas.rectangle(rect);
            canvas.fill();
            canvas.stroke();
            canvas.restoreState();
        }
        super.draw(drawContext);
    }
}