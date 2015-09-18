
<%@ page import="org.mesis.bila.Report" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'report.label', default: 'Report')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-report" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-report" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="code" title="${message(code: 'report.code.label', default: 'Code')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'report.description.label', default: 'Description')}" />
<%--					
						<g:sortableColumn property="modelName" title="${message(code: 'report.modelName.label', default: 'Model Name')}" />
					
						<g:sortableColumn property="outputFileName" title="${message(code: 'report.outputFileName.label', default: 'Output File Name')}" />
					
						<g:sortableColumn property="formatType" title="${message(code: 'report.formatType.label', default: 'Format Type')}" />
					
						<g:sortableColumn property="active" title="${message(code: 'report.active.label', default: 'Active')}" />
--%>					
					</tr>
				</thead>
				<tbody>
				<g:each in="${reportInstanceList}" status="i" var="reportInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${reportInstance.id}">${fieldValue(bean: reportInstance, field: "code")}</g:link></td>
					
						<td>${fieldValue(bean: reportInstance, field: "description")}</td>
<%--					
						<td>${fieldValue(bean: reportInstance, field: "modelName")}</td>
					
						<td>${fieldValue(bean: reportInstance, field: "outputFileName")}</td>
					
						<td>${fieldValue(bean: reportInstance, field: "formatType")}</td>
					
						<td><g:formatBoolean boolean="${reportInstance.active}" /></td>
--%>					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${reportInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
