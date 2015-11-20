package ua.com.goit.gojava7.kickstarter.model;

public class Quote{
	private String	quote;
	private String	author;
	public Quote(String quote, String author) {
		this.setQuoteName(quote);
		this.setAuthor(author);
	}

	public String getQuoteName() {
		return quote;
	}

	public void setQuoteName(String quote) {
		this.quote = quote;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}