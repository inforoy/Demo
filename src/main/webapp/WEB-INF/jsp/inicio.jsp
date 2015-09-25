<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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

<title>Principal</title>
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
		<c:url var="listNotificacionImgUrl" value="images/resources/listaNotificaciones.png" />
		<c:url var="editImgUrl" value="images/resources/edit.png" />
		<c:url var="deleteImgUrl" value="images/resources/delete.png" />
		<c:url var="verDetalleImgUrl" value="images/resources/verDetalle.png" />
		<c:url var="addAudienciaImgUrl"
			value="images/resources/addAudiencia.png" />
		<c:url var="addEscritoImgUrl" value="images/resources/addEscrito.png" />
		<c:url var="notificacionImgUrl"
			value="images/resources/addNotificacion.png" />
		<c:url var="verDetalleImgUrl" value="images/resources/verDetalle.png" />

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
			<h3>Bienvenido:</h3>
			<h3>
				<c:out	value="${datosUsuario.nombres} ${datosUsuario.apaterno} ${datosUsuario.amaterno}" />
			</h3>
			<form:form commandName="buscarProceso">
				<div id="form">
					<fieldset id="formFielset">
						<table border="0" align="center" id="form">
							<tr>
								<th><label>Buscar Por:</label> 
									<form:select path="opcion" class="combo_uno">
										<form:option value="1">Número de expediente</form:option>
										<form:option value="2">Demandante</form:option>
										<form:option value="3">Demandado</form:option>
										<form:option value="4">Número de Folder</form:option>
										<form:option value="5">Número de Notificación</form:option>
									</form:select></th>
								<th>
									<form:input path="txtBuscqueda" maxlength="50" class="input_one" placeholder="Ingrese datos a buscar" /> 
									<input type="submit" id="Buscar" name="Buscar" value="Buscar" class="btn_guardar" /></th>
							</tr>
						</table>


						

						<c:choose>
							<c:when test="${opcionSeleccionada == 1}">
							
								<table border="0">
									<tr>
										<th><display:table id="results" name="listaProcesoLitigantes" requestURI="/inicio.htm" pagesize="10" defaultsort="1" defaultorder="ascending">

												<display:column property="nroFolder" title="FOLDER" sortable="true" />

												<display:column property="nroExpediente" title="EXPEDIENTE" sortable="true" />

												<display:column title="DEMANDANTE">
													<c:url var="verDemandante" value="demandanteList.htm">
														<c:param name="nroExpedienteActual" value="${results.nroExpediente}" />
													</c:url>

													<a class="ifancybox" href="<c:out value="${verDemandante}"/>"><img src="<c:out value="${verDetalleImgUrl}"/>" title="Ver Demandantes"></img></a>
												</display:column>

												<display:column title="DEMANDADO">
													<c:url var="verDemandado" value="demandadoList.htm">
														<c:param name="nroExpedienteActual" value="${results.nroExpediente}" />
													</c:url>
													
													<a class="ifancybox" href="<c:out value="${verDemandado}"/>"><img src="<c:out value="${verDetalleImgUrl}"/>" title="Ver Demandados"></img></a>
												</display:column>

												<display:column title="NATURALEZA" property="materia.naturaleza.denominacion" />

												<display:column title="MATERIA" property="materia.denominacion" />
												<!-- Solo el rol procurador -->
												<security:authorize access="hasRole('Procurador')">
													<display:column title="ABOGADO">
														<c:forEach items="${results.asignados}" var="abogado">
															<c:out value="${abogado.id.usuario.nombres} ${abogado.id.usuario.apaterno} ${abogado.id.usuario.amaterno}"></c:out>
														</c:forEach>
													</display:column>
												</security:authorize>
												<!-- Solo el rol abogado -->
												<security:authorize access="hasRole('Abogado')">
													<display:column title="INSTANCIA">
														<c:forEach items="${results.instancias}" var="instancia">
															<c:out value="|${instancia.id.dependencia.denominacion}|"/><br />
														</c:forEach>
													</display:column>
												</security:authorize>
												
												<c:url var="addNotificacion" value="addNotificacion.htm">
													<c:param name="numExpediente" value="${results.nroExpediente}" />
												</c:url>
												
												<c:url var="listarNotificacion" value="busquedaNotificacion.htm">
													<c:param name="numExpediente" value="${results.nroExpediente}" />
												</c:url>

												<c:url var="editCcUrl" value="addProceso.htm">
													<c:param name="numExpediente" value="${results.nroExpediente}" />
												</c:url>

												<display:column title="NOTIFICACIÓN">
													<a href="<c:out value="${addNotificacion}"/>"><img src="<c:out value="${notificacionImgUrl}"/>" title="Agregar Notificacion"></img></a>
													<a href="<c:out value="${listarNotificacion}"/>"><img src="<c:out value="${listNotificacionImgUrl}"/>" title="Listar Notificacion"></img></a>
												</display:column>

												<display:column title="ACCIONES">
													<a href="<c:out value="${editCcUrl}"/>"><img src="<c:out value="${editImgUrl}"/>" title="Editar Proceso"></img></a>
													
												</display:column>

											</display:table></th>
									</tr>
								</table>
							</c:when>
							<c:when test="${opcionSeleccionada == 2}">
								
								<table border="0" align="center">
									<tr>
										<td>&nbsp;</td>
										<td valign="top"><display:table id="results" name="listaProcesoDemandante" requestURI="/inicio.htm" pagesize="10" defaultsort="1" defaultorder="ascending">
												<display:column title="FOLDER" property="proceso.nroFolder" sortable="true" />
												<display:column title="EXPEDIENTE" property="proceso.nroExpediente" sortable="true" />
												<c:url var="verDemandado" value="demandadoList.htm">
													<c:param name="nroExpedienteActual" value="${results.proceso.nroExpediente}" />
												</c:url>
												<display:column title="DEMANDADO">
													<a class="ifancybox" href="<c:out value="${verDemandado}"/>"> <img src="<c:out value="${verDetalleImgUrl}"/>" title="Ver Demandados"></img></a>
												</display:column>
												<c:url var="verDemandante" value="demandanteList.htm">
													<c:param name="nroExpedienteActual" value="${results.proceso.nroExpediente}" />
												</c:url>
												<display:column title="DEMANDANTE">
													<a class="ifancybox" href="<c:out value="${verDemandante}"/>"> <img src="<c:out value="${verDetalleImgUrl}"/>" title="Ver Demandantes"></img></a>
												</display:column>
												<display:column title="NATURALEZA" property="proceso.materia.naturaleza.denominacion" />
												<display:column title="MATERIA" property="proceso.materia.denominacion" />
												<!-- Solo el rol procurador -->
												<security:authorize access="hasRole('Procurador')">
													<display:column title="ABOGADO">
														<c:forEach items="${results.asignados}" var="abogado">
															<c:out value="${abogado.id.usuario.nombres} ${abogado.id.usuario.apaterno} ${abogado.id.usuario.amaterno}"></c:out>
														</c:forEach>
													</display:column>
												</security:authorize>
												<!-- Solo el rol abogado -->
												<security:authorize access="hasRole('Abogado')">
													<display:column title="INSTANCIA">
														<c:forEach items="${results.instancias}" var="instancia">
															<c:out value="|${instancia.id.dependencia.denominacion}|"/><br />
														</c:forEach>
													</display:column>
												</security:authorize>
												<c:url var="addNotificacion" value="addNotificacion.htm">
													<c:param name="numExpediente" value="${results.proceso.nroExpediente}" />
												</c:url>
												
												<c:url var="listarNotificacion" value="busquedaNotificacion.htm">
													<c:param name="numExpediente" value="${results.proceso.nroExpediente}" />
												</c:url>
												
												<c:url var="editCcUrl" value="addDemandante.htm">
													<c:param name="idDemandante" value="${results.proceso.nroExpediente}" />
												</c:url>
						
												<display:column title="NOTIFICACIÓN">
													<a href="<c:out value="${addNotificacion}"/>"> <img src="<c:out value="${notificacionImgUrl}"/>" title="Agregar Notificacion"></img></a>
													<a href="<c:out value="${listarNotificacion}"/>"><img src="<c:out value="${listNotificacionImgUrl}"/>" title="Listar Notificacion"></img></a>
												</display:column>
												<display:column title="ACCIONES">
													<a href="<c:out value="${editCcUrl}"/>"><img src="<c:out value="${editImgUrl}"/>" title="Editar Demandante"></img></a>
													
												</display:column>
											</display:table></td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</c:when>
							<c:when test="${opcionSeleccionada == 3}">
							
								<table border="0" align="center">
									<tr>
										<td>&nbsp;</td>
										<td valign="top"><display:table id="results" name="listaProcesoDemandado" requestURI="/inicio.htm" pagesize="10" defaultsort="1" defaultorder="ascending">

												<display:column title="FOLDER" property="proceso.nroFolder" sortable="true" />

												<display:column title="EXPEDIENTE" property="proceso.nroExpediente" sortable="true" />
												<c:url var="verDemandado" value="demandadoList.htm">
													<c:param name="nroExpedienteActual" value="${results.proceso.nroExpediente}" />
												</c:url>

												<display:column title="DEMANDADO">
													<a class="ifancybox" href="<c:out value="${verDemandado}"/>"><img src="<c:out value="${verDetalleImgUrl}"/>" title="Ver Demandados"></img></a>
												</display:column>

												<c:url var="verDemandante" value="demandanteList.htm">
													<c:param name="nroExpedienteActual" value="${results.proceso.nroExpediente}" />
												</c:url>

												<display:column title="DEMANDANTE">
													<a class="ifancybox" href="<c:out value="${verDemandante}"/>"><img src="<c:out value="${verDetalleImgUrl}"/>" title="Ver Demandantes"></img></a>
												</display:column>

												<display:column title="NATURALEZA" property="proceso.materia.naturaleza.denominacion" />

												<display:column title="MATERIA" property="proceso.materia.denominacion" />
												<!-- Solo el rol procurador -->
												<security:authorize access="hasRole('Procurador')">
													<display:column title="ABOGADO">
														<c:forEach items="${results.asignados}" var="abogado">
															<c:out value="${abogado.id.usuario.nombres} ${abogado.id.usuario.apaterno} ${abogado.id.usuario.amaterno}"></c:out>
														</c:forEach>
													</display:column>
												</security:authorize>
												<!-- Solo el rol abogado -->
												<security:authorize access="hasRole('Abogado')">
													<display:column title="INSTANCIA">
														<c:forEach items="${results.instancias}" var="instancia">
															<c:out value="|${instancia.id.dependencia.denominacion}|"/><br />
														</c:forEach>
													</display:column>
												</security:authorize>
												<c:url var="addNotificacion" value="addNotificacion.htm">
													<c:param name="numExpediente" value="${results.proceso.nroExpediente}" />
												</c:url>
												
												<c:url var="listarNotificacion" value="busquedaNotificacion.htm">
													<c:param name="numExpediente" value="${results.proceso.nroExpediente}" />
												</c:url>
												
												<c:url var="editCcUrl" value="addDemandado.htm">
													<c:param name="idDemandado" value="${results.proceso.nroExpediente}" />
												</c:url>

												<display:column title="NOTIFICACIÓN">
													<a href="<c:out value="${addNotificacion}"/>"><img src="<c:out value="${notificacionImgUrl}"/>" title="Agregar Notificacion"></img></a>
													<a href="<c:out value="${listarNotificacion}"/>"><img src="<c:out value="${listNotificacionImgUrl}"/>" title="Listar Notificacion"></img></a>
												</display:column>

												<display:column title="ACCIONES">
													<a href="<c:out value="${editCcUrl}"/>"><img src="<c:out value="${editImgUrl}"/>" title="Editar Demandado"></img></a>
													
												</display:column>

											</display:table></td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</c:when>
							<c:when test="${opcionSeleccionada == 4}">
							
								<table border="0" align="center">
									<tr>
										<td>&nbsp;</td>
										<td valign="top"><display:table id="results" name="listaProcesoLitigantes" requestURI="/inicio.htm" pagesize="5" defaultsort="1" defaultorder="ascending">
												<display:column title="FOLDER" property="nroFolder" />
												<display:column title="EXPEDIENTE" property="nroExpediente" />
												<c:url var="verDemandante" value="demandanteList.htm">
													<c:param name="nroExpedienteActual" value="${results.nroExpediente}" />
												</c:url>
												<display:column title="DEMANDANTE">
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
												<!-- Solo el rol procurador -->
												<security:authorize access="hasRole('Procurador')">
													<display:column title="ABOGADO">
														<c:forEach items="${results.asignados}" var="abogado">
															<c:out value="${abogado.id.usuario.nombres} ${abogado.id.usuario.apaterno} ${abogado.id.usuario.amaterno}"></c:out>
														</c:forEach>
													</display:column>
												</security:authorize>
												<!-- Solo el rol abogado -->
												<security:authorize access="hasRole('Abogado')">
													<display:column title="INSTANCIA">
														<c:forEach items="${results.instancias}" var="instancia">
															<c:out value="|${instancia.id.dependencia.denominacion}|"/><br />
														</c:forEach>
													</display:column>
												</security:authorize>
												<c:url var="addNotificacion" value="addNotificacion.htm">
													<c:param name="numExpediente" value="${results.nroExpediente}" />
												</c:url>
												
												<c:url var="listarNotificacion" value="busquedaNotificacion.htm">
													<c:param name="numExpediente" value="${results.nroExpediente}" />
												</c:url>
												
												<c:url var="editCcUrl" value="addDemandante.htm">
													<c:param name="idDemandante" value="${results.nroExpediente}" />
												</c:url>
											
												<display:column title="NOTIFICACIÓN">
													<a href="<c:out value="${addNotificacion}"/>"><img src="<c:out value="${notificacionImgUrl}"/>" title="Agregar Notificacion"></img></a>
													<a href="<c:out value="${listarNotificacion}"/>"><img src="<c:out value="${listNotificacionImgUrl}"/>" title="Listar Notificacion"></img></a>
												</display:column>
												<display:column title="ACCIONES">
													<a href="<c:out value="${editCcUrl}"/>"><img src="<c:out value="${editImgUrl}"/>" title="Editar Demandante"></img></a>
													
												</display:column>
											</display:table></td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</c:when>
							<c:when test="${opcionSeleccionada == 5}">
							
								<table border="0" align="center">
									<tr>
										<td>&nbsp;</td>
										<td valign="top"><display:table id="notificacion" name="listaNotificaciones" requestURI="/inicio.htm" pagesize="10" defaultsort="1" defaultorder="ascending">
												<display:column title="NOTIFICACIÓN" property="nroNotificacion" />
												<display:column title="FOLDER" property="proceso.nroFolder" />
												<display:column title="EXPEDIENTE" property="proceso.nroExpediente" />
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
												<c:if test="${datosUsuario.rol.idRol eq 2 || datosUsuario.rol.idRol eq 1}">
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
												<!-- Solo el rol procurador -->
												<security:authorize access="hasRole('Procurador')">
													<display:column title="ABOGADO">
														<c:forEach items="${results.asignados}" var="abogado">
															<c:out value="${abogado.id.usuario.nombres} ${abogado.id.usuario.apaterno} ${abogado.id.usuario.amaterno}"></c:out>
														</c:forEach>
													</display:column>
												</security:authorize>
												<!-- Solo el rol abogado -->
												<security:authorize access="hasRole('Abogado')">
													<display:column title="INSTANCIA">
														<c:forEach items="${results.instancias}" var="instancia">
															<c:out value="|${instancia.id.dependencia.denominacion}|"/><br />
														</c:forEach>
													</display:column>
												</security:authorize>
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
										<td>&nbsp;</td>
									</tr>
								</table>
							</c:when>
						</c:choose>



					</fieldset>
				</div>
			</form:form>
		</div>
		<jsp:include page="/WEB-INF/pie/pie.jsp" />
	</div>
</body>
</html>