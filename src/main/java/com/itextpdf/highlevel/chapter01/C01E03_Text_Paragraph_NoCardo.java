package com.itextpdf.highlevel.chapter01;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.font.PdfFontFactory.EmbeddingStrategy;
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
public class C01E03_Text_Paragraph_NoCardo {
    
    public static final String DEST = "results/chapter01/text_paragraph_no_cardo.pdf";
    
    public static final String REGULAR = "src/main/resources/fonts/Cardo-Regular.ttf";
    public static final String BOLD = "src/main/resources/fonts/Cardo-Bold.ttf";
    public static final String ITALIC = "src/main/resources/fonts/Cardo-Italic.ttf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C01E03_Text_Paragraph_NoCardo().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);
        
        // Add content: the fonts aren't embedded! Don't do this!
        PdfFont font = PdfFontFactory.createFont(REGULAR, PdfEncodings.WINANSI, EmbeddingStrategy.PREFER_NOT_EMBEDDED);
        PdfFont bold = PdfFontFactory.createFont(BOLD, PdfEncodings.WINANSI, EmbeddingStrategy.PREFER_NOT_EMBEDDED);
        PdfFont italic = PdfFontFactory.createFont(ITALIC, PdfEncodings.WINANSI, EmbeddingStrategy.PREFER_NOT_EMBEDDED);
        Text title = new Text("The Strange Case of Dr. Jekyll and Mr. Hyde").setFont(bold);
        Text author = new Text("Robert Louis Stevenson").setFont(font);
        Paragraph p = new Paragraph().setFont(italic).add(title).add(" by ").add(author);
        document.add(p);
        
        //Close document
        document.close();
    }
}
