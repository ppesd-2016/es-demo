function callElastic(){
var textbox=document.getElementById('globalSearchBox');
//var searchBy=document.getElementById('searchBy').value;
var searchBy= document.querySelector('input[name="searchBy"]:checked').value
var url= searchBy+"/getDetails?searchValue="+textbox.value;
makeAjaxGETCall(url,"searchResult");

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
