<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
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

<title>Registrar Personal</title>

</head>
<body>
	<c:url var="editImgUrl" value="images/resources/edit.png" />
	<c:url var="deleteImgUrl" value="images/resources/delete.png" />
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
			<form:form commandName="buscarPersonal">
				<div id="form">
					<fieldset id="formFielset">
						<br>
						<div>
							<fieldset id="formFielset">
								<label id="label_titulo_pestanas">Personal</label>
							</fieldset>
						</div>
						
						<table border="0" align="center" id="form">
							<tr>								
								<th>
									<label class="label">Nombres:</label>
									<form:input path="nombres" maxlength="45" class="input_one" placeholder="Ingrese Nombre" /> <input type="submit" id="Buscar" name="Buscar" value="Buscar" class="btn_guardar" /><br />
									<label class="label">Apellido Paterno:</label>
									<form:input path="apaterno" maxlength="45" class="input_one" placeholder="Ingrese Apellido Paterno" /><br />
									<label class="label">Apellido Materno:</label>
									<form:input path="amaterno" maxlength="45" class="input_one" placeholder="Ingrese Apellido Materno" /><br />
									<label class="label">Estado:</label>
									<form:select path="estadoLaboral" class="combo_uno">
										<form:option value="true" label="Activo"  />
										<form:option value="false" label="Inactivo"/>
									</form:select>									
								</th>								
								<th>																		
									<input type="button" id="AgregarPersonal" name="AgregarPersonal" onclick="location.href='<c:url value="addPersonal.htm"/>'" value="Nuevo Personal" class="btn_nuevo_expediente" />
								</th>
							</tr>
						</table>
					
						
						<table border="0" align="center">		
							<tr>
								<td>&nbsp;</td>
								<td valign="top">
									<display:table id="personal" name="listaPersonal" requestURI="/registrarUsuario.htm" pagesize="15" defaultsort="1" defaultorder="ascending">

									<display:column title="DNI" property="dni" sortable="true" />
									<display:column title="PERSONAL" sortable="true">
										<c:out value="${personal.nombres} ${personal.apaterno} ${personal.amaterno}"></c:out>
									</display:column>
									<display:column title="FECHA NACIMIENTO" property="fechaNacimiento" />
									<display:column title="CORREO" property="correo"/>
									<display:column title="DIRECCION" property="direccion"/>
									<display:column title="TÉLEFONO" property="telefono"/>
									<display:column title="FECHA INGRESO" property="fechaIngreso"/>
									<display:column title="PERFIL" property="rol.denominacion"/>
									<c:url var="editCcUrl" value="addPersonal.htm">
										<c:param name="idUsuario" value="${personal.dni}" />
									</c:url>
										
									<c:url var="deleteCcUrl" value="borrarPersonal.htm">
										<c:param name="idUsuario" value="${personal.dni}" />
									</c:url>
										
									<display:column title="ACCIONES">
										<a href="<c:out value="${editCcUrl}"/>"><img src="<c:out value="${editImgUrl}"/>" title="Editar Personal"></img></a>
										<a href="<c:out value="${deleteCcUrl}"/>"><img src="<c:out value="${deleteImgUrl}"/>" title="Eliminar Personal"></img></a>
									</display:column>
									</display:table>
								</td>
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