package com.tv.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tv.model.Category;
import com.tv.model.Video;

@Repository("categoryDao")
@Transactional
public class CategoryDaoImpl implements CategoryDao {
	private Log log = LogFactory.getLog(CategoryDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Category> findAll() {
		return sessionFactory.getCurrentSession()
				.createQuery("from Category c").list();
	}

	@Transactional(readOnly = true)
	@Override
	public Category findById(Long id) {
		return (Category) sessionFactory.getCurrentSession()
				.getNamedQuery("Category.findByCategoryId")
				.setParameter("categoryId", id).uniqueResult();
	}

	@Override
	public Category save(Category category) {
		sessionFactory.getCurrentSession().saveOrUpdate(category);
		log.info("category saved  " + category.getCategoryId());
		return category;
	}

	@Override
	public void delete(Long id) {
		sessionFactory.getCurrentSession().delete(id);

	}

}
