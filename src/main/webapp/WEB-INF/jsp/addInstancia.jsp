<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<!--  Imports -->
	<link rel="stylesheet" href="css/formularios/form.css" type="text/css">
	<link rel="stylesheet" href="css/formularios/inputs.css" type="text/css">
	<!--  Fin - Imports -->
	<script language="javascript" src="script/jquery.js"></script>

<link rel="stylesheet" type="text/css" href="css/estiloIndex.css"/>
<link rel="stylesheet" type="text/css" href="css/menuHorizontal.css"/>
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
		
		$("#datepicker").datepicker({
			showOn: "button",
			buttonImage: "images/calendar.gif",
			buttonImageOnly: true
		});
		var fechaCambioInstancia = "<c:out value='${instanciaGuardar.fechaCambio}' />";

		if(fechaCambioInstancia==null || fechaCambioInstancia==""){
			$("#datepicker").datepicker().datepicker("setDate", new Date());
		}
	});
	</script>
<!-- Fin Archivos para calendario -->
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
<title>Agregar Instancia</title>

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
<form:form name="form1" commandName="instanciaGuardar" method="POST">
<div id="form">
<fieldset id="formFielset">
<legend id="label_titulo_pestanas">AGREGAR INSTANCIA JUDICIAL DEL PROCESO</legend>
</fieldset>
<ol>
<li><label class="label">Número de Expediente:</label>
	<form:input readonly="true" path="id.proceso.nroExpediente" class="input_one" />
</li>
<li><label class="label">Órgano Judicial:</label>
	<form:select id="idOrganoJudicial" path="id.dependencia.organojudicial.idOrganojudicial"  class="combo_uno">
		<form:option value="0">----Elija Órgano Judicial----</form:option>
		<form:options items="${organosJudiciales}" itemLabel="denominacion" itemValue="idOrganojudicial" />
	</form:select>
</li>
<li><label class="label">Dependencia:</label>
	<form:select id="idDependencia" path="id.dependencia.idDependencia"  class="combo_uno"/>
	<font color="#FF0000">
		<form:errors id="dependenciaErrors" name="dependenciaErrors" path="id.dependencia" />
	</font>
</li>
<li><label class="label">Fecha de Cambio:</label>
	<form:input id="datepicker" maxlength="10" path="fechaCambio" class="input_date"  />
	<font color="#FF0000">
		<form:errors id="fechaCambioErrors" name="fechaCambioErrors" path="fechaCambio" />
	</font>
</li>

</ol>
<form:hidden id="organoJudicialId" path="id.dependencia.organojudicial.idOrganojudicial"/>
<p align="center">
	<input type="button" onclick="location.href='<c:url value="/inicio.htm"/>'" value="Cancelar" class="btn_asignar" /> 
	<input type="submit" value="Guardar" class="btn_guardar">
</p>

</div>
</form:form>
<script type="text/javascript">
	$(document).ready(function()
	{
		$("#idDependencia").cascade("#idOrganoJudicial",{
		ajax:{
			url:'/SysProc/jsonDependencia.htm'
			},
			//ajax: {url: 'script/datos-provincias.js'},
		template: commonTemplate,
		match: commonMatch
	});
	//forzamos un evento de cambio para que se carge por primera vez
	$("#idOrganoJudicial").val($("#organoJudicialId").val()).change();
	});
</script>
 </div><!-- FIN Cuerpo -->
<jsp:include page="/WEB-INF/pie/pie.jsp" />
</div><!-- FIN Contenedor PRINCIPAL -->

</body>
</html>