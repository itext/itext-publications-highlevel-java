/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.chapter03;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.image.Jbig2ImageData;
import com.itextpdf.io.image.TiffImageData;
import com.itextpdf.io.source.IRandomAccessSource;
import com.itextpdf.io.source.RandomAccessFileOrArray;
import com.itextpdf.io.source.RandomAccessSourceFactory;
import com.itextpdf.io.util.UrlUtil;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.test.annotations.WrapToTest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class C03E19_PagedImages {
    public static final String TEST1 = "src/main/resources/img/test/animated_fox_dog.gif";
    public static final String TEST2 = "src/main/resources/img/test/amb.jb2";
    public static final String TEST3 = "src/main/resources/img/test/marbles.tif";
    public static final String DEST = "results/chapter03/paged_images.pdf";
    
    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C03E19_PagedImages().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(
            new PdfWriter(new FileOutputStream(dest)));
        Document document = new Document(pdf);
        
        Image img;
        // Animated GIF
        URL url1 = UrlUtil.toURL(TEST1);
        List<ImageData> list = ImageDataFactory.createGifFrames(url1);
        for (ImageData data : list) {
            img = new Image(data);
            document.add(img);
        }
        
        // JBIG2
        URL url2 = UrlUtil.toURL(TEST2);
        IRandomAccessSource ras2 =
            new RandomAccessSourceFactory().createSource(url2);
        RandomAccessFileOrArray raf2 = new RandomAccessFileOrArray(ras2);
        int pages2 = Jbig2ImageData.getNumberOfPages(raf2);
        for (int i = 1; i <= pages2; i++) {
            img = new Image(ImageDataFactory.createJbig2(url2, i));
            document.add(img);
        }
            
        
        // TIFF
        URL url3 = UrlUtil.toURL(TEST3);
        IRandomAccessSource ras3 =
            new RandomAccessSourceFactory().createSource(url3);
        RandomAccessFileOrArray raf3 = new RandomAccessFileOrArray(ras3);
        int pages3 = TiffImageData.getNumberOfPages(raf3);
        for (int i = 1; i <= pages3; i++) {
            img = new Image(
                ImageDataFactory.createTiff(url3, true, i, true));
            document.add(img);
        }
        document.close();
    }
}
