package com.dashboard;

public class Greeting {

	private long id;
	private String content;

	private Quote quote;
	

	/**
	 * @return the quote
	 */
	public final Quote getQuote() {
		return quote;
	}

	/**
	 * @param quote
	 *            the quote to set
	 */
	public final void setQuote(Quote quote) {
		this.quote = quote;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}