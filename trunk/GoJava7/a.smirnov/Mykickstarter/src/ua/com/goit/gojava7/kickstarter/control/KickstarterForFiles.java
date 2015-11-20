package ua.com.goit.gojava7.kickstarter.control;

import java.util.List;

import ua.com.goit.gojava7.kickstarter.beans.Category;
import ua.com.goit.gojava7.kickstarter.beans.Faq;
import ua.com.goit.gojava7.kickstarter.beans.Payment;
import ua.com.goit.gojava7.kickstarter.beans.Project;
import ua.com.goit.gojava7.kickstarter.storage_in_files.CategoriesStorage;
import ua.com.goit.gojava7.kickstarter.storage_in_files.FaqStorage;
import ua.com.goit.gojava7.kickstarter.storage_in_files.PaymentStorage;
import ua.com.goit.gojava7.kickstarter.storage_in_files.ProjectsStorage;
import ua.com.goit.gojava7.kickstarter.storage_in_files.QuotesStorage;
import ua.com.goit.gojava7.kickstarter.view.ConsolePrinter;
import ua.com.goit.gojava7.kickstarter.view.ConsoleScanner;

public class KickstarterForFiles {

	private static final String SEPARATOR = "**********************************************************************";
	private static final String MOVE_TO_THE_NEXT_LINE = "\n";
	
	private ConsolePrinter consolePrinter;
	private ConsoleScanner consoleScanner;
	private CategoriesStorage categoriesStorage;
	private ProjectsStorage projectsStorage;
	private QuotesStorage quotesStorage;
	private PaymentStorage paymentStorage;
	private FaqStorage faqStorage;

	private Category selectedCategoryByUser;
	private Project selectedProjectByUser;

	public KickstarterForFiles() {
		this.consoleScanner = new ConsoleScanner();
		this.consolePrinter = new ConsolePrinter();
		this.categoriesStorage = new CategoriesStorage();
		this.quotesStorage = new QuotesStorage();
		this.paymentStorage = new PaymentStorage();
		this.projectsStorage = new ProjectsStorage();
		this.faqStorage = new FaqStorage();
	}

	public void start() {
		consolePrinter.print(quotesStorage.getRandomQuote());
		selectCategory();
	}
	
protected void selectCategory() {
		
		int selectedNumber;
		boolean userChoise = true;
		
		do {
			consolePrinter.printCategories(categoriesStorage.getAll());
			consolePrinter.print(SEPARATOR);
			consolePrinter.print("Please select category (0 for exit): ");
			selectedNumber = consoleScanner.getInt();

			if (selectedNumber < 0 || selectedNumber > categoriesStorage.getSize()) {
				consolePrinter.print("Please, enter the number between 0 and " + categoriesStorage.getSize());
				consolePrinter.print(SEPARATOR);
				
			} else if (selectedNumber != 0) {
				consolePrinter.print("You selected category number : " + selectedNumber);
				selectedCategoryByUser = categoriesStorage.getAll().get(selectedNumber - 1);
				consolePrinter.print(selectedCategoryByUser);
				consolePrinter.print(SEPARATOR);
				
				selectProjects(selectedCategoryByUser);
				
			} else {
				consolePrinter.print("You entered 0. Bye.");
				consolePrinter.print(SEPARATOR);
				userChoise = false;
			}
			
		} while (userChoise);
	}

	protected void selectProjects(Category category) {
		List<Project> projectsFromCategory = projectsStorage.getProjectsFromCategory(category);
		
		int numberOfSelectedProject;
		
		if (projectsFromCategory.size() == 0) {
			consolePrinter.print("There is no any project in selected category.");
			consolePrinter.print(SEPARATOR);
			
			numberOfSelectedProject = 0;
		} else {
			boolean userChoise = true;
			do {
				consolePrinter.printProjects(projectsFromCategory, faqStorage, paymentStorage);
				consolePrinter.print(SEPARATOR);
				consolePrinter.print("Please select project (0 for back to categories): ");
				
				numberOfSelectedProject = consoleScanner.getInt();
	
				if (numberOfSelectedProject < 0 || numberOfSelectedProject > projectsFromCategory.size()) {
					consolePrinter.print("Please, enter the number between 0 and " + projectsFromCategory.size());
					consolePrinter.print(SEPARATOR);
									
				} else if (numberOfSelectedProject != 0) {
					consolePrinter.print("You selected project number : " + numberOfSelectedProject);
					selectedProjectByUser = projectsFromCategory.get(numberOfSelectedProject - 1);
					consolePrinter.print(SEPARATOR);
					consolePrinter.printFullProjectInfo(selectedProjectByUser, faqStorage, paymentStorage);
					consolePrinter.print(SEPARATOR);
					
					activityInsideSelectedProject(selectedProjectByUser);
							
				} else {
					consolePrinter.print("You entered 0. Back to categories.");
					consolePrinter.print(SEPARATOR);
					userChoise = false;
				}
				
			} while (userChoise);
		}

	}	
		
