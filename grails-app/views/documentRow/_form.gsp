<%@ page import="org.mesis.bila.DocumentRow" %>



<div class="fieldcontain ${hasErrors(bean: documentRowInstance, field: 'account', 'error')} required">
	<label for="account">
		<g:message code="documentRow.account.label" default="Account" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="account" name="account.id" from="${org.mesis.bila.Account.list()}" optionKey="id" required="" value="${documentRowInstance?.account?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: documentRowInstance, field: 'debit', 'error')} ">
	<label for="debit">
		<g:message code="documentRow.debit.label" default="Debit" />
		
	</label>
	<g:checkBox name="debit" value="${documentRowInstance?.debit}" />

</div>

<div class="fieldcontain ${hasErrors(bean: documentRowInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="documentRow.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="amount" value="${fieldValue(bean: documentRowInstance, field: 'amount')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: documentRowInstance, field: 'note', 'error')} required">
	<label for="note">
		<g:message code="documentRow.note.label" default="Note" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="note" required="" value="${documentRowInstance?.note}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: documentRowInstance, field: 'document', 'error')} required">
	<label for="document">
		<g:message code="documentRow.document.label" default="Document" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="document" name="document.id" from="${org.mesis.bila.Document.list()}" optionKey="id" required="" value="${documentRowInstance?.document?.id}" class="many-to-one"/>

</div>

