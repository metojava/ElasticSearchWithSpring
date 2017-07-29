package com.tv.dao.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tv.dao.ChannelDao;
import com.tv.model.Channel;
import com.tv.model.Video;

@Service("channelDaoService")
@Repository
@Transactional
public class ChannelDaoService implements ChannelService{

	private Log log = LogFactory.getLog(ChannelDaoService.class);
	@Autowired
	private ChannelDao channelDao;

	@Override
	public List<Channel> findAll() {

		return channelDao.findAll();
	}


	@Override
	public List<Video> findAllVideosByChannel(Integer id) {
		Collection<Video> videos = channelDao.findAllVideosByChannel(id)
				.getVideoCollection();
		return new ArrayList(videos);
	}

	@Override
	public Channel findById(Integer id) {

		return channelDao.findById(id);
	}

	@Override
	public Channel save(Channel channel) {

		return channelDao.save(channel);
	}

	@Override
	public void delete(Integer id) {
		channelDao.delete(id);

	}

	@Override
	public List<Channel> findAllVideosOfChannel() {
		
		return channelDao.findAllVideosOfChannel();
	}



}
