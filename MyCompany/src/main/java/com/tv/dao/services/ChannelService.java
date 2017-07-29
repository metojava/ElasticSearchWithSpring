package com.tv.dao.services;

import java.util.List;

import com.tv.model.Channel;
import com.tv.model.Video;

public interface ChannelService {
	public List<Channel> findAll();

	public Channel findById(Integer id);

	public Channel save(Channel channel);

	public void delete(Integer id);

	public List<Video> findAllVideosByChannel(Integer id);
	
	public List<Channel> findAllVideosOfChannel();
}
