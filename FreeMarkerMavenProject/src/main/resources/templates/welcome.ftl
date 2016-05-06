<!DOCTYPE html>
<html lang="en">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Elastic Search Demo</title>
<script src="/JS/ElasticDemo.js"></script>
<link rel="stylesheet" type="text/css" href="/CSS/ElasticDemo.css">
<!--[if lt IE 9]>
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js">
</script>
<![endif]-->
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div id="entireContent">
<header>
<h1>Elastic Search Demo</h1>
</header>
<div id="mainDiv">
<div id="innerDiv">

	<div id="globalSearchDiv">
		<input type="text" id="globalSearchBox" name="globalSearchBox" size="40" style="font-size:16pt;"> 
	</div>
  <div id="searchByComponents">
        <label for="company">Companies</label>
        <input type="radio" name="searchBy" value="company" checked="checked">
        <label for="people">People</label>
        <input type="radio" name="searchBy" value="people">
		<input type="submit" value="Search" onclick="callElastic()">		
	</div>      
   </div>
  </div> 
  <div id="searchResult" style="height:65%">

  </div>
<footer>
<p>&copy;</p>
</footer>
</div>
</body>
</html>