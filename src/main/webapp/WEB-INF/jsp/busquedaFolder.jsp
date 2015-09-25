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
<meta charset = "utf-8">
<link rel="stylesheet" type="text/css" href="css/estiloIndex.css" />
<link rel="stylesheet" type="text/css" href="css/menuHorizontal.css" />
<link rel="stylesheet" href="css/displaytag.css" type="text/css">
<link rel="stylesheet" href="css/screen.css" type="text/css">
<link rel="stylesheet" href="css/site.css" type="text/css">
<!-- fancybox -->
<script src="script/jquery-1.4.3.min.js" type="text/javascript"></script>
<script src="script/jquery.fancybox.pack.js" type="text/javascript"></script>
<link href="css/jquery.fancybox.css" rel="stylesheet" type="text/css" media="screen" />
	
<!--  Imports -->
<link rel="stylesheet" href="css/formularios/form.css" type="text/css">
<link rel="stylesheet" href="css/formularios/inputs.css" type="text/css">
<!--  Fin - Imports -->
	
<title>Busqueda por Folder</title>

<script type="text/javascript">
	$(document).ready(function() {
		$(".ifancybox").fancybox({
			'width' : '80%',
			'height' : '80%',
			'autoScale' : false,
			'transitionIn' : 'none',
			'transitionOut' : 'none',
			'type' : 'iframe'
		});
	});
</script>
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
			<c:url var="editImgUrl" value="images/resources/edit.png" />
			<c:url var="deleteImgUrl" value="images/resources/delete.png" />
			<c:url var="notificacionImgUrl" value="images/resources/addNotificacion.png" />
			<c:url var="verDetalleImgUrl" value="images/resources/verDetalle.png" />
			
			<form:form commandName="folderBuscar">
				<div id="form">
					<fieldset id="formFielset">
						<br>
						<div>
							<fieldset id="formFielset">
								<label id="label_titulo_pestanas">Buscar Expediente</label>
							</fieldset>
						</div>
						
						
						<table border="0" align="center" id="form">
							<tr>								
								<th>
									<label class="label">N&#250;mero de Folder:</label>
									<form:input path="nroFolder" maxlength="50" class="input_one" placeholder="Ingrese el Nombre de Expediente" />										
									<input type="submit" id="Buscar" name="Buscar" value="Buscar" class="btn_guardar"/>								
								</th>								
								<th>																		
									<input type="button" id="AgregarExpediente" name="AgregarExpediente" onclick="location.href='<c:url value="/addProceso.htm"/>'" value="Nuevo Expediente" class="btn_nuevo_expediente" />
								</th>
							</tr>
						</table>
						<br>
						
						<table border="0" align="center">					
							<tr>
								<td>&nbsp;</td>
								<td valign="top"><display:table id="results"
										name="listaProcesoLitigantes" requestURI="/busquedaFolder.htm"
										pagesize="5" defaultsort="1" defaultorder="ascending">
										<display:column title="NÚMERO FOLDER" property="nroFolder" />
										<display:column title="NÚMERO EXPEDIENTE"
											property="nroExpediente" />
										<c:url var="verDemandante" value="demandanteList.htm">
											<c:param name="nroExpedienteActual"
												value="${results.nroExpediente}" />
										</c:url>
										<display:column title="DEMANDANTE">
											<a class="ifancybox" href="<c:out value="${verDemandante}"/>"><img
												src="<c:out value="${verDetalleImgUrl}"/>"
												title="Ver Demandantes"></img></a>
										</display:column>
										<c:url var="verDemandado" value="demandadoList.htm">
											<c:param name="nroExpedienteActual"
												value="${results.nroExpediente}" />
										</c:url>
										<display:column title="DEMANDADO">
											<a class="ifancybox" href="<c:out value="${verDemandado}"/>"><img
												src="<c:out value="${verDetalleImgUrl}"/>"
												title="Ver Demandados"></img></a>
										</display:column>
										<display:column title="NATURALEZA"
											property="materia.naturaleza.denominacion" />
										<display:column title="MATERIA"
											property="materia.denominacion" />
										<c:url var="addNotificacion" value="addNotificacion.htm">
											<c:param name="numExpediente"
												value="${results.nroExpediente}" />
										</c:url>
										<c:url var="editCcUrl" value="addDemandante.htm">
											<c:param name="idDemandante" value="${results.nroExpediente}" />
										</c:url>
										<c:url var="deleteCcUrl" value="borrarDemandante.htm">
											<c:param name="idDemandante" value="${results.nroExpediente}" />
										</c:url>
										<display:column title="AGREGAR NOTIFICACIÓN">
											<a href="<c:out value="${addNotificacion}"/>"><img
												src="<c:out value="${notificacionImgUrl}"/>"
												title="Agregar Notificacion"></img></a>
										</display:column>
										<display:column title="ACCIONES">
											<a href="<c:out value="${editCcUrl}"/>"><img
												src="<c:out value="${editImgUrl}"/>"
												title="Editar Demandante"></img></a>
											<a href="<c:out value="${deleteCcUrl}"/>"><img
												src="<c:out value="${deleteImgUrl}"/>"
												title="Eliminar Demandante"></img></a>
										</display:column>
									</display:table></td>
								<td>&nbsp;</td>
							</tr>
						</table>
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