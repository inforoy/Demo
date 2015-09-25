<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
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

<title>Agregar Escrito</title>

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
			
			<form:form name="form1" commandName="escritoGuardar">
				<div id="form">
				
					<fieldset id="formFielset">
						<div>
							<fieldset id="formFielset">
								<label id="label_titulo_pestanas">Registrar Estado</label>
							</fieldset>
						</div>

							<table>
								<tr>
									<th align="left"><label class="label">Denominación:</label></th>
									<th align="left"><form:input path="denominacion" maxlength="40" class="input_one" />
									<font color="#FF0000">
										<form:errors id="denominacionErrors" name="denominacionErrors" path="denominacion" />
									</font>
									</th>
								</tr>
								
								<tr>
									<th align="left"><label class="label">Sumilla:</label></th>
									<th align="left"><form:textarea cols="10" rows="10" path="sumilla" class="input_one" />
									<font color="#FF0000">
										<form:errors id="sumillaErrors" name="sumillaErrors" path="sumilla" />
									</font>
									</th>
								</tr>
								
								<tr>
									<th align="left"><label class="label">Número de Folios:</label></th>
									<th align="left"><form:input maxlength="30" path="nroFolios" class="input_numero" />
									<font color="#FF0000">
										<form:errors id="nroFoliosErrors" name="nroFoliosErrors" path="nroFolios" />
									</font>
									</th>
								</tr>
								
								<tr>
									<th align="left"><label class="label">Fecha de Emisión:</label></th>
									<th align="left"><form:input path="fechaEmision" id="datepicker" maxlength="10" class="input_date" />
									<font color="#FF0000">
										<form:errors id="fechaEmisionErrors" name="fechaEmisionErrors" path="fechaEmision" />
									</font>
									</th>
								</tr>								
								
								<tr>
									<th align="left"><label class="label">Observación:</label></th>
									<th align="left"><form:textarea cols="10" rows="5" path="observacion" class="input_observacion" />
									<font color="#FF0000">
										<form:errors id="observacionErrors" name="observacionErrors" path="observacion" />
									</font>
									</th>
								</tr>								
								
								<tr>
									<th align="left"><label class="label">Número de Notificación:</label></th>
									<th align="left"><form:input readonly="true" path="notificacion.nroNotificacion" class="input_one" />
									<font color="#FF0000">
										<form:errors id="notificacionErrors" name="notificacionErrors" path="notificacion" />
									</font>
									</th>
								</tr>								
										
								<tr>
									<th align="left"></th>
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