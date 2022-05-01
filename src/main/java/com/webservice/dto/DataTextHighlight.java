package com.webservice.dto;

public class DataTextHighlight {

	private String paragraphShort;
	private String termQuery;
	
	public DataTextHighlight() {}
	
	public DataTextHighlight(String p, String t) {
		setParagraphShort(p);
		setTermQuery(t);
	}
	
	public String getParagraphShort() {
		return paragraphShort;
	}
	public void setParagraphShort(String paragraphShort) {
		this.paragraphShort = paragraphShort;
	}
	public String getTermQuery() {
		return termQuery;
	}
	public void setTermQuery(String termQuery) {
		this.termQuery = termQuery;
	}
}
