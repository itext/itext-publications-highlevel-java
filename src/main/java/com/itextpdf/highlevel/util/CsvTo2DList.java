/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 layout objects
 */
package com.itextpdf.highlevel.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * @author iText
 */
public class CsvTo2DList {
   
    public static final List<List<String>> convert(String src, String separator) throws IOException {
        List<List<String>> resultSet = new ArrayList<List<String>>();
        
        BufferedReader br = new BufferedReader(new FileReader(src));
        String line;
        List record;
        while ((line = br.readLine()) != null) {
            StringTokenizer tokenizer = new StringTokenizer(line, separator);
            record = new ArrayList<String>();
            while (tokenizer.hasMoreTokens()) {
                record.add(tokenizer.nextToken());
            }
            resultSet.add(record);
        }
        return resultSet;
    }
    
}
