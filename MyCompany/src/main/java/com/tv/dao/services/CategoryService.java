package com.tv.dao.services;

import java.util.List;

import com.tv.model.Category;

public interface CategoryService {

	public List<Category> findAll();

	public Category findById(Long id);

	public Category save(Category category);

	public void delete(Long id);

}
