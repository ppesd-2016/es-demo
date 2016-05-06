package sample.freemarker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/company")
public class CompanyController {

	@RequestMapping(value="getDetails",method=RequestMethod.GET)
	String getDetails(@RequestParam("searchValue") String searchValue,Map<String, Object> model) {
		List<Company> listOfCompanies= new ArrayList<Company>();
		Company company1= new Company("26-0006162","Buapel Limited","http://www.buapellimited.com","contact@buapellimited.com","buapellimited.com","26-0006162");
		Company company2= new Company("74-0002552","Buapel","http://www.buapel.com","company@buapel.com","buapel.com","74-0002552");
		listOfCompanies.add(company1);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);listOfCompanies.add(company2);listOfCompanies.add(company2);listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		listOfCompanies.add(company2);
		model.put("companies", listOfCompanies);
		model.put("searchValue", searchValue);
		model.put("searchBy", "company");
		return "SearchResult";
	}
}
