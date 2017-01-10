	function validate()
	{
		var NameLocal1=document.getElementById("UserID") ; 
		NameLocal1.setCustomValidity("");
		if (NameLocal1.value=="") 
		{ 
			NameLocal1.setCustomValidity("The UserID field is not full") ; 
			return false ;
		} 
		
		 var NameLocal2=document.getElementById("Password") ;
		 NameLocal2.setCustomValidity("");		
		 if (NameLocal2.value=="") 
		 { 
			 NameLocal2.setCustomValidity("The Password field is not full") ; 
			 return false ;
		 }
		 document.forma.submit(); 
	}