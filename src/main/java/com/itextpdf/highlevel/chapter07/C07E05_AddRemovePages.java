/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package com.itextpdf.highlevel.chapter07;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author iText
 */
public class C07E05_AddRemovePages {

    public static final String SRC = "src/main/resources/pdfs/jekyll_hyde_bookmarked.pdf";
    public static final String DEST = "results/chapter07/jekyll_hyde_updated.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C07E05_AddRemovePages().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest) throws IOException {
        PdfReader reader = new PdfReader(src);
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(reader, writer);
        pdf.addEventHandler(PdfDocumentEvent.INSERT_PAGE, new AddPageHandler());
        pdf.addEventHandler(PdfDocumentEvent.REMOVE_PAGE, new RemovePageHandler());
        pdf.addNewPage(1, PageSize.A4);
        int total = pdf.getNumberOfPages();
        for (int i = 9; i <= total; i++) {
            pdf.removePage(9);
            if (i == 12)
                pdf.removeAllHandlers();
        }
        pdf.close();
    }

    protected class AddPageHandler implements IEventHandler {

        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdf = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            PdfCanvas pdfCanvas = new PdfCanvas(page);
            Canvas canvas = new Canvas(pdfCanvas, pdf, page.getPageSize());
            canvas.add(new Paragraph().add(docEvent.getType()));
        }

    }

    protected class RemovePageHandler implements IEventHandler {

        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            System.out.println(docEvent.getType());
        }

    }
}
