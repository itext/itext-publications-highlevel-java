package com.itextpdf.highlevel.chapter01;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import java.io.File;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class C01E08_SimulateBoldItalic {
    
    public static final String DEST = "results/chapter01/simulate_bold_italic.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C01E08_SimulateBoldItalic().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);
        
        // Add content
        // We don't suggest usage of simulateItalic() method to reach text obliquity since the result is written with the usual
        // rather than the italic font: we only emulate "obliquity". It's recommended to use an actual italic font instead.
        Text title1 = new Text("The Strange Case of ").simulateItalic();
        // We don't suggest usage of simulateBold() method to reach text thickness since the result is written with the usual
        // rather than the bold font: we only emulate "thickness". It's recommended to use an actual bold font instead.
        Text title2 = new Text("Dr. Jekyll and Mr. Hyde").simulateBold();
        Text author = new Text("Robert Louis Stevenson").simulateItalic().simulateBold();
        Paragraph p = new Paragraph()
                .add(title1).add(title2).add(" by ").add(author);
        document.add(p);
        
        //Close document
        document.close();
    }
}
