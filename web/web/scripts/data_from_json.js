/*$.getJSON("data/findCandidatesByPart.json", function(json) {
    console.log(json); 
	alert(json);// this will show the info it in firebug console
});*/
var elemToBind;
var elemToBind2;
function BindEvent(){
        elemToBind = document.getElementById ( "stat_piirkond" );
		elemToBind2 = document.getElementById ( "stat_partei" );
        elemToBind.onchange = function () { getSel (); }
		elemToBind2.onchange = function () { getSel (); }
    }
function getSel(){
		document.body.style.cursor = 'wait';
		setTimeout(function(){document.body.style.cursor = 'default';}, 1000);
		var val = elemToBind.value;
		var val2 = elemToBind2.value;
		console.log('val1:' + val + ', val2:' + val2);
		if (val == "koik" && val2 == "koik"){
			data_from_json("erakond");
		}
		else if ((val == "pk1" || val == "pk2" || val == "pk3") && val2 != "pt1" && val2 != "pt2" && val2 != "pt3"){
			data_from_json("piirkond")
		}
		
		else if ((val2 == "pt1" || val2 == "pt2" || val2 == "pt3") && val != "pk1" && val != "pk2" && val != "pk3"){
			data_from_json("erakond")
		}
		else{
			data_from_json("isik")
		}
    }

function data_from_json(choice){
	var json_path = 'data/'
	if (choice == "erakond") json_path += 'findCandidatesByParty.json';
	if (choice == "piirkond") json_path += 'findCandidatesByRegion.json';
	if (choice == "isik") json_path += 'findCandidatesByPartyAndRegion.json';
	
	$.getJSON(json_path, function(data) {
		var tbl_body = "";
		var tbl_header = "";
		var h_nimi = "<td>Nimi</td>";
		var h_erakond = "<td>Erakond</td>";
		var h_piirkond = "<td>Piirkond</td>";
		var h_haali = "<td>H‰‰li</td>";
		if (choice == "isik"){
			$.each(data.candidates, function() {
				var tbl_row = "";
				$.each(this.person, function(k , v) {
					if(k == 'name'){
						var haaled = Math.floor((Math.random()*10000)+1); 
						tbl_row += "<td>"+v+"</td>";
						tbl_row += "<td>"+haaled+"</td>";
					}
				})
				
				tbl_body += "<tr>"+tbl_row+"</tr>"; 
				
			})
			tbl_header += "<tr>"+h_nimi+h_haali+"</tr>";
		}	
		if (choice == "erakond"){
			$.each(data.candidates, function() {
				var tbl_row = "";
				$.each(this.person, function(k , v) {
					if(k == 'name'){
						tbl_row += "<td>"+v+"</td>";
					}
				})
				$.each(this.region, function(k, v){
					if(k == 'name'){
						var haaled = Math.floor((Math.random()*10000)+1); 
						tbl_row += "<td>"+v+"</td>";
						tbl_row += "<td>"+haaled+"</td>";
					}
				})
				tbl_body += "<tr>"+tbl_row+"</tr>";                 
			})
			tbl_header += "<tr>"+h_nimi+h_piirkond+h_haali+"</tr>";
		}
		if (choice == "piirkond"){
			$.each(data.candidates, function() {
				var tbl_row = "";
				$.each(this.person, function(k , v) {
					if(k == 'name'){
						tbl_row += "<td>"+v+"</td>";
					}
				})
				$.each(this.party, function(k, v){
					if(k == 'name'){
						var haaled = Math.floor((Math.random()*10000)+1); 
						tbl_row += "<td>"+v+"</td>";
						tbl_row += "<td>"+haaled+"</td>";
					}
				})
				tbl_body += "<tr>"+tbl_row+"</tr>";                 
			})
			tbl_header += "<tr>"+h_nimi+h_erakond+h_haali+"</tr>";			
		}
		//$("#my_table").remove();
		//var new_table = $('<table id="my_table" class="tablesorter"></table>');
		//$(new_table).append($('<thead>'+tbl_header+'</thead>'));
		//$(new_table).append($('<tbody>'+tbl_body+'</tbody>'));
		//$(table_area).append($(new_table));
		//$("#my_table thead").html(tbl_header)
		$("#my_table tbody").html(tbl_body);
		//$("#my_table").trigger("destroy");
		$("#my_table").tablesorter( {sortList: [[0,0]]} );
		$("#my_table").trigger("update");
		//$("#my_table").trigger("appendCache");
		//sorttable.makeSortable('#my_table');
		BindEvent();
	});
}