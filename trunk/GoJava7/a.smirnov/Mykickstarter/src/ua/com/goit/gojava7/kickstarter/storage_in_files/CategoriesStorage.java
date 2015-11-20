
package ua.com.goit.gojava7.kickstarter.storage_in_files;

import ua.com.goit.gojava7.kickstarter.model.Category;
import ua.com.goit.gojava7.kickstarter.templates.AbstractTemplateFiles;

public class CategoriesStorage extends AbstractTemplateFiles<Category> {
	
	public CategoriesStorage() {
		Category category1 = new Category("Arts");
		Category category2 = new Category("Movie");
		Category category3 = new Category("Sports");
		Category category4 = new Category("Culture");
		Category category5 = new Category("Food");

		add(category1);
		add(category2);
		add(category3);
		add(category4);
		add(category5);
	}
}