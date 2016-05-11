package com.ts.esdemo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder.Operator;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum DocumentType {

	company {
		@Override
		public void setSearchQuery(SearchRequestBuilder requestBuilder, String text) {
			requestBuilder.setTypes(toString());
			requestBuilder.setQuery(QueryBuilders.matchQuery("name", text));
		}

		@Override
		public void setSuggestQuery(SearchRequestBuilder requestBuilder, String text) {
		}

	},
	employee {
		@Override
		public void setSearchQuery(SearchRequestBuilder requestBuilder, String text) {
			requestBuilder.setTypes(toString());
			requestBuilder.setQuery(QueryBuilders.matchQuery("firstname", text));
		}

		@Override
		public void setSuggestQuery(SearchRequestBuilder requestBuilder, String text) {
		}

	},
	stock {
		@Override
		public void setSearchQuery(SearchRequestBuilder requestBuilder, String text) {
		}

		@Override
		public void setSuggestQuery(SearchRequestBuilder requestBuilder, String text) {
		}

	},
	global {
		@Override
		public void setSearchQuery(SearchRequestBuilder requestBuilder, String text) {
			requestBuilder.setQuery(QueryBuilders.queryString(text).field("company.name").defaultOperator(Operator.OR)
					.field("employee.firstname"));
		}

		@Override
		public void setSuggestQuery(SearchRequestBuilder requestBuilder, String text) {
			requestBuilder.setQuery(QueryBuilders.queryString(text).field("company.name.name_autoc")
					.defaultOperator(Operator.OR).field("employee.fullname.fullname_autoc"));
		}

	};

	private static final Logger logger = LoggerFactory.getLogger(DocumentType.class);

	public abstract void setSearchQuery(SearchRequestBuilder requestBuilder, String text);

	public abstract void setSuggestQuery(SearchRequestBuilder requestBuilder, String text);

	public void processSearchResponse(SearchResponse response, Map<String, Object> model, String searchText,
			DocumentType searchType) {
		SearchHits hits = response.getHits();
		logger.debug("Query returned: " + hits.getTotalHits() + " record(s).");

		List<Company> companies = new ArrayList<Company>();
		List<Employee> employees = new ArrayList<Employee>();

		for (SearchHit eachHit : hits.getHits()) {
			Map<String, Object> fields = eachHit.getSource();
			DocumentType resultDocType = DocumentType.valueOf(eachHit.getType());
			switch (resultDocType) {
			case company: {
				companies.add(createCompany(fields));
				break;
			}
			case employee: {
				employees.add(createEmployee(fields));
				break;
			}
			default: {
				logger.warn(resultDocType + " handling is not yet supported.");
				break;
			}
			}
		}
		if (companies.size() > 0) {
			model.put("companies", companies);
		}
		if (employees.size() > 0) {
			model.put("employees", employees);
		}
		model.put("searchValue", searchText);
		model.put("searchBy", searchType.toString());
	}

	private Company createCompany(Map<String, Object> fields) {
		Company company = new Company();
		company.setName(blankIfNull(fields.get("name")));
		company.setCode(blankIfNull(fields.get("code")));
		company.setUrl(blankIfNull(fields.get("url")));
		company.setEmail(blankIfNull(fields.get("email")));
		company.setSector(blankIfNull(fields.get("sector")));
		company.setRegnumber(blankIfNull(fields.get("reg_number")));
		return company;
	}

	private Employee createEmployee(Map<String, Object> fields) {

		Employee employee = new Employee();
		employee.setFullName(blankIfNull(fields.get("fullname")));
		employee.setFirstName(blankIfNull(fields.get("firstname")));
		employee.setMiddleName(blankIfNull(fields.get("middlename")));
		employee.setLastName(blankIfNull(fields.get("lastname")));
		employee.setWorkEmail(blankIfNull(fields.get("workemail")));

		// TODO: there is a mapping issue for these fields
		// employee.setAge(age);
		// employee.setContactNumber(contactNumber);
		// employee.setCompanyCode(companyCode);
		// employee.setEmployeeId(employeeId);
		// employee.setPersonalEmail(personalEmail);

		return employee;
	}

	private String blankIfNull(Object value) {
		if (value == null) {
			return "";
		}
		return (String) value;
	}
}