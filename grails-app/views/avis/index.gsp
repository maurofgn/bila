
<%@ page import="org.mesis.bila.Avis" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'avis.label', default: 'Avis')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-avis" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-avis" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'avis.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="address" title="${message(code: 'avis.address.label', default: 'Address')}" />
					
						<g:sortableColumn property="city" title="${message(code: 'avis.city.label', default: 'City')}" />
					
						<g:sortableColumn property="postalCode" title="${message(code: 'avis.postalCode.label', default: 'Postal Code')}" />
					
						<g:sortableColumn property="region" title="${message(code: 'avis.region.label', default: 'Region')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'avis.email.label', default: 'Email')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${avisInstanceList}" status="i" var="avisInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${avisInstance.id}">${fieldValue(bean: avisInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: avisInstance, field: "address")}</td>
					
						<td>${fieldValue(bean: avisInstance, field: "city")}</td>
					
						<td>${fieldValue(bean: avisInstance, field: "postalCode")}</td>
					
						<td>${fieldValue(bean: avisInstance, field: "region")}</td>
					
						<td>${fieldValue(bean: avisInstance, field: "email")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${avisInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
