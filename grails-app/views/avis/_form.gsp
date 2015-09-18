<%@ page import="org.mesis.bila.Avis" %>



<div class="fieldcontain ${hasErrors(bean: avisInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="avis.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="60" required="" value="${avisInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: avisInstance, field: 'address', 'error')} required">
	<label for="address">
		<g:message code="avis.address.label" default="Address" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="address" maxlength="60" required="" value="${avisInstance?.address}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: avisInstance, field: 'city', 'error')} required">
	<label for="city">
		<g:message code="avis.city.label" default="City" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="city" maxlength="60" required="" value="${avisInstance?.city}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: avisInstance, field: 'postalCode', 'error')} required">
	<label for="postalCode">
		<g:message code="avis.postalCode.label" default="Postal Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="postalCode" type="number" min="0" max="99999" value="${avisInstance.postalCode}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: avisInstance, field: 'region', 'error')} required">
	<label for="region">
		<g:message code="avis.region.label" default="Region" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="region" maxlength="4" required="" value="${avisInstance?.region}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: avisInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="avis.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" maxlength="60" required="" value="${avisInstance?.email}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: avisInstance, field: 'phone', 'error')} ">
	<label for="phone">
		<g:message code="avis.phone.label" default="Phone" />
		
	</label>
	<g:textField name="phone" maxlength="20" value="${avisInstance?.phone}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: avisInstance, field: 'contactName', 'error')} ">
	<label for="contactName">
		<g:message code="avis.contactName.label" default="Contact Name" />
		
	</label>
	<g:textField name="contactName" maxlength="60" value="${avisInstance?.contactName}"/>

</div>

