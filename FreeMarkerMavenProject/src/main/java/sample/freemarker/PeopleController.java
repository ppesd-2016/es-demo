package sample.freemarker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/people")
public class PeopleController {

	@RequestMapping(value="getDetails",method=RequestMethod.GET)
	String getDetails(@RequestParam("searchValue") String searchValue,Map<String, Object> model) {
		List<People> listOfPeople= new ArrayList<People>();
		People person1= new People("Nathaniel Meyer","Nathaniel","","Meyer","nathaniel.meyer@linger.eu","13301096599@gmail.com",2,"481-614-455","26-0006162","nathanielm");
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);listOfPeople.add(person1);listOfPeople.add(person1);listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		listOfPeople.add(person1);
		model.put("listOfpeople", listOfPeople);
		model.put("searchValue", searchValue);
		model.put("searchBy", "people");
		return "SearchResult";
	}
}
