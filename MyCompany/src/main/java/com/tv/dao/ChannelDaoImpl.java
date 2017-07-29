package com.tv.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tv.model.Channel;

@Repository("channelDao")
@Transactional
public class ChannelDaoImpl implements ChannelDao {
	private Log log = LogFactory.getLog(ChannelDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Channel> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from Channel c")
				.list();
	}

	@Transactional(readOnly = true)
	public Channel findAllVideosByChannel(Integer id) {

		Channel channelWithVideos = (Channel) sessionFactory
				.getCurrentSession()
				.getNamedQuery("Channel.findAllVideosByChannel")
				.setParameter("id", id).uniqueResult();
		return channelWithVideos;
	}

	@Override
	public Channel save(Channel channel) {
		sessionFactory.getCurrentSession().saveOrUpdate(channel);
		log.info("channel saved  " + channel.getChannelId());
		return channel;
	}

	@Override
	public List<Channel> findAllVideosOfChannel() {
		// Channel.findAllVideos
		return sessionFactory.getCurrentSession()
				.getNamedQuery("Channel.findAllVideos").list();

	}

	@Transactional(readOnly = true)
	@Override
	public Channel findById(Integer id) {
		return (Channel) sessionFactory.getCurrentSession()
				.getNamedQuery("Channel.findByChannelId")
				.setParameter("channelId", id).uniqueResult();
	}

	@Override
	public void delete(Integer id) {
		sessionFactory.getCurrentSession().delete(id);

	}

}
