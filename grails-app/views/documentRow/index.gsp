
<%@ page import="org.mesis.bila.DocumentRow" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'documentRow.label', default: 'DocumentRow')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-documentRow" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-documentRow" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="documentRow.account.label" default="Account" /></th>
					
						<g:sortableColumn property="debit" title="${message(code: 'documentRow.debit.label', default: 'Debit')}" />
					
						<g:sortableColumn property="amount" title="${message(code: 'documentRow.amount.label', default: 'Amount')}" />
					
						<g:sortableColumn property="note" title="${message(code: 'documentRow.note.label', default: 'Note')}" />
					
						<th><g:message code="documentRow.document.label" default="Document" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${documentRowInstanceList}" status="i" var="documentRowInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${documentRowInstance.id}">${fieldValue(bean: documentRowInstance, field: "account")}</g:link></td>
					
						<td><g:formatBoolean boolean="${documentRowInstance.debit}" /></td>
					
						<td>${fieldValue(bean: documentRowInstance, field: "amount")}</td>
					
						<td>${fieldValue(bean: documentRowInstance, field: "note")}</td>
					
						<td>${fieldValue(bean: documentRowInstance, field: "document")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${documentRowInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
