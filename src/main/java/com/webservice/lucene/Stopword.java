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
	   
}
