///**
// * 
// */
//
//$(function(){
//	$(".form-group").change(function(){
////		if($("#password").val()!=$("#confirmationPassword").val()){
////			$("#passMessage").show();
////			$("#passMessage").text("確認用パスワードが一致していません");
////			$("#submit").prop("disabled", true);
////		}else{
////			$("#passMessage").hide();
////			$("#submit").prop("disabled", false);
////		}
//		
//		var hostUrl="http://localhost:8080/checkPass";
//		var pass=$("#password").val();
//		var confPass=$("#confirmationPassword").val();
//		
//		$.ajax({
//			url:hostUrl,
//			type:"post",
//			dataType:"json",
//			data:{
//				pass:pass,
//				confPass:confPass
//			},
//			async:true
//			
//		}).done(function(data){
//			console.log(data);
//			console.dir(JSON.stringify(data));
//			$("#passMessage").html(data.passMessage);
//			
//			
//			
//		}).fail(function(XMLHttpRequest,textStatus,errorThrown){
//			alert("エラー");
//			console.log("XMLHttpRequest : "+XMLHttpRequest.status);
//			console.log("textStatus: "+textStatus);
//			console.log("errorThrown: "+errorThrown.message);
//		})
//		
//	})
//	
//	
//})
