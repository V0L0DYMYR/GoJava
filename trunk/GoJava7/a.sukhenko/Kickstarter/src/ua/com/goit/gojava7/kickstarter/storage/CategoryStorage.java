package ua.com.goit.gojava7.kickstarter.storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import ua.com.goit.gojava7.kickstarter.model.Category;
@XmlRootElement(name = "categories")
@XmlSeeAlso({Category.class})
public class CategoryStorage{
	private List<Category> categories = new ArrayList<Category>();
	
	
	@XmlElement(name = "category")
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}



	public List<Category> getCategories() {
		return categories;
	}
	

	
	public Category getCategoryById(int id) {
		return categories.get(id);
	}

	public int size() {
		return categories.size();
	}

	public void addCategory(Category cat) {
		categories.add(cat);
	}

}