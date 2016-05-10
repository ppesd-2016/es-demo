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

import com.ts.esdemo.model.Company;
import com.ts.esdemo.search.client.ESDemoSearchClient;

@Controller
@RequestMapping("/es")
public class ESDemoController {

	private static final Logger logger = LoggerFactory.getLogger(ESDemoController.class);

	@Autowired
	private ESDemoSearchClient client;

	private static final String ES_INDEX_NAME = "fakecompany";

	private enum DocumentTypes {
		company, employee, stock
	}

	@RequestMapping("/clusterhealth")
	public String getClusterHealth() {
		logger.debug("Received cluster health info request.");
		ClusterHealthResponse response = this.client.getClient().admin().cluster().health(new ClusterHealthRequest())
				.actionGet();
		return response.toString();
	}

	@RequestMapping(value = "/search/company", method = RequestMethod.GET)
	public String searchCompanies(@RequestParam("text") String text, Map<String, Object> model) throws IOException {
		logger.debug("Recieved search request for company: " + text);
		SearchRequestBuilder requestBuilder = this.client.getClient().prepareSearch(ES_INDEX_NAME);
		requestBuilder.setTypes(DocumentTypes.company.toString()).setSearchType(SearchType.QUERY_AND_FETCH)
				.setQuery(QueryBuilders.matchQuery("name", text));
		logger.info("Search request query: " + requestBuilder.toString());
		SearchResponse response = requestBuilder.execute().actionGet();
		SearchHits hits = response.getHits();
		List<Company> companies = new ArrayList<Company>();
		for (SearchHit eachHit : hits.getHits()) {
			Map<String, Object> fields = eachHit.getSource();

			Company company = new Company();
			company.setName(blankIfNull(fields.get("name")));
			company.setCode(blankIfNull(fields.get("code")));
			company.setUrl(blankIfNull(fields.get("url")));
			company.setEmail(blankIfNull(fields.get("email")));
			company.setSector(blankIfNull(fields.get("sector")));
			company.setRegnumber(blankIfNull(fields.get("reg_number")));

			companies.add(company);
		}
		model.put("companies", companies);
		model.put("searchValue", text);
		model.put("searchBy", "company");
		return "searchresult";
	}

	@RequestMapping(value = "/suggest", method = RequestMethod.GET)
	public String suggest(@RequestParam("text") String text, Map<String, Object> model) throws IOException {
		logger.debug("Received suggestion request for: " + text);
		SearchRequestBuilder requestBuilder = this.client.getClient().prepareSearch(ES_INDEX_NAME);
		requestBuilder.setQuery(QueryBuilders.queryString(text));
		logger.info("Auto suggest query: " + requestBuilder.toString());
		SearchResponse response = requestBuilder.execute().actionGet();
		SearchHits hits = response.getHits();
		List<String> result = new ArrayList<String>();
		for (SearchHit eachHit : hits.getHits()) {
			Map<String, Object> fields = eachHit.getSource();
			Object value = fields.get("name");
			if (value != null) {
				result.add((String) value);
			}
		}
		model.put("autoSuggest", result);
		return "autosuggest";
	}

	@RequestMapping("/welcome")
	public String welcome(Map<String, Object> model) {
		model.put("message", "Elasticsearch in practice");
		return "welcome";
	}

	private String blankIfNull(Object value) {
		if (value == null) {
			return "";
		}
		return (String) value;
	}
}
