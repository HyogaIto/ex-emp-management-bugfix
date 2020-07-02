/**
 * 
 */


 $( function() {
      $("#empName").on("keyup",function() {
		$.ajax({
			url:"http://localhost:8080/employee/auteComplete",
			type:"POST",
			async:true
		}).done(function(data){
			console.dir(JSON.stringify(data));
			  $( "#empName" ).autocomplete({
				  source: data.empName
			  })
		}).fail(function(XMLHttpRecest,testStatus,errorThrown){
			alert("正しい結果を得られませんでした");
			console.log("XMLHttpRecest:"+XMLHttpRecest);
			console.log("testStatus:"+testStatus);
			console.log("errorThrown:"+errorThrown);
		})
	
    
    });
  });