<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
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
	<link rel="stylesheet" href="css/formularios/demos.css">
	<script>
	$(function() {
		$.datepicker.setDefaults($.extend({showMonthAfterYear: false}, $.datepicker.regional['es']));
		$( "#datepicker" ).datepicker({
			showOn: "button",
			buttonImage: "images/calendar.gif",
			buttonImageOnly: true
		});
	});
	</script>
<!-- Fin Archivos para calendario -->

<title>Agregar Audiencia</title>

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
			
			<form:form name="form1" commandName="audienciaGuardar">
			
			<div id="form">
			<fieldset id="formFielset">		
			<div>
				<fieldset id="formFielset">
					<label id="label_titulo_pestanas">Crear Audiencia</label>
				</fieldset>
			</div>
			
						<table>
							<tr>
								<th align="left" ><label class="label">Diligencia: </label></th>
								<th align="left"><form:input path="diligencia" maxlength="30" class="input_one" />
								<font color="#FF0000">
									<form:errors id="diligenciaErrors" name="diligenciaErrors" path="diligencia" />
								</font>
								</th>
																
							</tr>
							
							<tr>
								<th align="left" ><label class="label">Hora:</label></th>
								<th align="left"><form:input path="hora" maxlength="15" class="input_hora" />
								<font color="#FF0000">
									<form:errors id="horaErrors" name="horaErrors" path="hora" />
								</font>
								</th>
							</tr>
							
							<tr>
								<th align="left" ><label class="label">Fecha de Audiencia: </label></th>
								<th align="left"><form:input path="fechaAudiencia" id="datepicker" class="input_date" maxlength="10"  />
								<font color="#FF0000">
									<form:errors id="fechaAudienciaErrors" name="fechaAudienciaErrors" path="fechaAudiencia" />
								</font>
								</th>
							</tr>
							
							<tr>
								<th align="left" ><label class="label">Fecha de Registro: </label></th>
								<th align="left">
									 <form:input path="fechaRegistro" id="datepicker_dos" class="input_date_dos" maxlength="10"  />
								<font color="#FF0000">
									<form:errors id="fechaRegistroErrors" name="fechaRegistroErrors" path="fechaRegistro" />
								</font>
								</th>
							</tr>
							
							<tr>
								<th align="left" ><label class="label">Asistencia: </label></th>
								<th align="left"><form:checkbox path="asistencia" class="input_one" />
								<font color="#FF0000">
									<form:errors id="asistenciaErrors" name="asistenciaErrors" path="asistencia" />
								</font>
								</th>
							</tr>
							
							<tr>
								<th align="left" ><label class="label">Observación: </label></th>
								<th align="left"><form:textarea cols="10" rows="5" path="observacion" class="input_observacion" />
								<font color="#FF0000">
									<form:errors id="observacionErrors" name="observacionErrors" path="observacion" />
								</font>
								</th>
							</tr>
							
							<tr>
								<th align="left" ><label class="label">Número de Notificación: </label></th>
								<th align="left"><form:input path="notificacion.nroNotificacion" readonly="true" class="input_one" />
								<font color="#FF0000">
									<form:errors id="notificacionErrors" name="notificacionErrors" path="notificacion" />
								</font>
								</th>
							</tr>
							
							<tr>
								<th align="left" ><label class="label"></label></th>
								<th align="left">
									<p align="center">
										<input type="submit" value="Guardar" class="btn_guardar" />
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