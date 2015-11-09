package ua.com.goit.gojava7.kickstarter.storage;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ua.com.goit.gojava7.kickstarter.domain.Category;

public class CategoryStorage {

	private List<Category> categories = new ArrayList<Category>();
		
	public void add(Category category) {
		categories.add(category);
	}
	
	public List<Category> get() {
		return Collections.unmodifiableList(categories);		
	}
	
	public Category get(int index) {
		return categories.get(index);		
	}
	
	public Integer size() {
		return categories.size();		
	}
	
	//TODO
	// OLEG SRL violated
	public  void printForChoice() {
		System.out.println("\n_________________________________________");
		System.out.println("0: for exit");
		for(int i = 0; i < categories.size(); i++) {
			System.out.println(i + 1 + ": " + categories.get(i));
		}		
	}		
}
