var valija_id;
var haal;

function kasutajainfo(google_id){
	$.getJSON('/IsikServlet?google_id='+google_id, function(data) {
		$.each(data, function(k, v) {
			valija_id = google_id;
			haal = v.kandidaat;
			$("#valija_nimi").text(v.given_name + " " + v.family_name);
			$("#valija_email").text(v.email);
			if (haal != -1) {
				$("#kelle_poolt").text("Kelle poolt hääletasite: " + v.k_eesnimi + " " + v.k_perenimi);
			}
			else {
				$("#kelle_poolt").text("Teie hääl on veel andmata.");
			}
		})
		$("#tuhista_haal").click(function() {tuhista_haal(valija_id)});
	});
}

function vote(valija_id){
	if (logged_in == 1 && valija_id != '-1') {
		$.get('/AuthServlet?google_id='+valija_id+'&vote='+window.location.hash.substr(2), function(){
			$("#vali_button").hide();
			$("#vali_hoiatus").text("Tänan hääle eest!");
			$("#vali_hoiatus").show();
			haal = window.location.hash.substr(2);
		})
	}
}

function tuhista_haal(google_id){
	if (logged_in == 1 && valija_id != '-1'){
		$.get('/AuthServlet?google_id='+valija_id+'&tuhista=yes', function(){
			$("#kelle_poolt").text("Teie hääl on veel andmata.");
			haal = -1;
		});
	}
}