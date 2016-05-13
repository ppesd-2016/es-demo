<div class="container" style="height:100%">
  <div class="row" style="height:100%">
      <div class="panel panel-default" style="height:95%">

        <#if stockprices?? && stockprices?has_content>
       <div id="showStockpriceDiv">
        <table class="table table-striped">
          <thead>
              <tr>
  				<th>Date</th> 
  				<th>Stock Price</th> 
  			</tr>
          </thead>
         <tbody>
         <#list stockprices as stockprice>
  			<tr>
  				<td>${stockprice.date!}</td>	
  				<td>${stockprice.stockprice!}</td> 
  			</tr> 			            
          </#list> 
          </tbody>
        </table>
        </div>
         <#else>
    		Stock prices not found for this company.
        </#if>
      </div>
  </div>
</div>