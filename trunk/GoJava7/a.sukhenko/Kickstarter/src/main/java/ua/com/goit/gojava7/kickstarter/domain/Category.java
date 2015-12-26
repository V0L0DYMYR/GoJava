package ua.com.goit.gojava7.kickstarter.domain;

public class Category{
    private String categoryName;
    private int    categoryId;

    public Category() {
    }

    public Category(String categoryName, int categoryId) {
        super();
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    @Override
    public String toString() {
        return "Category. categoryId: " + categoryId + " categoryName: " + categoryName;
    }
}
