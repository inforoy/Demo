<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<script src="script/jquery-1.4.3.min.js" type="text/javascript"></script>
<script src="script/jquery.fancybox.pack.js" type="text/javascript"></script>
<link href="css/jquery.fancybox.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" href="css/displaytag.css" type="text/css">
<link rel="stylesheet" href="css/screen.css" type="text/css">
<link rel="stylesheet" href="css/site.css" type="text/css">

<link rel="stylesheet" href="css/formularios/inputs.css" type="text/css">
<link rel="stylesheet" href="css/formularios/form.css" type="text/css">


<title>Lista Expedientes</title>

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
<c:url var="editImgUrl" value="images/resources/edit.png" />
<!--<c:url var="deleteImgUrl" value="images/resources/delete.png" /> -->
<c:url var="notificacionImgUrl" value="images/resources/addNotificacion.png" />
<c:url var="verDetalleImgUrl" value="images/resources/verDetalle.png" />
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
		<!-- FIN Contenedor Menu -->
		
		<div align="center">

			<form:form commandName="procesoBuscar">
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
									<label class="label">N&#250;mero de Expediente:</label>
									<form:input path="nroExpediente" maxlength="50" class="input_one" placeholder="Ingrese el Número de Expediente" />
									<input type="submit" id="Buscar" name="Buscar" value="Buscar" class="btn_buscar" />
								</th>
								<th>
									<input type="button" id="AgregarExpediente" name="AgregarExpediente" onclick="location.href='<c:url value="/addProceso.htm"/>'" value="Nuevo Expediente" class="btn_nuevo_expediente" />
								</th>
							</tr>	
						</table>
						<table border="0">
							<tr>
								<th>
									<display:table id="results" name="listaProcesoLitigantes" requestURI="/procesoList.htm" pagesize="10" defaultsort="1" defaultorder="ascending">
								
										<display:column property="nroFolder" title="FOLDER" sortable="true" />
										
										<display:column property="nroExpediente" title="EXPEDIENTE" sortable="true" />

										<display:column title="DEMANDANTE">
											<c:url var="verDemandante" value="demandanteList.htm">
												<c:param name="nroExpedienteActual" value="${results.nroExpediente}" />
											</c:url>
											
											<a class="ifancybox" href="<c:out value="${verDemandante}"/>"><img src="<c:out value="${verDetalleImgUrl}"/>" title="Ver Demandantes"></img></a>
										</display:column>

										<c:url var="verDemandado" value="demandadoList.htm">
											<c:param name="nroExpedienteActual" value="${results.nroExpediente}" />
										</c:url>

										<display:column title="DEMANDADO">
											<a class="ifancybox" href="<c:out value="${verDemandado}"/>"><img src="<c:out value="${verDetalleImgUrl}"/>" title="Ver Demandados"></img></a>
										</display:column>
										
										<display:column title="NATURALEZA" property="materia.naturaleza.denominacion" />
										
										<display:column title="MATERIA" property="materia.denominacion" />
										
										<c:url var="addNotificacion" value="addNotificacion.htm">
											<c:param name="numExpediente" value="${results.nroExpediente}" />
										</c:url>

										<c:url var="editCcUrl" value="addProceso.htm">
											<c:param name="numExpediente" value="${results.nroExpediente}" />
										</c:url>

								<!-- 	<c:url var="deleteCcUrl" value="borrarProceso.htm">
											<c:param name="numExpediente" value="${results.nroExpediente}" />
										</c:url> -->
										<display:column title="AGREGAR NOTIFICACIÓN">
											<a href="<c:out value="${addNotificacion}"/>"><img src="<c:out value="${notificacionImgUrl}"/>" title="Agregar Notificacion"></img></a>
										</display:column>
										
										<display:column title="ACCIONES">
											<a href="<c:out value="${editCcUrl}"/>"><img src="<c:out value="${editImgUrl}"/>" title="Editar Proceso"></img></a>
								<!-- 		<a href="<c:out value="${deleteCcUrl}"/>"><img src="<c:out value="${deleteImgUrl}"/>" title="Eliminar Proceso"></img></a> -->
										</display:column>

									</display:table></th>
							</tr>
						</table>
					</fieldset>
				</div>
			</form:form>
		</div>
		<!-- FIN Cuerpo -->
		<jsp:include page="/WEB-INF/pie/pie.jsp" />
		<!-- FIN footer -->
	</div>
	<!-- FIN Contenedor PRINCIPAL -->
</body>
</html>