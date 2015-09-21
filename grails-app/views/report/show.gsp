
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
			
		<g:form action="generateReport" controller="jasperReport"  >
		
		
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
			
			<g:hiddenField name="_file" value="${reportInstance?.modelName}" />
				
			<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'outputFileName', 'error')} required">
				<label for="outputFileName">
					<g:message code="report.outputFileName.label" default="Output File Name" />
					<span class="required-indicator">*</span>
				</label>
				<g:textField name="_name" maxlength="60" required="" value="${reportInstance?.outputFileName}"/>

			</div>

			<div class="fieldcontain ${hasErrors(bean: reportInstance, field: 'formatType', 'error')} required">
				<label for="formatType">
					<g:message code="report.formatType.label" default="Format Type" />
					<span class="required-indicator">*</span>
				</label>
				<g:select name="_format" from="${reportInstance.constraints.formatType.inList}" required="" value="${reportInstance?.formatType}" valueMessagePrefix="report.formatType"/>

			</div>

			<div class="fieldcontain required">
				<label for="year">
					<g:message code="report.year.label" default="Anno" />
					<span class="required-indicator">*</span>
				</label>

				<g:field name="year" type="number" min="${yearMin}" max="${yearMax}" value="${year}" required=""/>
			</div>
			
			</ol>
			<fieldset class="buttons">
				<g:submitButton name="create" class="print" value="${message(code: 'default.button.print.label', default: 'print')}" />
			</fieldset>
		</g:form>
		
		</div>
	</body>
</html>
