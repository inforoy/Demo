<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/estiloIndex.css" />
<link rel="stylesheet" type="text/css" href="css/menuHorizontal.css" />
<link rel="stylesheet" href="css/displaytag.css" type="text/css">
<link rel="stylesheet" href="css/screen.css" type="text/css">
<link rel="stylesheet" href="css/site.css" type="text/css">

<!--  Imports -->
<link rel="stylesheet" href="css/formularios/form.css" type="text/css">
<link rel="stylesheet" href="css/formularios/inputs.css" type="text/css">
<!--  Fin - Imports -->

<title>Mostrar Carga Laboral</title>

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
			<c:url var="asignacionImgUrl" value="images/resources/addAsignacion.png" />
			<form:form commandName="asignadoBuscar">
				<div id="form">
					<fieldset id="formFielset">
						<br>
						<div>
							<fieldset id="formFielset">
								<label id="label_titulo_pestanas">Lista de Expedientes NO Asignados</label>
							</fieldset>
						</div>
						<display:table id="proceso" name="listaNoAsignados" requestURI="/busquedaAsignacion.htm" pagesize="5" defaultsort="1" defaultorder="ascending">
							
							<display:column title="NÚMERO FOLDER" property="nroFolder" />
							
							<display:column title="NÚMERO EXPEDIENTE" property="nroExpediente" />
							
							<display:column title="NATURALEZA" property="materia.naturaleza.denominacion" />
							
							<display:column title="MATERIA" property="materia.denominacion" />
							
							<c:url var="addAsignacion" value="addAsignacion.htm">
								<c:param name="numExpediente" value="${proceso.nroExpediente}" />
							</c:url>
							
							<display:column title="ASIGNAR">
								<a href="<c:out value="${addAsignacion}"/>"><img src="<c:out value="${asignacionImgUrl}"/>" title="Agregar Notificacion"></img></a>
							</display:column>
							
						</display:table>
					</fieldset>
				</div>
			</form:form>

		</div>
		<!-- FIN Cuerpo -->
		<jsp:include page="/WEB-INF/pie/pie.jsp" />
	</div>
	<!-- FIN Contenedor PRINCIPAL -->

</body>
</html>