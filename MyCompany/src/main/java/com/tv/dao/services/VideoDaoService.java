package com.tv.dao.services;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tv.dao.VideoDao;
import com.tv.model.Video;

@Service("videoDaoService")
@Repository
@Transactional
public class VideoDaoService implements VideoService {

	private Log log = LogFactory.getLog(CategoryDaoService.class);
	@Autowired
	private VideoDao videoDao;

	@Override
	public List<Video> findAll() {

		return videoDao.findAll();
	}

	@Override
	public Video findById(Integer id) {

		return videoDao.findById(id);
	}

	@Override
	public Video save(Video video) {

		return videoDao.save(video);
	}

	@Override
	public void delete(Integer id) {
		videoDao.delete(id);

	}

}
