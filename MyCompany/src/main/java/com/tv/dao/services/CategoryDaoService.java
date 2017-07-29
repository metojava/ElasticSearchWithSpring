package com.tv.dao.services;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tv.dao.CategoryDao;
import com.tv.model.Category;

@Service("categoryDaoService")
@Repository
@Transactional
public class CategoryDaoService implements CategoryService {

	private Log log = LogFactory.getLog(CategoryDaoService.class);
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<Category> findAll() {
		List<Category> categories = categoryDao.findAll();

		return categories;
	}

	@Override
	public Category findById(Long id) {

		return categoryDao.findById(id);
	}

	@Override
	public Category save(Category category) {
		
		return categoryDao.save(category);
	}

	@Override
	public void delete(Long id) {
		categoryDao.delete(id);

	}

}
