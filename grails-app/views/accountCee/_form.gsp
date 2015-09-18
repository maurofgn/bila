<%@ page import="org.mesis.bila.AccountCee" %>



<div class="fieldcontain ${hasErrors(bean: accountCeeInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="accountCee.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" maxlength="10" required="" value="${accountCeeInstance?.code}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: accountCeeInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="accountCee.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" maxlength="60" required="" value="${accountCeeInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: accountCeeInstance, field: 'active', 'error')} ">
	<label for="active">
		<g:message code="accountCee.active.label" default="Active" />
		
	</label>
	<g:checkBox name="active" value="${accountCeeInstance?.active}" />

</div>

<div class="fieldcontain ${hasErrors(bean: accountCeeInstance, field: 'seqNo', 'error')} required">
	<label for="seqNo">
		<g:message code="accountCee.seqNo.label" default="Seq No" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="seqNo" type="number" value="${accountCeeInstance.seqNo}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: accountCeeInstance, field: 'parent', 'error')} ">
	<label for="parent">
		<g:message code="accountCee.parent.label" default="Parent" />
		
	</label>
	
	<g:select id="parent" name="parent.id" from="${
		org.mesis.bila.AccountCee.createCriteria().list{
			eq('summary', true)
			eq('active', true)
			}
		}" 
		optionKey="id" value="${accountCeeInstance?.parent?.id}" class="many-to-one" 
	/>

</div>

<div class="fieldcontain ${hasErrors(bean: accountCeeInstance, field: 'summary', 'error')} ">
	<label for="summary">
		<g:message code="accountCee.summary.label" default="Summary" />
		
	</label>
	<g:checkBox name="summary" value="${accountCeeInstance?.summary}" />

</div>

<div class="fieldcontain ${hasErrors(bean: accountCeeInstance, field: 'debit', 'error')} ">
	<label for="debit">
		<g:message code="accountCee.debit.label" default="Debit" />
		
	</label>
	<g:checkBox name="debit" value="${accountCeeInstance?.debit}" />

</div>

<div class="fieldcontain ${hasErrors(bean: accountCeeInstance, field: 'total', 'error')} ">
	<label for="total">
		<g:message code="accountCee.total.label" default="Total" />
		
	</label>
	<g:checkBox name="total" value="${accountCeeInstance?.total}" />

</div>

<div class="fieldcontain ${hasErrors(bean: accountCeeInstance, field: 'sons', 'error')} ">
	<label for="sons">
		<g:message code="accountCee.sons.label" default="Sons" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${accountCeeInstance?.sons?}" var="s">
    <li><g:link controller="accountCee" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="accountCee" action="create" params="['accountCee.id': accountCeeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'accountCee.label', default: 'AccountCee')])}</g:link>
</li>
</ul>


</div>

