<%@ page import="org.mesis.bila.Account" %>



<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="account.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" maxlength="10" required="" value="${accountInstance?.code}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="account.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" maxlength="60" required="" value="${accountInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'active', 'error')} ">
	<label for="active">
		<g:message code="account.active.label" default="Active" />
		
	</label>
	<g:checkBox name="active" value="${accountInstance?.active}" />

</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'accountCee', 'error')} required">
	<label for="accountCee">
		<g:message code="account.accountCee.label" default="Account Cee" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="accountCee" name="accountCee.id" from="${org.mesis.bila.AccountCee.list()}" optionKey="id" required="" value="${accountInstance?.accountCee?.id}" class="many-to-one"/>

</div>

