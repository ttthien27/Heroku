package com.webservice.dto;

public class DocumentSearch {

	private String docId;
	private String docUrl;
	private String docTitle;
	private String docDescription;
	private String docParagraphShort;
	private String docTermQuery;
	
	public DocumentSearch() {
		// TODO Auto-generated constructor stub
	}
	
	public DocumentSearch(String id,String t,String d) {
		// TODO Auto-generated constructor stub
		setDocId(id);setDocTitle(t);setDocDescription(d);
	}
	
	public DocumentSearch(String docId,String docUrl, String docTitle, String docDescription, String docParagraphShort,
			String docTermQuery) {
		super();
		this.docId = docId;
		this.docUrl = docUrl;
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

	public String getDocUrl() {
		return docUrl;
	}

	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}
	
	
}
