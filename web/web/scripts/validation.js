
function validate_combobox(combobox){  
	if(combobox.selectedIndex == 0){
		$(combobox).addClass("invalid_input");
		return false;
	}
	else{
		$(combobox).removeClass("invalid_input");
		return true;
	}
}

function validate(text_box, re){
	var to_test = text_box.value;
	if (re.test(to_test)){
		$(text_box).removeClass("invalid_input");
		return false;
	}
	else{
		$(text_box).addClass("invalid_input");
		return true;
	}
}

function validateText(text_box){
	// Validation rule
	var re = /[A-Za-z -']$/;
	return validate(text_box, re);
}
function validateIsikukood(text_box){
	var re = /^\d{11}$/;
	return validate(text_box, re);
}

function validateAlphanumeric(text_box){   
	var re= /^[0-9a-zA-Z]+$/;
	return validate(text_box, re);
}

function validateNumeric(text_box){
	var re = /^[0-9]+$/;
	return validate(text_box, re);
}

function validateEmail(text_box){
	var re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	return validate(text_box, re);
}

function ready_kandidaat_form(){
	
	$("#kandidaat_nimi").blur(function(){
		validateText(this);
	});
	
	$("#kandidaat_id").blur(function(){
		validateIsikukood(this);
	});
	
	$("#kandidaat_erakond").blur(function(){
		validate_combobox(this);
	});
	
	$("#kandidaat_piirkond").blur(function(){
		validate_combobox(this);
	});
	
	$("#kandidaat_aadress").blur(function(){
		validateAlphanumeric(this);
	});
	
	$("#kandidaat_telefon").blur(function(){
		validateNumeric(this);
	});
	
	$("#kandidaat_epost").blur(function(){
		validateEmail(this);
	});
	
	$("#kandidaat_pilt").blur(function(){
	});
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