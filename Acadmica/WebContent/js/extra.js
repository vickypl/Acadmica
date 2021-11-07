$(document).ready(function () {
	
	$('#mytable').DataTable();
	
	$('#wiki').hide();
	/*$('#wiki').hide(1000, function() {
		console.log('hidden');
	}); */    
	$('#wiki').show(1000, function() {
		console.log('show');
	});
});