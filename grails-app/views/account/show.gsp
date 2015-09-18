
<%@ page import="org.mesis.bila.Account" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'account.label', default: 'Account')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-account" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-account" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list account">
			
				<g:if test="${accountInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="account.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${accountInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${accountInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="account.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${accountInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${accountInstance?.active}">
				<li class="fieldcontain">
					<span id="active-label" class="property-label"><g:message code="account.active.label" default="Active" /></span>
					
						<span class="property-value" aria-labelledby="active-label"><g:formatBoolean boolean="${accountInstance?.active}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${accountInstance?.accountCee}">
				<li class="fieldcontain">
					<span id="accountCee-label" class="property-label"><g:message code="account.accountCee.label" default="Account Cee" /></span>
					
						<span class="property-value" aria-labelledby="accountCee-label"><g:link controller="accountCee" action="show" id="${accountInstance?.accountCee?.id}">${accountInstance?.accountCee?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:accountInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${accountInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
