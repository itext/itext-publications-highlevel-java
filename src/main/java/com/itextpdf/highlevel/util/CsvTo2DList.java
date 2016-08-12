/*
 * This example was written by Bruno Lowagie
 * in the context of the book: iText 7 building blocks
 */
package com.itextpdf.highlevel.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * @author iText
 */
public class CsvTo2DList {
   
    public static final List<List<String>> convert(String src, String separator) throws IOException {
        List<List<String>> resultSet = new ArrayList<>();
        
        BufferedReader br = new BufferedReader(
            new InputStreamReader(new FileInputStream(src), "UTF8"));
        String line;
        List record;
        while ((line = br.readLine()) != null) {
            StringTokenizer tokenizer = new StringTokenizer(line, separator);
            record = new ArrayList<>();
            while (tokenizer.hasMoreTokens()) {
                record.add(tokenizer.nextToken());
            }
            resultSet.add(record);
        }
        return resultSet;
    }
    
}
