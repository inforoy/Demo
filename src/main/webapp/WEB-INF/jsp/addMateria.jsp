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

<title>Agregar Materia</title>

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

			<form:form commandName="materiaGuardar">
				<div id="form">
					<fieldset id="formFielset">
						<div>
							<fieldset id="formFielset">
								<label id="label_titulo_pestanas">Registrar Materia</label>
							</fieldset>
						</div>
						<table border="0">
							<tr>
								<form:hidden path="idMateria"/>
								<th align="left">
									<label class="label">Tipo de Naturaleza:</label></th>
								<th align="left">
									<form:select path="naturaleza.idNaturaleza" class="combo_uno">
										<form:option value="0">----Seleccione Naturaleza----</form:option>
										<form:options items="${tipoNaturaleza}"	itemLabel="denominacion" itemValue="idNaturaleza" />
									</form:select>
									<font color="#FF0000">
										<form:errors id="naturalezaErrors" name="naturalezaErrors" path="naturaleza" />
									</font>
								</th>
							</tr>
							<tr>
								<th align="left"><label class="label">Denominación:</label></th>
								<th>
									<form:input maxlength="50" path="denominacion" class="input_one" />
									<font color="#FF0000">
										<form:errors id="denominacionErrors" name="denominacionErrors" path="denominacion" />
									</font>
								</th>
							</tr>
							<tr>
								<th align="left"></th>
								<th>
									<p align="center">
										<input type="button" onclick="location.href='<c:url value="/materiaList.htm"/>'" value="Cancelar" class="btn_asignar" />
										<input type="submit" value="Guardar" class="btn_guardar">
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