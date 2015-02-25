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


  var request = sdcard.addNamed(file, d.yyyymmdd()+".txt");

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