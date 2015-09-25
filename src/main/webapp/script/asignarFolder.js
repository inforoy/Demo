	function doAjaxPost() {  
		 // var anio = $('#anio').val();
		  $.ajax({  
		    type: "POST",  //indica el metodo que se va usar "GET" o "POST"
		    url: "/SysProc/addFolder.htm",  //URL a la que se hace la llamada
		    data: {varanio:$("#anio").val()},  //Datos que son enviados a la URL a la que se hace la llamada
		    success: function(response){  //Es la función que se encarga de interactuar con el usuario una vez completa la llamada con el servidor.
		      	$("#numfolder").val(response);
		    	//$("#info").html(response);
		    }  
		  });  
		}  

