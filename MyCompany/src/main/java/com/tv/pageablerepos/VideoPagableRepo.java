package com.tv.pageablerepos;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tv.model.Video;

@Repository
public interface VideoPagableRepo extends
		PagingAndSortingRepository<Video, Serializable> {

	List<Video> findByName(String name);
}
