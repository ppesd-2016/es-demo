package com.ts.esdemo.controller;

import java.io.IOException;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ts.esdemo.search.client.ESDemoSearchClient;

@RestController
@RequestMapping("/es")
public class ESDemoController {

	private static final Logger logger = LoggerFactory.getLogger(ESDemoController.class);

	@Autowired
	private ESDemoSearchClient client;

	@RequestMapping("/clusterhealth")
	public String getClusterHealth() {
		ClusterHealthResponse response = this.client.getClient().admin().cluster().health(new ClusterHealthRequest())
				.actionGet();
		return response.toString();
	}

	@RequestMapping("/search/company")
	public String getSuggestResult(@RequestParam("text") String text) throws IOException {
		SearchRequestBuilder requestBuilder = this.client.getClient().prepareSearch("fake_company");
		SearchResponse response = requestBuilder.setTypes("company").setSearchType(SearchType.QUERY_AND_FETCH)
				.setQuery(QueryBuilders.matchQuery("name", text)).execute().actionGet();
		SearchHits hits = response.getHits();
		XContentBuilder builder = XContentFactory.jsonBuilder();
		builder.startObject();
		hits.toXContent(builder, ToXContent.EMPTY_PARAMS);
		builder.endObject();
		return builder.string();
	}

}