	protected void activityInsideSelectedProject(Project selectedProject) {
		StringBuilder chooseMenuInProject = new StringBuilder();
		
		chooseMenuInProject.
			append("Please select (0 for back to projects): ").
			append(MOVE_TO_THE_NEXT_LINE).
			append("1 : Donate money").
			append(MOVE_TO_THE_NEXT_LINE).
			append("2 : Ask a question");
		
		int choseNumber;
		do {
			
			consolePrinter.print(chooseMenuInProject.toString());
			consolePrinter.print(SEPARATOR);
			choseNumber = consoleScanner.getInt();

			if (choseNumber == 1) {
				consolePrinter.print("You selected 1. Donate money on project ");
				consolePrinter.print(SEPARATOR);
				donateMoneyForProject(selectedProject);
				consolePrinter.print(SEPARATOR);
			} 
			
			if (choseNumber == 2) {
				consolePrinter.print("You selected 2. Ask a question ");
				consolePrinter.print(SEPARATOR);
				askQuestion(selectedProject);
				consolePrinter.print(SEPARATOR);
			}
			
		} while (choseNumber != 0);
		
		consolePrinter.print("You entered 0. Back to projects.");
		consolePrinter.print(SEPARATOR);
	}
	
	protected void donateMoneyForProject(Project project) {
		String userName = consoleScanner.parseUserName();
		long cardNumber = consoleScanner.parseCreditCardNumber();
		
		providePaymentMethods(project, userName, cardNumber);
		
		consolePrinter.printShortProjectInfo(project, faqStorage, paymentStorage);	
	}
	
	protected void askQuestion(Project project) {
		String question = consoleScanner.parseAskingQuestion();
		Faq faq = new Faq(question);
		faq.setProjectID(project.getUniqueID());
		faqStorage.add(faq);
		
		consolePrinter.print(SEPARATOR);
		consolePrinter.printShortProjectInfo(project, faqStorage, paymentStorage);
		
	}
	
	protected void providePaymentMethods(Project project, String userName, Long creditCardNumber) {
		String paymentMenu =  "Please select option : \n"
							+ " 1 - Donate 1$ \n"
							+ " 2 - Donate 10$ \n"
							+ " 3 - Donate 40$ \n"
							+ " 4 - Donate any amount that you want";
		consolePrinter.print(paymentMenu);
		
		Payment payment = null;
		
		do {
			
			int userNumber = consoleScanner.getInt();
			
			if (userNumber == 1) {
				consolePrinter.print("Thank you. You donated 1$.");
				consolePrinter.print(SEPARATOR);
				payment = new Payment(userName, creditCardNumber, 1);
				payment.setProjectID(project.getUniqueID());
				project.setCollectedSum(payment.getDonatingSum());
				paymentStorage.add(payment);
				
			} else if (userNumber == 2) {
				consolePrinter.print("Thank you. You donated 10$.");
				consolePrinter.print(SEPARATOR);
				payment = new Payment(userName, creditCardNumber, 10);
				payment.setProjectID(project.getUniqueID());
				project.setCollectedSum(payment.getDonatingSum());
				paymentStorage.add(payment);
				
			} else if (userNumber == 3) {
				consolePrinter.print("Thank you. You donated 40$.");
				consolePrinter.print(SEPARATOR);
				payment = new Payment(userName, creditCardNumber, 40);
				payment.setProjectID(project.getUniqueID());
				project.setCollectedSum(payment.getDonatingSum());
				paymentStorage.add(payment);
				
			} else if (userNumber == 4) {
				int donatingSum = consoleScanner.parseDonatingAmount();
				consolePrinter.print("Thank you. You donated " + donatingSum + ".");
				consolePrinter.print(SEPARATOR);
				payment = new Payment(userName, creditCardNumber, donatingSum);
				payment.setProjectID(project.getUniqueID());
				project.setCollectedSum(payment.getDonatingSum());
				paymentStorage.add(payment);
				
			} else {
				System.out.println("Please enter number between 1 and 4");
			}
		} while (payment == null);
	}
	
	public void stop() {
		consoleScanner.close();
	}
}
	

