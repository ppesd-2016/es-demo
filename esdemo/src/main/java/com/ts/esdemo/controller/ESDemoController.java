package com.ts.esdemo.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ts.esdemo.model.Company;
import com.ts.esdemo.search.client.ESDemoSearchClient;

@Controller
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

	@RequestMapping(value = "/search/company", method = RequestMethod.GET)
	public String getSuggestResult(@RequestParam("text") String text, Map<String, Object> model) throws IOException {
		SearchRequestBuilder requestBuilder = this.client.getClient().prepareSearch("fake_company");
		SearchResponse response = requestBuilder.setTypes("company").setSearchType(SearchType.QUERY_AND_FETCH)
				.setQuery(QueryBuilders.matchQuery("name", text)).execute().actionGet();
		SearchHits hits = response.getHits();

		for (SearchHit eachHit : hits.getHits()) {
			Map<String, Object> fields = eachHit.getSource();

			Company company = new Company();
			company.setName(blankIfNull((String) fields.get("name")));
			company.setCode(blankIfNull((String) fields.get("code")));
			company.setUrl(blankIfNull((String) fields.get("url")));
			company.setEmail(blankIfNull((String) fields.get("email")));
			company.setSector(blankIfNull((String) fields.get("sector")));
			company.setRegnumber(blankIfNull((String) fields.get("reg_number")));

			model.put("companies", Collections.singletonList(company));
			model.put("searchValue", text);
			model.put("searchBy", "company");
		}
		return "searchresult";
	}

	@RequestMapping("/welcome")
	public String welcome(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", "ES Demo");
		return "welcome";
	}

	private String blankIfNull(String value) {
		if (value == null) {
			return "";
		}
		return value;
	}
}
