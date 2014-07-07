/**
 * authos:leo-sjtu
 */
function addTask(rootPath){
	location.href=rootPath+"task/2addTask";
}

function saveTask(rootPath){
	$.ajaxFileUpload({
		url : rootPath+"task/addTask",
		data:{
			addStaff:$.cookie('username'),
			tasknum: $("#tasknum").val(),
			taskstaff:$("#taskstaff").val(),
			itemname:$("#itemname").val(),
			},
		secureuri : false,
		fileElementId : 'file', // 文件选择框的id属性
		dataType : 'xml', // 服务器返回的格式，可以是json
		success : function(data, status) // 相当于java中try语句块的用法
		{
			if(data.hasOwnProperty("error")){
				alert(data.error);
			}else{
				alert(data.success);
			}
		},
		error : function(data, status, e) // 相当于java中catch语句块的用法
		{
			alert("通信错误");
		}
	});
}

function searchTask(rootPath){
	$.ajax({
		url:rootPath+"task/searchTask",
		type: "POST",
		data:{
			tasknum: $("#tasknum").val(),
			taskstaff:$("#taskstaff").val(),
			itemname:$("#itemname").val(),
			state:$("#state").val(),
		},
		success:function(data){
			$("#taskTable").show();
			if (data.taskSize > 0) {
				paginationTask(data.taskSize);
			}else{
				alert("没有找到该任务单");
			}
		},
		error:function(){
			alert("通信错误");
		},
	});
}

function searchTask2(rootPath){
	$.ajax({
		url:rootPath+"task/searchTask2",
		type: "POST",
		data:{
			tasknum: $("#tasknum").val(),
			taskstaff:$("#taskstaff").val(),
			itemname:$("#itemname").val(),
		},
		success:function(data){
			$("#evTable").toggle();
			if (data.taskSize > 0) {
				showTaskTable2(data);
			}else{
				alert("没有找到该任务单");
			}
		},
		error:function(){
			alert("通信错误");
		},
	});
}
function showTaskTable(data){
	console.log(data);
	$("tbody").empty();        //移除Id为Result的表格里的行，从第二行开始（这里根据页面布局不同页变）   
	for(var j=0;j<data.taskList.length;j++){
		var state="未完工";
		if(data.taskList[j].state=="true"){
			state="已完工";
		}
		var photo='<td>暂无照片</td>';
		//alert(data.taskList[j].photo);
		if(data.taskList[j].photo.trim()!=""&&data.taskList[j].photo!="null"){
			photo='<td>'+
			'<a href="'+data.taskList[j].photo+'" class="ceraBox"><img src="'+data.taskList[j].photo+'"  style="height: 20px; width: 20px" />查看详情</a>'+
    		'</td>';
		}
		/*<a href="<c:url value="${photo.path}"/>" class="ceraBox" title="${photo.description}"> 
		<img src="<c:url value="${photo.path}"/>" style="height: 200px; width: 200px" />
	</a>*/
		$("tbody").append(
				'<tr>'+
		    		'<td id="tasknum'+j+'">'+data.taskList[j].tasknum+'</td>'+
		    		'<td id="itemname'+j+'">'+data.taskList[j].itemname+'</td>'+
		    		'<td id="chgstaff'+j+'">'+data.taskList[j].chgstaff+'</td>'+
		    		photo+
		    		'<td id="state'+j+'">'+state+'</td>'+
		    		'<td><div class="btn-group">'+
						'<button id="chgTaskInfoBtn" class="btn btn-primary" data-toggle="modal" onclick="chgTask('+j+')" >修改</button>'+
						'<button id="rmTaskInfoBtn" class="btn btn-primary" onclick="deleteTask('+j+')">删除</button>'+
					'</div></td>'+
	    		'</tr>');
		 window.addEvent('domready', function(){
				$$('a.ceraBox').cerabox();
			});  
	 }
}

function showTaskTable2(data){
	$("tbody").empty();        //移除Id为Result的表格里的行，从第二行开始（这里根据页面布局不同页变）   
	for(var j=0;j<data.taskList.length;j++){
		var score="";
		if(data.taskList[j].score==="unevaluated"){
			score='<button class="btn btn-primary"  onclick="evalTask('+j+')">评价</button>';
		}else{
			score=data.taskList[j].score;
		}
		$("tbody").append(
				'<tr>'+
		    		'<td id="tasknum'+j+'">'+data.taskList[j].tasknum+'</td>'+
		    		'<td id="taskstaff'+j+'">'+data.taskList[j].chgstaff+'</td>'+
		    		'<td id="department'+j+'">'+data.taskList[j].department+'</td>'+
		    		'<td id="itemname'+j+'">'+data.taskList[j].itemname+'</td>'+
		    		'<td>'+score+'</td>'+
	    		'</tr>');
	 }
}

function paginationTask(taskSize){
		var pageIndex=0;
		getTaskPage(pageIndex);
		//分页，PageCount是总条目数，这是必选参数，其它参数都是可选
		$("#Pagination").pagination(taskSize, {
			callback : getTaskPage, //PageCallback() 为翻页调用次函数。
			prev_text : "上一页",
			next_text : "下一页 ",
			items_per_page : getPerPageNum(),
			num_edge_entries : 2, //两侧首尾分页条目数
			num_display_entries : 6, //连续分页主体部分分页条目数
			current_page : pageIndex, //当前页索引
		});
}

//请求数据   
function getTaskPage(index) {
	$.ajax({
		url : "/autojobserver/task/paginationTask", //提交到一般处理程序请求数据   
		type : "POST",
		data : {
			tasknum : $("#tasknum").val(),
			taskstaff : $("#taskstaff").val(),
			itemname : $("#itemname").val(),
			state : $("#state").val(),
			pos : index,
			num : getPerPageNum(),
		},
		 //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                   
		success : function(data) {
			showTaskTable(data);
		},
	});
}

function saveTaskChg(rootPath){
	$.ajax({
		url:rootPath+"task/addTask",
		type: "POST",
		data:{
			addStaff:$.cookie('username'),
			tasknum: $("#tasknum").val(),
			taskstaff:$("#taskstaff").val(),
			itemname:$("#itemname").val(),
			uploadfile:"null",
		},
		success:function(data){
			if(data.hasOwnProperty("error")){
				alert(data.error);
			}else{
				alert(data.success);
			}
		},
		error:function(){
			alert("通信错误");
		},
	});
}

function showPhoto(path){
	alert('in');
	location.href="/path";
}

function getAllStaff(){
	$.ajax({
		url:"getAllUser",
		type: "GET",
		success:function(data){
			if(data.length!=0){
				for(var len=0;len<data.length; len++){
					$("#taskstaff").append("<option>"+data[len]+"</option>");
				}
			}
		},
		error:function(){
			alert("通信错误");
		},
	});
}
function getAllItemName(){
	$.ajax({
		url:"getAllItemName",
		type: "GET",
		success:function(data){
			if(data.length!=0){
				for(var len=0;len<data.length; len++){
					$("#itemname").append('<option>'+data[len]+'</option>');
				}
			}
		},
		error:function(){
			alert("通信错误");
		},
	});
}

