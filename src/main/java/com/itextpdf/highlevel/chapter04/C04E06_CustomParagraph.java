package com.itextpdf.highlevel.chapter04;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.CanvasArtifact;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.Background;
import com.itextpdf.layout.properties.Property;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.ParagraphRenderer;
import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C04E06_CustomParagraph {

    class MyParagraphRenderer extends ParagraphRenderer {

        public MyParagraphRenderer(Paragraph modelElement) {
            super(modelElement);
        }

        @Override
        public void drawBackground(DrawContext drawContext) {
            Background background = this.<Background>getProperty(Property.BACKGROUND);
            if (background != null) {
                Rectangle bBox = getOccupiedAreaBBox();
                boolean isTagged =
                    drawContext.isTaggingEnabled();
                if (isTagged) {
                    drawContext.getCanvas().openTag(new CanvasArtifact());
                }
                Rectangle bgArea = applyMargins(bBox, false);
                if (bgArea.getWidth() <= 0 || bgArea.getHeight() <= 0) {
                    return;
                }
                drawContext.getCanvas().saveState()
                    .setFillColor(background.getColor())
                    .roundRectangle(
                    (double)bgArea.getX() - background.getExtraLeft(),
                    (double)bgArea.getY() - background.getExtraBottom(),
                    (double)bgArea.getWidth()
                        + background.getExtraLeft() + background.getExtraRight(),
                    (double)bgArea.getHeight()
                        + background.getExtraTop() + background.getExtraBottom(),
                    5)
                    .fill().restoreState();
                if (isTagged) {
                    drawContext.getCanvas().closeTag();
                }
            }
        }
    }

    public static final String DEST = "results/chapter04/custom_paragraph.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C04E06_CustomParagraph().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);

        Paragraph p1 = new Paragraph(
            "The Strange Case of Dr. Jekyll and Mr. Hyde");
        p1.setBackgroundColor(ColorConstants.ORANGE);
        document.add(p1);

        Paragraph p2 = new Paragraph(
            "The Strange Case of Dr. Jekyll and Mr. Hyde");
        p2.setBackgroundColor(ColorConstants.ORANGE);
        p2.setNextRenderer(new MyParagraphRenderer(p2));
        document.add(p2);

        document.close();
    }
}
