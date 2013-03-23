	// This function will validate Nimi.  
function validateName(x){
    // Validation rule
    var re = /[A-Za-z -']$/;
    // Check input
    if(re.test(document.getElementById(x).value)){
        // Hide error prompt
        document.getElementById(x + 'Error').style.display= "none";
        return true;
    }else{
		document.getElementById(x).style.background ='#e35152';
		// Show error prompt
        document.getElementById(x + 'Error').style.display = "block";
        return false;
	}
}
// This function will validate ID.  
	function kandidaat_id_validation(x){
	var re = /^\d{11}$/;
	if (document.getElementById(x).value.match(re)){
		document.getElementById(x + 'Error').style.display = "none";
        return true;
    }else{
		document.getElementById(x).style.background ='#e35152';
		// Show error prompt
        document.getElementById(x + 'Error').style.display = "block";
        return false;
	}
}
	function erakondselect(x){  
	if(document.getElementById(x).selectedIndex !== 0){
        document.getElementById(x + 'Error').style.display = "none";
        return true;
    }else{
		document.getElementById(x).style.background ='#e35152';
		document.getElementById(x + 'Error').style.display = "block";
        return false;   
    }
}
	function piirkondselect(x){  
	if(document.getElementById(x).selectedIndex !== 0){
        document.getElementById(x + 'Error').style.display = "none";
        return true;
    }else{
		document.getElementById(x).style.background ='#e35152';
		document.getElementById(x + 'Error').style.display = "block";
        return false;   
    }
}
function alphanumeric(x){   
	var re= /^[0-9a-zA-Z]+$/;  
	if (document.getElementById(x).value.match(re)){
		document.getElementById(x + 'Error').style.display = "none";
        return true;
    }else{
		document.getElementById(x).style.background ='#e35152';
		// Show error prompt
        document.getElementById(x + 'Error').style.display = "block";
        return false;
	}
}
function allnumeric(x){
	var re = /^\d{10}$/;
	if (document.getElementById(x).value.match(re)){
		document.getElementById(x + 'Error').style.display = "none";
        return true;
    }else{
		document.getElementById(x).style.background ='#e35152';
		// Show error prompt
        document.getElementById(x + 'Error').style.display = "block";
        return false;
	}
}
function ValidateEmail(x){  
	var re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(document.getElementById(x).value.match(re)){
        document.getElementById('kandidaat_epostError').style.display = "none";
        return true;
    }else{
		document.getElementById(x).style.background ='#e35152';
		document.getElementById('kandidaat_epostError').style.display = "block";
        return false;
    }
}
function validateForm(){
    // Set error catcher
    var error = 0;
	// Check name
    if(!validateName('kandidaat_nimi')){
        document.getElementById('kandidaat_nimiError').style.display = "block";
        error++;
    }
	if(!kandidaat_id_validation('kandidaat_id')){
		document.getElementById('kandidaat_idError').style.display = "block";
        error++;
    }
	if(!erakondselect('kandidaat_erakond')){
        document.getElementById('kandidaat_erakondError').style.display = "block";
        error++;
    }
	if(!piirkondselect('kandidaat_piirkond')){
        document.getElementById('kandidaat_piirkondError').style.display = "block";
        error++;
    }
	if(!alphanumeric('kandidaat_aadress')){
		document.getElementById('kandidaat_aadressError').style.display = "block";
        error++;
	}
	if(!allnumeric('kandidaat_telefon')){
		document.getElementById('kandidaat_telefonError').style.display = "block";
        error++;
	}
	if(!ValidateEmail('kandidaat_epost')){
        document.getElementById('kandidaat_epostError').style.display = "block";
        error++;
    }
    // Don't submit form if there are errors
	if(error > 0){
        return false;
    } 
}