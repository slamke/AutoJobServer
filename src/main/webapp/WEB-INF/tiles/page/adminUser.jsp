<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<div id="text_content" class="row  text-center container">
	<div class="row container">
		<h3>管理用户</h3>
	</div>
	<hr />
	<div class="row container ">
		<div class="col-md-12" style="text-align: center">
			<div class="table-responsive">
				<table class="table table-bordered table-hover table-striped">
					<thead>
						<tr>
							<th style="text-align:center">用户名
							</th>
							<th style="text-align:center">真名
							</th>
							<th style="text-align:center">电话
							</th>
							<th style="text-align:center">权限
							</th>
							<th style="text-align:center">部门
							</th>
							<th style="text-align:center">操作
							</td>
						</tr>
					</thead>
					<tbody id="addUser"></tbody>

				</table>

				<div id="Pagination" class="flickr"></div>
				<hr />

				<div class="row">
					<input type="hidden" id="rownum" value="0" />
					<!-- <button class="btn btn-lg btn-primary" onclick="addRow()">添加</button> -->
					<button class="btn btn-primary" data-toggle="modal"
						data-target="#myModal">添加新用户</button>
				</div>
			</div>
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<form class="form-horizontal" id="addUserForm" role="form"
						onsubmit="return false;">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">添加新用户</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="" username" class="col-sm-2 control-label">用户名</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="username"
											name="username" placeholder="用户名">
									</div>
								</div>
								<div class="form-group">
									<label for="" username" class="col-sm-2 control-label">初始密码</label>
									<div class="col-sm-10">
										<label for="" username" class="col-sm-2 control-label">123456</label>
									</div>
								</div>
								<div class="form-group">
									<label for="realname" class="col-sm-2 control-label">真实姓名</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="realname"
											id="realname" placeholder="真实姓名">
									</div>
								</div>
								<div class="form-group">
									<label for="department" class="col-sm-2 control-label">部门</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="department"
											id="department" placeholder="部门">
									</div>
								</div>
								<div class="form-group">
									<label for="telephone" class="col-sm-2 control-label">电话</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="telephone"
											id="telephone" placeholder="电话">
									</div>
								</div>
								<div class="form-group">
									<label for="authority" class="col-sm-2 control-label">管理员</label>
									<div class="col-sm-10 text-left">
										<input id="authority" name="authority" type="checkbox">
									</div>
								</div>

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
								<button type="submit" class="btn btn-primary"
									onclick="validateSave(<%=request.getContextPath()%>/)">保存</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</form>
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- 
				<button class="btn btn-lg btn-primary" onclick="removeRow()">删除</button> -->
		</div>
	</div>

	<!--   <button type="button" id="AssignPCM" class="btn btn-primary " onclick="assignpcm()">Assign PC-Member</button>-->

	<br /> <br />
</div>
</div>
<div class="col-md-1"></div>
</div>
<script>
var staffNum=${staffNum};
var pageIndex = 0;     //页面索引初始值   
var pageSize = 3;     //每页显示条数初始化，修改显示条数，修改这里即可   
if(staffNum>0){
	InitTable(0);    //Load事件，初始化表格数据，页面索引为0（第一页）
		                       //分页，PageCount是总条目数，这是必选参数，其它参数都是可选
	$("#Pagination").pagination(staffNum, {
		callback: InitTable,  //PageCallback() 为翻页调用次函数。
		prev_text: "上一页",
		next_text: "下一页 ",
		items_per_page:pageSize,
		num_edge_entries: 2,       //两侧首尾分页条目数
		num_display_entries: 6,    //连续分页主体部分分页条目数
		current_page: pageIndex,   //当前页索引
	});
		                       //翻页调用   
	function PageCallback(index, jq) {             
		InitTable(index);  
	}
	function deleteUser(username){
		var r=confirm("确定要删除该用户吗？")
		if (r==true){
		    $.ajax({
		        url : "admin/deleteuser?username="+username,
		        type : "GET",
		        success : function(data) {
		        	alert("删除成功");
		        	location.href="adminUser";
		        },
		        error : function(data) {        
		            alert("网络错误");
		        }
		    }); 
		}
	}
	
                    //请求数据   
	function InitTable(index) {                                  
		$.ajax({   
		    url: '<%=request.getContextPath()%>/adminUser/list',      //提交到一般处理程序请求数据   
		    type: "POST",  
		    data: {
		          "pageIndex":index,
		          "pageSize":pageSize
		    },        //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                   
		    success: function(data) {
		          $("tbody").empty();        //移除Id为Result的表格里的行，从第二行开始（这里根据页面布局不同页变）   
		          for(var j=0;j<data.staffSubList.length;j++){
		        		var auth="用户";
		        		if (data.staffSubList[j].auth=="1"){
		        			auth="管理员";
		        		}
		          		$("tbody").append(
		               '<tr>'+
			            '<td id="location'+j+'">'+data.staffSubList[j].username+'</td>'+
			            '<td id="time'+j+'">'+data.staffSubList[j].realname+'</td>'+
			            '<td id="realname'+j+'">'+data.staffSubList[j].telephone+'</td>'+
			            '<td id="realname'+j+'">'+auth+'</td>'+
			            '<td id="department'+j+'">'+data.staffSubList[j].department+'</td>'+
			            '<td id="operation'+j+'"><div class="btn-group"><a class="btn btn-primary" href="<%=request.getContextPath()%>/admin/changeInfo?username='+data.staffSubList[j].username+'">修改信息</a>'+
			            		'<a class="btn btn-primary" onclick="deleteUser(\''+data.staffSubList[j].username+'\')">删除用户</a></div></td>'+
		            	'</tr>');
		          };
		    },
	    }); 
   }
}
                </script>