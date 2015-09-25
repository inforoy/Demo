<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Página de Descarga</title>
</head>
<body>

	<h1>Página de Descarga</h1>
	<c:url value="/main/download/pdf.htm" var="downloadPdf" />
	<a href="<c:out value='${downloadPdf}'/>">Descargar PDF</a>

</body>
</html>