package net.kzn.shoppingbackend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public ProductDAOImpl() {
		super();
	}

	public ProductDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Product get(int productId) {
		return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
	}

	@Override
	public List<Product> list() {
		return sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class).getResultList();
	}

	@Override
	public boolean add(Product product) {
		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Product product) {
		try {
			product.setActive(false);
			this.update(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProduct = "FROM Product WHERE active = :active";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveProduct);
		query.setParameter("active", true);
		return query.getResultList();
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String selectActiveProduct = "FROM Product WHERE active = :active AND categoryId = :categoryId";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveProduct, Product.class);
		query.setParameter("active", true);
		query.setParameter("categoryId", categoryId);
		return query.getResultList();
	}

	@Override
	public List<Product> getLastestActiveProducts(int count) {
		String selectActiveProduct = "FROM Product WHERE active = :active ORDER BY id";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveProduct, Product.class);
		query.setParameter("active", true);
		query.setFirstResult(0);
		query.setMaxResults(count);
		return query.getResultList();
	}

}
