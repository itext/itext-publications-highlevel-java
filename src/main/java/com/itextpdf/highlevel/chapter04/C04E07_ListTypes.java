/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter04;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.property.ListNumberingType;
import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C04E07_ListTypes {
    
    public static final String DEST = "results/chapter04/list_types.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C04E07_ListTypes().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        
        // Initialize document
        PageSize pagesize = PageSize.A6.rotate();
        Document document = new Document(pdf, pagesize);
        //Set column parameters
        float offSet = 36;
        float gutter = 23;
        float columnWidth = (pagesize.getWidth() - offSet * 2) / 2 - gutter;
        float columnHeight = pagesize.getHeight() - offSet * 2;

        //Define column areas
        Rectangle[] columns = {
            new Rectangle(offSet, offSet, columnWidth, columnHeight),
            new Rectangle(offSet + columnWidth + gutter, offSet, columnWidth, columnHeight)};
        document.setRenderer(new ColumnDocumentRenderer(document, columns));    

        List list = new List();
        list.add("Dr. Jekyll");
        list.add("Mr. Hyde");
        document.add(list);
        
        list = new List(ListNumberingType.DECIMAL);
        list.add("Dr. Jekyll");
        list.add("Mr. Hyde");
        document.add(list);
        
        list = new List(ListNumberingType.ENGLISH_LOWER);
        list.add("Dr. Jekyll");
        list.add("Mr. Hyde");
        document.add(list);
        
        list = new List(ListNumberingType.ENGLISH_UPPER);
        list.add("Dr. Jekyll");
        list.add("Mr. Hyde");
        document.add(list);
        
        list = new List(ListNumberingType.GREEK_LOWER);
        list.add("Dr. Jekyll");
        list.add("Mr. Hyde");
        document.add(list);
        
        
        list = new List(ListNumberingType.GREEK_UPPER);
        list.add("Dr. Jekyll");
        list.add("Mr. Hyde");
        document.add(list);
        
        list = new List(ListNumberingType.ROMAN_LOWER);
        list.add("Dr. Jekyll");
        list.add("Mr. Hyde");
        document.add(list);
        
        list = new List(ListNumberingType.ROMAN_UPPER);
        list.add("Dr. Jekyll");
        list.add("Mr. Hyde");
        document.add(list);
        
        list = new List(ListNumberingType.ZAPF_DINGBATS_1);
        list.add("Dr. Jekyll");
        list.add("Mr. Hyde");
        document.add(list);
        
        list = new List(ListNumberingType.ZAPF_DINGBATS_2);
        list.add("Dr. Jekyll");
        list.add("Mr. Hyde");
        document.add(list);
        
        list = new List(ListNumberingType.ZAPF_DINGBATS_3);
        list.add("Dr. Jekyll");
        list.add("Mr. Hyde");
        document.add(list);
        
        list = new List(ListNumberingType.ZAPF_DINGBATS_4);
        list.add("Dr. Jekyll");
        list.add("Mr. Hyde");
        document.add(list);
        //Close document
        document.close();
    }
}
