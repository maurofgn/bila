
<%@ page import="org.mesis.bila.AccountCee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'accountCee.label', default: 'AccountCee')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-accountCee" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-accountCee" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="code" title="${message(code: 'accountCee.code.label', default: 'Code')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'accountCee.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="active" title="${message(code: 'accountCee.active.label', default: 'Active')}" />
					
						<g:sortableColumn property="seqNo" title="${message(code: 'accountCee.seqNo.label', default: 'Seq No')}" />
					
						<th><g:message code="accountCee.parent.label" default="Parent" /></th>
					
						<g:sortableColumn property="summary" title="${message(code: 'accountCee.summary.label', default: 'Summary')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${accountCeeInstanceList}" status="i" var="accountCeeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${accountCeeInstance.id}">${fieldValue(bean: accountCeeInstance, field: "code")}</g:link></td>
					
						<td>${fieldValue(bean: accountCeeInstance, field: "description")}</td>
					
						<td><g:formatBoolean boolean="${accountCeeInstance.active}" /></td>
					
						<td>${fieldValue(bean: accountCeeInstance, field: "seqNo")}</td>
					
						<td>${fieldValue(bean: accountCeeInstance, field: "parent")}</td>
					
						<td><g:formatBoolean boolean="${accountCeeInstance.summary}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${accountCeeInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
