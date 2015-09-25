<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<html>
  <head>
    <title>Iniciar Sesi&oacute;n SysProc</title>
    <link href="css/login/css/style.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
  <div id="box">
  	<div class="elements">
  	<div class="avatar"></div>
	    <h1 align="center">Iniciar Sesi&oacute;n</h1>
	    <form name="f" action="<c:url value='j_spring_security_check'/>" method="POST">
	    	<input type='text' name='j_username' class='username' placeholder='Usuario' />
	    	<input type='password' name='j_password' class='password' placeholder='Contraseña'>
	    	 <c:if test="${not empty param.error}">
	    	 	<font color="#FF0000">
					<span></br>Error vuelva a intentarlo<em></em></span>
				</font>
			</c:if>
			<p align="center">
				<input name="submit" type="submit" value="Ingresar">
				<input name="reset" type="reset">
			</p>
	
	    </form>
	    <c:if test="${not empty param.info}">						
			<c:if test="${param.info eq 1}">
				<font color="#FF0000">
					<p></br>Sesi&oacute;n Finalizada Correctamente</p>
				</font>
			</c:if>			
			<c:if test="${param.info eq 2}">
				<font color="#FF0000">
					<p></br>Tu sesi&oacute;n ha expirado, por favor, inicia la sesi&oacute;n nuevamente.</p>
				</font>
			</c:if>		
		</c:if>
	 </div>
	</div>
  </body>
</html>
