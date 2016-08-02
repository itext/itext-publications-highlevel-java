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
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.property.ListNumberingType;
import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C04E10_NestedLists {
    
    public static final String DEST = "results/chapter04/nested_list.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C04E10_NestedLists().createPdf(DEST);
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
        
        List list1 = new List(ListNumberingType.DECIMAL);
        List listEL = new List(ListNumberingType.ENGLISH_LOWER);
        listEL.add("Dr. Jekyll");
        listEL.add("Mr. Hyde");
        ListItem liEL = new ListItem();
        liEL.add(listEL);
        list1.add(liEL);
        List listEU = new List(ListNumberingType.ENGLISH_UPPER);
        listEU.add("Dr. Jekyll");
        listEU.add("Mr. Hyde");
        ListItem liEU = new ListItem();
        liEU.add(listEU);
        list1.add(liEU);
        ListItem li1 = new ListItem();
        li1.add(list1);
        list.add(li1);
        
        ListItem li = new ListItem();
        List listGL = new List(ListNumberingType.GREEK_LOWER);
        listGL.add("Dr. Jekyll");
        listGL.add("Mr. Hyde");
        li.add(listGL);
        List listGU = new List(ListNumberingType.GREEK_UPPER);
        listGU.add("Dr. Jekyll");
        listGU.add("Mr. Hyde");
        li.add(listGU);
        List listRL = new List(ListNumberingType.ROMAN_LOWER);
        listRL.add("Dr. Jekyll");
        listRL.add("Mr. Hyde");
        li.add(listRL);
        List listRU = new List(ListNumberingType.ROMAN_UPPER);
        listRU.add("Dr. Jekyll");
        listRU.add("Mr. Hyde");
        li.add(listRU);
        list.add(li);
        
        List listZ1 = new List(ListNumberingType.ZAPF_DINGBATS_1);
        listZ1.add("Dr. Jekyll");
        listZ1.add("Mr. Hyde");
        ListItem liZ1 = new ListItem();
        liZ1.add(listZ1);
        
        List listZ2 = new List(ListNumberingType.ZAPF_DINGBATS_2);
        listZ2.add("Dr. Jekyll");
        listZ2.add("Mr. Hyde");
        ListItem liZ2 = new ListItem();
        liZ2.add(listZ2);
        
        List listZ3 = new List(ListNumberingType.ZAPF_DINGBATS_3);
        listZ3.add("Dr. Jekyll");
        listZ3.add("Mr. Hyde");
        ListItem liZ3 = new ListItem();
        liZ3.add(listZ3);
        
        List listZ4 = new List(ListNumberingType.ZAPF_DINGBATS_4);
        listZ4.add("Dr. Jekyll");
        listZ4.add("Mr. Hyde");
        ListItem liZ4 = new ListItem();
        liZ4.add(listZ4);
        
        listZ3.add(liZ4);
        listZ2.add(liZ3);
        listZ1.add(liZ2);
        list.add(liZ1);
        document.add(list);
        //Close document
        document.close();
    }
}
