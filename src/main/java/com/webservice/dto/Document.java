package com.webservice.dto;

public class Document {

	private String title;
	private String description;
	
	public Document() {
		// TODO Auto-generated constructor stub
	}
	
	public Document(String t,String d) {
		// TODO Auto-generated constructor stub
		title=t;description=d;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
