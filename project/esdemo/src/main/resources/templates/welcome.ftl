<!DOCTYPE html>
<html lang="en">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Elastic Search Demo</title>
</head>
<body>
<div id="entireContent">
<header>
Elastic Search Demo
</header>
<div id="mainDiv">
<div id="innerDiv">


	<div id="globalSearchDiv">
		<input type="text" id="globalSearchBox" name="globalSearchBox" size="40" style="font-size:16pt;" list="searchResultDatalist" autocomplete="off"> 
		<datalist id="searchResultDatalist"> </datalist>
	</datalist>
	
	</div>
  <div id="searchByComponents">
        <label>Companies</label>
        <input type="radio" name="searchBy" value="company" checked="checked" autocomplete="off">
        <label>Employee</label>
        <input type="radio" name="searchBy" value="employee" autocomplete="off">
        <label>Both</label>
        <input type="radio" name="searchBy" value="global" autocomplete="off">
		<input type="submit" value="Search" onclick="callElastic();" class="btn btn-default">		
	</div> 
     
   </div>
  </div> 
  <div id="searchResultTable" style="height:65%"></div>
<footer>
<p>&copy;</p>
</footer>
</div>
<link rel="stylesheet" type="text/css" href="/CSS/ElasticDemo.css">
<link rel="stylesheet" type="text/css" href="/CSS/ExternalCSS/bootstrap.min.css">
 <script src="/JS/ExternalJS/jquery.min.js"></script>
<script src="/JS/ExternalJS/bootstrap.min.js"></script>
<script src="/JS/ElasticDemo.js"></script>
</body>
</html>