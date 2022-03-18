package com.webservice.dto;

public class Document {

	private String docId;
	private String docTitle;
	private String docDescription;
	
	public Document() {
		// TODO Auto-generated constructor stub
	}
	
	public Document(String id,String t,String d) {
		// TODO Auto-generated constructor stub
		setDocId(id);setDocTitle(t);setDocDescription(d);
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public String getDocDescription() {
		return docDescription;
	}

	public void setDocDescription(String docDescription) {
		this.docDescription = docDescription;
	}

	
	
}
