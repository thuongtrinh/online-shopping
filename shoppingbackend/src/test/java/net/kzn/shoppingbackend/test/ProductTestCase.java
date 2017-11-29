package net.kzn.shoppingbackend.test;

import static org.junit.Assert.assertEquals;
import java.sql.SQLException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private Product product;

	@BeforeClass
	public static void init() throws SQLException {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.kzn.shoppingbackend");
		context.refresh();
		productDAO = (ProductDAO) context.getBean("productDAO");
	}

	@Test
	public void testCRUDProduct() {

		/*product = new Product();
		product.setName("Oppo Selfie S53");
		product.setDescription("This is some description for oppo mobile phones");
		product.setUnitPrice(25000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		product.setBrand("Oppo");
		product.setQuantity(5);

		assertEquals("Something went wrong while inserting a new product!", true, productDAO.add(product));*/

		// reading and updating the category
		/*product = productDAO.get(2);
		product.setName("Samsung Galaxy S7");
		assertEquals("Something went wrong while updating the existing record!", true, productDAO.update(product));
		assertEquals("Something went wrong while deleting the existing record!", true, productDAO.delete(product));*/
		assertEquals("Something went wrong while fetching the list of product!", 6, productDAO.list().size());
		assertEquals("Something went wrong while fetching the list of product!", 5, productDAO.listActiveProducts().size());
		assertEquals("Something went wrong while fetching the list of product!", 3, productDAO.listActiveProductsByCategory(3).size());
		assertEquals("Something went wrong while fetching the list of product!", 2, productDAO.listActiveProductsByCategory(1).size());
		assertEquals("Something went wrong while fetching the list of product!", 4, productDAO.getLastestActiveProducts(4).size());
	}

}

