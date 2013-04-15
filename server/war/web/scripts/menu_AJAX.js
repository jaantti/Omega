
var options, a;
jQuery(function(){
	options = {
			//lookup: ['January', 'February', 'March', 'April', 'May'],
			serviceUrl: '/SearchServlet',
			onSelect: function(value) {window.location.hash = "#K"+value.data}
	};
	a = $('.search').autocomplete(options);
});

var logged_in = 0;
var google_id = '-1';

function login_toggle(){

	if (logged_in == 1) {
		logged_in = 0;
		google_id = '-1';
	}
	else {
		window.location = '/LoginServlet';
	}
	update_login();
	load_page(window.location.hash);
}

function update_login(){
	if (logged_in == 1) {
		$("#logi_sisse").hide();
		$("#logi_valja").show();
		$("#vali_button").show();
		$("#vali_hoiatus").hide();
	}
	else {
		$("#logi_valja").hide();
		$("#logi_sisse").show();
		$("#vali_button").hide();
		$("#vali_hoiatus").show();
	}
}

function lae_poliitik(tag) {
	if (logged_in == 0) {
		$("#vali_button").hide();
		$("#vali_hoiatus").show();
	}
	else {
		$("#vali_button").show();
		$("#vali_hoiatus").hide();
	}
	
	id = tag.substring(2);
	if (haal == id) {
		$("#vali_button").hide();
		$("#vali_hoiatus").show();
		$("#vali_hoiatus").text("Tänan hääle eest!");
	}
	$.getJSON('/CandidateServlet?id='+id, function(data) {
		$.each(data, function(k, v) {
			$("#k_info h2").text(v.nimi);
			$("#k_info h3").text(v.piirkond.nimi +", "+ v.erakond.nimi);
		})
	});
}

function genereeri_poliitik(nimi, id){
	var poliitik=$('<div id="K'+id+'" class="poliitik"></div>');
	var symlink=$('<a href="javascript:void(0)"></a>');
	var portree=$('<img src="profile_small.png"/>');
	var nimetus=$('<p>'+nimi+'</p>');
	symlink.append(portree).append(nimetus);
	poliitik.append(symlink);
	$(".erakond").append(poliitik);
	$("#K"+id).click(function() {window.location.hash = "#K"+id});
}

function populate_area(area_id, selected_id) {
	//area_id = area_id.substr(1);
	//selected_id = selected_id.substr(1);
	var data = '';
	if (!navigator.onLine){

		switch (area_id.substr(1)){
		case '1':
			data = $.parseJSON(localStorage.regions1);
			break;		
		case '2':
			data = $.parseJSON(localStorage.regions2);
			break;
		case '3':
			data = $.parseJSON(localStorage.regions3);
			break;
		case '4':
			data = $.parseJSON(localStorage.regions4);
			break;
		case '5':
			data = $.parseJSON(localStorage.regions5);
			break;
		case '6':
			data = $.parseJSON(localStorage.regions6);
			break;
		case '7':
			data = $.parseJSON(localStorage.regions7);
			break;
		case '8':
			data = $.parseJSON(localStorage.regions8);
			break;
		case '9':
			data = $.parseJSON(localStorage.regions9);
			break;
		case '10':
			data = $.parseJSON(localStorage.regions10);
			break;
		case '11':
			data = $.parseJSON(localStorage.regions11);
			break;
		case '12':
			data = $.parseJSON(localStorage.regions12);
			break;
		case '13':
			data = $.parseJSON(localStorage.regions13);
			break;
		case '14':
			data = $.parseJSON(localStorage.regions14);
			break;
		case '15':
			data = $.parseJSON(localStorage.regions15);
			break;
		}
		genereeri_erakond(data, selected_id);
	}
	else{
		var input = '/PartyServlet?piirkond_id='+area_id.substr(1);
		$.getJSON(input, function(data2) {
			if(navigator.onLine){
				genereeri_erakond(data2, selected_id);
			}

		});
	}

}

