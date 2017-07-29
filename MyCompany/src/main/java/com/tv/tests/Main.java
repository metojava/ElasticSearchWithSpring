package com.tv.tests;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.tv.dao.services.VideoService;
import com.tv.elastics.Video;
import com.tv.elastics.repository.VideoElasticRepository;

public class Main {

	static ObjectMapper mapper = JsonFactory.create();
	
	@Autowired
	public static VideoElasticRepository repository;
	
	@Bean
	public static ElasticsearchTemplate elasticsearchTemplate() throws Exception {
		
		return new ElasticsearchTemplate(client());
	}
	
	public static void main(String[] args) throws Exception {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring-context.xml");
		ctx.refresh();

		VideoService videoDaoService = (VideoService) ctx.getBean("videoDaoService");
		//videoDaoService.findAll().forEach(System.out::println);
		
		List<Video> videos = new ArrayList<>();
		videoDaoService.findAll().forEach(video -> {
			Video vid = new Video();
			vid.setVideoId(video.getVideoId());
			vid.setName(video.getName());
			vid.setDescription(video.getDescription());
			vid.setImage(video.getImage());
			videos.add(vid);
		});
		
		List<IndexQuery> indexQueries = new ArrayList<>();
		videos.forEach(video -> {
			IndexQuery videoIndex = new IndexQueryBuilder().withId("" + video.getVideoId()).withObject(video).build();
			indexQueries.add(videoIndex);
		});
		elasticsearchTemplate().bulkIndex(indexQueries);
		
		System.out.println("there was inserted " + getCountofinserted() + " videos");
		System.out.println("---------------------------     Videos List     --------------------------");
		findAllVideos();
		ctx.close();

	}

	public static long getCountofinserted() throws Exception {
		CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria());
		long count = elasticsearchTemplate().count(criteriaQuery, Video.class);
		return count;
	}

	public static void findAllVideos() throws Exception {
		StringQuery stringQuery = new StringQuery(QueryBuilders.matchAllQuery().toString());
		List<Video> videos = elasticsearchTemplate().queryForList(stringQuery, Video.class);
		videos.forEach(System.out::println);
	}

	public static void findVideo(String name) throws Exception {
		CriteriaQuery singleCriteriaQuery = new CriteriaQuery(new Criteria("name").contains(name));
		List<Video> videosList = elasticsearchTemplate().queryForList(singleCriteriaQuery, Video.class);
		System.out.println("Videos list: " + videosList);
	}

	public static void findEmployeesByAge(int age) throws Exception {
		

		SearchResponse response = client().prepareSearch("television").setTypes("video")
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(QueryBuilders.termQuery("videoId", 2)) // Query
				.setFrom(0).setSize(60).setExplain(true).get();

		SearchHit[] hits = response.getHits().getHits();
		for (SearchHit hit : hits) {
			Video vid = mapper.readValue(hit.getSourceAsString(), Video.class);
			System.out.println(vid);
		}

	}
	private static Client client() throws Exception {
		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
		TransportClient client = new TransportClient.Builder().settings(settings).build()
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
		return client;
	}
}
