<!DOCTYPE html>
<html lang="en">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Elastic Search Demo</title>
<script src="/JS/ElasticDemo.js"></script>
<link rel="stylesheet" type="text/css" href="/CSS/ElasticDemo.css">
<link rel="stylesheet" type="text/css" href="/CSS/ExternalCSS/bootstrap.min.css">
 <script src="/JS/ExternalJS/jquery.min.js"></script>
<script src="/JS/ExternalJS/bootstrap.min.js"></script>
</head>
<body>
<div id="entireContent">
<header>
Elastic Search Demo
</header>
<div id="mainDiv">
<div id="innerDiv">


	<div id="globalSearchDiv">
		<input type="text" id="globalSearchBox" name="globalSearchBox" size="40" style="font-size:16pt;" autocomplete="off"> 
	</div>
  <div id="searchByComponents">
        <label for="company">Companies</label>
        <input type="radio" name="searchBy" value="company" checked="checked" autocomplete="off">
        <label for="people">People</label>
        <input type="radio" name="searchBy" value="people" autocomplete="off">
		<input type="submit" value="Search" onclick="callElastic()">		
	</div> 
     
   </div>
  </div> 
  <div id="searchResult" style="height:65%"></div>
<footer>
<p>&copy;</p>
</footer>
</div>
</body>
</html>