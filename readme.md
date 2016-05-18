<h3>System requirements</h3>
<hr>
JDK 1.7+<br>
2GB+ RAM<br>
ES 1.4.4<br>
HDD Free Space 2GB+<br>
Eclipse/IntelliJIdea<br>

For specific company:-

http://localhost:9200/company1/_search


{
  "query": {
    "bool": {
      "must": [
        {
          "wildcard": {
            "employee.companycode.companycode_autoc": "**"
          }
        }
      ],
      "must_not": [],
      "should": []
    }
  },
  "from": 0,
  "size": 0,
  "sort": [],
  "aggs": {
    "group_by_state": {
      "terms": {
        "field": "employee.companycode",
        "size": 0
      }
    }
  }
}

Query to get average stock price of a company:

{
  "size": 0,
  "query": {
    "term": {
      "companycode": "01-0004757"
    }
  },
  "aggs": {
    "single_avg_stockprice": {
      "avg": {
        "field": "stockprice"
      }
    }
  }
}