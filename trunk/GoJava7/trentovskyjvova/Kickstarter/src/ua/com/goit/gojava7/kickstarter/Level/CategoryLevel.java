package ua.com.goit.gojava7.kickstarter.Level;

import java.util.List;

import ua.com.goit.gojava7.kickstarter.console.ConsoleScanner;
import ua.com.goit.gojava7.kickstarter.domain.Category;
import ua.com.goit.gojava7.kickstarter.domain.Project;

public class CategoryLevel implements Level {

	public String generateAnswer(List<Category> categories, int userChoise,
			Category selectedCategory, Project selectedProject) {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("You selected '")
				.append(selectedCategory.getName()).append("' category")
				.append("\n");

		String format = "%-3s|%-20s|%10s|%10s|%10s%n";
		stringBuilder.append(String.format(format, "", "name", "funded",
				"days to go", "pledged"));
		int index = 1;
		for (Project project : selectedCategory.getProjects()) {
			stringBuilder.append(String.format(format, index++,
					project.getName(), project.getFunded(),
					project.getDaysToGo(), project.getPledged()));
		}
		stringBuilder.append("0 : main menu").append("\n");
		stringBuilder.append("Select a project");

		return stringBuilder.toString();
	}

	public Category findSelectedCategory(List<Category> categories,
			int userChoise, Category selectedCategory) {
		if (selectedCategory == null) {
			selectedCategory = categories.get(userChoise);
		}
		return selectedCategory;
	}

	public String validateUserChoise(List<Category> categories, int userChoise,
			Category selectedCategory) {
		StringBuilder stringBuilder = new StringBuilder();

		if (userChoise < 0 || userChoise > selectedCategory.projectsSize()) {
			stringBuilder.append("Please, enter the number between 0 and ")
					.append(selectedCategory.projectsSize());
		}
		return stringBuilder.toString();
	}

	public String fillOutForm(Project project, int userChoise) {
		return "";
	}

	public Project findSelectedProject(int userChoise,
			Category selectedCategory, Project selectedProject) {
		// TODO Auto-generated method stub
		return null;
	}

	public String fillOutForm(Project project, int userChoise,
			ConsoleScanner consoleScanner) {

		return "";
	}

}