function get_areas(){
	$.getJSON('/RegionServlet', function(data) {
		$.each(data, function(k, v) {
			var id = v.id;
			var name = v.nimi;
			var loc = $('<li id="P'+id+'"><a href="javascript:void(0)" >'+name+'</a></li>')
			$("#areas").append(loc);
			$("#P"+id).click(function() {window.location.hash = "kandidaadid%%P"+id});
		})
		$("#areas").append($("#kasutajainfo"));
	});
}
function genereeri_erakond(data, selected_id){
	$("#main").show();
	$("#main h2").text($("#"+window.location.hash.split("%%")[1]).text());
	if (selected_id == undefined) {selected_id = "ALL"};
	var parties = ["ALL"];
	var party=$('<li id="ALL"><a href="javascript:void(0)" >Kõik parteid</a></li>');
	$("#party_tabs ul").append(party);
	if (selected_id == "ALL") {$("#ALL").addClass("selected_tab")};
	$("#ALL").click(function() {
		var curHash = window.location.hash.split("%%");
		window.location.hash = curHash[0] +"%%"+ curHash[1]
	});
	$.each(data, function(k, v) {
		var id = v.id;
		var party_id = v.idErakond;
		if($.inArray(party_id, parties) == -1){
			parties.push(party_id);
			var party=$('<li id="E'+party_id+'"><a href="javascript:void(0)" >'+v.nimiErakond+'</a></li>')
			$("#party_tabs ul").append(party);
			$("#E"+party_id).click(function() {
				var curHash = window.location.hash.split("%%");
				window.location.hash = curHash[0] +"%%"+ curHash[1] +"%%E"+ party_id
			});
		}
		if (selected_id == "ALL" || selected_id.substr(1) == party_id) {
			genereeri_poliitik(v.nimi, id);
		}
	});
	var length = parties.length;
	element = null;
	for (var i = 0; i < length; i++) {
		element = parties[i];
		$("#E"+element).removeClass("selected_tab")
	}
	$("#E"+selected_id.substr(1)).addClass("selected_tab");
	$(".loading_img").remove();	
}
$(document).ready(function(){
	get_areas();
	update_login();
	offline_ready();
	$("#logi_valja").click(function() {login_toggle()});
	$("#logi_sisse").click(function() {login_toggle()});
	$("#index").click(function() {window.location.hash = ""});
	$("#kandidaadid_piirkond1").click(function() {window.location.hash = "#kandidaadid_piirkond1"});
	$("#kandidaadid_piirkond2").click(function() {window.location.hash = "#kandidaadid_piirkond2"});
	$("#kasutajainfo").click(function() {window.location.hash = "#kasutajainfo%%"+valija_id});
	$("#lisa_kandidaadina").click(function() {window.location.hash = "#lisa_kandidaadina"});
	$("#statistika").click(function() {window.location.hash = "#statistika"});
	$("#abi").click(function() {window.location.hash = "#abi"});
	$('#stat_otsi').click(function() {});

});

function load_page(tag){
	window.location.hash = tag;
	tag = tag.split("%%");
	switch(tag[0])
	{
	case "":
		document.title = "Index";
		$("#main").load("index.html #main");
		$("#page_name").load("index.html #page_name");
		break;
	case "#kandidaadid":
		document.title = "Kandidaadid";
		$("#page_name").load("kandidaadid.html #page_name");
		$("#main").load("kandidaadid.html #main", function(){
			$("#main").hide();
			$('<img class="loading_img" src="loading_transparent.gif">').load(function() {
				$(this).width(48).height(48).prependTo($("#footer"));
			});
			populate_area(tag[1], tag[2]);
		});
		break;
	case "#kasutajainfo":
		if (logged_in == 0) {
			document.title = "Sisselogimine";
			$("#main").load("sisselogimine.html #main");
			$("#page_name").load("sisselogimine.html #page_name");
		}
		else {
			document.title = "Valija info";
			$("#page_name").load("valija_info.html #page_name");
			$("#main").load("valija_info.html #valija_info", function(){
				kasutajainfo(tag[1])
			});
		}
		break;
	case "#lisa_kandidaadina":
		if (logged_in == 0) {
			document.title = "Sisselogimine";
			$("#main").load("sisselogimine.html #main");
			$("#page_name").load("sisselogimine.html #page_name");
		}
		else {
			document.title = "Kandidaadina lisamine";
			$("#page_name").load("lisa_kandidaadina.html #page_name");
			$("#main").load("lisa_kandidaadina.html #lisa_kandidaadina", function(){
				validateF();
			});
		}
		break;
	case "#statistika":
		document.title = "Statistika";
		$("#main").load("statistika.html #statistika", function(){
			data_from_json(1);
		});
		$("#page_name").load("statistika.html #page_name");

		break;
	case "#abi":
		document.title = "Abi hääletamisel";
		$("#main").load("abi.html #main");
		$("#page_name").load("abi.html #page_name");
		break;
	case "#loginredirect":
		google_id = tag[1];
		if (google_id != "-1") {
			logged_in = 1;
		}
		update_login();
		window.location.hash = "kasutajainfo%%"+google_id;
		
		break;
	default:
		document.title = "Kandidaadi info";
	$("#page_name").load("kandidaadi_info.html #page_name");
	$("#main").load("kandidaadi_info.html #k_info", function(){
		lae_poliitik(tag[0])
	});
	}
}

