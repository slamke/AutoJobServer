<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<br />
<div class="row">
	<div class="col-md-1" style="text-align: center"></div>
	<div class="col-md-10" style="text-align: center">
		<form class="form-horizontal" role="form" onsubmit="return false;">
			<div class="row">
				<div class="form-group col-sm-6">
					<label for="itemname" class="col-sm-4 control-label">项目名称</label>
					<div class="col-sm-8">
						<select class="form-control" id="itemname" name="itemname">
							<option></option>
						</select>
					</div>
				</div>
				<div class="form-group col-sm-6">
					<label for="taskstaff" class="col-sm-4 control-label">任务负责人</label>
					<div class="col-sm-8">
						<select class="form-control" id="taskstaff" name="taskstaff">
							<option></option>
						</select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-6">
					<label for="inputPassword3" class="col-sm-4 control-label">填报日期</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="date1" placeholder="">
					</div>
				</div>
				<div class="form-group col-sm-6">
					<label for="inputPassword3" class="col-sm-4 control-label">至</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="date2" placeholder="">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-6">
					<label for="inputPassword3" class="col-sm-4 control-label">记录类型</label>
					<div class="col-sm-8">
						<select id="recordtype" class="form-control">
							<option value=""></option>
							<option value="上班签到">上班签到</option>
							<option value="下班填单">下班填单</option>
						</select>
					</div>
				</div>
				<div class="form-group col-sm-6">
					<label for="itemnum" class="col-sm-4 control-label">项目编号</label>
					<div class="col-sm-8">
						<select class="form-control" id="itemnum" name="itemnum">
							<option></option>
						</select>
					</div>
				</div>
			</div>
			<div class="row text-center">
				<button class="btn btn-primary"
					onclick="search(<%=request.getContextPath()%>/)">查询</button>
			</div>
			<br />
		</form>
		<div id="table" class="table-responsive">
			<table class="table table-bordered table-hover table-striped">
				<h3>泰伦智能工作管理系统</h3>
				<thead>
					<tr>
						<th>项目工地简称</th>
						<th>用户名</th>
						<th>填报日期</th>
						<th>填报时间</th>
						<th>记录类型</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<div class="row" id="hiddenDiv"></div>
		<div id="Pagination" class="flickr"></div>
	</div>
	<div class="col-md-1"></div>
</div>
<br />
<script>
					
	                $(document).ready(function() { 
	                	getAllStaff();
	                	getAllItemName();
	                	getAllItemNum();
	            		$("#table").hide();
	                	//用于日历
	               		var selectDate;
	               		var myDate = new Date();
	               		var today=myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();
	                	
	                    $('#date1').datepicker({//添加日期选择功能  
	                        numberOfMonths:1,//显示几个月  
	                        showButtonPanel:true,//是否显示按钮面板  
	                        dateFormat: 'yy-mm-dd',//日期格式  
	                        clearText:"清除",//清除日期的按钮名称  
	                        closeText:"关闭",//关闭选择框的按钮名称  
	                        yearSuffix: '年', //年的后缀  
	                        showMonthAfterYear:true,//是否把月放在年的后面  
	                        defaultDate:today,//默认日期  
	                       // minDate:'2011-03-05',//最小日期  
	                      //  maxDate:'2011-03-20',//最大日期  
	                        monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
	                        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
	                        dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
	                        dayNamesMin: ['日','一','二','三','四','五','六'],  
	                        onSelect: function(selectedDate) {//选择日期后执行的操作  
/* 	                        	$("#date1").val(selectedDate);
	                            selectDate=selectedDate;
 */	                       	 	 var latterDate=$("#date2").val();
								if(latterDate!=null&&latterDate!=""){
									if(compareDate(selectedDate,latterDate)==true){
										alert("前面的值不能大于后面的值");
										$("#date1").val("");
									}
								} 
	                        }  
	                    });  
	                    $('#date2').datepicker({//添加日期选择功能  
	                        numberOfMonths:1,//显示几个月  
	                        showButtonPanel:true,//是否显示按钮面板  
	                        dateFormat: 'yy-mm-dd',//日期格式  
	                        clearText:"清除",//清除日期的按钮名称  
	                        closeText:"关闭",//关闭选择框的按钮名称  
	                        yearSuffix: '年', //年的后缀  
	                        showMonthAfterYear:true,//是否把月放在年的后面  
	                        defaultDate:today,//默认日期  
	                       	minDate:$("#date1").val(),//最小日期  
	                      //  maxDate:'2011-03-20',//最大日期  
	                        monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
	                        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
	                        dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
	                        dayNamesMin: ['日','一','二','三','四','五','六'],  
	                        onSelect: function(selectedDate) {//选择日期后执行的操作  
	                       		 var formerDate=$("#date1").val();
	                        	if(formerDate!=null&&formerDate!=""){
	                        		if(compareDate(selectedDate,formerDate)==false){
	                        			alert("后面的值不能大于前面的值");
	                        			$("#date2").val("");
	                        		}
	                        	} 
	                        }  
	                    });
	                   
	                }); 
	                
	                
//用于分页

	                
</script>