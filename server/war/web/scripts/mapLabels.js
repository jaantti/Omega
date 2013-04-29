
function addLabel(labelText, position, map, erakond_id){
	var myOptions = {
	         content: labelText
	        ,alignBottom: true
	        ,boxClass: "mapLabel mapLabel-" + erakond_id
	        /*,boxStyle: {
	           //border: "1px solid black",
	           textAlign: "center"
	          ,fontSize: "10pt"
	          ,width: "50px"
	         }*/
	        ,disableAutoPan: true
	        ,pixelOffset: new google.maps.Size(-25, 10)
	        ,position: position//new google.maps.LatLng(58.378139, 26.721642)
	        ,closeBoxURL: ""
	        ,isHidden: false
	        ,pane: "mapPane"
	        ,enableEventPropagation: true
	};

	var ibLabel = new InfoBox(myOptions);
	ibLabel.open(map);
}