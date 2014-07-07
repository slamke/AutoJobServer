<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<br />
<div class="row">
	<div class="col-md-1" style="text-align: center"></div>
	<div class="col-md-10" style="text-align: center">
		
		<div class="table-responsive">
			<table class="table table-hover table-striped">
			<div class="btn-group" data-toggle="buttons" style="float:right;">
			  <label class="btn btn-primary">
			    <input type="radio" name="options" id="option1">未处理</label>
			  <label class="btn btn-success">
			    <input type="radio" name="options" id="option2">已处理</label>
			</div>
				<thead>
					<tr >
						<th class="text-center">申报人</th>
						<th class="text-center">申报标题</th>
						<th style="text-align:center">详情</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<div class="row"></div>
		<div id="Pagination" class="flickr"></div>
	</div>
	<div class="col-md-1"></div>
</div>
<br />
<script type="text/javascript">  
$(function(){  
    var declsize=${declsize};
    if(declsize>0){
    	paginationTask(declsize);
    }
    $("#option1").click(function(){
    	location.reload();
    })
    $("#option2").click(function(){
    	getDone();
    	//$("button").hidden();
    })
});  
function paginationTask(declsize){
	var pageIndex=0;
	getDeclPage(pageIndex);
	//分页，PageCount是总条目数，这是必选参数，其它参数都是可选
	$("#Pagination").pagination(declsize, {
		callback : getDeclPage, //PageCallback() 为翻页调用次函数。
		prev_text : "上一页",
		next_text : "下一页 ",
		items_per_page : getPerPageNum(),
		num_edge_entries : 2, //两侧首尾分页条目数
		num_display_entries : 6, //连续分页主体部分分页条目数
		current_page : pageIndex, //当前页索引
	});
}

function getDeclPage(index) {
	$.ajax({
		url : "/autojobserver/urgency/list/undone/page", //提交到一般处理程序请求数据   
		type : "POST",
		data : {
			page : index,
			num : getPerPageNum(),
		},
		 //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                   
		success : function(data) {
			console.log(data);
			showDeclTable(data);
			//showTaskTable(data);
		},
	});
}

function showDeclTable(data){
	if(data.declartioninfos.length!=0){
		$("tbody").empty();
		for(var j=0;j<data.declartioninfos.length;j++){
			$("tbody").append('<tr>'+
		    		'<td id="staff'+j+'">'+data.declartioninfos[j].staffName+'</td>'+
		    		'<td id="taskstaff'+j+'">'+data.declartioninfos[j].title+'</td>'+
		    		'<td id="department'+j+'"><button class="btn btn-primary"  onclick="showUrgency('+j+')">查看详情</button></td>'+
		    		'<input type="hidden" id="id'+j+'" value="'+data.declartioninfos[j].id+'"/>'+
	    		'</tr>');
		}
	} 
}
function showUrgency(index){
	
	var id=$("#id"+index).val();
	location.href="/autojobserver/urgency/"+id+"/detail";
}

function getDone(){
	$.ajax({
		url : "/autojobserver/urgency/list/done", //提交到一般处理程序请求数据   
		type : "GET",
		data : {
		},
		 //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                   
		success : function(data) {
			var declsize=data.declsize;
			if(declsize>0){
				paginationDonePage(declsize);
			}
		},
	});
}

function getDeclDonePage(index) {
	$.ajax({
		url : "/autojobserver/urgency/list/done/page", //提交到一般处理程序请求数据   
		type : "POST",
		data : {
			page : index,
			num : getPerPageNum(),
		},
		 //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                   
		success : function(data) {
			console.log(data);
			showDeclDoneTable(data);
			//showTaskTable(data);
		},
	});
}

function paginationDonePage(declsize){
	var pageIndex=0;
	getDeclDonePage(pageIndex);
	//分页，PageCount是总条目数，这是必选参数，其它参数都是可选
	$("#Pagination").pagination(declsize, {
		callback : getDeclDonePage, //PageCallback() 为翻页调用次函数。
		prev_text : "上一页",
		next_text : "下一页 ",
		items_per_page : getPerPageNum(),
		num_edge_entries : 2, //两侧首尾分页条目数
		num_display_entries : 6, //连续分页主体部分分页条目数
		current_page : pageIndex, //当前页索引
	});
}

function showDeclDoneTable(data){
	if(data.declartioninfos.length!=0){
		$("tbody").empty();
		for(var j=0;j<data.declartioninfos.length;j++){
			$("tbody").append('<tr>'+
		    		'<td id="staff'+j+'">'+data.declartioninfos[j].staffName+'</td>'+
		    		'<td id="taskstaff'+j+'">'+data.declartioninfos[j].title+'</td>'+
		    		'<td id="department'+j+'"><button class="btn btn-primary"  onclick="showUrgency('+j+')">查看详情</button></td>'+
		    		'<input type="hidden" id="id'+j+'" value="'+data.declartioninfos[j].id+'"/>'+
	    		'</tr>');
		}
	} 
}
</script>

