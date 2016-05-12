package com.ts.esdemo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

import com.ts.esdemo.model.DocumentType;
import com.ts.esdemo.search.client.ESDemoSearchClient;

@Controller
@RequestMapping("/es")
public class ESDemoController {

	private static final Logger logger = LoggerFactory.getLogger(ESDemoController.class);

	@Autowired
	private ESDemoSearchClient client;

	private static final String ES_INDEX_NAME = "fake_company";

	@RequestMapping("/clusterhealth")
	public String getClusterHealth() {
		logger.debug("Received cluster health info request.");
		ClusterHealthResponse response = this.client.getClient().admin().cluster().health(new ClusterHealthRequest())
				.actionGet();
		return response.toString();
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchCompanies(@RequestParam(value = "type", defaultValue = "global") DocumentType type,
			@RequestParam("text") String text, Map<String, Object> model) throws IOException {
		
		logger.debug("Recieved search request for : " + text + " and type: " + type);

		// form the request
		SearchRequestBuilder requestBuilder = getRequestBuilder(ES_INDEX_NAME);
		type.setSearchQuery(requestBuilder, text);
		requestBuilder.setSearchType(SearchType.QUERY_AND_FETCH);
		logger.info("Search request query: " + requestBuilder.toString());

		// execute the request and process the response
		SearchResponse response = requestBuilder.execute().actionGet();
		type.processSearchResponse(response, model, text, type);

		return "searchresult";
	}

	
	@RequestMapping(value = "/search/employeesbycompany", method = RequestMethod.GET)
	public String searchEmployeesByCompany(@RequestParam(value = "type", defaultValue = "global") DocumentType type,
			@RequestParam("text") String text, Map<String, Object> model) throws IOException {

		logger.debug("Recieved search request for : " + text + " and type: " + type);
		SearchRequestBuilder requestBuilder = getRequestBuilder(ES_INDEX_NAME,type.toString());
		requestBuilder.setSearchType(SearchType.QUERY_AND_FETCH);
				
		requestBuilder.setQuery(QueryBuilders.matchQuery("companycode", text));
		logger.info("Search request query: " + requestBuilder.toString());
		// execute the request and process the response
		SearchResponse response = requestBuilder.execute().actionGet();
		type.processSearchResponse(response, model, text, type);
				
		return "employees";

	}
	
	@RequestMapping(value = "/suggest", method = RequestMethod.GET)
	public String suggest(@RequestParam("text") String text, Map<String, Object> model) throws IOException {
		logger.debug("Received suggestion request for: " + text);

		// for the request
		SearchRequestBuilder requestBuilder = getRequestBuilder(ES_INDEX_NAME);
		DocumentType.global.setSuggestQuery(requestBuilder, text);
		logger.info("Auto suggest query: " + requestBuilder.toString());

		// execute the request and process the response
		SearchResponse response = requestBuilder.execute().actionGet();
		SearchHits hits = response.getHits();
		logger.debug("Suggest query returned: " + hits.getTotalHits() + " record(s).");
		List<String> result = new ArrayList<String>();
		for (SearchHit eachHit : hits.getHits()) {
			String type = eachHit.getType();
			Map<String, Object> fields = eachHit.getSource();
			Object value = DocumentType.company.toString().equals(type) ? fields.get("name") : fields.get("fullname");
			if (value != null) {
				result.add(value + "(" + type + ")");
			}
		}
		model.put("autoSuggest", result);
		return "autosuggest";
	}

	private SearchRequestBuilder getRequestBuilder(String index, String... types) {
		SearchRequestBuilder requestBuilder = this.client.getClient().prepareSearch(index);
		if (types != null && types.length > 0) {
			requestBuilder.setTypes(types);
		}
		return requestBuilder;
	}

	@RequestMapping("/welcome")
	public String welcome(Map<String, Object> model) {
		model.put("message", "Elasticsearch in practice");
		return "welcome";
	}

}
