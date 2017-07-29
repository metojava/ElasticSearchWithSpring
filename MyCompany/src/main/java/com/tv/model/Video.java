package com.tv.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "video")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v"),
		@NamedQuery(name = "Video.findByVideoId", query = "SELECT v FROM Video v WHERE v.videoId = :videoId"),
		@NamedQuery(name = "Video.findByName", query = "SELECT v FROM Video v WHERE v.name = :name"),
		@NamedQuery(name = "Video.findByDescription", query = "SELECT v FROM Video v WHERE v.description = :description"),
		@NamedQuery(name = "Video.findByImage", query = "SELECT v FROM Video v WHERE v.image = :image") })
public class Video implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "videoId")
	private Integer videoId;
	@Basic(optional = false)
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "image")
	private String image;
	@JoinTable(name = "channelvideos", joinColumns = {
			@JoinColumn(name = "videoId", referencedColumnName = "videoId") }, inverseJoinColumns = {
					@JoinColumn(name = "channelId", referencedColumnName = "channelId") })
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Channel> channelCollection;
	@JoinTable(name = "playlistvideos", joinColumns = {
			@JoinColumn(name = "videoId", referencedColumnName = "videoId") }, inverseJoinColumns = {
					@JoinColumn(name = "playlistId", referencedColumnName = "playlistId") })
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Playlist> playlistCollection;
	@JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
	@ManyToOne(optional = false)
	private Category categoryId;

	public Video() {
	}

	public Video(Integer videoId) {
		this.videoId = videoId;
	}

	public Video(Integer videoId, String name) {
		this.videoId = videoId;
		this.name = name;
	}

	public Integer getVideoId() {
		return videoId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@XmlTransient
	public Collection<Channel> getChannelCollection() {
		return channelCollection;
	}

	public void setChannelCollection(Collection<Channel> channelCollection) {
		this.channelCollection = channelCollection;
	}

	@XmlTransient
	public Collection<Playlist> getPlaylistCollection() {
		return playlistCollection;
	}

	public void setPlaylistCollection(Collection<Playlist> playlistCollection) {
		this.playlistCollection = playlistCollection;
	}

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (videoId != null ? videoId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Video)) {
			return false;
		}
		Video other = (Video) object;
		if ((this.videoId == null && other.videoId != null)
				|| (this.videoId != null && !this.videoId.equals(other.videoId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Video [videoId=" + videoId + ", name=" + name + ", description=" + description + ", image=" + image
				+ ", channelCollection=" + channelCollection + ", playlistCollection=" + playlistCollection
				+ ", categoryId=" + categoryId + "]";
	}

}