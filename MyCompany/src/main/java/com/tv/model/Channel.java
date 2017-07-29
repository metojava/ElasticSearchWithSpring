/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tv.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author User
 */
@Entity
@Table(name = "channel")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Channel.findAll", query = "SELECT c FROM Channel c"),
		@NamedQuery(name = "Channel.findAllVideos", query = "SELECT distinct c FROM Channel c left join fetch c.videoCollection"),
		@NamedQuery(name = "Channel.findAllVideosByChannel", query = "SELECT distinct c FROM Channel c left join fetch c.videoCollection where c.channelId = :id"),
		@NamedQuery(name = "Channel.findByChannelId", query = "SELECT c FROM Channel c WHERE c.channelId = :channelId"),
		@NamedQuery(name = "Channel.findByName", query = "SELECT c FROM Channel c WHERE c.name = :name") })
public class Channel implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "channelId")
	private Integer channelId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 30)
	@Column(name = "name")
	private String name;
	@ManyToMany(mappedBy = "channelCollection")
	private Collection<Video> videoCollection;

	public Channel() {
	}

	public Channel(Integer channelId) {
		this.channelId = channelId;
	}

	public Channel(Integer channelId, String name) {
		this.channelId = channelId;
		this.name = name;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public Collection<Video> getVideoCollection() {
		return videoCollection;
	}

	public void setVideoCollection(Collection<Video> videoCollection) {
		this.videoCollection = videoCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (channelId != null ? channelId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Channel)) {
			return false;
		}
		Channel other = (Channel) object;
		if ((this.channelId == null && other.channelId != null)
				|| (this.channelId != null && !this.channelId
						.equals(other.channelId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.tv.Channel[ channelId=" + channelId + " ]";
	}

}
