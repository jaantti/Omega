
var logged_in = 0;

function login_toggle(){
   if (logged_in == 1) {
      logged_in = 0
   }
   else {
      logged_in = 1
   }
   update_login();
   load_page(window.location.hash);
}

function update_login(){
   if (logged_in == 1) {
      $("#logi_sisse").hide();
      $("#logi_valja").show();
   }
   else {
      $("#logi_valja").hide();
      $("#logi_sisse").show();
   }
}

function lae_poliitik(tag) {
	
	id = tag.substring(1);
	$.getJSON('data/candidate.json', function(data) {
      $.each(data, function(k, v) {
      	if (k == "id" && v == "1234567890") { //Static id to load the provided data file.
      		$("#k_info h2").text(data.person.name);
      		$("#k_info h3").text(data.region.name +", "+ data.party.name);
      	}
		})
	});
}

function genereeri_poliitik(nimi, id){
	var poliitik=$('<div id="'+id+'" class="poliitik"></div>');
	var symlink=$('<a href="javascript:void(0)"></a>');
	var portree=$('<img src="profile_small.png"/>');
	var nimetus=$('<p>'+nimi+'</p>');
	symlink.append(portree).append(nimetus);
	poliitik.append(symlink);
   $(".erakond").append(poliitik);
   $("#"+id).click(function() {window.location.hash = "#"+id});
}
    
function populate_party(){
   $.getJSON('data/findCandidatesByPartyAndRegion.json', function(data) {
      $.each(data.candidates, function(k, v) {
      	var id = v.id;
			$.each(this.person, function(k , v) {
				if(k == 'name'){
					genereeri_poliitik(v, id);
				}
			})
		})
	});
}

function populate_area(area_id, selected_id) {
	$.getJSON('data/findCandidatesByRegion.json', function(data) {
		$("#main h2").text($("#"+window.location.hash.split("%%")[1]).text());
		if (selected_id == undefined) {selected_id = "ALL"};
		var parties = ["ALL"];
		var party=$('<li id="ALL"><a href="javascript:void(0)" >Kõik parteid</a></li>');
		$("#party_tabs ul").append(party);
		$("#ALL").click(function() {window.location.hash = window.location.hash.split("%%")[0] +"%%"+ window.location.hash.split("%%")[1]});
      $.each(data.candidates, function(k, v) {
      	var id = v.id;
			var party_id = v.party.id;
			if($.inArray(party_id, parties) == -1){
				parties.push(party_id);
				var party=$('<li id="'+party_id+'"><a href="javascript:void(0)" >'+v.party.name+'</a></li>')
				$("#party_tabs ul").append(party);
				$("#"+party_id).click(function() {window.location.hash = window.location.hash.split("%%")[0] +"%%"+ window.location.hash.split("%%")[1] +"%%"+ party_id});
			}
			if (selected_id == "ALL" || selected_id == party_id) {
				genereeri_poliitik(v.person.name, id);
			}
		})
		var length = parties.length,
		element = null;
		for (var i = 0; i < length; i++) {
			element = parties[i];
			$("#"+element).removeClass("selected_tab")
		}
		$("#"+parties[$.inArray(selected_id, parties)]).addClass("selected_tab");
		//alert(selected_id+" | "+parties);
	});
}

function get_areas(){
	$.getJSON('data/region.json', function(data) {
		$.each(data.region, function(k, v) {
			var id = v.id;
			var name = v.name;
			var loc = $('<li id="'+id+'"><a href="javascript:void(0)" >'+name+'</a></li>')
			$("#areas").append(loc);
			$("#"+id).click(function() {window.location.hash = "kandidaadid%%"+id});
		})
		$("#areas").append($("#kasutajainfo"));
	});
}

$(document).ready(function(){
	get_areas();
	update_login();
   $("#logi_valja").click(function() {login_toggle()});
   $("#logi_sisse").click(function() {login_toggle()});
   $("#index").click(function() {window.location.hash = ""});
   $("#kandidaadid_piirkond1").click(function() {window.location.hash = "#kandidaadid_piirkond1"});
   $("#kandidaadid_piirkond2").click(function() {window.location.hash = "#kandidaadid_piirkond2"});
   $("#kasutajainfo").click(function() {window.location.hash = "#kasutajainfo"});
   $("#lisa_kandidaadina").click(function() {window.location.hash = "#lisa_kandidaadina"});
   $("#statistika").click(function() {window.location.hash = "#statistika"});
   $("#abi").click(function() {window.location.hash = "#abi"});

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
      $("#main").load("kandidaadid.html #main");
      $("#page_name").load("kandidaadid.html #page_name", function(){
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
            $("#main").load("valija_info.html #valija_info");
            $("#page_name").load("valija_info.html #page_name");
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
   				ready_kandidaat_form();
   				});
         }
      break;
      case "#statistika":
      document.title = "Statistika";
      $("#main").load("statistika.html #statistika", function(){
   				data_from_json("piirkond");
   				});
      $("#page_name").load("statistika.html #page_name");
		
			
      break;
      case "#abi":
      document.title = "Abi hääletamisel";
      $("#main").load("abi.html #main");
      $("#page_name").load("abi.html #page_name");
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
