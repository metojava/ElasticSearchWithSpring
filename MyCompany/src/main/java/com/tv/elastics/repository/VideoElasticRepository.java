package com.tv.elastics.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.tv.elastics.Video;

@Repository
public interface VideoElasticRepository extends ElasticsearchRepository<Video,String> {

}
