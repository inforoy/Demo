<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PAGINA DE ERROR</title>
</head>
<body background="<c:url value='/images/resources/background.png'/>" style="background-repeat: no-repeat;background-position: 50% 0%;">
			
			<h1><em>Error del Sistema.....Vuelva a Intentarlo</em></h1><br><br><br><br>
			<b>Nombre:</b><c:out value="${nombre}"></c:out><br><br>
			<b>Mensaje:</b><c:out value="${mensaje}"></c:out><br><br><br><br>
			<c:url var="retornoImgUrl" value="images/resources/regresar.png" />
			<c:url var="paginaInicio" value="inicio.htm"/>
			<a href="<c:out value="${paginaInicio}"/>"><img src="<c:out value="${retornoImgUrl}"/>" title="Regresar Pagina de Inicio"></img></a>

</body>
</html>