<#if autoSuggest?? && autoSuggest?has_content>

	<#list autoSuggest as suggestion>

		<option>${suggestion}</option>

	</#list>

</#if>