
<%@ page import="org.mesis.bila.Avis" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'avis.label', default: 'Avis')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-avis" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
<%--
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
--%>
			</ul>
		</div>
		<div id="show-avis" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list avis">
			
				<g:if test="${avisInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="avis.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${avisInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${avisInstance?.address}">
				<li class="fieldcontain">
					<span id="address-label" class="property-label"><g:message code="avis.address.label" default="Address" /></span>
					
						<span class="property-value" aria-labelledby="address-label"><g:fieldValue bean="${avisInstance}" field="address"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${avisInstance?.city}">
				<li class="fieldcontain">
					<span id="city-label" class="property-label"><g:message code="avis.city.label" default="City" /></span>
					
						<span class="property-value" aria-labelledby="city-label"><g:fieldValue bean="${avisInstance}" field="city"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${avisInstance?.postalCode}">
				<li class="fieldcontain">
					<span id="postalCode-label" class="property-label"><g:message code="avis.postalCode.label" default="Postal Code" /></span>
					
						<span class="property-value" aria-labelledby="postalCode-label"><g:fieldValue bean="${avisInstance}" field="postalCode"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${avisInstance?.region}">
				<li class="fieldcontain">
					<span id="region-label" class="property-label"><g:message code="avis.region.label" default="Region" /></span>
					
						<span class="property-value" aria-labelledby="region-label"><g:fieldValue bean="${avisInstance}" field="region"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${avisInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="avis.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${avisInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${avisInstance?.phone}">
				<li class="fieldcontain">
					<span id="phone-label" class="property-label"><g:message code="avis.phone.label" default="Phone" /></span>
					
						<span class="property-value" aria-labelledby="phone-label"><g:fieldValue bean="${avisInstance}" field="phone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${avisInstance?.contactName}">
				<li class="fieldcontain">
					<span id="contactName-label" class="property-label"><g:message code="avis.contactName.label" default="Contact Name" /></span>
					
						<span class="property-value" aria-labelledby="contactName-label"><g:fieldValue bean="${avisInstance}" field="contactName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${avisInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="avis.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${avisInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${avisInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="avis.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${avisInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:avisInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${avisInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
<%--
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
--%>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
