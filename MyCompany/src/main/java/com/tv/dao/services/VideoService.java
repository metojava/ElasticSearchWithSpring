package com.tv.dao.services;

import java.util.List;

import com.tv.model.Video;

public interface VideoService {
	public List<Video> findAll();

	public Video findById(Integer id);

	public Video save(Video video);

	public void delete(Integer id);

}
