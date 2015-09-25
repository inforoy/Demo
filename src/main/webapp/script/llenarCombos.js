 function fn_cargarMateria(){
	 $.ajax({type: 'POST',
         url:    "/SysProc/cargarMateria.htm",
         data:   {idNaturaleza:$('#idNaturaleza').attr('value')
                 },
         success:  function(responseText){
                      fn_cargarCombo(responseText, document.getElementById("idMateria"));
         	       },
         dataType: 'json',
         async: false
    });
}

function fn_cargarCombo(data, combo){


combo.length=1;
combo.options[0] = new Option('[todos]', '');
$.each(data,function(i, objeto){
     combo.options[i] = new Option(objeto.text, objeto.id); 
});
}