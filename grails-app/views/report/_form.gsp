<%@ page import="org.mesis.bila.Report" %>



<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="report.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" maxlength="20" required="" value="${reportInstance?.code}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="report.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" maxlength="60" required="" value="${reportInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'modelName', 'error')} required">
	<label for="modelName">
		<g:message code="report.modelName.label" default="Model Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="modelName" maxlength="60" required="" value="${reportInstance?.modelName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'outputFileName', 'error')} required">
	<label for="outputFileName">
		<g:message code="report.outputFileName.label" default="Output File Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="outputFileName" maxlength="60" required="" value="${reportInstance?.outputFileName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'formatType', 'error')} required">
	<label for="formatType">
		<g:message code="report.formatType.label" default="Format Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="formatType" from="${reportInstance.constraints.formatType.inList}" required="" value="${reportInstance?.formatType}" valueMessagePrefix="report.formatType"/>

</div>

<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'active', 'error')} ">
	<label for="active">
		<g:message code="report.active.label" default="Active" />
		
	</label>
	<g:checkBox name="active" value="${reportInstance?.active}" />

</div>

