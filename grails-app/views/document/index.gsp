
<%@ page import="org.mesis.bila.Document" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'document.label', default: 'Document')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-document" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-document" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="document.docType.label" default="Doc Type" /></th>
					
						<g:sortableColumn property="dateReg" title="${message(code: 'document.dateReg.label', default: 'Date Reg')}" />
					
						<g:sortableColumn property="dateDoc" title="${message(code: 'document.dateDoc.label', default: 'Date Doc')}" />
					
						<g:sortableColumn property="nrDoc" title="${message(code: 'document.nrDoc.label', default: 'Nr Doc')}" />
					
						<g:sortableColumn property="amount" title="${message(code: 'document.amount.label', default: 'Amount')}" />
					
						<g:sortableColumn property="note" title="${message(code: 'document.note.label', default: 'Note')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${documentInstanceList}" status="i" var="documentInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${documentInstance.id}">${fieldValue(bean: documentInstance, field: "docType")}</g:link></td>
					
						<td><g:formatDate date="${documentInstance.dateReg}" /></td>
					
						<td><g:formatDate date="${documentInstance.dateDoc}" /></td>
					
						<td>${fieldValue(bean: documentInstance, field: "nrDoc")}</td>
					
						<td>${fieldValue(bean: documentInstance, field: "amount")}</td>
					
						<td>${fieldValue(bean: documentInstance, field: "note")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${documentInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
