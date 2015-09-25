<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/estiloIndex.css" />
<link rel="stylesheet" type="text/css" href="css/menuHorizontal.css" />

<!-- inputs -->
<link rel="stylesheet" href="css/formularios/inputs.css" type="text/css">
<link rel="stylesheet" href="css/formularios/form.css" type="text/css">
<!-- fin-inputs -->

<!-- Archivos para calendario -->
	<link rel="stylesheet" href="css/formularios/themes/base/jquery.ui.all.css">
	<script src="script/jquery-1.9.1.js"></script>
	 <script src="script/ui/i18n/jquery.ui.datepicker-es.js"></script>
	<script src="script/ui/jquery.ui.core.js"></script>
	<script src="script/ui/jquery.ui.widget.js"></script>
	<script src="script/ui/jquery.ui.datepicker.js"></script>
	<script type="text/javascript" src="script/ui/minified/jquery-ui.min.js"></script>
	<link rel="stylesheet" href="css/formularios/demos.css">
<!-- fin fechas -->
<script type="text/javascript">
$(function() {
		$.datepicker.setDefaults($.extend({showMonthAfterYear: false}, $.datepicker.regional['es']));
		$( "#datepicker" ).datepicker({
			showOn: "button",
			buttonImage: "images/calendar.gif",
			buttonImageOnly: true
		});
		$("#datepicker").datepicker().datepicker("setDate", new Date());
	});
function mostrarMensajeValidacion(idField,mensaje){
	var idFormateadoField='#'+idField;
	$(idFormateadoField).attr('title', mensaje);
	$(idFormateadoField).tooltip({
		position: {
			my:'left center',
			at:'right+10 center',
		    fadeInSpeed:400
	      }
	 }).tooltip( "open" );
	
}
</script>
<title>Asignar Carga Laboral</title>


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
		<div align="center">

			<form:form name="form1" commandName="asignadoGuardar">
				<div id="form">
					<fieldset id="formFielset">
						<div>
							<fieldset id="formFielset">
								<label id="label_titulo_pestanas">Asignar Carga laboral</label>
							</fieldset>
						</div>
						<table border="0">
							<tr>
								<th align="left">
									<label class="label">Número Expediente</label>
								</th>
								<th align="left">
									<form:input id="idNroExpediente" path="id.proceso.nroExpediente" readonly="true" class="input_one" />
									<spring:bind path="id.proceso.nroExpediente">								
										<c:if test="${status.error}">
											<script type="text/javascript">										
												mostrarMensajeValidacion('idNroExpediente',"<c:out value='${status.errorMessage}'/>");
											</script>										
										</c:if>								
									</spring:bind>
								</th>
							</tr>
							<tr>
								<th align="left"><label class="label">Abogado</label></th>
								<th align="left"><form:select id="idCboAbogado" path="id.usuario.dni" class="combo_uno">
										<option value="0">Seleccione Abogado</option>
										<c:forEach var="abogado" items="${abogados}">
											<form:option value="${abogado.dni}">
												<c:out value="${abogado.nombres} ${abogado.apaterno} ${abogado.amaterno}" />
											</form:option>
										</c:forEach>
									</form:select>
									<spring:bind path="id.usuario.dni">								
										<c:if test="${status.error}">
											<script type="text/javascript">										
												mostrarMensajeValidacion('idCboAbogado',"<c:out value='${status.errorMessage}'/>");
											</script>										
										</c:if>								
									</spring:bind>
								</th>
							</tr>
							<tr>
								<th align="left">
									<label class="label">Fecha de Asignacion</label>
								</th>
								<th align="left">
									<form:input path="fechaAsignado" id="datepicker" maxlength="10" class="input_date" />
									<spring:bind path="fechaAsignado">								
										<c:if test="${status.error}">
											<script type="text/javascript">										
												mostrarMensajeValidacion('datepicker',"<c:out value='${status.errorMessage}'/>");
											</script>										
										</c:if>								
									</spring:bind>
								</th>
							</tr>
							<tr>
								<th align="left"><label class="label">Observacion</label></th>
								<th align="left">
									<form:textarea id="idObservacion" path="observacion" cols="10" rows="5"	class="input_observacion" />
									<spring:bind path="observacion">								
										<c:if test="${status.error}">
											<script type="text/javascript">										
												mostrarMensajeValidacion('idObservacion',"<c:out value='${status.errorMessage}'/>");
											</script>										
										</c:if>								
									</spring:bind>
								</th>
							</tr>
							<tr>
								<th></th>
								<th align="right">
									<p align="center">
										<input type="submit" value="Asignar" class="btn_asignar">
									</p>
								</th>
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