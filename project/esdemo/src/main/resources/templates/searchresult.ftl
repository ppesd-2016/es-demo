<div class="container" style="height:100%">
  <div class="row" style="height:100%">
      <div class="panel panel-default" style="height:92%">
        <div class="panel-heading">
          <h4>
             You Searched for <b>${searchValue}</b>. Below are the resulting <b><#if searchBy == "company">Companies<#else> People </#if></b>
          </h4>
        </div>
       <#if companies??>
       <div id="searchTableDiv">
        <table class="table table-striped">
          <thead>
             <tr>
  				<th>Name</th> 
  				<th>Code</th> 
  				<th>Url</th> 
  				<th>Email</th> 
  				<th>Sector</th> 
  				<th>Regnumber</th>   
 			 </tr>
          </thead>
        <tbody>  
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
          </tbody>
        </table>
        </div>
       </#if>
        <#if listOfpeople??>
       <div id="searchTableDiv">
        <table class="table table-striped">
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
         <tbody>
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
          </tbody>
        </table>
        </div>
       </#if>
      </div>
  </div>
</div>