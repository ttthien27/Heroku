package com.webservice.dto;

public class Document {

	private String docId;
	private String docTitle;
	private String docDescription;
	private String docParagraphShort;
	private String docTermQuery;
	
	public Document() {
		// TODO Auto-generated constructor stub
	}
	
	public Document(String id,String t,String d) {
		// TODO Auto-generated constructor stub
		setDocId(id);setDocTitle(t);setDocDescription(d);
	}
	
	public Document(String docId, String docTitle, String docDescription, String docParagraphShort,
			String docTermQuery) {
		super();
		this.docId = docId;
		this.docTitle = docTitle;
		this.docDescription = docDescription;
		this.docParagraphShort = docParagraphShort;
		this.docTermQuery = docTermQuery;
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

	public String getDocParagraphShort() {
		return docParagraphShort;
	}

	public void setDocParagraphShort(String docParagraphShort) {
		this.docParagraphShort = docParagraphShort;
	}

	public String getDocTermQuery() {
		return docTermQuery;
	}

	public void setDocTermQuery(String docTermQuery) {
		this.docTermQuery = docTermQuery;
	}

	
	
}
