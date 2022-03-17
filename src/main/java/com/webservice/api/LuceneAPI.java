package com.webservice.api;

import java.io.File;

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
    public String getFileRoot() {
		String str="";
		 File[] roots = File.listRoots();
	        for (File root : roots) {
	            str = str + root.getAbsolutePath()+" ";
	        }
        return str;
    }

}

/*@RestController

public class LuceneAPI {

       @GetMapping("/test")

       public String testAPI() {

             return "success";

   }

}*/
