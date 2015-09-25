<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/estiloIndex.css"/>
<link rel="stylesheet" type="text/css" href="css/menuHorizontal.css"/>
<script src="script/jquery-1.4.3.min.js" type="text/javascript"></script>
<script src="script/jquery.fancybox.pack.js" type="text/javascript"></script>
<link href="css/jquery.fancybox.css" rel="stylesheet" type="text/css" media="screen"/>
<link rel="stylesheet" href="css/displaytag.css" type="text/css">
<link rel="stylesheet" href="css/screen.css" type="text/css">
<link rel="stylesheet" href="css/site.css" type="text/css">
<title>Listar Demandante</title>
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
<script type="text/javascript">
$(document).ready(function(){
    $(".ifancybox").fancybox({
         'width' : '75%',
         'height' : '75%',
         'autoScale' : false,
         'transitionIn' : 'none',
         'transitionOut' : 'none',
         'type' : 'iframe'
     });
 });
</script>

</head>
<body>
<c:url var="editImgUrl" value="images/resources/edit.png" />
<div id="contenedorPrincipal">
<div> 
<form:form commandName="demandanteBuscar">
<div id="form">
<fieldset id="formFielset">
<legend>BUSQUEDA DE DEMANDANTES</legend>
<table border="0" align="center">
	<tr valign="top">
		<td>&nbsp;</td>
		<td>
			<fieldset style="border:2px solid #990000;">
				<legend>Filtros de B&#250;squeda:</legend>
				<table border="0">	
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
				  	</tr>						  
					<tr>
						<td>Denominacion: </td>
						<td><form:input path="denominacion" maxlength="50"/></td>
						
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					 <tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><input type="submit" id="Buscar" name="Buscar"  value="Buscar"/></td>
						<td>&nbsp;</td>				
					</tr>				  					  
				</table>	
			</fieldset>					
		</td>
		<td>&nbsp;</td>									
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>	
		<td>&nbsp;</td>	
	</tr>
	<tr>
		<td>&nbsp;</td>	
		<td valign="top">		
		<% int i=0;%>
			<display:table id="results" name="listaDemandantes"  pagesize="5"
						defaultsort="1"  defaultorder="ascending" >
				<% i++;%>		
							
	            <display:column title="Nro" sortable="true">
	            	<%out.print(i); %>
	            </display:column>
	            <display:column property="denominacion" title="DENOMINACIÓN" sortable="true"  />
	            <c:url var="editCcUrl" value="addDemandante.htm">
				   <c:param name="idDemandante" value="${results.idDemandante}" />
				</c:url>
				<display:column title="ACCIONES">
					<a href="<c:out value="${editCcUrl}"/>"><img src="<c:out value="${editImgUrl}"/>" title="Editar Demandante"></img></a>
				</display:column> 
		</display:table>												
		</td>
		<td>&nbsp;</td>			
	</tr>		
</table>
</fieldset>
</div>	
</form:form>
 </div><!-- FIN Cuerpo -->
</div><!-- FIN Contenedor PRINCIPAL -->

</body>
</html>