<%@ page import="org.mesis.bila.Document" %>



<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'docType', 'error')} required">
	<label for="docType">
		<g:message code="document.docType.label" default="Doc Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="docType" name="docType.id" from="${org.mesis.bila.DocType.list()}" optionKey="id" required="" value="${documentInstance?.docType?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'dateReg', 'error')} required">
	<label for="dateReg">
		<g:message code="document.dateReg.label" default="Date Reg" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateReg" precision="day"  value="${documentInstance?.dateReg}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'dateDoc', 'error')} required">
	<label for="dateDoc">
		<g:message code="document.dateDoc.label" default="Date Doc" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateDoc" precision="day"  value="${documentInstance?.dateDoc}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'nrDoc', 'error')} required">
	<label for="nrDoc">
		<g:message code="document.nrDoc.label" default="Nr Doc" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nrDoc" maxlength="60" required="" value="${documentInstance?.nrDoc}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="document.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="amount" value="${fieldValue(bean: documentInstance, field: 'amount')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'note', 'error')} ">
	<label for="note">
		<g:message code="document.note.label" default="Note" />
		
	</label>
	<g:textField name="note" value="${documentInstance?.note}"/>

</div>

<g:if test="${documentInstance?.id}">
	<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'rows', 'error')} ">
		<label for="rows">
			<g:message code="document.rows.label" default="Rows" />
			
		</label>
		
	<ul class="one-to-many">
	<g:each in="${documentInstance?.rows?}" var="r">
		<li><g:link controller="documentRow" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
	</g:each>
	<li class="add">
	<g:link controller="documentRow" action="create" params="['document.id': documentInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'documentRow.label', default: 'DocumentRow')])}</g:link>
	</li>
	</ul>


	</div>
</g:if>
