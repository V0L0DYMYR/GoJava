package ua.com.goit.gojava7.kickstarter;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ua.com.goit.gojava7.kickstarter.console.ConsolePrinter;
import ua.com.goit.gojava7.kickstarter.model.Category;
import ua.com.goit.gojava7.kickstarter.model.Project;
import ua.com.goit.gojava7.kickstarter.model.User;

public class BodyTest {
	String newLine = "\r\n";
	Body body = new Body();
	ConsolePrinter consolePrinter = new ConsolePrinter();
	Kickstarter kickstarter = new Kickstarter();
	
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		body.setConsolePrinter(consolePrinter);
	   System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	
	@Test
	public void testBody() {
		assertNotNull(body);
	}

	@Test
	public void testGenerateHeader() {
		body.generateHeader();
		assertThat(outContent.toString(),is(Body.WELCOME_TO_KICKSTARTER_BETA+newLine));
	}

	@Test
	public void testGenerateFooter() {
		body.generateFooter();
		assertThat(outContent.toString(), is(Body.GO_IT_KICKSTARTER_C_BY_ARTUR_SUKHENKO+newLine));
	}

	@Test
	public void testGenerateBody() {
		/*
		kickstarter.init();
		kickstarter.setBody(body);
		body.generateBody();
		fail("Not yet implemented");*/
	}

	@Test
	public void testGenerateQuoteBlock() {
		fail("Not yet implemented");
	}

	@Test
	public void testGenerateCategories() {
		String stuff = ConsolePrinter.HORIZONTAL_LINE+newLine;
		stuff += Body.CATEGORIES;
		consolePrinter.printHorizontalLine();
		
				body.generateCategories();	
				//assertThat()
				
	}

	@Test
	public void testGenerateMainPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testGenerateCategoryInfo() {
		Category cat = new Category();
		cat.setCategoryId(1);
		cat.setCategoryName("category");
		body.generateCategoryInfo(cat);
		assertThat(outContent.toString(), is(cat.getCategoryId() + "# " + cat.getCategoryName()+newLine));
	}

	@Test
	public void testGenerateProjectInfo() {
		Project project = new Project();
		Map<String,String> qa = new HashMap<String,String>();
		Category cat = new Category("cat", 1);
		project.setProjectCategory(cat);
		project.setMoneyNeeded(100000.0);
		project.addBacker(new User(), 50000.0);
		qa.put("Question", "Answer");
		project.setDemoLink("http");
		project.setEnddate(LocalDateTime.now().plusHours(1).minusMinutes(1));
		project.setProjectName("Test Project");
		project.setProjectHistory("history");
		project.setProjectDescription("Description");
		project.setQuestionsAndAnswers(qa);
		body.generateProjectInfo(project);
		
		String stuff = "===========================" + newLine;
		stuff += "Project: " + project.getProjectName() + "   |  Category: " + project.getProjectCategory().getCategoryName()+ newLine;
		stuff+= "59 minutes left"+ newLine;
		stuff+= "[ Description ]"+ newLine;
		stuff+= "Funded: " + project.getFundedPercentage() + " Backers: " + project.getBackers().size() + " | Pledged: $" + project.getMoneyPledged()+ newLine;
		assertThat(outContent.toString(), is(stuff));
	}

}