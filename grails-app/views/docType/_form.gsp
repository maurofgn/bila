<%@ page import="org.mesis.bila.DocType" %>



<div class="fieldcontain ${hasErrors(bean: docTypeInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="docType.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" maxlength="10" required="" value="${docTypeInstance?.code}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: docTypeInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="docType.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" maxlength="60" required="" value="${docTypeInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: docTypeInstance, field: 'active', 'error')} ">
	<label for="active">
		<g:message code="docType.active.label" default="Active" />
		
	</label>
	<g:checkBox name="active" value="${docTypeInstance?.active}" />

</div>

