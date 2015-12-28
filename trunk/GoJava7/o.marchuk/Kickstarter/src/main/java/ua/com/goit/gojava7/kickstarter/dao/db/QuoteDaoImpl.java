package ua.com.goit.gojava7.kickstarter.dao.db;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ua.com.goit.gojava7.kickstarter.dao.QuoteDao;
import ua.com.goit.gojava7.kickstarter.domain.Quote;

@Repository
public class QuoteDaoImpl implements QuoteDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Quote getRandomQuote() {
		Session session = sessionFactory.openSession();
		
		Criteria criteria = session.createCriteria(Quote.class);
		criteria.add(Restrictions.sqlRestriction("1=1 order by rand()"));
		criteria.setMaxResults(1);

		Quote quote = (Quote) criteria.uniqueResult();

		session.close();

		return quote;
	}

}
