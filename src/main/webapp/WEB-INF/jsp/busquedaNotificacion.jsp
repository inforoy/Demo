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
<!-- fancybox -->
<script src="script/jquery-1.4.3.min.js" type="text/javascript"></script>
<script src="script/jquery.fancybox.pack.js" type="text/javascript"></script>
<link href="css/jquery.fancybox.css" rel="stylesheet" type="text/css"
	media="screen" />

<!--  Imports -->
<link rel="stylesheet" href="css/formularios/form.css" type="text/css">
<link rel="stylesheet" href="css/formularios/inputs.css" type="text/css">
<!--  Fin - Imports -->

<title>Busqueda Notificacion</title>

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
			<c:url var="verDetalleImgUrl" value="images/resources/verDetalle.png" />
			<c:url var="addAudienciaImgUrl"
				value="images/resources/addAudiencia.png" />
			<c:url var="addEscritoImgUrl" value="images/resources/addEscrito.png" />
			
			<form:form commandName="notificacionBuscar">
				<div id="form">
					<fieldset id="formFielset">
						<br>
						<div>
							<fieldset id="formFielset">
								<label id="label_titulo_pestanas">Buscar Notificación</label>
							</fieldset>
						</div>
						
						
						<table border="0" align="center" id="form">
							<tr>								
								<th>
									<label class="label">N&#250;mero de Notificacion:</label>
									<form:input path="nroNotificacion" maxlength="50" class="input_one" placeholder="Ingrese el Número de Notificación" />
									<input type="submit" id="Buscar" name="Buscar" value="Buscar" class="btn_guardar" />									
								</th>								
								<th>		
									&nbsp;							
<%-- 									<input type="button" id="AgregarExpediente" name="AgregarExpediente" onclick="location.href='<c:url value="/addProceso.htm"/>'" value="Nuevo Expediente" class="btn_nuevo_expediente" /> --%>
								</th>
							</tr>
						</table>
						
					
						<table border="0" align="center">						
							<tr>
<!-- 								<td>&nbsp;</td> -->
								<td valign="top"><display:table id="notificacion" name="listaNotificaciones" requestURI="/busquedaNotificacion.htm" pagesize="10" defaultsort="1" defaultorder="ascending">
										<display:column title="NÚMERO NOTIFICACIÓN" property="nroNotificacion" />
										<display:column title="NÚMERO FOLDER" property="proceso.nroFolder" />
										<display:column title="NÚMERO EXPEDIENTE" property="proceso.nroExpediente" />
										<display:column title="FECHA INGRESO" property="fechaIngreso" />
										<display:column title="FECHA EMISIÓN" property="fechaResolucion" />
										<display:column title="RESOLUCIÓN" property="nroResolucion" />
										<c:url var="verDemandante" value="demandanteList.htm">
											<c:param name="nroExpedienteActual" value="${notificacion.proceso.nroExpediente}" />
										</c:url>
										<display:column title="DEMANDANTE">
											<a class="ifancybox" href="<c:out value="${verDemandante}"/>"><img src="<c:out value="${verDetalleImgUrl}"/>" title="Ver Demandantes"></img></a>
										</display:column>
										<c:url var="verDemandado" value="demandadoList.htm">
											<c:param name="nroExpedienteActual" value="${notificacion.proceso.nroExpediente}" />
										</c:url>
										<display:column title="DEMANDADO">
											<a class="ifancybox" href="<c:out value="${verDemandado}"/>"><img src="<c:out value="${verDetalleImgUrl}"/>" title="Ver Demandados"></img></a>
										</display:column>
										<c:if
											test="${datosUsuario.rol.idRol eq 2 || datosUsuario.rol.idRol eq 1}">
											<c:url var="addEscritoCcUrl" value="addEscrito.htm">
												<c:param name="numNotificacion" value="${notificacion.nroNotificacion}" />
											</c:url>
											<display:column title="AGREGAR ESCRITO">
												<a href="<c:out value="${addEscritoCcUrl}"/>"><img src="<c:out value="${addEscritoImgUrl}"/>" title="Agregar Escrito"></img></a>
											</display:column>
											<c:url var="addAudienciaCcUrl" value="addAudiencia.htm">
												<c:param name="numNotificacion" value="${notificacion.nroNotificacion}" />
											</c:url>
											<display:column title="AGREGAR AUDIENCIA">
												<a href="<c:out value="${addAudienciaCcUrl}"/>"><img src="<c:out value="${addAudienciaImgUrl}"/>" title="Agregar Audiencia"></img></a>
											</display:column>
										</c:if>
										<c:url var="editCcUrl" value="addNotificacion.htm">
											<c:param name="numNotificacion" value="${notificacion.nroNotificacion}" />
										</c:url>
										<c:url var="deleteCcUrl" value="borrarNotificacion.htm">
											<c:param name="numNotificacion" value="${notificacion.nroNotificacion}" />
										</c:url>
										<display:column title="ACCIONES">
											<a href="<c:out value="${editCcUrl}"/>"><img src="<c:out value="${editImgUrl}"/>" title="Editar Notificacion"></img></a>
											<a href="<c:out value="${deleteCcUrl}"/>"><img src="<c:out value="${deleteImgUrl}"/>" title="Eliminar Notificacion"></img></a>
										</display:column>
									</display:table></td>
<!-- 								<td>&nbsp;</td> -->
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