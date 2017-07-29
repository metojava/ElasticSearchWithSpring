package com.tv.elastics;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 *
 * @author User
 */

@Document(indexName = "television", type = "video")
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private Integer videoId;

    private String name;
 
    private String description;

    private String image;

    public Video() {
    }

    
    public Video(Integer videoId, String name, String description, String image) {
		super();
		this.videoId = videoId;
		this.name = name;
		this.description = description;
		this.image = image;
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

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (videoId != null ? videoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Video)) {
            return false;
        }
        Video other = (Video) object;
        if ((this.videoId == null && other.videoId != null) || (this.videoId != null && !this.videoId.equals(other.videoId))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "Video [videoId=" + videoId + ", name=" + name + ", description=" + description + ", image=" + image
				+ "]";
	}

    
    
}