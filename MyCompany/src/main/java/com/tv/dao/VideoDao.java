package com.tv.dao;

import java.util.List;

import com.tv.model.Video;

public interface VideoDao {
	
	public List<Video> findAll();

	public Video findById(Integer id);

	public Video save(Video video);

	public void delete(Integer id);
}
