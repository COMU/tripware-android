function listContents(storagename) {

//Clear up the list first
 $('#results').html("");
 var files = navigator.getDeviceStorage(storagename);

 var cursor = files.enumerate();

  cursor.onsuccess = function () {
//alert("Got something");
   var file = this.result;
   if (file != null) {
     $("<p><input type='checkbox' class='sec' name='file[]' value='" + file.name + "'/>" + file.name + "</p>").appendTo('#results');
     this.done = false;
    }
   else {
     $('<p><input type="button" class="btn btn-primary" value="Kaydet" /></p>').appendTo('#results');
    this.done = true;
   }

  if (!this.done) {
      this.continue();
   }
 }
}
 
 $('input.btn').live('click', function() {
 $('input.sec:checked').each(function() {
 // alert($(this).val())
   $('#results').html("start")
  var sdcard = navigator.getDeviceStorage("sdcard");
 var file   = new Blob([$(this).val()], {type: "text/plain"});


  var request = sdcard.addNamed(file, "yeni4.txt");

  request.onsuccess = function () {
   var name = this.result;
   $('#results').html("yazıldı")
    console.log('File "' + name + '" successfully wrote on the sdcard storage area');
 }

  // An error typically occur if a file with the same name already exist
  request.onerror = function () {
   $('#results').html("hata")
    console.warn('Unable to write the file: ' + this.error);
 }

});

$('#results').html("text")
}); 

$(document).ready(function(){
 listContents("sdcard");

});
