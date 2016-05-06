
<div width="100%" height="65%">
	  You Searched for <b>${searchValue}</b>. Below are the resulting <b><#if searchBy == "company">Companies<#else> People </#if></b>
  <#if companies??>
  <div>
  <table id="searchTable" width="100%" class="table table-striped">
  <tr>
  		<th>Name</th> 
  		<th>Code</th> 
  		<th>Url</th> 
  		<th>Email</th> 
  		<th>Sector</th> 
  		<th>Regnumber</th>   
  </tr>
  <#list companies as company>
  	<tr>	
  		<td>${company.name}</td> 
  		<td>${company.code}</td> 
  		<td>${company.url}</td> 
  		<td>${company.email}</td> 
  		<td>${company.sector}</td> 
  		<td>${company.regnumber}</td> 
  	</tr>
  	<#else>
    No Results were found
  </#list>
  </div>
  </#if>
  
    <#if listOfpeople??>
  <div>
  <table id="searchTable" width="100%"  class="table table-striped">
  <thead>
  <tr>
  		<th>Full Name</th> 
  		<th>First name</th> 
  		<th>Middle Name</th> 
  		<th>Last Name</th> 
  		<th>Personal Email</th> 
  		<th>Age</th> 
  		<th>Contact Number</th> 
  		<th>Company Code</th> 
  		<th>Employee Id</th>   
  </tr>
  </thead>
  <#list listOfpeople as person>
  	<tr>	
  		<td>${person.firstName}</td> 
  		<td>${person.middleName}</td> 
  		<td>${person.lastName}</td> 
  		<td>${person.workEmail}</td> 
  		<td>${person.personalEmail}</td> 
  		<td>${person.age}</td> 
  		<td>${person.contactNumber}</td> 
  		<td>${person.companyCode}</td> 
  		<td>${person.employeeId}</td> 
  	</tr>
  	<#else>
    No Results were found
  </#list>
  </div>
  </#if>
  
  </div>