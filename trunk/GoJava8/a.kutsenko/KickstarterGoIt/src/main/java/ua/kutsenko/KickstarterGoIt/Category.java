package ua.kutsenko.KickstarterGoIt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Category {

	private String name;
	private List<Category> categoriesList = new ArrayList<Category>();
	private List<Project> projectList = new ArrayList<Project>();
	private Project project = new Project();
	private int selector;
	private Category category;

	public Category(String name, List<Project> projectList) {
		this.name = name;
		this.projectList = projectList;
	}

	public Category() {

	}

	public int getSelector() {
		return selector;
	}

	public String getName() {
		return name;
	}

	public List<Project> getProjectList() {

		return this.projectList;
	}

	public void showCategory() {

		for (int i = 0; i < categoriesList.size(); i++) {
			System.out.println(i + 1 + "-" + categoriesList.get(i).name);
		}
	}

	public void initCategory() {
		categoriesList.add(new Category("It", project.getItProjectList()));
		categoriesList.add(new Category("Films", project.getFilmsProjectList()));
		categoriesList.add(new Category("Music", project.getMusicProjectList()));
	}

	private void printString(String toPrint) {
		System.out.print(toPrint);
	}

	public void showProjects(Category selectedCategory) {
		for (int i = 0; i < selectedCategory.projectList.size(); i++) {
			Project project = selectedCategory.projectList.get(i);
			System.out.print(i + 1 + " - ");
			printProject(project);
		}

	}

	private void printProject(Project project) {
		System.out.println("Name project - " + project.getName());
		System.out.println("Description project - " + project.getDecription());
		System.out.println("Required budget - " + project.getRequiredBudget() + "$");
		System.out.println("Gathered budget - " + project.getGatheredBudget() + "$");
		System.out.println("days to go project - " + project.getDaysLeft());
		System.out.println("--------------------");

	}

	public void printProjectInfo(Project project) {
		System.out.println("History project - " + project.getHistoryProject());
		System.out.println("url to video - " + project.getUrlToVideo());
		System.out.println("Question and answer - " + project.getqA());
		System.out.println();

	}

	public Category selectCategory() {
		printString("Select category, 0 to exit :");
		Scanner sc = new Scanner(System.in);
		// category = categoriesList.get(selector);
		selector = sc.nextInt() - 1;
		if (selector == -1) {
			endApp(selector);
		}
		if (selector == 8) {
			category.showCategory();
			Category cat = new Category();
			cat = category.selectCategory();
			category.showProjects(cat);
			category.selectProject(cat);
		}
		if (selector < 0 || categoriesList.size() < selector + 1) {
			System.out.println("Неверный индекс меню ");
			System.out.println();
			Kickstarter kick = new Kickstarter();
			kick.run();
		}
		category = categoriesList.get(selector);
		System.out.println("You selelect: " + category.getName());
		return category;

	}

	public Project selectProject(Category selectedCategory) {
		System.out.println("Select project, 0 to exit, 9 back to category  ");
		Scanner sc = new Scanner(System.in);
		selector = sc.nextInt();

		if (selector == 0) {
			endApp(selector);
		}
		if (selector == 9) {
			Kickstarter kick = new Kickstarter();
			kick.run();
		}
		if (selector < 0 || selectedCategory.getProjectList().size() < selector) {
			System.out.println("Неверный индекс меню - " + selector);
			System.out.println();
			Kickstarter kick = new Kickstarter();
			kick.run();
		}

		Project selectProject = selectedCategory.projectList.get(selector - 1);
		printProject(selectProject);
		printProjectInfo(selectProject);
		System.out.println("8 invest project , 9 back to gategory ,"
				+ " 7 Ask question");
		selector = sc.nextInt();
		if (selector == 8) {
			invest(selectProject,selectedCategory);
		}if (selector == 9) {
			Kickstarter kick = new Kickstarter();
			kick.run();
		}if(selector == 7) {
			askQuestion(selectProject,selectedCategory);
		}
		return selectProject;
	}

	private void askQuestion(Project projectIn, Category selectedCategory) {
		Scanner sc = new Scanner(System.in);		
		System.out.println("Enter you question");
		String question = sc.nextLine();				
		projectIn.setqA(question);
		Project selectProject = projectIn;
		printProject(selectProject);
		printProjectInfo(selectProject);
		
	}

	private void endApp(int selector2) {
		System.exit(selector2);

	}

	private void invest(Project projectIn, Category selectedCategory) {
		System.out.println("Select pay: 1 quick , 2 card");
		Scanner sc = new Scanner(System.in);
		int pay = sc.nextInt();
		if(pay == 2){
		System.out.println("Enter you inputting card holder");
		String cardHolder = sc.nextLine();
		System.out.println("Enter you name card");
		String nameCard = sc.nextLine();
		System.out.println("Enter you invest mooney: ");		
		int plus = sc.nextInt();		
		projectIn.setGatheredBudget(projectIn.getGatheredBudget(), plus);
		Project selectProject = projectIn;
		printProject(selectProject);
		printProjectInfo(selectProject);
		}if(pay == 1){
			System.out.println("1 - 100$, 2 - 200$, 3 - 50$");
			int hotSum = sc.nextInt();
			if(hotSum == 1){
			projectIn.setGatheredBudget(projectIn.getGatheredBudget(), 100);
			Project selectProject = projectIn;
			printProject(selectProject);
			printProjectInfo(selectProject);
			}if(hotSum == 2){
				projectIn.setGatheredBudget(projectIn.getGatheredBudget(), 200);
				Project selectProject = projectIn;
				printProject(selectProject);
				printProjectInfo(selectProject);
				}if(hotSum == 3){
					projectIn.setGatheredBudget(projectIn.getGatheredBudget(), 500);
					Project selectProject = projectIn;
					printProject(selectProject);
					printProjectInfo(selectProject);
					}
		// return selectProject;
	}
	}
}
