package ua.com.goit.gojava7.kickstarter.dao.jdbc.postgre;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import ua.com.goit.gojava7.kickstarter.beans.Category;
import ua.com.goit.gojava7.kickstarter.dao.memory.util.Memory;
import ua.com.goit.gojava7.kickstarter.util.Utils;

public class CategoryPostgreDAOTest {

    Memory mem;
    CategoryPostgreDAO dao;

    @Before
    public void setUp() throws Exception {
        Properties properties = Utils.readProperties("./kicks-files/config.properties");
        Connection connection = DriverManager.getConnection(
                properties.getProperty("driver") + "://" + 
                properties.getProperty("url") + "/" + 
                properties.getProperty("database"),
                properties.getProperty("user"), 
                properties.getProperty("password"));
        
        mem = new Memory();
        
        dao = new CategoryPostgreDAO(connection);
        dao.clear();
        
    }

    @Test
    public void testAddGetAll() {
        dao.addAll(mem.getCategories());
        assertThat(mem.getCategories(), is(dao.getAll()));
    }
    
    @Test
    public void testAddGetl() {
        Category element = new Category(1, "t1");
        dao.add(new Category(2, "t0"));
        dao.add(element);
        dao.add(new Category(3, "t2"));
        assertThat(dao.get(1), is(element));
    }
    
    @Test
    public void testException() {
        Category element = new Category(1, "t1");
        dao.add(element);
        dao.add(element);
    }

}
