package com.anmertrix.dao.memory;

import com.anmertrix.Category;
import com.anmertrix.dao.CategoryDao;


public class CategoryDaoMemory extends CategoryDao {

	
	public void fillCategory() {
		categories.add(new Category("Sport"));
		categories.add(new Category("IT"));
		categories.add(new Category("Medicine"));
		categories.add(new Category("Photo"));
		categories.add(new Category("Music"));
		categories.add(new Category("Video"));
	}

}
