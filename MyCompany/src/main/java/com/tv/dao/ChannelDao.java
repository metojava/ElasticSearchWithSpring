package com.tv.dao;

import java.util.List;

import com.tv.model.Channel;

public interface ChannelDao {
	public List<Channel> findAll();

	public Channel findById(Integer id);

	public Channel save(Channel channel);

	public void delete(Integer id);

	public Channel findAllVideosByChannel(Integer id);

	public List<Channel> findAllVideosOfChannel();
}
