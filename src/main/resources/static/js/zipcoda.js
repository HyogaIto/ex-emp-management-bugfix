/**
 * 
 */


$(function(){
	$("#zipCode").on("keyup",function(){
		$.ajax({
			url: 'https://zipcoda.net/api',
			dataType: 'jsonp',
			data: {
				zipcode: $("#zipCode").val()
			},
			async:true
		}).done(function(data){
			console.dir(JSON.stringify(data));
			$("#address").val(data.items[0].address);
		}).fail(function(XMLHttpRequest,textStatus,errorThrown){
			console.log("XMLHttpRequest : "+XMLHttpRequest.status);
			console.log("textStatus: "+textStatus);
			console.log("errorThrown: "+errorThrown.message);
		})
	})
})