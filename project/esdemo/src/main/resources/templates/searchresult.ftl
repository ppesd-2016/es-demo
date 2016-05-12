<div class="container" style="height:100%">
  <div class="row" style="height:100%">
      <div class="panel panel-default" style="height:95%">
        <div class="panel-heading">
          <h4>
             You Searched for <b>${searchValue}</b>. Below are the resulting <b><#if searchBy == "company">Companies<#elseif searchBy == "employee"> Employees <#else>Companies and Employees</#if></b>
          </h4>
        </div>
       <#if searchBy == "company" || searchBy == "global"> 
       	<#if companies?? && companies?has_content>
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
  					<th>Show Employees</th>  
 			 	</tr>
          	</thead>
        	<tbody>  
        	<#list companies as company>
  				<tr>	
  					<td>${company.name!}</td> 
  					<td>${company.code!}</td> 
  					<td>${company.url!}</td> 
  					<td>${company.email!}</td> 
  					<td>${company.sector!}</td> 
  					<td>${company.regnumber!}</td> 
  					<td> <button type="button" data-toggle="modal" data-target="#myEmployees" class="btn btn-default" onclick=callElastic1(${company.code}); >Show Employees</button></td>
  				</tr>
         	</#list> 
          	</tbody>
        	</table>
        	</div>
        	<#elseif searchBy == "company">
    		No companies were found.
        </#if>
       </#if>
       <#if  searchBy == "employee"|| searchBy == "global"> 
        <#if employees?? && employees?has_content>
       <div id="searchTableDiv">
        <table class="table table-striped">
          <thead>
              <tr>
  				<th>Full Name</th> 
  				<th>First name</th> 
  				<th>Middle Name</th> 
  				<th>Last Name</th> 
  				<th>Work Email</th>
  				<th>Personal Email</th> 
  				<th>Age</th> 
  				<th>Contact Number</th> 
  				<th>Company Code</th> 
  				<th>Employee Id</th>   
  			</tr>
          </thead>
         <tbody>
         <#list employees as employee>
  			<tr>
  				<td>${employee.fullName!}</td>	
  				<td>${employee.firstName!}</td> 
  				<td>${employee.middleName!}</td> 
  				<td>${employee.lastName!}</td> 
  				<td>${employee.workEmail!}</td> 
  				<td>${employee.personalEmail!}</td> 
  				<td>${employee.age!}</td> 
  				<td>${employee.contactNumber!}</td> 
  				<td>${employee.companyCode!}</td> 
  				<td>${employee.employeeId!}</td> 
  			</tr> 			            
          </#list> 
          </tbody>
        </table>
        </div>
         <#elseif searchBy == "employee">>
    			No employee was found.
        </#if>
       </#if>
        <#if searchBy == "global" && !(companies?has_content) && !(employees?has_content)> 
      	 <div id="searchTableDiv">
      	 No Companies and Employees Found.
      	 </div>
      	</#if>
      	
 <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" id="myEmployees">
    <div class="custom-class">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                 <h4 class="modal-title" id="myModalLabel">Employees</h4>

            </div>
            <div class="modal-body" id="showEmployeesContent"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
    
    
      </div>
  </div>
</div>