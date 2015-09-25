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
		$('#datepicker_uno').datepicker({
			showOn: "button",
			buttonImage: "images/calendar.gif",
			buttonImageOnly: true,
			dateFormat: 'dd/mm/yy', 
			changeMonth: true, 
			changeYear: true, 
			yearRange: '-100:+0'
		});
		
		$( "#datepicker_dos" ).datepicker({
			showOn: "button",
			buttonImage: "images/calendar.gif",
			buttonImageOnly: true
		});
		$( "#datepicker_tres" ).datepicker({
			showOn: "button",
			buttonImage: "images/calendar.gif",
			buttonImageOnly: true
		});
	});
	</script>
<!-- Fin Archivos para calendario -->

<title>Agregar Personal</title>
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

			<form:form name="form1" commandName="guardarPersonal">
				<div id="form">
					<fieldset id="formFielset">
						<div>
							<fieldset id="formFielset">
								<label id="label_titulo_pestanas">Registrar Personal</label>
							</fieldset>
						</div>
						<table border="0">
							<tr>
								<th align="left"><label class="label">DNI:</label></th>
								<th align="left">
								<form:input path="dni" maxlength="8" class="input_one" />
								<font color="#FF0000">
									<form:errors id="dniErrors" name="dniErrors" path="dni" />
								</font>
								</th>
							</tr>
							<tr>
								<th align="left"><label class="label">Nombres:</label></th>
								<th align="left">
								<form:input path="nombres" class="input_one" />
								<font color="#FF0000">
									<form:errors id="nombresErrors" name="nombresErrors" path="nombres" />
								</font>
								</th>
							</tr>
							<tr>
								<th align="left"><label class="label">Apellido Paterno:</label></th>
								<th align="left">
								<form:input path="apaterno" class="input_one" />
								<font color="#FF0000">
									<form:errors id="apaternoErrors" name="apaternoErrors" path="apaterno" />
								</font>
								</th>
							</tr>
							<tr>
								<th align="left"><label class="label">Apellido Materno:</label></th>
								<th align="left">
								<form:input path="amaterno" class="input_one" />
								<font color="#FF0000">
									<form:errors id="amaternoErrors" name="amaternoErrors" path="amaterno" />
								</font>
								</th>
							</tr>
							<tr>
								<th align="left"><label class="label">Tipo de Perfil:</label>
								<th align="left">
								<form:select id="idNaturaleza" path="rol.idRol" class="combo_uno">
									<form:option value="0">----Seleccione Perfil----</form:option>
									<form:options items="${tipoPerfil}" itemLabel="denominacion" itemValue="idRol" />
								</form:select>
								<font color="#FF0000">
									<form:errors id="rolErrors" name="rolErrors" path="rol" />
								</font>
								</th>
							</tr>

							<tr>
								<th align="left"><label class="label">Fecha de Nacimiento:</label></th>
								<th align="left">
									<form:input path="fechaNacimiento"	id="datepicker_uno" maxlength="10" class="input_date" />
									<font color="#FF0000">
										<form:errors id="fechaNacimientoErrors" name="fechaNacimientoErrors" path="fechaNacimiento" />
									</font>
								</th>
							</tr>
							<tr>
								<th align="left"><label class="label">Correo:</label></th>
								<th align="left"><form:input path="correo" maxlength="30" class="input_one" />
									<font color="#FF0000">
										<form:errors id="correoErrors" name="correoErrors" path="correo" />
									</font>		
								</th>
							</tr>
							<tr>
								<th align="left"><label class="label">Dirección:</label></th>
								<th align="left"><form:input path="direccion" class="input_one" maxlength="50" />
								<font color="#FF0000">
									<form:errors id="direccionErrors" name="direccionErrors" path="direccion"  />
								</font>
								</th>
							</tr>
							<tr>
								<th align="left"><label class="label">Teléfono:</label></th>
								<th align="left"><form:input path="telefono" maxlength="9" class="input_observacion" />
								<font color="#FF0000">
									<form:errors id="telefonoErrors" name="telefonoErrors" path="telefono" />
								</font>
								</th>
							</tr>
							<tr>
								<th align="left"><label class="label">Username:</label></th>
								<th align="left"><form:input path="username" class="input_one" maxlength="20" />
								<font color="#FF0000">
									<form:errors id="usernameErrors" name="usernameErrors" path="username"  />
								</font>
								</th>
							</tr>
							<tr>
								<th align="left"><label class="label">Password:</label></th>
								<th align="left"><form:password path="passname" maxlength="10" class="input_one" />
								<font color="#FF0000">
									<form:errors id="passnameErrors" name="passnameErrors" path="passname" />
								</font>
								</th>
							</tr>
							<tr>
								<th align="left"><label class="label">Confirmar Password:</label></th>
								<th align="left"><form:password path="confirmarPassname" maxlength="10" class="input_one" />
								<font color="#FF0000">
									<form:errors id="confirmarPassnameErrors" name="confirmarPassnameErrors" path="confirmarPassname" />
								</font>
								</th>
							</tr>
							<tr>
								<th align="left"><label class="label">Fecha de Ingreso:</label></th>
								<th align="left"><form:input path="fechaIngreso" id="datepicker_dos" maxlength="10" class="input_date" />
								<font color="#FF0000">
									<form:errors id="fechaIngresoErrors" name="fechaIngresoErrors" path="fechaIngreso" />
								</font>	
								</th>
							</tr>
							<tr>
								<th align="left"><label class="label">Fecha de Salida:</label></th>
								<th align="left"><form:input path="fechaSalida" id="datepicker_tres" maxlength="10" class="input_date" />
								<font color="#FF0000">
									<form:errors id="fechaSalidaErrors" name="fechaSalidaErrors" path="fechaSalida" />
								</font>	
								</th>
							</tr>
							<tr>
								<th align="left"><label class="label">Estado Laboral:</label></th>
								<th align="left"><form:checkbox path="estadoLaboral" label="Activo" />
								<font color="#FF0000">
									<form:errors id="estadoLaboralErrors" name="estadoLaboralErrors" path="estadoLaboral" />
								</font>	
								</th>
							</tr>
							<tr>
								<th align="left"><label class="label"></label></th>
								<th align="left">
									<p align="center">
										<input type="button" onclick="location.href='<c:url value="/registrarUsuario.htm"/>'" value="Cancelar" class="btn_asignar" />
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