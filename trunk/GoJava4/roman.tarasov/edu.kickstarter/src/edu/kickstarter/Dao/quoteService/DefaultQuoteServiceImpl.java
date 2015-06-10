package edu.kickstarter.Dao.quoteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import edu.kickstarter.entity.Quote;

public class DefaultQuoteServiceImpl implements QuoteService {
	List<Quote> quotes;

	public DefaultQuoteServiceImpl() {
		quotes = new ArrayList<Quote>();
		Quote quote = new Quote();
		quote.setID(8);
		quote.setQuote("Explore projects, everywhere");
		quotes.add(quote);

		quote = new Quote();
		quote.setID(4);
		quote.setQuote("Each and every Kickstarter project is the independent creation of someone like you");
		quotes.add(quote);
	}

	@Override
	public Quote getRandomQuote() {
		return quotes.get(new Random().nextInt(quotes.size()));
	}

	@Override
	public List<Quote> getAll() {
		return quotes;
	}
}