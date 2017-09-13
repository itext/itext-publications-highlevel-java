/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2017 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 building blocks
 */
package com.itextpdf.highlevel.chapter05;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.test.annotations.WrapToTest;
import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class C05E04_ColumnHeights {
    
    public static final String DEST = "results/chapter05/column_heights.pdf";
       
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E04_ColumnHeights().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        
        Paragraph p =
            new Paragraph("The Strange Case of\nDr. Jekyll\nand\nMr. Hyde")
            .setBorder(new DashedBorder(0.3f));
        
        // Initialize document
        Document document = new Document(pdf);
        Table table = new Table(UnitValue.createPercentArray(new float[]{1}));
        table.setWidthPercent(100);
        table.addCell(p);
        Cell cell = new Cell().setHeight(45).add(p);
        table.addCell(cell);
        cell = new Cell().setMinHeight(45).add(p);
        table.addCell(cell);
        cell = new Cell().setMinHeight(135).add(p);
        table.addCell(cell);
        cell = new Cell().setMaxHeight(45).add(p);
        table.addCell(cell);
        cell = new Cell().setMaxHeight(135).add(p);
        table.addCell(cell);
        cell = new Cell().add(p).setRotationAngle(Math.PI / 6);
        table.addCell(cell);
        document.add(table);
        document.close();
    }
}
