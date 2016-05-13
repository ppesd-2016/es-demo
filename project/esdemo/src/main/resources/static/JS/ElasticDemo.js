var globalSearchBox= document.getElementById('globalSearchBox');
globalSearchBox.onkeyup = function(e) {
	var value= (globalSearchBox.value).replace(/\(company\)/i,"").replace(/\(employee\)/i,"");
	if(e.keyCode!==40 && e.keyCode!==38 && e.keyCode!==13 && globalSearchBox.value.length>=4){
		makeAjaxGETCall("suggest?text="+value,"searchResultDatalist");	
	}
	else if(globalSearchBox.value.length<4){
		document.getElementById("searchResultDatalist").innerHTML='';
	}
}
function search(){
var searchBy= document.querySelector('input[name="searchBy"]:checked').value;
var value= (globalSearchBox.value).replace(/\(company\)/i,"").replace(/\(employee\)/i,"");
var url= "search?text="+value+"&type="+searchBy;
makeAjaxGETCall(url,"searchResultTable");
}
function searchEmployeesByCompany(companycode){
var url= "search/employeesbycompany?text="+companycode+"&type=employee";
makeAjaxGETCall(url,"showEmployeesContent");
}
function searchStockpriceByCompany(companycode){
	var url= "search/stockpricebycompany?text="+companycode+"&type=stock";
	makeAjaxGETCall(url,"showStockPrices");
} 
function makeAjaxGETCall(url,id){	
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
    	document.getElementById(id).innerHTML= xhttp.responseText;
    	return ;
    }
  };
  xhttp.open("GET", url+"&t=" + Math.random(), true);
  xhttp.send();
}
