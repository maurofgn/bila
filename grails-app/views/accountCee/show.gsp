
<%@ page import="org.mesis.bila.AccountCee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'accountCee.label', default: 'AccountCee')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-accountCee" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-accountCee" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list accountCee">
			
				<g:if test="${accountCeeInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="accountCee.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${accountCeeInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${accountCeeInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="accountCee.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${accountCeeInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${accountCeeInstance?.active}">
				<li class="fieldcontain">
					<span id="active-label" class="property-label"><g:message code="accountCee.active.label" default="Active" /></span>
					
						<span class="property-value" aria-labelledby="active-label"><g:formatBoolean boolean="${accountCeeInstance?.active}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${accountCeeInstance?.seqNo}">
				<li class="fieldcontain">
					<span id="seqNo-label" class="property-label"><g:message code="accountCee.seqNo.label" default="Seq No" /></span>
					
						<span class="property-value" aria-labelledby="seqNo-label"><g:fieldValue bean="${accountCeeInstance}" field="seqNo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${accountCeeInstance?.parent}">
				<li class="fieldcontain">
					<span id="parent-label" class="property-label"><g:message code="accountCee.parent.label" default="Parent" /></span>
					
						<span class="property-value" aria-labelledby="parent-label"><g:link controller="accountCee" action="show" id="${accountCeeInstance?.parent?.id}">${accountCeeInstance?.parent?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${accountCeeInstance?.summary}">
				<li class="fieldcontain">
					<span id="summary-label" class="property-label"><g:message code="accountCee.summary.label" default="Summary" /></span>
					
						<span class="property-value" aria-labelledby="summary-label"><g:formatBoolean boolean="${accountCeeInstance?.summary}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${accountCeeInstance?.debit}">
				<li class="fieldcontain">
					<span id="debit-label" class="property-label"><g:message code="accountCee.debit.label" default="Debit" /></span>
					
						<span class="property-value" aria-labelledby="debit-label"><g:formatBoolean boolean="${accountCeeInstance?.debit}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${accountCeeInstance?.total}">
				<li class="fieldcontain">
					<span id="total-label" class="property-label"><g:message code="accountCee.total.label" default="Total" /></span>
					
						<span class="property-value" aria-labelledby="total-label"><g:formatBoolean boolean="${accountCeeInstance?.total}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${accountCeeInstance?.sons}">
				<li class="fieldcontain">
					<span id="sons-label" class="property-label"><g:message code="accountCee.sons.label" default="Sons" /></span>
					
						<g:each in="${accountCeeInstance.sons}" var="s">
						<span class="property-value" aria-labelledby="sons-label"><g:link controller="accountCee" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:accountCeeInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${accountCeeInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
