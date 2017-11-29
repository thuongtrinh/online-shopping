package net.kzn.shoppingbackend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public CategoryDAOImpl() {
		super();
	}

	public CategoryDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*static {
		// add first category
		Category category = new Category();
		category.setId(1);
		category.setName("Television");
		category.setDescription("This is TV category");
		category.setImageURL("CAT_1.jpg");
		categories.add(category);

		// add second category
		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("This is mobile category");
		category.setImageURL("CAT_2.jpg");
		categories.add(category);

		// add third category
		category = new Category();
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("This is Laptop category");
		category.setImageURL("CAT_3.jpg");
		categories.add(category);
	}*/

	@Override
	public List<Category> list() {
		String selectActiveCategory = "FROM Category WHERE active = :active";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active", true);
		return query.getResultList();
	}

	// get single category based on id
	@Override
	public Category get(int id) {
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override
	@Transactional
	public boolean add(Category category) {
		try {
			//add the category to the database table
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// Update a single category
	@Override
	public boolean update(Category category) {
		try {
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Delete a category by set its active variable to false
	@Override
	public boolean delete(Category category) {
		try {
			category.setActive(false);
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

