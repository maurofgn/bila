
<%@ page import="org.mesis.bila.Document" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'document.label', default: 'Document')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-document" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-document" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list document">
			
				<g:if test="${documentInstance?.docType}">
				<li class="fieldcontain">
					<span id="docType-label" class="property-label"><g:message code="document.docType.label" default="Doc Type" /></span>
					
						<span class="property-value" aria-labelledby="docType-label"><g:link controller="docType" action="show" id="${documentInstance?.docType?.id}">${documentInstance?.docType?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentInstance?.dateReg}">
				<li class="fieldcontain">
					<span id="dateReg-label" class="property-label"><g:message code="document.dateReg.label" default="Date Reg" /></span>
					
						<span class="property-value" aria-labelledby="dateReg-label"><g:formatDate date="${documentInstance?.dateReg}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentInstance?.dateDoc}">
				<li class="fieldcontain">
					<span id="dateDoc-label" class="property-label"><g:message code="document.dateDoc.label" default="Date Doc" /></span>
					
						<span class="property-value" aria-labelledby="dateDoc-label"><g:formatDate date="${documentInstance?.dateDoc}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentInstance?.nrDoc}">
				<li class="fieldcontain">
					<span id="nrDoc-label" class="property-label"><g:message code="document.nrDoc.label" default="Nr Doc" /></span>
					
						<span class="property-value" aria-labelledby="nrDoc-label"><g:fieldValue bean="${documentInstance}" field="nrDoc"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentInstance?.amount}">
				<li class="fieldcontain">
					<span id="amount-label" class="property-label"><g:message code="document.amount.label" default="Amount" /></span>
					
						<span class="property-value" aria-labelledby="amount-label"><g:fieldValue bean="${documentInstance}" field="amount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentInstance?.note}">
				<li class="fieldcontain">
					<span id="note-label" class="property-label"><g:message code="document.note.label" default="Note" /></span>
					
						<span class="property-value" aria-labelledby="note-label"><g:fieldValue bean="${documentInstance}" field="note"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentInstance?.rows}">
				<li class="fieldcontain">
					<span id="rows-label" class="property-label"><g:message code="document.rows.label" default="Rows" /></span>
					
						<g:each in="${documentInstance.rows}" var="r">
						<span class="property-value" aria-labelledby="rows-label"><g:link controller="documentRow" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:documentInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${documentInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
