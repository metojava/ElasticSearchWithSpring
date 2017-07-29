package com.tv.dao.services;

import java.util.List;

import com.tv.model.Playlist;

public interface PlaylistService {
	public List<Playlist> findAll();

	public Playlist findById(Long id);

	public Playlist save(Playlist playlist);

	public void delete(Long id);

	// returns all playlists with videos
	public List<Playlist> findAllChannelsVideos();

	// returns single playlist with videos
	public Playlist findPlaylistVideosById(Integer playListId);
}
