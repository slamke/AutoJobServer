/**
 * author:leo-sjtu
 */
function getPerPageNum(){
	return 10;
}

function pagereload(){
    window.location.reload(); 
}

function getAllItemNum(){
	$.ajax({
		url:"getAllItemNum",
		type: "GET",
		success:function(data){
			if(data.length!=0){
				for(var len=0;len<data.length; len++){
					$("#itemnum").append("<option>"+data[len]+"</option>");
				}
			}
		},
		error:function(){
			alert("通信错误");
		},
	});
}