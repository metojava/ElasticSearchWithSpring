package com.tv.dao;

import java.util.List;

import com.tv.model.Category;

public interface CategoryDao {

	public List<Category> findAll();

	public Category findById(Long id);

	public Category save(Category category);

	public void delete(Long id);

}
