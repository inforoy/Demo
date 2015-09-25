<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/estiloIndex.css"/>
<link rel="stylesheet" type="text/css" href="css/menuHorizontal.css"/>

<!--  Imports -->
<link rel="stylesheet" href="css/formularios/form.css" type="text/css">
<link rel="stylesheet" href="css/formularios/inputs.css" type="text/css">
<!--  Fin - Imports -->
<title>Agregar Organo Judicial</title>
  
</head>
<body>
<div id="contenedorPrincipal">
	<security:authorize access="hasRole('Procurador')">
		<jsp:include page="/WEB-INF/cabecera/cabeceraProcurador.jsp" />
	</security:authorize>
	<security:authorize access="hasRole('Abogado')">
		<jsp:include page="/WEB-INF/cabecera/cabeceraAbogado.jsp" />
	</security:authorize>
	<security:authorize access="hasRole('Mesa de Partes')">
		<jsp:include page="/WEB-INF/cabecera/cabeceraMesaPartes.jsp" />
	</security:authorize>
<div>
<form:form name="form1" commandName="organoJudicialGuardar">
<div id="form">
<fieldset id="formFielset">
<legend>CREAR ÓRGANO JUDICIAL</legend>

<ol>
<form:hidden path="idOrganojudicial"/>
<li><label class="label">Denominación:</label>
	<form:input path="denominacion" maxlength="100" class="input_one"/>
	<font color="#FF0000">
		<form:errors id="denominacionErrors" name="denominacionErrors" path="denominacion" />
	</font>
</li>
</ol>
<p align="center">
	<input type="button" onclick="location.href='<c:url value="/registrarOrganoJudicial.htm"/>'" value="Cancelar" class="btn_asignar" />
	<input type="submit" value="Guardar" class="btn_guardar"/>
</p>
</fieldset>
</div>
</form:form>
 </div><!-- FIN Cuerpo -->
<jsp:include page="/WEB-INF/pie/pie.jsp" />
</div><!-- FIN Contenedor PRINCIPAL -->

</body>
</html>