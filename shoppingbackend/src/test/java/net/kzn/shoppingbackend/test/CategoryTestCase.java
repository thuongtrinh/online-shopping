package net.kzn.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private Category category;

	@BeforeClass
	public static void init() throws SQLException {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.kzn.shoppingbackend");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}

	/*@Test
	public void testAddCategory() {
		category = new Category();
		category.setName("TV");
		category.setDescription("This is some description for television!");
		category.setImageURL("CAT_1.png");
		assertEquals("Successfully added a category inside the table!", true, categoryDAO.add(category));
	}*/

	/*@Test
	public void testGetCategory() {
		category = categoryDAO.get(2);
		assertEquals("Successfully get a category with name is Mobile", "Mobile", category.getName());
	}*/

	/*@Test
	public void testUpdateCategory() {
		category.setName("Mobile");
		category.setActive(true);
		assertEquals("Successfully update a category with name to Mobile", true, categoryDAO.update(category));
	}*/

	/*@Test
	public void testDeleteCategory() {
		category = categoryDAO.get(4);
		assertEquals("Successfully update a category with name to Mobile", true, categoryDAO.delete(category));
	}*/

	/*@Test
	public void testGetListCategory() {
		assertEquals("Successfully get list all categories from DB", 3, categoryDAO.list().size());
	}*/

	@Test
	public void testCRUDCategory() {
		// Test add operation
		// add third category
		category = new Category();
		category.setName("Television");
		category.setDescription("This is some description for television");
		category.setImageURL("CAT_1.png");
		category.setActive(true);
		assertEquals("Successfully added a category inside the table!", true, categoryDAO.add(category));
		
		category = new Category();
		category.setName("Mobile");
		category.setDescription("This is mobile category");
		category.setImageURL("CAT_2.jpg");
		category.setActive(true);
		assertEquals("Successfully added a category inside the table!", true, categoryDAO.add(category));

		// add third category
		category = new Category();
		category.setName("Laptop");
		category.setDescription("This is Laptop category");
		category.setImageURL("CAT_3.jpg");
		category.setActive(true);
		assertEquals("Successfully added a category inside the table!", true, categoryDAO.add(category));

		category = new Category();
		category.setName("TVs");
		category.setDescription("This is some description for TV");
		category.setImageURL("CAT_1.png");
		category.setActive(true);
		assertEquals("Successfully added a category inside the table!", true, categoryDAO.add(category));
		
		// Test get a category 
		category = categoryDAO.get(3);
		assertEquals("Successfully get a category with name is Mobile", "Laptop", category.getName());

		// Test update a category
		category = categoryDAO.get(4);
		category.setName("TV");
		category.setActive(true);
		assertEquals("Successfully update a category with name to TV", true, categoryDAO.update(category));

		// Test delete a category
		category = categoryDAO.get(4);
		assertEquals("Successfully update a category with name to TV", true, categoryDAO.delete(category));

		// Test get list category
		assertEquals("Successfully get list all categories from DB", 3, categoryDAO.list().size());
	}

}
