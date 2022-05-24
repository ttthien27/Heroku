package com.webservice.lucene;

import java.io.File;
import java.io.IOException;
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

import com.webservice.lucene.VNCoreNLP;
import com.webservice.dto.DataTextHighlight;
import com.webservice.lucene.Stopword;


public class Searcher {
	
	IndexSearcher indexSearcher;
    //QueryParser queryParser;
    Query query;

    public List<com.webservice.dto.DocumentSearch> Search(String pathIndex, String search) {
        List<com.webservice.dto.DocumentSearch> list = new ArrayList<com.webservice.dto.DocumentSearch>();
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

            String field = "paragraphAnalyzer";
            QueryParser parser = new QueryParser(field, a);
            String queryString = VNCharacterUtils.removeAccent(VNCoreNLP.useVNCoreNLP(search));
            //String queryString = (search);
            Query query = parser.parse(queryString);
            System.out.println(query);

            Directory dir = FSDirectory.open(new File(pathIndex).toPath());
            IndexReader index = DirectoryReader.open(dir);

            indexSearcher = new IndexSearcher(index);

            int top = 10;
            TopDocs docs = indexSearcher.search(query, top);

            //System.out.printf("%-10s%-150s\n", "Rank", "Description", "Score", "Title");
            //int rank = 1;
            for (ScoreDoc scoreDoc : docs.scoreDocs) {
                //double score = scoreDoc.score;
                Document documentFromSearch = indexSearcher.doc(scoreDoc.doc);
                String docId = documentFromSearch.get("docId");
                String url = documentFromSearch.get("url");
                String title = documentFromSearch.get("title");
                String description = documentFromSearch.get("description");
                String paragraph = documentFromSearch.get("paragraph");
                
                int count = 0;
                DataTextHighlight dta = getPara(search, paragraph, "...", 0, count);
                //System.out.println(str);
                
                //str = str + rank + " " + description + "@";
                //System.out.printf("%-10d%-150s\n", rank, description, score, title);
                list.add(new com.webservice.dto.DocumentSearch(docId,url,title,description,dta.getParagraphShort(),dta.getTermQuery()));
                //rank++;
            }
            index.close();
            dir.close();
        } catch (Exception e) {
            System.err.println("err: seacher" + e);
        }
        //System.out.println(str);
        
