<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/estiloIndex.css"/>
<link rel="stylesheet" type="text/css" href="css/menuHorizontal.css"/>
<title>Datos del Expediente Guardado</title>
  <style type="text/css">
	#form {
	    border:5px solid #FFFFFF;
	    width:500px;
		margin:auto;
	}
	#formFielset {
		border:2px solid #990000;

	}
	#form legend{
	    font-weight:bold;
	    font-size:12px;
	}

</style>
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
<form:form commandName="ultimoProceso">	
<div id="form">
<fieldset id="formFielset">
<legend>EXPEDIENTE GUARDADO</legend>	
	<table border="0" align="center">
		<tr valign="top">
			<td>&nbsp;</td>
			<td>
				<table>						
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>	
					<tr>		
						<td>&nbsp;</td>			
						<td><label>Numero Expediente:</label></td>
						<td><form:input path="nroExpediente" maxlength="50" readonly="true"/></td>
					</tr>
					<tr>		
						<td>&nbsp;</td>			
						<td><label>Materia:</label></td>
						<td><form:input path="materia.denominacion" maxlength="50" readonly="true"/></td>
					</tr>
					<tr>		
						<td>&nbsp;</td>			
						<td><label>Estado:</label></td>
						<td><form:input path="estado.denominacion" maxlength="50" readonly="true"/></td>
					</tr>
					<tr>		
						<td>&nbsp;</td>			
						<td><label>Numero de Folder:</label></td>
						<td><form:input path="nroFolder" maxlength="50" readonly="true"/></td>
					</tr>
					<tr>		
						<td>&nbsp;</td>			
						<td><label>Cuantia:</label></td>
						<td><form:input path="cuantia" maxlength="50" readonly="true"/></td>
					</tr>
					<tr>		
						<td>&nbsp;</td>			
						<td><label>Pretension:</label></td>
						<td><form:input path="pretension" maxlength="50" readonly="true"/></td>
					</tr>
					<tr>		
						<td>&nbsp;</td>			
						<td><label>Etapa Procesal:</label></td>
<%-- 						<td><form:input path="etapaProcesal" maxlength="50" readonly="true"/></td> --%>
						<td><c:forEach var="instancia" items="${instancias}">
								<c:out value="${instancia.id.dependencia.denominacion}"/>							
							</c:forEach></td>
					</tr>
					<tr>		
						<td>&nbsp;</td>			
						<td><label>Fecha Inicio:</label></td>
						<td><form:input path="fechaInicio" maxlength="50" readonly="true"/></td>
					</tr>
					<tr>		
						<td>&nbsp;</td>			
						<td><label>Demandado:</label></td>
						<td><c:forEach var="demandado" items="${listaProcesoDemandado}">
								<c:out value="${demandado.denominacion}"/>							
							</c:forEach></td>
					</tr>
					<tr>		
						<td>&nbsp;</td>			
						<td><label>Demandante:</label></td>
						<td><c:forEach var="demandante" items="${listaProcesoDemandante}">
								<c:out value="${demandante.denominacion}"/>							
							</c:forEach></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>		
					<tr>
						<td>&nbsp;</td>
						<td align="left">
							
							<input type="button" id="Cancelar" name="Cancelar" 
									onclick="location.href='<c:url value="/inicio.htm"/>'"
									value="Finalizar"/>		
						</td>
						<td>&nbsp;</td>
					</tr>																																																																																																								
				</table>
			</td>
			<td>&nbsp;</td>									
		</tr>		
	</table>
	</fieldset>
	</div>	
</form:form>
 </div><!-- FIN Cuerpo -->
 <jsp:include page="/WEB-INF/pie/pie.jsp" />
<!-- FIN footer -->
</div><!-- FIN Contenedor PRINCIPAL -->
</body>
</html>