
<%@ page import="org.mesis.bila.Report" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'report.label', default: 'Report')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-report" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-report" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			
			
<%--			
http://localhost:8080/bila/jasperReport/generateReport?_format=PDF&_name=bilancio_2015&_file=bilancio 
--%>
			
		<g:form url="[ action:'generateReport', controller:'jasperReport']" >
		
		
		
			<ol class="property-list report">
			
			
				<g:if test="${reportInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="report.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${reportInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${reportInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="report.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${reportInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${reportInstance?.modelName}">
				<li class="fieldcontain">
					<span id="modelName-label" class="property-label"><g:message code="report.modelName.label" default="Model Name" /></span>
					
						<span class="property-value" aria-labelledby="modelName-label"><g:fieldValue bean="${reportInstance}" field="modelName"/></span>
					
				</li>
				</g:if>

				

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




				
		<a href="/bila/jasperReport/generateReport?_format=XLS&_name=bilancio&_file=bilancio">
			XLS
		</a>
		|
		<a href="/bila/jasperReport/generateReport?_format=PDF&_name=bilancio_2015&_file=bilancio">
			PDF
		</a>
				
				
<%--			
				<g:if test="${reportInstance?.outputFileName}">
				<li class="fieldcontain">
					<span id="outputFileName-label" class="property-label"><g:message code="report.outputFileName.label" default="Output File Name" /></span>
					
						<span class="property-value" aria-labelledby="outputFileName-label"><g:fieldValue bean="${reportInstance}" field="outputFileName"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${reportInstance?.formatType}">
				<li class="fieldcontain">
					<span id="formatType-label" class="property-label"><g:message code="report.formatType.label" default="Format Type" /></span>
					
						<span class="property-value" aria-labelledby="formatType-label"><g:fieldValue bean="${reportInstance}" field="formatType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${reportInstance?.active}">
				<li class="fieldcontain">
					<span id="active-label" class="property-label"><g:message code="report.active.label" default="Active" /></span>
					
						<span class="property-value" aria-labelledby="active-label"><g:formatBoolean boolean="${reportInstance?.active}" /></span>
					
				</li>
				</g:if>
--%>			
				<g:if test="${reportInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="report.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${reportInstance?.dateCreated}" type="datetime" style="SHORT"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${reportInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="report.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${reportInstance?.lastUpdated}" type="datetime" style="SHORT" /></span>
					
				</li>
				</g:if>
			
			</ol>
			
			
			<fieldset class="buttons">
				<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
			</fieldset>
		</g:form>
			
			
			
			
			
			<g:form url="[resource:reportInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${reportInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>