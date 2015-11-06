package ua.com.goit.gojava7.kickstarter.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ua.com.goit.gojava7.kickstarter.dao.CategoryDAO;

public class Project {

	private List<String> projectCategories = new ArrayList<>();
	// OLEG why DAO? Why not list of categories? Does it means that any project relates to all categories?
	// OLEG not used, BTW
	private CategoryDAO storageOfCategories;
	private String title;
	private String briefDescription;
	private String fullDescription;
	// OLEG double and money!!! WTF?
	private double requiredAmountOfMoney;
	// OLEG double and money!!! WTF?
	// OLEG Amout != Amount
	private double currentAmoutOfMoney;
	// OLEG do we really need it? It looks like calculated field
	private int daysLeft;
	// OLEG do we have code conversions to deny this and use class variable declaration on separated lines? let's discuss.
	// OLEG what is these data for? the end date of fundraising? So, rename it
	private int day, month, year;

	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}

	public String getBriefDescription() {
		return briefDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public double getRequiredAmountOfMoney() {
		return requiredAmountOfMoney;
	}

	public void setRequiredAmountOfMoney(double money) {
		this.requiredAmountOfMoney = money;
	}

	// OLEG is it set? May be add?
	public void setCurrentAmoutOfMoney(double money) {
		this.currentAmoutOfMoney += money;
	}

	public double getCurrentAmoutOfMoney() {
		return currentAmoutOfMoney;
	}

	public int getCalculationDaysLeft() {
		Date date = new Date();
		// OLEG remember about locale and time zone
		Calendar currentCalendar = Calendar.getInstance();
		// OLEG check we need this
		currentCalendar.setTime(date);

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		// OLEG what if month is 0? Will it work?
		calendar.set(Calendar.MONTH, (month - 1));

		// OLEG can we use calendar method to calculate this?
		long diff = calendar.getTimeInMillis() - currentCalendar.getTimeInMillis();
		long days = diff / (1000 * 60 * 60 * 24);
		// OLEG nice. So it can be never finished :)
		return Math.abs((int) days);
	}

	public void setFinalDateForFundraising(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;

		this.daysLeft = getCalculationDaysLeft();
	}

	public int getDaysLeft() {
		return daysLeft;
	}

	// OLEG is it really set? May be add? How really set categories? Or reset?
	public void setProjectCategory(String categoryName) {
		projectCategories.add(categoryName);
	}

	// OLEG is it really needed to be unmodifiable?
	public List<String> getCategories() {
		return Collections.unmodifiableList(projectCategories);
	}
}