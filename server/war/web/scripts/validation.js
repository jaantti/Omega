function validateF () {
	var form = $("#lisa_kandidaadina");
	var firstname = $("#kandidaat_eesnimi");
	var lastname = $("#kandidaat_perenimi");	
	var id = $("#kandidaat_id");
	var party = $("#kandidaat_erakond");
	var area = $("#kandidaat_piirkond");
	var adr = $("#kandidaat_aadress");
	var phone = $("#kandidaat_telefon");
	var email = $("#kandidaat_epost");
	var letters = /[A-Za-z -']$/;
	var idnumbers =  /^\d{11}$/;
	var alphanumeric = /^[0-9a-zA-Z ]+$/;
	var numbers =  /^[0-9]+$/;
	var validemail =  /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	$("#submit_kandidaat").click(function(){
		var error = 0;
		if(!firstname.val().match(letters)){
			firstname.addClass("error");
			//nameInfo.text("We want names with more than 3 letters!");
			//nameInfo.addClass("error");
			error++;
			return false;
		}
		else if (!lastname.val().match(letters)){
			lastname.addClass("error");
			//nameInfo.text("We want names with more than 3 letters!");
			//nameInfo.addClass("error");
			error++;
			return false;
		}
		else if (!id.val().match(idnumbers)){
			error++;
			return false;
		}
		else if (party.val()== "Default"){
			error++;
			return false;
		}
		else if (area.val()== "Default"){
			error ++;
			return false;
		}
		else if (!adr.val().match(alphanumeric)){
			alert("error");
			error ++;
			return false;
		}
		else if (!phone.val().match(numbers)){
			alert("telefoninr vale");
			error ++;
			return false;
		}
		else if (!email.val().match(validemail)){
			alert("emailikene");
			error ++;
			return false;
		}
		else{
			var str = firstname.serialize();
			var stra = lastname.serialize();
			var str1 = id.serialize();
			var str2 = party.serialize();
			var str3 = area.serialize();
			var str4 = adr.serialize();
			var str5 = phone.serialize();
			var str6 = email.serialize();
		}
		if (error >= 1){
			return false;
		}
		else{
			alert("Sain postini" + name );
			$.ajax({
				type: "post",
				url: "/RegisterServlet",
				data: {"kandidaat_eesnimi": str, "kandidaat_perenimi": stra, "kandidaat_id": str1, "kandidaat_erakond": str2, "kandidaat_piirkond":str3, "kandidaat_aadress": str4, "kandidaat_telefon": str5, "kandidaat_epost": str6},
				success: function(){

					//clear_form_elements("#lisa_kandidaadina");
					alert("success");
				}			
			});

		}
	});
}



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
		return true;
	}
	else{
		$(text_box).addClass("invalid_input");
		return false;
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

function clear_form_elements(ele) {
	$(ele).find(':input').each(function() {
		switch(this.type) {
		case "kandidaat_nimi":
		case "kandidaat_id":
		case "kandidaat_aadress":
		case "kandidaat_telefon":
		case "kandidaat_epost":
		case "kandidaat_erakond":
			this.checked = false;
		case "kandidaat_piirkond":
			this.checked = false;
		}
	});
}