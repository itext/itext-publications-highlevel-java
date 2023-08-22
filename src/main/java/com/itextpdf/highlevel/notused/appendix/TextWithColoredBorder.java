package com.itextpdf.highlevel.notused.appendix;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import java.io.File;
import java.io.IOException;

public class TextWithColoredBorder {

    public static final String DEST = "results/appendix/jekyll_hyde_text.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TextWithColoredBorder().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf);
        document.add(new Paragraph("Paragraph with orange border").setBorder(new SolidBorder(ColorConstants.ORANGE, 5)));
        Text text = new Text("Text with orange border").setBorder(new SolidBorder(ColorConstants.ORANGE, 5));
        document.add(new Paragraph(text));
        Link link = new Link("Link with orange border", PdfAction.createURI("http://itextpdf.com"));
        link.setBorder(new SolidBorder(ColorConstants.ORANGE, 5));
        document.add(new Paragraph(link));
        document.close();
    }
}
