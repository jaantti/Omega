function cursor_wait() {
document.body.style.cursor = 'wait';
}

function cursor_clear() {
document.body.style.cursor = 'default';
}
// Does some arduous calculation
function calc() {
	cursor_wait();
	var dummy = 0;

	for (var i=0; i<10000;i++) {
		for (var z=0; i<10000;i++) {
			dummy = dummy + z + i;
		}
	}
	cursor_clear();
}