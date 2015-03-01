
//storage file list 
function listContents(storagename) {
  console.log("app lunch");
	//Clear up the list first
	$('#results').html("");
	var files = navigator.getDeviceStorage(storagename);
	var cursor = files.enumerate();
		cursor.onsuccess = function () {
			var file = this.result;
			if (file != null) {
				//file name add checkbox
				$("<label><p><input type='checkbox' class='sec' name='file[]' value='" + file.name + "'/>" + file.name + "</p></label>").appendTo('#results');
				this.done = false;
			}
			else {
				//save button 
				$('<p><input type="button" class="btn btn-primary" value="Kaydet" /></p>').appendTo('#results');
				this.done = true;
			}
			if (!this.done) {
				this.continue();
			}
		}
}

//button click event
$('input.btn').live('click', function() {
  console.log("buton click");
	var fileNames = [];
	$('input.sec:checked').each(function() {
		alert($(this).val())
		//creating files in sdcard
		$('#results').html("start")
		fileNames.push($(this).val());
	});
	
	var fileString = fileNames.join("\n");
	var file = new Blob([fileString], {type: "text/plain"});
	//files named with the time information
	var sdcard = navigator.getDeviceStorage("sdcard");
	var request = sdcard.addNamed(file, d.yyyymmdd()+".txt");
	//var request = sdcard.addNamed(file, "2015.02.25-23.41.03.txt");
	//is printed on the selected files in the file
	request.onsuccess = function () {  
		// for(i=0;i<$('input.sec:checked').length;i++){
		// var temp = new Array();
		//temp = $(this).split(' ');
		var name = $(this).result;
		// var name2=name.toString();
		//var name=$('input.sec:checked').val()
		//.toString();
		$('#results').html("yazıldı");
		console.log('File "' + name + '" successfully wrote on the sdcard storage area');
		//alert(name);   
	}
	
	// An error typically occur if a file with the same name already exist
	request.onerror = function () {
		$('#results').html("hata")
		console.warn('Unable to write the file: ' + this.error);
	}

	$('#results').html("text")
}); 

//listed files stored on sdcard
$(document).ready(function(){
	listContents("sdcard");
});

//Retrieving current date time information
Date.prototype.yyyymmdd = function() {
	var yyyy = this.getFullYear().toString();
	var mm = (this.getMonth()+1).toString(); // getMonth() is zero-based
	var dd  = this.getDate().toString();
	var h = this.getHours().toString();
	var m = this.getMinutes().toString();
	var s = this.getSeconds().toString();

	return yyyy + "." + (mm[1]?mm:"0"+mm[0]) + "." + (dd[1]?dd:"0"+dd[0]) + "-" + (h[1]?h:"0"+h[0]) +"." + (m[1]?m:"0"+m[0]) +"." + (s[1]?s:"0"+s[0]); // padding
};
d = new Date();
alert( d.yyyymmdd() );