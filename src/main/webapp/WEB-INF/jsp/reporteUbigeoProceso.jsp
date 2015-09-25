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
			buttonImageOnly: true,
			dateFormat: 'dd/mm/yy', 
			changeMonth: true, 
			changeYear: true, 
			yearRange: '-100:+0'
		});
		$( "#datepicker1" ).datepicker({
			showOn: "button",
			buttonImage: "images/calendar.gif",
			buttonImageOnly: true,
			dateFormat: 'dd/mm/yy', 
			changeMonth: true, 
			changeYear: true, 
			yearRange: '-100:+0'
		});
	});
	</script>
<!-- Fin Archivos para calendario -->


<title>Reporte Ubigeo</title>

<script language="javascript" src="script/popcalendar.js"></script>
<script language="javascript" src="script/jquery.cascade.js"></script>
<script language="javascript" src="script/jquery.cascade.ext.js"></script>
<script language="javascript" src="script/jquery.templating.js"></script>

<script type="text/javascript">
	function commonTemplate(item) {
		return "<option value='" + item.Value + "'>" + item.Text + "</option>";
	};
	function commonMatch(selectedValue) {
		return this.When == selectedValue;
	};
</script>
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

			<form:form name="form1" commandName="ubigeoInstancia">
				<div id="form">
					<fieldset id="formFielset">
						<div>
							<fieldset id="formFielset">
								<label id="label_titulo_pestanas">Reporte de Procesos Judiciales por Ubigeo</label>
							</fieldset>
						</div>						
						
						<ol>
							<li>
								<label class="label">Tipo de Naturaleza:</label> 
								<form:select id="idNaturaleza" path="id.proceso.materia.naturaleza.idNaturaleza" class="combo_uno" >
									<form:options items="${tipoNaturaleza}" itemLabel="denominacion" itemValue="idNaturaleza" />
								</form:select>
							</li>
							
							<li>
								<label class="label">Tipo de Materia:</label> 
								<form:select id="idMateria" path="id.proceso.materia.idMateria" class="combo_uno"></form:select>
							</li>
							
							<li>
								<label class="label">Elija Ubigeo</label> 
								<form:select path="id.dependencia.ubigeo.idUbigeo" id="mostrarUbigeo" class="combo_uno">
									<form:options items="${tipoUbigeo}" itemLabel="denominacion" itemValue="idUbigeo" id="ubigeo" />
								</form:select>
							</li>
							
							<li>
								<label class="label">Elija Estado</label> 
								<form:select path="id.proceso.estado.idEstado" id="mostrarEstado" class="combo_uno">
									<form:options items="${tipoEstado}" itemLabel="denominacion" itemValue="idEstado" id="estado" />
								</form:select>
							</li>
							
							<li>
								<label class="label">Fecha de Inicio:</label> 
								<form:input type="text" id="datepicker"  maxlength="10" path="id.proceso.fechaInicio" class="input_date" />
								<font color="#FF0000">
									<form:errors path="id.proceso.fechaInicio" />
								</font>
							</li>
							
							<li>
								<label class="label">Fecha Fin:</label> 
								<form:input type="text"	id="datepicker1" maxlength="10" path="id.proceso.fechaFin" class="input_date"/>
								<font color="#FF0000">
									<form:errors path="id.proceso.fechaFin" />
								</font>
							</li>
							<li>
								<c:if test="${not empty error}">
								<font color="#FF0000">
									<c:out value="${error}"/>
								</font>
								</c:if>
							</li>
						</ol>
						
						<p align="center">
							<input type="button" onclick="location.href='<c:url value="/inicio.htm"/>'" value="Cancelar" class="btn_asignar" />
							<input type="submit" value="Ver Reporte" class="btn_nuevo_expediente" />
						</p>
					</fieldset>
				</div>
			</form:form>
		</div>
		<!-- FIN Cuerpo -->
		<jsp:include page="/WEB-INF/pie/pie.jsp" />
		<!-- FIN footer -->
	</div>
	<!-- FIN Contenedor PRINCIPAL -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#idMateria").cascade("#idNaturaleza", {
				ajax : {
					url : '/SysProc/jsonMateria.htm'
				},
				//ajax: {url: 'script/datos-provincias.js'},
				template : commonTemplate,
				match : commonMatch
			});
			//forzamos un evento de cambio para que se carge por primera vez
			$("#idNaturaleza").change();
		});
	</script>
</body>
</html>