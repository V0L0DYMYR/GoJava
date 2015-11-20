package ua.com.goit.gojava7.kickstarter.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ua.com.goit.gojava7.kickstarter.model.Category;
import ua.com.goit.gojava7.kickstarter.model.Project;
import ua.com.goit.gojava7.kickstarter.model.Quote;
import ua.com.goit.gojava7.kickstarter.storage.ProjectManager;

public class ConsolePrinter {
	public static final String HORIZONTAL_LINE = "===========================";

	public static void print(String s) {
		System.out.println(s);
	}

	public static void printHorizontalLine() {
		System.out.println(HORIZONTAL_LINE);
	}

	public static void println(Quote quote) {
		System.out.println("\"" + quote.getQuoteName() + "\"\n" + quote.getAuthor());

	}

	public static void printCategory(Category b) {
		System.out.println(b.getCategoryId() + "# " + b.getCategoryName());

	}

	public static void print(List<Category> categories) {
		System.out.println("Categories: ");
		for (int i = 0; i < categories.size(); i++) {
			System.out.println(i+1 + "#" + categories.get(i).getCategoryName());
		}
	}

	public static void showProjectList(Category cat, ProjectManager projectManager) {
		ArrayList<Project> projects = projectManager.getProjectsByCategory(cat);
		for (int i = 0; i < projects.size(); i++) {
			print(i + 1 + "# " + projects.get(i).getProjectName());
		}

	}

	public static  void printFullProjectInfo(Project project) {
		printHorizontalLine();
		print("Project: " + project.getProjectName() + "   |  Category: "
				+ project.getProjectCategory().getCategoryName());
		print(project.getProjectEndTime());
		print("[ " + project.getProjectDescription() + " ]");
		print("History: " + getBlankStringIfNull(project.getProjectHistory()));
		print("Demo: " + getBlankStringIfNull(project.getDemoLink()));
		print("Funded: " + project.getFundedPercentage() + " Backers: " + project.getBackers().size() + " | Pledged: $"
				+ project.getMoneyPledged());
		printQuestionsAndAnswers(project.getQuestionsAndAnswers());

	}

	public  static void printQuestionsAndAnswers(Map<String, String> qa) {
		print("Questions and Answers:");
		qa.forEach((q, a) -> {
			print("Q: " + q);
			print("A: " + a);
			printHorizontalLine();
		});
	}

	public static String getBlankStringIfNull(String s) {
		String blank = " - ";
		if (s == null)
			return blank;
		else {
			return s;
		}
	}

}
