/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter05;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Bruno Lowagie (iText Software)
 */
public class C05E15_CellPositioning {
    
    public static final String DEST = "results/chapter05/cell_position.pdf";
       
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E15_CellPositioning().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        
        // Initialize document
        Document document = new Document(pdf);
        
        Cell cell = new Cell().add("The Strange Case of")
            .setWidth(75).setHeight(25)
            .setFontColor(Color.WHITE).setBackgroundColor(Color.BLUE);
        document.add(cell);
        cell = new Cell().add("Dr. Jekyll")
            .setRelativePosition(20, 20, 0, 0);
        cell.setNextRenderer(new RoundedCornersCellRenderer(cell));
        document.add(cell);
        cell = new Cell().add("and")
            .setBackgroundColor(Color.YELLOW)
            .setFixedPosition(56, 690, 40);
        document.add(cell);
        cell = new Cell().add("Mr. Hyde")
            .setBackgroundColor(Color.LIGHT_GRAY)
            .setFixedPosition(110, 690, UnitValue.createPercentValue(50));
        document.add(cell);
        document.close();
    }
    
    private class RoundedCornersCellRenderer extends CellRenderer {
        public RoundedCornersCellRenderer(Cell modelElement) {
            super(modelElement);
        }

        @Override
        public void drawBorder(DrawContext drawContext) {
            Rectangle occupiedAreaBBox = getOccupiedAreaBBox();
            float[] margins = getMargins();
            Rectangle rectangle = applyMargins(occupiedAreaBBox, margins, false);
            PdfCanvas canvas = drawContext.getCanvas();
            canvas.roundRectangle(rectangle.getX() + 1, rectangle.getY() + 1,
                rectangle.getWidth() - 2, rectangle.getHeight() -2, 5).stroke();
            super.drawBorder(drawContext);
        }
    }
}
