/**
 * 
 */
function doAjaxPost() {  
	  // get the form values  
	  var name = $('#name').val();
	  var education = $('#education').val();
	   
	  $.ajax({  
	    type: "POST",  
	    url: contexPath + "/AddUser.htm",  
	    data: "name=" + name + "&education=" + education,  
	    success: function(response){
	    	alert("LLEGOOO");
	    	var returnedData = JSON.parse(response);

	      // we have the response 
	      if(returnedData.status == "SUCCESS"){
	    	  alert("entro al fi");
	    	  userInfo = "<ol>";
	    	  for(i =0 ; i < returnedData.result.length ; i++){
	    		  userInfo += "<br><li><b>Name</b> : " + returnedData.result[i].name + 
	    		  ";<b> Education</b> : " + returnedData.result[i].education;
	    	  }
	    	  userInfo += "</ol>";
	    	  $('#info').html("User has been added to the list successfully. " + userInfo);
	    	  $('#name').val('');
		      $('#education').val('');
		      $('#error').hide('slow');
		      $('#info').show('slow');
	      }else{
	    	  alert("entro al else");
	    	  errorInfo = "";
	    	  for(i =0 ; i < returnedData.result.length ; i++){
	    		  errorInfo += "<br>" + (i + 1) +". " + returnedData.result[i].code;
	    	  }
	    	  $('#error').html("Please correct following errors: " + errorInfo);
	    	  $('#info').hide('slow');
	    	  $('#error').show('slow');
	      }	      
	    },  
	    error: function(e){  
	      alert('Error: ' + e);  
	    }  
	  });  
	}  