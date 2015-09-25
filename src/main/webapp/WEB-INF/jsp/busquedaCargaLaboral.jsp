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

<!--  Imports -->
<link rel="stylesheet" href="css/formularios/form.css" type="text/css">
<link rel="stylesheet" href="css/formularios/inputs.css" type="text/css">
<!--  Fin - Imports -->

<title>Busqueda de Carga Laboral</title>

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
			<form:form commandName="abogadoBuscar">
				<div id="form">
					<fieldset id="formFielset">
						<br>
						<div>
							<fieldset id="formFielset">
								<label id="label_titulo_pestanas">Buscar Abogado: </label>
							</fieldset>
						</div>
						
						<table border="0" align="center" id="form">
							<tr>								
								<th>
									<label class="label">Elegir Abogado:</label>
										<form:select path="id.usuario.dni" class="combo_uno" >
											<option value="">Seleccione Abogado</option>
											<c:forEach var="abogado" items="${abogados}">
												<form:option value="${abogado.dni}">
													<c:out value="${abogado.nombres} ${abogado.apaterno} ${abogado.amaterno}" />
												</form:option>
											</c:forEach>
										</form:select>
									
									
									<input type="submit" id="Buscar" name="Buscar" value="Buscar" class="btn_guardar" />									
								</th>					
								
							</tr>
						</table>
																
						<table border="0" align="center">			
							<tr>
								<td>&nbsp;</td>
								<td valign="top"><display:table id="asignado"
										name="listaAbogadoProceso"
										requestURI="/busquedaCargaLaboral.htm" pagesize="5"
										defaultsort="1" defaultorder="ascending">
										<display:column title="NÚMERO FOLDER"
											property="id.proceso.nroFolder" />
										<display:column title="NÚMERO EXPEDIENTE"
											property="id.proceso.nroExpediente" />
										<display:column title="NATURALEZA"
											property="id.proceso.materia.naturaleza.denominacion" />
										<display:column title="MATERIA"
											property="id.proceso.materia.denominacion" />
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