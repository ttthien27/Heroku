package com.webservice.dto;

public class Document{
	


	private String docId;
	private String docUrl;
	private String docTitle;
	private String docDescription;
	
	public Document(String docId, String docUrl, String docTitle, String docDescription) {
		super();
		this.docId = docId;
		this.docUrl = docUrl;
		this.docTitle = docTitle;
		this.docDescription = docDescription;
	}
	
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getDocUrl() {
		return docUrl;
	}
	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
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
