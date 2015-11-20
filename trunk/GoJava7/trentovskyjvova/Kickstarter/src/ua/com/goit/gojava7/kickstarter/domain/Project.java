package ua.com.goit.gojava7.kickstarter.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Project {
	private String name;
	private int funded;
	private int daysToGo;
	private int pledged;
	private String description;
	private String owner;
	private int goal;
	private String linkVideo;
	private List<String> questions;
	
	public Project(String name) {
		setName(name);
		setFunded(0);
		setDaysToGo(40);
		setPledged(0);
		setDescription("");
		setOwner("");
		setGoal(0);
		setLinkVideo("");
		questions = new ArrayList<String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDaysToGo() {
		return daysToGo;
	}

	public void setDaysToGo(int daysToGo) {
		this.daysToGo = daysToGo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFunded() {
		return funded;
	}

	public void setFunded(int funded) {
		this.funded = funded;
	}

	public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}

	public String getLinkVideo() {
		return linkVideo;
	}

	public void setLinkVideo(String linkVideo) {
		this.linkVideo = linkVideo;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getPledged() {
		return pledged;
	}

	public void setPledged(int pledged) {
		this.pledged = pledged;
	}

	public String getAllDetails() {
		StringBuilder projectDetails = new StringBuilder();

		projectDetails.append("name: ").append(name).append("\n");
		projectDetails.append("funded: ").append(funded).append("\n");
		projectDetails.append("daysToGo: ").append(daysToGo).append("\n");
		projectDetails.append("pledged: ").append(pledged).append("\n");
		projectDetails.append("description: ").append(description).append("\n");
		projectDetails.append("owner: ").append(owner).append("\n");
		projectDetails.append("goal: ").append(goal).append("\n");
		projectDetails.append("linkVideo: ").append(linkVideo).append("\n");
		for (String question : questions) {
			projectDetails.append("Question: '").append(question).append("'\n");
		}
		return projectDetails.toString();
	}
	
	public void addQuestion(String question){
		questions.add(question);
	}
	
	public List<String> getQuestions(){
		return questions;
	}
}