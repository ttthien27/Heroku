package com.webservice.api;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.webservice.dto.DocumentSearch;
import com.webservice.dto.Document;
import com.webservice.lucene.*;



@Controller

public class LuceneAPI {
	
	
	private FileReader fr;

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "Hello World";
    }
	
	/*@RequestMapping(value = "/showStr", method = RequestMethod.GET)
    @ResponseBody
    public DocumentSearch showStr() {
		DocumentSearch document = new DocumentSearch("Test","Test", "Test");
        return document;
    }*/

	/*@RequestMapping(value = "/showStrtoStr", method = RequestMethod.GET)
    @ResponseBody
    public DocumentSearch showStrtoStr(@RequestBody ObjectNode objectNode) {
    	String str=objectNode.get("str").asText();
    	DocumentSearch document = new DocumentSearch(str,str, str);
        return document;
    }*/
	
	@RequestMapping(value = "/getFile", method = RequestMethod.GET)
	@ResponseBody
    public DocumentSearch getFileRoot() throws IOException {
		String string = "src/main/resources/Txt/test.txt";
		fr = new FileReader(string);
        String str = new String();
        int i;
        while ((i = fr.read()) != -1) {
            str = str + (char) i;
        }
        DocumentSearch document = new DocumentSearch(str,str, str);
        return document;
    }
	
	/*@RequestMapping(value = "/searchDocument", method = RequestMethod.GET)
    @ResponseBody
    public List<DocumentSearch> showDocument(@RequestBody ObjectNode objectNode) {
		String query=objectNode.get("query").asText();
		List<com.webservice.dto.DocumentSearch> list = new ArrayList<com.webservice.dto.DocumentSearch>();
		Searcher s = new Searcher();
		list = s.Search("src/main/resources/Index/", query);
        return list;
    }*/
	
	@RequestMapping(value = "/searchDocument", method = RequestMethod.POST)
    @ResponseBody
    public List<DocumentSearch> searchDocument(@RequestBody ObjectNode objectNode) {
		String query=objectNode.get("query").asText();
		List<com.webservice.dto.DocumentSearch> list = new ArrayList<com.webservice.dto.DocumentSearch>();
		Searcher s = new Searcher();
		list = s.Search("src/main/resources/Index/", query);
        return list;
    }
	
	@RequestMapping(value = "/searchDocumentCategory", method = RequestMethod.POST)
    @ResponseBody
    public List<Document> searchDocumentCategory(@RequestBody ObjectNode objectNode) {
		String query=objectNode.get("query").asText();
		List<com.webservice.dto.Document> list = new ArrayList<com.webservice.dto.Document>();
		Searcher s = new Searcher();
		list = s.SearchDocumentCategory("src/main/resources/Index/", query);
        return list;
    }
	
	@RequestMapping(value = "/searchDocumentTitle", method = RequestMethod.POST)
    @ResponseBody
    public List<Document> searchDocumentTitle(@RequestBody ObjectNode objectNode) {
		String query=objectNode.get("query").asText();
		List<com.webservice.dto.Document> list = new ArrayList<com.webservice.dto.Document>();
		Searcher s = new Searcher();
		list = s.SearchDocumentTitle("src/main/resources/Index/", query);
        return list;
    }
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public List<DocumentSearch> search(@RequestParam String query) {
		String str = query.replaceAll("@", " ");
		System.out.println(str);
		List<com.webservice.dto.DocumentSearch> list = new ArrayList<com.webservice.dto.DocumentSearch>();
		Searcher s = new Searcher();
		list = s.Search("src/main/resources/Index/", str);
        return list;
    }

}

/*@RestController

public class LuceneAPI {

       @GetMapping("/test")

       public String testAPI() {

             return "success";

   }

}*/
