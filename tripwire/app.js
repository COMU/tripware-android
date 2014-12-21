function listContents(storagename) {
	   
			//Clear up the list first
			$('#results').html("");
		    var files = navigator.getDeviceStorage(storagename);

			var cursor = files.enumerate();

			cursor.onsuccess = function () {
			  //alert("Got something");
			  var file = this.result;
			  if (file != null) {
  			  
			    $("<p><input type='checkbox' />" + file.name +  "</p>").appendTo('#results');
			    this.done = false;
			  }
			  else {
			    this.done = true;
			  }

			  if (!this.done) {
				this.continue();
			  }
			}
}
$(document).ready(function(){

	  
	      listContents('sdcard');
	
	   
});