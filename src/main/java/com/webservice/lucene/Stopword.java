package com.webservice.lucene;

import java.util.List;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.lucene.analysis.CharArraySet;

public class Stopword {
	  
	   public static CharArraySet getStopwords() throws IOException{
	       List<String> stopwords = Files.readAllLines(Paths.get("src/main/resources/Txt/vietnamese-stopwords-dash.txt"));
	       CharArraySet charArraySet = new CharArraySet(stopwords, true);
//	       for(String s:stopwords){
//	           charArraySet.add(s);
//	       }
	       return charArraySet;
	   }
	   
	   public static List<String> loadStopwords() throws IOException {
	        //List<String> stopwords = Files.readAllLines(Paths.get("C:\\Users\\Admin\\Downloads\\vietnamese-stopwords-master\\vietnamese-stopwords.txt"));
	        List<String> stopwords = Files.readAllLines(Paths.get("src/main/resources/Txt/vietnamese-stopwords-dash.txt"));
	        return stopwords;
	    }
	   
	   public static String removeStopwords(String str) throws IOException {
	        String[] allWords = str.split(" ");

	        List<String> stopwords = loadStopwords();
	        StringBuilder builder = new StringBuilder();
	        String result = "";
	        for (String word : allWords) {
	            if (!stopwords.contains(word)) {
	                builder.append(word);
	                builder.append(' ');
	            }
	            result = builder.toString().trim();
//	    assertEquals(result, target);
	        }
	        return result;
	    }
	   
}
