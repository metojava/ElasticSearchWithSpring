package com.tv.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tv.model.Video;

@Repository("videoDao")
@Transactional
public class VideoDaoImpl implements VideoDao {

	private Log log = LogFactory.getLog(VideoDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Video> findAll() {

		return sessionFactory.getCurrentSession().createQuery("from Video v")
				.list();
	}

	@Transactional(readOnly = true)
	@Override
	public Video findById(Integer id) {
		return (Video) sessionFactory.getCurrentSession()
				.getNamedQuery("Video.findByVideoId")
				.setParameter("videoId", id).uniqueResult();

	}

	@Override
	public Video save(Video video) {
		sessionFactory.getCurrentSession().saveOrUpdate(video);
		log.info("Video saved  " + video.getVideoId());
		return video;
	}

	@Override
	public void delete(Integer id) {
		sessionFactory.getCurrentSession().delete(id);

	}

}