        return list;
    }
    
    static DataTextHighlight getPara(String query, String paragraph, String para0, int time, int count) throws IOException {

        String t = Stopword.removeStopwords(VNCoreNLP.useVNCoreNLP(query.toLowerCase()));
        String t2 = (VNCoreNLP.useVNCoreNLP(query.toLowerCase()));
        //System.out.println(t);
        //System.out.println(t2);
        String listTerm = "";
        String[] ts = t.split(" ");
        int dem = 0;
        //String para0 = "...";
        //int count = 0;
        if (count < 3) {
            for (int j = 0; j < ts.length; j++) {
                boolean ktr = false;
                //System.out.println(ts[j].replaceAll("_", " "));
                if (paragraph.indexOf(ts[j].replaceAll("_", " ")) != -1) {
                    int i = paragraph.indexOf(ts[j].replaceAll("_", " "));
                    if (time != 0) {
                        i = paragraph.indexOf(ts[j].replaceAll("_", " "), i + ts[j].length());
                        if(i==-1) continue;
                    }
                    //System.out.println("----------1------------");
                    //int i = paragraph.indexOf("Giảng viên");
                    if (i > 40 && i != -1) {
                    	listTerm = listTerm + ts[j] + "@";
                        int kc = 0;
                        int kc_startIndex = -1;
                        for (int k = i; k >= 0; k--) {
                            char c = paragraph.charAt(k);
                            char b = ' ';
                            if (kc == 8) {
                                break;
                            }
                            if (c == b) {
                                kc++;
                                kc_startIndex = k;
                            };
                        }
                        kc = 0;
                        int kc_endIndex = -1;
                        for (int k = i; k < paragraph.length(); k++) {
                            char c = paragraph.charAt(k);
                            char b = ' ';
                            if (kc == 8) {
                                break;
                            }
                            if (c == b) {
                                kc++;
                                kc_endIndex = k;
                            };

                        }
                        String t_sb = paragraph.substring(kc_startIndex + 1, kc_endIndex);
                        para0 = para0 + t_sb;
                        dem++;
                        ktr = true;
                        //System.out.println("..." + t_sb + "...");
                    } else if (i != -1) {
                    	listTerm = listTerm + ts[j] + "@";
                        int kc = 0;
                        int kc_endIndex = -1;
                        for (int k = i; k <= paragraph.length(); k++) {
                            char c = paragraph.charAt(k);
                            char b = ' ';
                            if (kc == 8) {
                                break;
                            }
                            if (c == b) {
                                kc++;
                                kc_endIndex = k;
                            };
                        }
                        String t_sb = paragraph.substring(i, kc_endIndex);
                        para0 = para0 + t_sb;
                        dem++;
                        ktr = true;
                        //System.out.println("..." + t_sb + "...");
                    }
                }
                if (ktr) {
                    para0 = para0 + "...";
                }
                if ((dem + count) == 3) {
                    break;
                }
            }

            if (dem <= 1) {
                String[] ts2 = t2.split(" ");
                for (int j = 0; j < ts2.length; j++) {
                    boolean ktr = false;
                    //System.out.println(ts[j].replaceAll("_", " "));
                    if (paragraph.indexOf(ts2[j].replaceAll("_", " ")) != -1) {
                        int i = -1;
                        i = paragraph.indexOf(ts2[j].replaceAll("_", " "));
                        if (time != 0) {
                            i = paragraph.indexOf(ts2[j].replaceAll("_", " "), i + ts2[j].length());
                            if(i==-1) continue;
                        }
                        //int i = paragraph.indexOf("giảng viên");
                        if (i > 40 && i != -1) {
                        	listTerm = listTerm + ts2[j] + "@";
                            int kc = 0;
                            int kc_startIndex = -1;
                            for (int k = i; k >= 0; k--) {
                                char c = paragraph.charAt(k);
                                char b = ' ';
                                if (kc == 8) {
                                    break;
                                }
                                if (c == b) {
                                    kc++;
                                    kc_startIndex = k;
                                };
                            }

                            kc = 0;
                            int kc_endIndex = -1;
                            for (int k = i; k < paragraph.length(); k++) {
                                char c = paragraph.charAt(k);
                                char b = ' ';
                                if (kc == 8) {
                                    break;
                                }
                                if (c == b) {
                                    kc++;
                                    kc_endIndex = k;
                                };
                            }
                            String t_sb = paragraph.substring(kc_startIndex + 1, kc_endIndex);
                            para0 = para0 + t_sb;
                            dem++;
                            ktr = true;
                            //System.out.println("..." + t_sb + "...");
                        } else if (i != -1) {
                        	listTerm = listTerm + ts2[j] + "@";
                            int kc = 0;
                            int kc_endIndex = -1;
                            for (int k = i; k < paragraph.length(); k++) {
                                char c = paragraph.charAt(k);
                                char b = ' ';
                                if (kc == 8) {
                                    break;
                                }
                                if (c == b) {
                                    kc++;
                                    kc_endIndex = k;
                                };
                            }
                            String t_sb = paragraph.substring(i, kc_endIndex);
                            para0 = para0 + t_sb;
                            dem++;
                            ktr = true;
                            //System.out.println("..." + t_sb + "...");
                        }
                    }
                    if (ktr) {
                        para0 = para0 + "...";
                    }
                    if ((dem + count) == 3) {
                        break;
                    }
                }
            }
            if ((dem + count) == 2&&time<3) {
            	DataTextHighlight dta = new DataTextHighlight();
            	dta = getPara(query, paragraph, para0, time+1, dem);
            	para0 = para0 + dta.getParagraphShort();
            	listTerm = listTerm + dta.getTermQuery()+"@";
            }
        }
        //System.out.println(para0);
        return new DataTextHighlight(para0,listTerm.substring(0, listTerm.length()-1));
    }
    
    public List<com.webservice.dto.Document> SearchDocumentCategory(String pathIndex, String search) {
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

            String field = "paragraphAnalyzer";
            QueryParser parser = new QueryParser(field, a);
            String queryString = VNCharacterUtils.removeAccent(VNCoreNLP.useVNCoreNLP(search));
            //String queryString = (search);
            Query query = parser.parse(queryString);
            System.out.println(query);

            Directory dir = FSDirectory.open(new File(pathIndex).toPath());
            IndexReader index = DirectoryReader.open(dir);

            indexSearcher = new IndexSearcher(index);

            int top = 20;
            TopDocs docs = indexSearcher.search(query, top);

            //System.out.printf("%-10s%-150s\n", "Rank", "Description", "Score", "Title");
            //int rank = 1;
            for (ScoreDoc scoreDoc : docs.scoreDocs) {
                //double score = scoreDoc.score;
                Document documentFromSearch = indexSearcher.doc(scoreDoc.doc);
                String docId = documentFromSearch.get("docId");
                String url = documentFromSearch.get("url");
                String title = documentFromSearch.get("title");
                String description = documentFromSearch.get("description");
                //String paragraph = documentFromSearch.get("paragraph");
                
                int count = 0;
                //DataTextHighlight dta = getPara(search, paragraph, "...", 0, count);
                //System.out.println(str);
                
                //str = str + rank + " " + description + "@";
                //System.out.printf("%-10d%-150s\n", rank, description, score, title);
                list.add(new com.webservice.dto.Document(docId,url,title, description));
                //rank++;
            }
            index.close();
            dir.close();
        } catch (Exception e) {
            System.err.println("err: seacher" + e);
        }
        //System.out.println(str);
        
        return list;
    }
    
    public List<com.webservice.dto.Document> SearchDocumentTitle(String pathIndex, String search) {
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

            String field = "titleAnalyzer";
            QueryParser parser = new QueryParser(field, a);
            String queryString = VNCharacterUtils.removeAccent(VNCoreNLP.useVNCoreNLP(search));
            //String queryString = (search);
            Query query = parser.parse(queryString);
            System.out.println(query);

            Directory dir = FSDirectory.open(new File(pathIndex).toPath());
            IndexReader index = DirectoryReader.open(dir);

            indexSearcher = new IndexSearcher(index);

            int top = 100;
            TopDocs docs = indexSearcher.search(query, top);

            //System.out.printf("%-10s%-150s\n", "Rank", "Description", "Score", "Title");
            //int rank = 1;
            for (ScoreDoc scoreDoc : docs.scoreDocs) {
                //double score = scoreDoc.score;
                Document documentFromSearch = indexSearcher.doc(scoreDoc.doc);
                String docId = documentFromSearch.get("docId");
                String url = documentFromSearch.get("url");
                String title = documentFromSearch.get("title");
                String description = documentFromSearch.get("description");
                //String paragraph = documentFromSearch.get("paragraph0");
                
                int count = 0;
                //DataTextHighlight dta = getPara(search, paragraph, "...", 0, count);
                //System.out.println(str);
                
                //str = str + rank + " " + description + "@";
                //System.out.printf("%-10d%-150s\n", rank, description, score, title);
                list.add(new com.webservice.dto.Document(docId,url,title, description));
                //rank++;
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
