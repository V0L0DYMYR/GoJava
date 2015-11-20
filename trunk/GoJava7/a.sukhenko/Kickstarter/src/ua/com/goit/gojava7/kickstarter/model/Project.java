package ua.com.goit.gojava7.kickstarter.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class Project{
	private static final String		MINUTES_LEFT		= " minutes left";
	private static final String		HOURS_LEFT			= " hours left";
	private static final String		DAYS_LEFT			= " days left";
	private static final String		SECONDS_LEFT		= " seconds left";
	private String					projectName;
	private String					projectDescription;
	private double					moneyNeeded;
	private String					projectHistory;
	private String					demoLink;
	private Map<String, String>		questionsAndAnswers	= new HashMap<>();
	private Category				projectCategory;
	private HashMap<User, Double>	backers				= new HashMap<>();
	private LocalDateTime			enddate;

	public Project() {

	}
	public Project(String projectName, String projectDescription,
			Category projectCategory, LocalDateTime enddate) {
		super();
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.projectCategory = projectCategory;
		this.enddate = enddate;
	}

	public double getMoneyNeeded() {
		return moneyNeeded;
	}

	public void setMoneyNeeded(double moneyNeeded) {
		this.moneyNeeded = moneyNeeded;
	}

	public String getProjectEndTime() {
		ZoneId zoneId = ZoneId.systemDefault();
		long epoch = getEndDate().atZone(zoneId).toEpochSecond();
		long time = epoch - System.currentTimeMillis() / 1000;
		String msg = +time + SECONDS_LEFT;
		if (time >= 86400) {
			msg = (time / 86400) + DAYS_LEFT;
		} else if ((time >= 3600) && ((time % 3600) == 0)) {
			msg = (time / 60 / 60) + HOURS_LEFT;

		} else if (time >= 60) {
			msg = (time / 60) + MINUTES_LEFT;
		}
		return msg;
	}

	public void addBacker(User u, Double money) {
		if (backers.containsKey(u)) {
			backers.put(u, backers.get(u) + money);
		} else {
			backers.put(u, money);
		}
	}

	public String getFundedPercentage() {
		String percentage = (float) ((getMoneyPledged() * 100)
				/ getMoneyNeeded()) + "%";
		return percentage;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public double getMoneyPledged() {
		double moneyPledged = 0;
		for (Double money : backers.values()) {
			moneyPledged += money;
		}
		return moneyPledged;
	}

	public Category getProjectCategory() {
		return projectCategory;
	}

	public void setProjectCategory(Category projectCategory) {
		this.projectCategory = projectCategory;
	}

	public HashMap<User, Double> getBackers() {
		return backers;
	}

	public LocalDateTime getEndDate() {
		return enddate;
	}

	public void setEnddate(LocalDateTime enddate) {
		this.enddate = enddate;
	}

	public String getProjectHistory() {
		return projectHistory;
	}

	public void setProjectHistory(String projectHistory) {
		this.projectHistory = projectHistory;
	}

	public Map<String, String> getQuestionsAndAnswers() {
		return questionsAndAnswers;
	}

	public void setQuestionsAndAnswers(
			Map<String, String> questionsAndAnswers) {
		this.questionsAndAnswers = questionsAndAnswers;
	}

	public String getDemoLink() {
		return demoLink;
	}

	public void setDemoLink(String demoLink) {
		this.demoLink = demoLink;
	}

}