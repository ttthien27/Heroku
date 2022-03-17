package com.webservice.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.webservice.dto.Document;


@Controller

public class LuceneAPI {
	
	
	private FileReader fr;

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "Hello World";
    }
	
	@RequestMapping(value = "/showStr", method = RequestMethod.GET)
    @ResponseBody
    public Document showStr() {
    	Document document = new Document("Test", "Test");
        return document;
    }

	@RequestMapping(value = "/showStrtoStr", method = RequestMethod.GET)
    @ResponseBody
    public Document showStrtoStr(@RequestBody ObjectNode objectNode) {
    	String str=objectNode.get("str").asText();
    	Document document = new Document(str, str);
        return document;
    }
	
	@RequestMapping(value = "/getFile", method = RequestMethod.GET)
	@ResponseBody
    public Document getFileRoot() throws IOException {
		String string = "src/main/resources/Index/test.txt";
		fr = new FileReader(string);
        String str = new String();
        int i;
        while ((i = fr.read()) != -1) {
            str = str + (char) i;
        }
        Document document = new Document(str, str);
        return document;
    }

}

/*@RestController

public class LuceneAPI {

       @GetMapping("/test")

       public String testAPI() {

             return "success";

   }

}*/
