package com.tv.dao;

import java.util.List;

import com.tv.model.Playlist;
import com.tv.model.Video;

public interface PlaylistDao {
	public List<Playlist> findAll();

	public Playlist findById(Long id);

	public Playlist save(Playlist playlist);

	public void delete(Long id);

	public List<Playlist> findAllChannelsVideos();

	public Playlist findPlaylistVideosById(Integer playListId);
}
