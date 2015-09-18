
<%@ page import="org.mesis.bila.DocumentRow" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'documentRow.label', default: 'DocumentRow')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-documentRow" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-documentRow" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list documentRow">
			
				<g:if test="${documentRowInstance?.account}">
				<li class="fieldcontain">
					<span id="account-label" class="property-label"><g:message code="documentRow.account.label" default="Account" /></span>
					
						<span class="property-value" aria-labelledby="account-label"><g:link controller="account" action="show" id="${documentRowInstance?.account?.id}">${documentRowInstance?.account?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentRowInstance?.debit}">
				<li class="fieldcontain">
					<span id="debit-label" class="property-label"><g:message code="documentRow.debit.label" default="Debit" /></span>
					
						<span class="property-value" aria-labelledby="debit-label"><g:formatBoolean boolean="${documentRowInstance?.debit}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentRowInstance?.amount}">
				<li class="fieldcontain">
					<span id="amount-label" class="property-label"><g:message code="documentRow.amount.label" default="Amount" /></span>
					
						<span class="property-value" aria-labelledby="amount-label"><g:fieldValue bean="${documentRowInstance}" field="amount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentRowInstance?.note}">
				<li class="fieldcontain">
					<span id="note-label" class="property-label"><g:message code="documentRow.note.label" default="Note" /></span>
					
						<span class="property-value" aria-labelledby="note-label"><g:fieldValue bean="${documentRowInstance}" field="note"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentRowInstance?.document}">
				<li class="fieldcontain">
					<span id="document-label" class="property-label"><g:message code="documentRow.document.label" default="Document" /></span>
					
						<span class="property-value" aria-labelledby="document-label"><g:link controller="document" action="show" id="${documentRowInstance?.document?.id}">${documentRowInstance?.document?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:documentRowInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${documentRowInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
