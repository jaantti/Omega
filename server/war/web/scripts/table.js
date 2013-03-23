/*$.getJSON("data/findCandidatesByPart.json", function(json) {
    console.log(json); 
	alert(json);// this will show the info it in firebug console
});*/
var elemToBind;
var elemToBind2;
var new_table;
var tbody;
var tbl_header;
var t_head_status = [0,0,0];
function BindEvent(){
        elemToBind = document.getElementById ( "stat_piirkond" );
		elemToBind2 = document.getElementById ( "stat_partei" );
        elemToBind.onchange = function () {sleep(1000)}
		elemToBind2.onchange = function () {sleep(1000)}
    }

function sleep(t){
	loadingImage('loading_transparent.gif', 48, 48, '#table_area');
	document.body.style.cursor = 'wait';
	setTimeout(function(){document.body.style.cursor = 'default';}, 1000);
	setTimeout(function(){getSel()}, t);
}

function getSel(){
		
		
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
	tbody = [];
	var json_path = 'data/'
	if (choice == "erakond") json_path += 'findCandidatesByParty.json';
	if (choice == "piirkond") json_path += 'findCandidatesByRegion.json';
	if (choice == "isik") json_path += 'findCandidatesByPartyAndRegion.json';
	
	$.getJSON(json_path, function(data) {

		tbl_header = "";
		var h_nimi = '<td id="t_head_0" class ="header">Nimi</td>';
		var h_erakond = '<td id="t_head_1" class ="header">Erakond</td>';
		var h_piirkond = '<td id="t_head_1" class ="header">Piirkond</td>';
		var h_haali = '<td id="t_head_2" class ="header">H‰‰li</td>';
		if (choice == "isik"){
			var i = 0;
			$.each(data.candidates, function() {				
				$.each(this.person, function(k , v) {
					if(k == 'name'){
						var haaled = Math.floor((Math.random()*10000)+1); 
						tbody.push([]);
						tbody[i].push(v);
						tbody[i].push(haaled);
						i++;
					}
				})				
			})
			tbl_header += "<tr>"+h_nimi+h_haali+"</tr>";
			console.log(tbody);
		}	
		if (choice == "erakond"){
			var i = 0;
			$.each(data.candidates, function() {				
				$.each(this.person, function(k , v) {
					if(k == 'name'){
						tbody.push([]);
						tbody[i].push(v);
					}
				})
				$.each(this.region, function(k, v){
					if(k == 'name'){
						var haaled = Math.floor((Math.random()*10000)+1); 
						tbody[i].push(v);
						tbody[i].push(haaled);
						i++;
					}
				})
			})
			tbl_header += "<tr>"+h_nimi+h_piirkond+h_haali+"</tr>";
			console.log(tbody)
		}
		if (choice == "piirkond"){
			var i = 0;
			$.each(data.candidates, function() {				
				$.each(this.person, function(k , v) {
					if(k == 'name'){
						tbody.push([]);
						tbody[i].push(v);
					}
				})
				$.each(this.party, function(k, v){
					if(k == 'name'){
						var haaled = Math.floor((Math.random()*10000)+1); 
						tbody[i].push(v);
						tbody[i].push(haaled);
						i++;
					}
				})                
			})
			tbl_header += "<tr>"+h_nimi+h_erakond+h_haali+"</tr>";
			console.log(tbody);
		}
		t_body = tbody;
		populate_table(tbl_header, t_body);
		
		//$("#my_table thead").html(tbl_header)
		//$("#my_table tbody").html(tbl_body);
		//$("#my_table").trigger("destroy");
		//$("#my_table").tablesorter( {sortList: [[0,0]]} );
		//$("#my_table").trigger("update");
		//$("#my_table").trigger("appendCache");
		//sorttable.makeSortable('#my_table');
		BindEvent();
	});
}
function populate_table(table_header, table_body){
	$("#my_table").remove();
	new_table = $('<table id="my_table" class="tablesorter"></table>');
	$(new_table).append($('<thead>'+tbl_header+'</thead>'));
		
	for (var i = 0; i < tbody.length; i++){
		var tbl_body = "";
		console.log(tbody[i]);
		for (var j = 0; j < tbody[i].length; j++){
			tbl_body += '<td>'+tbody[i][j]+'</td>';
		}
		$(new_table).append($('<tbody>'+tbl_body+'</tbody>'));
	}
			
	$(table_area).append($(new_table));
	$("#t_head_0").click(function() {
		if (t_head_status[0] == 0){
			sort_table(0,0);
			t_head_status[0] = 1;
			//$("#t_head_0").removeClass("headerSortDown");
			$("#t_head_0").addClass("headerSortUp");

		}
		else if (t_head_status[0] == 1){
			sort_table(0,1);
			t_head_status[0] = 0;
			//$("#t_head_0").removeClass("headerSortUp");
			$("#t_head_0").addClass("headerSortDown");
		}
		
	});
	$("#t_head_1").click(function() {
		if (t_head_status[1] == 0){
			sort_table(1,0);
			t_head_status[1] = 1;
			//$("#t_head_1").removeClass("headerSortDown");
			$("#t_head_1").addClass("headerSortUp");
		}
		else if (t_head_status[1] == 1){
			sort_table(1,1);
			t_head_status[1] = 0;
			//$("#t_head_1").removeClass("headerSortUp");
			$("#t_head_1").addClass("headerSortDown");
		}
	});
	$("#t_head_2").click(function() {
		if (t_head_status[2] == 0){
			sort_table(2,0);
			t_head_status[2] = 1;
			//$("#t_head_2").removeClass("headerSortDown");
			$("#t_head_2").addClass("headerSortUp");
		}
		else if (t_head_status[2] == 1){
			sort_table(2,1);
			t_head_status[2] = 0;
			//$("#t_head_2").removeClass("headerSortUp");
			$("#t_head_2").addClass("headerSortDown");
		}
	});
	
}
function sort_table(column, direction){
	if (t_body[0].length == 2 && column == 2) column = 1;
	console.log(t_body[0].length);
	for (var j = 0; j < t_body.length; j++){
		for(var i = 0; i < t_body.length-1; i++){
			if (direction == 0){
				if (t_body[i][column] > t_body[i+1][column]){
					var abi = t_body[i+1];
					t_body[i+1] = t_body[i];
					t_body[i] = abi;
					}
			}
			if (direction == 1){
				if (t_body[i][column] < t_body[i+1][column]){
					var abi = t_body[i+1];
					t_body[i+1] = t_body[i];
					t_body[i] = abi;
					}		
			}
		}
	}
		
	populate_table(tbl_header, t_body);
	
	
}
function loadingImage(path, width, height, target) {
    $("#my_table").remove();
	$('<img class="loading_img" src="'+ path +'">').load(function() {
		$(this).width(width).height(height).appendTo(target);
    });
	setTimeout(function(){
		console.log("timeout");
		$(".loading_img").remove();
	}, 1000);
	
}