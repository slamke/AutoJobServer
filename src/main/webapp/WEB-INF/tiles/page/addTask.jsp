<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">
	$(function() {
		var success = "${success}";
		if (success != "") {
			$("#myModal").modal();
			setTimeout(function() {
				location.href = "task/addTask";
			}, 1500);
		}
		var error = "${error}";
		if (error != "") {
			$("#myModal").modal();
			/* setTimeout(function() {
				location.href = "task/addTask";
			},1500); */
		}
		$("#addstaff").val($.cookie('username'));
		getAllStaff();
		getAllItemName();
		
		
	});
	var maxsize = 2*1024*1024;//2M
	  var errMsg = "上传的附件文件不能超过2M！！！";
	  var tipMsg = "您的浏览器暂不支持计算上传文件的大小，确保上传文件不要超过2M，建议使用IE、FireFox、Chrome浏览器。";
	  var  browserCfg = {};
	  var ua = window.navigator.userAgent;
	  if (ua.indexOf("MSIE")>=1){
	   browserCfg.ie = true;
	  }else if(ua.indexOf("Firefox")>=1){
	   browserCfg.firefox = true;
	  }else if(ua.indexOf("Chrome")>=1){
	   browserCfg.chrome = true;
	  }
	  function checkfile(){
		   try{
		     var obj_file = document.getElementById("uploadfile");
		     if(obj_file.value==""){
		      alert("请先选择上传文件");
		      return false;
		     }
		     var filesize = 0;
		     if(browserCfg.firefox || browserCfg.chrome ){
		      filesize = obj_file.files[0].size;
		     }else if(browserCfg.ie){
		      var obj_img = document.getElementById('tempimg');
		      obj_img.dynsrc=obj_file.value;
		      filesize = obj_img.fileSize;
		     }else{
		      alert(tipMsg);
		       return false;
		     }
		     if(filesize==-1){
		      alert(tipMsg);
		      return false;
		     }else if(filesize>maxsize){
		      alert(errMsg);
		      return false;
		    }else{
		     //alert("文件大小符合要求");
		      return true;
		    }
		   }catch(e){
		    alert(e);
		   }
		  }
	function validateTask() {
		var taskNum = $("#tasknum").val().trim();
		var taskstaff = $("#taskstaff").val().trim();
		var itemname = $("#itemname").val().trim();
		var file = $("#uploadfile").val().trim();
		if (taskNum == "" || taskNum == null) {
			return false;
		}
		if (taskstaff == "" || taskstaff == null) {
			return false;
		}
		if (itemname == "" || itemname == null) {
			return false;
		}
		if (file == "" || file == null) {
			return false;
		}
		return checkfile();
		//return true;
	}
</script>
<br />
<div class="row">
	<div class="col-md-1" style="text-align: center"></div>
	<div class="col-md-10" style="text-align: center">
		<form id="addTaskForm" class="form-horizontal" role="form"
			action="task/addTask" method="post" enctype="multipart/form-data"
			onsubmit="return validateTask()">
			<div class="row">
				<div class="form-group col-sm-6">
					<label for="tasknum" class="col-sm-4 control-label">任务单号</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="tasknum"
							name="tasknum" placeholder="任务单号">
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
				<div class="form-group col-sm-6">
					<label for="itemname" class="col-sm-4 control-label">项目名称</label>
					<div class="col-sm-8">
						<select class="form-control" id="itemname" name="itemname">
							<option></option>
						</select>
					</div>
				</div>
				<div class="form-group col-sm-6">
					<label for="file" class="col-sm-4 control-label">上传照片</label>
					<img id="tempimg" dynsrc="" src="" style="display:none" />
					<div class="col-sm-6">
						<input type="file" name="file" class="form-control"
							id="uploadfile" placeholder="上传照片">
					</div>
					<label for="file" class="col-sm-2 control-label text-center text-warning"><4.5Mb</label>
				</div>
				<div class="form-group">
					<input type="hidden" id="addstaff" name="addStaff" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-4">
					<label for="starttime" class="col-sm-6 control-label">任务工期</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="starttime" name="starttime" placeholder="">
					</div>
				</div>
				<div class="form-group col-sm-4">
					<label for="deadline" class="col-sm-4 control-label">至</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="deadline" name="deadline" placeholder="">
					</div>
				</div>
				<div class="form-group col-sm-4">
					<label for="days" class="col-sm-4 control-label">总天数</label>
					<div class="col-sm-6">
						<label for="days" class="col-sm-4 control-label" id="days"></label>
					</div>
				</div>
			</div>
			<br />
			<div class="row">
				<input type="submit" class="btn btn-primary">
				<!-- <button class="btn btn-primary" onclick="goBack()">返回</button> -->
			</div>
		</form>
		<br />

	</div>
	<div class="col-md-1"></div>
</div>
<br />
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<c:if test="${success!=null||success!=''}">
					<c:out value="${success}" />
				</c:if>
				<c:if test="${error!=null||error!=''}">
					<c:out value="${error}" />
				</c:if>
				<%-- <c:out value="${success}" /> --%>
			</div>
		</div>
	</div>
</div>
<script>
	function goBack() {
		history.go(-1);
	}
	//用于日历
		var selectDate;
		var myDate = new Date();
		var today=myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();
    $(document).ready(function() {  
        $('#starttime').datepicker({//添加日期选择功能  
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
*/	                       	 	 var latterDate=$("#deadline").val();
				if(latterDate!=null&&latterDate!=""){
					if(compareDate(selectedDate,latterDate)==true){
						alert("前面的值不能大于后面的值");
						$("#starttime").val("");
					}
				} 
            }  
        });  
        $('#deadline').datepicker({//添加日期选择功能  
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
           		var formerDate=$("#starttime").val();
            	if(formerDate!=null&&formerDate!=""){
            		if(compareDate(selectedDate,formerDate)==false){
            			alert("后面的值不能大于前面的值");
            			$("#deadline").val("");
            		}
            	} 
            	
            	var s1=$("#starttime").val();
            	var s2=selectedDate;
            	s1 = s1.replace(/-/g, "/"); 
            	s2 = s2.replace(/-/g, "/"); 
            	s1 = new Date(s1);
            	s2 = new Date(s2);

            	var days= s2.getTime() - s1.getTime(); 
            	var time = parseInt(days / (1000 * 60 * 60 * 24));

            	$("#days").text(time);
            }  
        });
       
    }); 
    
</script>
