<div class="container" style="height:100%">
  <div class="row" style="height:100%">
      <div class="panel panel-default" style="height:95%">

      <#if employees?? && employees?has_content>
       <div id="showEmployeedDiv">
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
         <#elseif searchBy == "employee">
    			No employee was found.
        </#if>
      </div>
  </div>
</div>