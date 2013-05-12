/*global window: false*/
/*global $, jQuery*/
function validateF () {
	'use strict';
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
		'use strict';
		var error = 0;
		if(!firstname.val().match(letters)){
			firstname.addClass("error");
			//nameInfo.text("We want names with more than 3 letters!");
			//nameInfo.addClass("error");
			error++;
		}
		else if (!lastname.val().match(letters)){
			lastname.addClass("error");
			//nameInfo.text("We want names with more than 3 letters!");
			//nameInfo.addClass("error");
			error++;
		}
		else if (!id.val().match(idnumbers)){
			error++;
		}
		else if (party.val()== "Default"){
			error++;
		}
		else if (area.val()== "Default"){
			error ++;
		}
		else if (!adr.val().match(alphanumeric)){
			error ++;
		}
		else if (!phone.val().match(numbers)){
			error ++;
		}
		else if (!email.val().match(validemail)){
			error ++;
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
			$.ajax({
				type: "post",
				url: "/RegisterServlet",
				data: {"kandidaat_eesnimi": str, "kandidaat_perenimi": stra, "kandidaat_id": str1, "kandidaat_erakond": str2, "kandidaat_piirkond":str3, "kandidaat_aadress": str4, "kandidaat_telefon": str5, "kandidaat_epost": str6},
				success: function(){
				}			
			});

		}
	});
}



function validate_combobox(combobox){  
	'use strict';
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
	'use strict';
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
	'use strict';
	// Validation rule
	var re = /[A-Za-z -']$/;
	return validate(text_box, re);
}
function validateIsikukood(text_box){
	'use strict';
	var re = /^\d{11}$/;
	return validate(text_box, re);
}

function validateAlphanumeric(text_box){   
	'use strict';
	var re= /^[0-9a-zA-Z]+$/;
	return validate(text_box, re);
}

function validateNumeric(text_box){
	'use strict';
	var re = /^[0-9]+$/;
	return validate(text_box, re);
}

function validateEmail(text_box){
	'use strict';
	var re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	return validate(text_box, re);
}

function clear_form_elements(ele) {
	'use strict';
	$(ele).find(':input').each(function() {
		switch(this.type) {
		case "kandidaat_nimi":
			break;
		case "kandidaat_id":
			break;
		case "kandidaat_aadress":
			break;
		case "kandidaat_telefon":
			break;
		case "kandidaat_epost":
			break;
		case "kandidaat_erakond":
			this.checked = false;
			break;
		case "kandidaat_piirkond":
			this.checked = false;
			break;
		}
	});
}