package com.webservice.lucene;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Searcher {
	IndexSearcher indexSearcher;
    //QueryParser queryParser;
    Query query;

    public List<com.webservice.dto.Document> Searcher(String pathIndex, String search) {
        String str = "";
        List<com.webservice.dto.Document> list = new ArrayList<com.webservice.dto.Document>();
        try {
            //String pathIndex = "D:\\lucene\\IndexJson";

            //CharArraySet stopWords = Stopword.getStopwords();

            Analyzer a = new Analyzer() {
                @Override
                protected Analyzer.TokenStreamComponents createComponents(String fieldName) {
                    // Step 1: tokenization (Lucene's StandardTokenizer is suitable for most text retrieval occasions)
                    Analyzer.TokenStreamComponents ts = new Analyzer.TokenStreamComponents(new StandardTokenizer());
                    // Step 2: transforming all tokens into lowercased ones (recommended for the majority of the problems)
                    ts = new Analyzer.TokenStreamComponents(ts.getTokenizer(), new LowerCaseFilter(ts.getTokenStream()));

                    //ts = new Analyzer.TokenStreamComponents(ts.getTokenizer(), new StopFilter(ts.getTokenStream(), stopWords));
                    return ts;
                }
            };

            String field = "paragraph";
            QueryParser parser = new QueryParser(field, a);
            //String queryString = useVNCoreNLP(search);
            String queryString = (search);
            Query query = parser.parse(queryString);
            System.out.println(query);

            Directory dir = FSDirectory.open(new File(pathIndex).toPath());
            IndexReader index = DirectoryReader.open(dir);

            indexSearcher = new IndexSearcher(index);

            int top = 10;
            TopDocs docs = indexSearcher.search(query, top);

            System.out.printf("%-10s%-150s\n", "Rank", "Description", "Score", "Title");
            int rank = 1;
            for (ScoreDoc scoreDoc : docs.scoreDocs) {
                double score = scoreDoc.score;
                Document documentFromSearch = indexSearcher.doc(scoreDoc.doc);
                String title = documentFromSearch.get("title");
                String description = documentFromSearch.get("description");
                //str = str + rank + " " + description + "@";
                //System.out.printf("%-10d%-150s\n", rank, description, score, title);
                list.add(new com.webservice.dto.Document(title, description));
                rank++;
            }
            index.close();
            dir.close();
        } catch (Exception e) {
            System.err.println("err: seacher" + e);
        }
        //System.out.println(str);
        
        return list;
    }
}
