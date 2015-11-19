package ua.com.goit.gojava7.kickstarter;

import org.junit.After;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.theories.Theories.TheoryAnchor;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.validator.TestClassValidator;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.verification.NeverWantedButInvoked;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ua.com.goit.gojava7.kickstarter.console.CategoryPrinter;
import ua.com.goit.gojava7.kickstarter.console.ConsoleScanner;
import ua.com.goit.gojava7.kickstarter.console.Printer;
import ua.com.goit.gojava7.kickstarter.console.ProjectPrinter;
import ua.com.goit.gojava7.kickstarter.console.QuotePrinter;
import ua.com.goit.gojava7.kickstarter.domain.Category;
import ua.com.goit.gojava7.kickstarter.domain.Project;
import ua.com.goit.gojava7.kickstarter.domain.Question;
import ua.com.goit.gojava7.kickstarter.domain.Quote;
import ua.com.goit.gojava7.kickstarter.domain.Reward;
import ua.com.goit.gojava7.kickstarter.storage.CategoryStorage;
import ua.com.goit.gojava7.kickstarter.storage.QuoteStorage;

@RunWith(MockitoJUnitRunner.class)
public class KickstarterTest {

	private PrintStream systemOut;
	
	@Mock
	private ConsoleScanner consoleScanner;
	@Mock
	private Printer printer;
	
	@Mock
	private CategoryPrinter categoryPrinter;
	@Mock
	private ProjectPrinter projectPrinter;
	@Mock
	private QuotePrinter quotePrinter;
	//@Mock
	private QuoteStorage quoteStorage;
	@Mock
	private CategoryStorage categoryStorage;
	
	@InjectMocks
	private Kickstarter kickstarter = new Kickstarter(quoteStorage, categoryStorage);
	
	@Before
	public void setUp() {
		systemOut = System.out;		
	}
	
	@After
	public void tearDown() {
		System.setOut(systemOut);
	}
	
	@Test
	public void TestViewProject() {
		
	}
	
	
	@Test
	public void testViewProject() {
		Project project = new Project("NameTest", "DescriptionTest", 100000, 100, 10, "HistoryTest", "LinkTest");	
		when(consoleScanner.getOption()).thenReturn("0");
		kickstarter.viewProject(project);
		verify(projectPrinter).printFull(project);
	}
	
	@Test
	public void testChooseOptionOfProjectEnteger0() {
		Project project = new Project("NameTest", "DescriptionTest", 100000, 100, 10, "HistoryTest", "LinkTest");	
		
		when(consoleScanner.getOption()).thenReturn("0");
		kickstarter.chooseOptionOfProject(project);
		verifyNoMoreInteractions(printer);
	}
	
	@Test
	public void testChooseOptionOfProjectEntegerA() {
		Project project = new Project("NameTest", "DescriptionTest", 100000, 100, 10, "HistoryTest", "LinkTest");	
		when(consoleScanner.getOption()).thenReturn("a");
		kickstarter.chooseOptionOfProject(project);
		verify(printer).print(contains("Ask your question about project:"));
		//TODO How do it?
		//verify(kickstarter).addQuestion(project);
	}
	
	@Test	
	public void testChooseOptionOfProjectEntegerB() {
		Project project = new Project("NameTest", "DescriptionTest", 100000, 100, 10, "HistoryTest", "LinkTest");	
		when(consoleScanner.getOption()).thenReturn("b");
		kickstarter.chooseOptionOfProject(project);
		verify(printer).print(contains("Enter your name:"));
		//TODO How do it?
		//verify(kickstarter).donate(project);
	}
	
	@Test
	public void testDonate() {
		Project project = new Project("NameTest", "DescriptionTest", 100000, 100, 10, "HistoryTest", "LinkTest");	
		kickstarter.donate(project);
		when(consoleScanner.getName()).thenReturn("jjkljfhc");
		when(consoleScanner.getCreditCard()).thenReturn("kjblvycyx");
		verify(printer).print(contains("Enter your name:"));
		verify(printer).print(contains("Enter your card's number:"));
	}

	@Test
	public void testChooseRewardEnteredNumberOfSomeReward() {
		Project project = new Project("NameTest", "DescriptionTest", 100000, 100, 10, "HistoryTest", "LinkTest");		
		List<Reward> rewards = new ArrayList<>();
		rewards.add(new Reward(10, "ten bonuses"));
		rewards.add(new Reward(20, "twenty bonuses"));
		project.setRewards(rewards);
		when(consoleScanner.getInt(0, rewards.size() + 1)).thenReturn(1);
		kickstarter.chooseReward(project);
		verify(printer).print(contains("Let's choose your reward!"));
		verify(printer).print(contains("Amount of your donation is $10"));
		verify(printer).print(contains("It was collected before: $100"));
		verify(printer).print(contains("Now collected: $110"));
	}
	
	@Test
	public void testChooseRewardEntered0() {
		Project project = new Project("NameTest", "DescriptionTest", 100000, 100, 10, "HistoryTest", "LinkTest");		
		List<Reward> rewards = new ArrayList<>();
		rewards.add(new Reward(10, "ten bonuses"));
		rewards.add(new Reward(20, "twenty bonuses"));
		project.setRewards(rewards);
		
		when(consoleScanner.getInt(0, rewards.size() + 1)).thenReturn(0);
		kickstarter.chooseReward(project);
		verify(printer).print(contains("Let's choose your reward!"));
		verifyNoMoreInteractions(printer);
	}	
	
	@Test
	public void testChooseRewardEnteredLastNumber() {
		Project project = new Project("NameTest", "DescriptionTest", 100000, 100, 10, "HistoryTest", "LinkTest");		
		List<Reward> rewards = new ArrayList<>();
		rewards.add(new Reward(10, "ten bonuses"));
		rewards.add(new Reward(20, "twenty bonuses"));
		project.setRewards(rewards);
		
		when(consoleScanner.getInt(0, rewards.size() + 1)).thenReturn(3);
		when(consoleScanner.getInt(1, 99900)).thenReturn(200);
		kickstarter.chooseReward(project);
		verify(printer).print(contains("Enter amount from 1 to 99900"));
		verify(printer).print(contains("It was collected before: $100"));
		verify(printer).print(contains("Now collected: $300"));
	}	
	
	
	@Test
	public void testDoDonate() {	
		Project currentProject = new Project("NameTest", "DescriptionTest", 100000, 100, 10, "HistoryTest", "LinkTest");
		
		assertThat(currentProject.getPledged(), is(100));
		kickstarter.doDonate(currentProject, 20);
		assertThat(currentProject.getPledged(), is(120));
	}
	
	@Test
	public void testAddQuestion() {		
		Project project = new Project("Name Of Test Project", "DescriptionTest", 100000, 100, 10, "HistoryTest", "LinkTest");
		project.setQuestions(new ArrayList<Question>());
		
		when(consoleScanner.getString()).thenReturn("My question");
		assertThat(project.getQuestions().size(), is(0));
		kickstarter.addQuestion(project);
		verify(printer).print(contains("Ask your question about project:"));
		assertThat(project.getQuestions().get(0).getQuestion(), is("My question"));
		assertThat(project.getQuestions().get(0).getAnswer(), is("There is no answer yet"));
		assertNotNull(project.getQuestions().get(0).getTime());
		assertThat(project.getQuestions().size(), is(1));
	}
	
	@Test
	public void testShutdown() {
		kickstarter.shutdown();
		verify(consoleScanner).close();
	}

}
