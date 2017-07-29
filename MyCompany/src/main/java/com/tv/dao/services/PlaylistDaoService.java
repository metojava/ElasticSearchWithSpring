package com.tv.dao.services;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tv.dao.PlaylistDao;
import com.tv.model.Playlist;

@Service("playlistDaoService")
@Repository
@Transactional
public class PlaylistDaoService implements PlaylistService {

	private Log log = LogFactory.getLog(PlaylistDaoService.class);
	@Autowired
	private PlaylistDao playlistDao;

	@Override
	public List<Playlist> findAll() {

		return playlistDao.findAll();
	}

	@Override
	public Playlist findById(Long id) {

		return playlistDao.findById(id);
	}

	@Override
	public Playlist save(Playlist playlist) {

		return playlistDao.save(playlist);
	}

	@Override
	public void delete(Long id) {
		playlistDao.delete(id);

	}

	@Override
	public List<Playlist> findAllChannelsVideos() {
		// TODO Auto-generated method stub
		return playlistDao.findAllChannelsVideos();
	}

	@Override
	public Playlist findPlaylistVideosById(Integer playListId) {
		// TODO Auto-generated method stub
		return playlistDao.findPlaylistVideosById(playListId);
	}

}