var recentHash = "";

function pollHash(currentTab) {
	if (window.location.hash==recentHash) {
		return; // Nothing's changed since last polled.
	}
	recentHash = window.location.hash;
	// URL has changed, update the UI accordingly.
	load_page(currentTab);
}

function initializeStateFromURL() {
	var currentTab = window.location.hash;
	pollHash(currentTab);
}

window.onload = function() {
	initializeStateFromURL();
	setInterval(initializeStateFromURL, 100);

}
function offline_ready(){
	$.getJSON('/HaaledServlet?koik=1', function(data) {
		localStorage.koik=JSON.stringify(data)

	});
	$.getJSON('/RegionServlet', function(data) {
		localStorage.regions=JSON.stringify(data)
		console.log(data)

	});
	$.getJSON('/PartyServlet?piirkond_id=1', function(data) {
		localStorage.regions1=JSON.stringify(data)
		console.log(data)

	});
	$.getJSON('/PartyServlet?piirkond_id=2' , function(data) {
		localStorage.regions2=JSON.stringify(data)
		console.log(data)

	});
	$.getJSON('/PartyServlet?piirkond_id=3' , function(data) {
		localStorage.regions3=JSON.stringify(data)
		console.log(data)

	});
	$.getJSON('/PartyServlet?piirkond_id=4' , function(data) {
		localStorage.regions4=JSON.stringify(data)
		console.log(data)

	});
	$.getJSON('/PartyServlet?piirkond_id=5' , function(data) {
		localStorage.regions5=JSON.stringify(data)
		console.log(data)

	});
	$.getJSON('/PartyServlet?piirkond_id=6', function(data) {
		localStorage.regions6=JSON.stringify(data)
		console.log(data)

	});
	$.getJSON('/PartyServlet?piirkond_id=7', function(data) {
		localStorage.regions7=JSON.stringify(data)
		console.log(data)

	});
	$.getJSON('/PartyServlet?piirkond_id=8', function(data) {
		localStorage.regions8=JSON.stringify(data)
		console.log(data)

	});
	$.getJSON('/PartyServlet?piirkond_id=9', function(data) {
		localStorage.regions9=JSON.stringify(data)
		console.log(data)

	});
	$.getJSON('/PartyServlet?piirkond_id=10', function(data) {
		localStorage.regions10=JSON.stringify(data)
		console.log(data)

	});
	$.getJSON('/PartyServlet?piirkond_id=11', function(data) {
		localStorage.regions11=JSON.stringify(data)
		console.log(data)

	});
	$.getJSON('/PartyServlet?piirkond_id=12', function(data) {
		localStorage.regions12=JSON.stringify(data)
		console.log(data)

	});
	$.getJSON('/PartyServlet?piirkond_id=13', function(data) {
		localStorage.regions13=JSON.stringify(data)
		console.log(data)

	});
	$.getJSON('/PartyServlet?piirkond_id=14', function(data) {
		localStorage.regions14=JSON.stringify(data)
		console.log(data)

	});
	$.getJSON('/PartyServlet?piirkond_id=15', function(data) {
		localStorage.regions15=JSON.stringify(data)
		console.log(data)

	});
}
