package goit5.nikfisher.kickstarter.dao;

import goit5.nikfisher.kickstarter.model.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryCategories implements Categories {

    private Map<Integer, Category> categories = new HashMap<>();

    private int index = 0;

    public void add(Category category) {

        categories.put(index++, category);
    }

    public List<String> getCategories() {

        List<String> result = new ArrayList<>();

        for (int i = 0; i < index; i++) {
            result.add(String.valueOf(i + 1) + ") " + categories.get(i).getName());
        }
        return result;
    }

    public Category get(int index) {
        return categories.get(index);
    }

    public int size() {
        return index;
    }
}