package com.tv.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tv.model.Channel;
import com.tv.model.Playlist;
import com.tv.model.Video;

@Repository("playlistDao")
@Transactional
public class PlaylistDaoImpl implements PlaylistDao {
	private Log log = LogFactory.getLog(PlaylistDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Playlist> findAll() {
		return sessionFactory.getCurrentSession()
				.createQuery("from Playlist c").list();
	}

	@Override
	public Playlist findById(Long id) {
		return (Playlist) sessionFactory.getCurrentSession()
				.getNamedQuery("Playlist.findByPlaylistId")
				.setParameter("playlistId", id).uniqueResult();
	}

	@Override
	public Playlist save(Playlist playlist) {
		sessionFactory.getCurrentSession().saveOrUpdate(playlist);
		log.info("Playlist saved  " + playlist.getPlaylistId());
		return playlist;
	}

	@Override
	public void delete(Long id) {
		sessionFactory.getCurrentSession().delete(id);
	}

	@Override
	public List<Playlist> findAllChannelsVideos() {
		// findAllVideos
		return sessionFactory.getCurrentSession()
				.getNamedQuery("Playlist.findAllVideos").list();
	}

	@Override
	public Playlist findPlaylistVideosById(Integer playListId) {
		// findVideosByPlaylistId
		return (Playlist) sessionFactory.getCurrentSession()
				.getNamedQuery("Playlist.findVideosByPlaylistId")
				.setParameter("playlistId", playListId).uniqueResult();
	}

}
