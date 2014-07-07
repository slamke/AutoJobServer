function mlogin(rootPath){
	$.ajax({
		url:rootPath+"login/submit",
		type: "POST",
		data:{
			username:$("#username").val(),
			password:$("#password").val()
		},
		success:function(data){
			var checkin=data.checkin;
			if(checkin==true){
				var username=data.username;
				var admin=data.admin;
				$.cookie("username",username,{path:'/'});
				$.cookie("admin",admin,{path:'/'});
				$.cookie("login",checkin,{path:'/'});
				location.href=rootPath+"home";
			}
			else{
				$("#errmess").text("用户名或密码错误");
			}
		},
		error:function(){
			alert("通信错误");
		}
	});
}

function validateSave(rootPath){
	$(document).ready(function() {
		$("#addUserForm").validate({
			 	errorClass: "myerror",
				 submitHandler: function() {
				    save(rootPath);
				  },	
			   rules: {
				 username: "required",
				 realname: "required",
				 telephone: "required",
				 department: "required",
				 },
			  messages: {
				  username:"请填写用户名",
				  realname:"请填写真名",
				  telephone:"请填写电话号码",
				  department:"请填写部门名称",
			  }
		    });
		});
}
function save(rootPath){
	//alert("insave");
	var auth=0;
	if($('#authority').is(':checked')){
		auth=1;
		//alert("in");
	}
	$.ajax({
        url : "/autojobserver/addUser",
        type : "POST",
        data:{
        	username:$("#username").val(),
        	realname:$("#realname").val(),
        	tel:$("#telephone").val(),
        	department:$("#department").val(),
        	auth:auth,
        },
        success : function(data) {
            if(data.success){
	        	//alert("ok");
	        	location.href=rootPath+"adminUser";
            }
            else{
            	alert(data.error);
            }
        },
        error : function(data) {        
            alert("connection failed! please try later");
        }
    }); 
}

function addRow(){
	var i=$("#rownum").val();
	
	$("#addUser").append('<tr  id="TR-'+i+'"></tr>');
	 	$("#TR-" + i).append('<td  id="TD-'+ i + '-'+ 'username' + '"><input id="username'+i+'" type="text"></td>');
	    $("#TR-" + i).append('<td  id="TD-'+ i + '-'+ 'password'+ '"><input id="password'+i+'" type="text"></td>');
	    $("#TR-" + i).append('<td  id="TD-'+ i + '-'+ 'realname'+ '"><input id="realname'+i+'" type="text"></td>');
	    $("#TR-" + i).append('<td  id="TD-'+ i + '-'+ 'telephone'+ '"><input id="telephone'+i+'" type="text"></td>');
	    $("#TR-" + i).append('<td  id="TD-'+ i + '-'+ 'authority'+ '"><input id="authority'+i+'" type="text"></td>');
	    $("#TR-" + i).append('<td  id="TD-'+ i + '-'+ 'save'+ '"><button id="save'+i+'" class="btn btn-primary" onclick="save()">保存</button></td>');
	i++;
	$("#rownum").val(i);
}

function removeRow(rootPath){
	var j=0;
	var stafflist=new Array();
	$('input:checkbox[name="select-checkbox"]:checked').each(function(){
		var i=$(this).val();
		staff=new Object();
		staff.username=$("#username"+i).val();
		staff.password=$("#password"+i).val();
		staff.realname=$("#realname"+i).val();
    	staff.tel=$("#telephone"+i).val();
    	staff.auth=$("#authority"+i).val();
    	stafflist.push(staff);
		j++;
	});
	if(j==0){
		alert("Please choose a User");
	}
	$.ajax({
        url : "/autojobserver/removeUser",
        type : "POST",
        data:{
        	
        },
        success : function(data) {
            $("#TD-"+i+"save").empty();
            $("#TD-"+i+"save").append('<button id="change'+i+'" class="btn btn-primary" onclick="change() value="'+i+'"">修改</button>');
        	
        	//fillrow(i,data)  ;
        },
        error : function(data) {        
            alert("connection failed! please try later");
        }
    }); 
	$("#TR-"+i).empty();
}

function showItem(index){
	var location=$("#location"+index).html();
	var jobinfoid=$("#jobinfoid"+index).val();
	window.location.href="itemDetail?location="+location+"&jobinfoid="+jobinfoid;
}

function savechange(rootPath){
	$(document).ready(function() {
		 $("#chgform").validate({
			 errorClass: "myerror",
				 submitHandler: function() {
				    savechange2(rootPath);
				  },	
			   rules: {
				 username: "required",
				 realname: "required",
				 telephone: "required",
				 department: "required",
				 oldcode : "required",
				 newcode:"required",
				 newcode2:{
					 required:true,
					 equalTo:"#newcode"
				 }
				
			  },
			  messages: {
				  username:"请填写用户名",
				  realname:"请填写真名",
				  telephone:"请填写电话号码",
				  oldcode:"请输入旧密码",
				  department: "请输入部门信息",
				  newcode:"请输入新密码",
				  newcode2:{
					  required:"请再次输入新密码",
					  equalTo:"两次输入不相同"
				  }
					  
			  }
		    });
		});
}

function adminSavechange(rootPath,username){
	$(document).ready(function() {
		 $("#chgform").validate({
			 errorClass: "myerror",
				 submitHandler: function() {
					 adminSavechangeDo(rootPath,username);
				  },	
			   rules: {
				 username: "required",
				 realname: "required",
				 telephone: "required",
				 department: "required",
			  },
			  messages: {
				  username:"请填写用户名",
				  realname:"请填写真名",
				  telephone:"请填写电话号码",
				  department: "请输入部门信息",
			  }
		    });
		});
}

function adminSavechangeDo(rootPath,username){
	$.ajax({
        url : rootPath+"admin/changeInfo/submit",
        type : "POST",
        data:{
        	 oldun:username,
        	 username: $("#username").val(),
			 realname: $("#realname").val(),
			 tel: $("#telephone").val(),
			 department:$("#department").val()
        },
        success : function(data) {
            if(data.success==true){
            	alert("修改成功！");
            }
            else{
            	alert("系统错误");
            }
        },
        error : function(data) {        
            alert("connection failed! please try later");
        }
    }); 
}

function savechange2(rootPath){
	var oldusername=$.cookie("username");
	
	$.ajax({
        url : rootPath+"changeInfo/submit",
        type : "POST",
        data:{
        	 oldun:oldusername, 
        	 username: $("#username").val(),
			 realname: $("#realname").val(),
			 tel: $("#telephone").val(),
			 department:$("#department").val(),
			 password : $("#oldcode").val(),
			 newcode:$("#newcode").val(),
        },
        success : function(data) {
        	newUserName=data.newUserName;
            if(data.success==true){
            	alert("修改成功！");
            	$.cookie("username",newUserName,{path:"/"});
            	location.href=rootPath+"/changeInfo?username="+newUserName;
            }
            else{
            	alert("密码错误");
            }
        	
        	//fillrow(i,data)  ;
        },
        error : function(data) {        
            alert("connection failed! please try later");
        }
    }); 
}

function changePsw(rootPath){
	$(document).ready(function() {
		 $("#chgform").validate({
			 errorClass: "myerror",
				 submitHandler: function() {
				    changePsw2(rootPath);
				  },	
			   rules: {
				 oldcode : "required",
				 newcode:"required",
				 newcode2:{
					 required:true,
					 equalTo:"#newcode"
				 }
			  },
			  messages: {
				  oldcode:"请输入旧密码",
				  newcode:"请输入新密码",
				  newcode2:{
					  required:"请再次输入新密码",
					  equalTo:"两次输入不相同"
				  }
					  
			  }
		    });
		});
}
function adminChangePsw(rootPath,username){
	$(document).ready(function() {
		 $("#chgform").validate({
			 errorClass: "myerror",
				 submitHandler: function() {
					 changePswWithoutOldPWD(rootPath,username);
				  },	
			   rules: {
				 newcode:"required",
				 newcode2:{
					 required:true,
					 equalTo:"#newcode"
				 }
			  },
			  messages: {
				  newcode:"请输入新密码",
				  newcode2:{
					  required:"请再次输入新密码",
					  equalTo:"两次输入不相同"
				  }
					  
			  }
		    });
		});
}
function changePsw2(rootPath){
	var oldusername=$.cookie("username");
	$.ajax({
        url : rootPath+"/changePassword",
        type : "POST",
        data:{
        	 oldun:oldusername, 
			 password : $("#oldcode").val(),
			 newcode:$("#newcode").val(),
        },
        success : function(data) {
            if(data.success==true){
            	alert("修改成功！");
            	$.cookie("username",null,{path:"/"});
            	$.cookie("admin",null,{path:"/"});
            	$.cookie("login",null,{path:"/"});
            	location.href=rootPath+"/login";
            }
            else{
            	alert("密码错误");
            }
        	
        	//fillrow(i,data)  ;
        },
        error : function(data) {        
            alert("connection failed! please try later");
        }
    }); 
}

function changePswWithoutOldPWD(rootPath,username){
	$.ajax({
        url : rootPath+"/adminChangePassword",
        type : "POST",
        data:{
        	 oldun:username,
			 newcode:$("#newcode").val()
        },
        success : function(data) {
            if(data.success==true){
            	alert("修改成功！");
//            	$.cookie("username",null,{path:"/"});
//            	$.cookie("admin",null,{path:"/"});
//            	$.cookie("login",null,{path:"/"});
//            	location.href=rootPath+"/login";
            }
            else{
            	alert("密码错误");
            }
        	
        	//fillrow(i,data)  ;
        },
        error : function(data) {        
            alert("connection failed! please try later");
        }
    }); 
}
function addSite2(rootPath){
	$.ajax({
        url : rootPath+"addWorkSite",
        type : "POST",
        data:{
        	 num: $("#siteNum").val(),
			 sitename: $("#siteName").val(),
			 description: $("#description").val(),
        },
        success : function(data) {
            if(data.success==true){
            	location.href=rootPath+"/siteInfo";
            	/*$("#successInfo").text("添加成功！");
            	setTimeout(function(){location.href=rootPath+"/siteInfo";},2000);
            	*/
            	/*alert("ok");
            	location.href=rootPath+"/siteInfo";*/
            }
            else{
            	alert("错误！"+data.error);
            }
        },
        error : function(data) {        
            alert("connection failed! please try later");
        }
    }); 
}

function addSite(rootPath){
	$(document).ready(function() {
		 $("#addd").validate({
			 errorClass: "myerror",
				 submitHandler: function() {
					addSite2(rootPath);
				  },	
			   rules: {
				 siteNum: "required",
				 siteName: "required",
				 description: "required",
			  },
			  messages: {
				  siteNum:"请填写项目编号",
				  siteName:"请填写工地简称",
				  description:"请填写工地详细说明",
			  }
		    });
		});
}

function deleteSite(rootPath,index){
	 var r=confirm("确定删除吗？");
	 //alert($("#id"+index).val());
	 if(r==true){
			$.ajax({
		        url : rootPath+"deleteWorkSite",
		        type : "POST",
		        data:{
		        	 id: $("#id"+index).val(),
		        },
		        success : function(data) {
		        	alert("删除成功");
		        	location.href=rootPath+"siteInfo";
		        },
		        error : function(data) {        
		            alert("网络错误");
		        }
		    }); 
	 }
}

function chgSiteInfo(rootPath,i){
	location.href=rootPath+"changeSiteInfo?num="+$("#num"+i).text();
}

function saveSiteInfo(rootPath){
	$.ajax({
	    url : rootPath+"saveSiteInfo",
	    type : "POST",
	    data:{
	    	 num: $("#siteNum11").val(),
	    	 sitename:$("#siteName").val(),
	    	 description:$("#description").val(),
	    	 oldnum:$("#oldnum").val(),
	    },
	    success : function(data) {
	    	//alert("ok");
	    	//console.log(data);
	    	if(data.success){
	    		location.href=rootPath+"siteInfo";
	    	}
	    	else{
	    		var err="";
	    		for(var j=0;j<data.error.length;j++){
	    			err+=data.error[j];
	    			err+="    ";
	    		}
	    		$("#errMess").text(err);
	    	}
	    },
	    error : function(data) {        
	        alert("网络错误");
	    }
	}); 
}

function validateChangeSiteInfo(rootPath){
	$(document).ready(function() {
		 $("#chgInfoForm").validate({
			 errorClass: "myerror",
				 submitHandler: function() {
					 saveSiteInfo(rootPath);
				  },	
			   rules: {
				   siteNum: "required",
				   siteName: "required",
			  },
			  messages: {
				  siteNum:"请填写工地编号",
				  siteName:"请填写项目工地简称",
			  }
		    });
		});
}
/*
 * 多选项搜索的函数
 */
function search(rootPath){
	$.ajax({
	    url : rootPath+"search",
	    type : "POST",
	    data:{
	    	 siteName: $("#itemname").val(),
	    	 userName:$("#taskstaff").val(),
	    	 recordType:$("#recordtype").val(),
	    	 startTime:$("#date1").val(),
	    	 endTime:$("#date2").val(),
	    	 itemNum:$("#itemnum").val(),
	    },
	    
	    success : function(data) {
	    	$("#table").show();
	    	var itemNum=data.itemNum;

	    	$("#hiddenDiv").empty();
	    	$("#hiddenDiv").append('<input type="hidden" id="hidsitename" value="'+$("#itemname").val()+'">');
	    	$("#hiddenDiv").append('<input type="hidden" id="hidusername" value="'+$("#taskstaff").val()+'">');
	    	$("#hiddenDiv").append('<input type="hidden" id="hidrecordtype" value="'+$("#recordtype").val()+'">');
	    	$("#hiddenDiv").append('<input type="hidden" id="hidstartdate" value="'+$("#date1").val()+'">');
	    	$("#hiddenDiv").append('<input type="hidden" id="hidenddate" value="'+$("#date2").val()+'">');
	    	pagination(itemNum,rootPath+"search/pagination");
	    },
	   /* error : function(data) {        
	        alert("网络错误");
	    }*/
	}); 
}
/*
 * 添加假表单
 */
function showJobInfo(index){
	var siteName=$("#sitename"+index).html();
	var userName=$("#username"+index).html();
	var date=$("#date"+index).html();
	var type=$("#type"+index).html();
	var id=$("#jobinfoid"+index).val();
	var submitForm = document.createElement("form");
	document.body.appendChild(submitForm);
	submitForm.method = "POST";
	addElement(submitForm,"siteName",siteName);
	addElement(submitForm,"userName",userName);
	addElement(submitForm,"date",date);
	addElement(submitForm,"type",type);
	addElement(submitForm,"id",id);
	if(type=="上班签到"){
		 submitForm.action ="beforeWork";
	}else{
		 submitForm.action ="afterWork";
	}
    submitForm.submit();
}
/*
 * 为添加假表单的子函数
 */
function addElement(submitForm,name,value){
	var newElement = document.createElement("input");
    newElement.setAttribute("name",name);
    newElement.setAttribute("type","hidden");
    newElement.setAttribute("value",value);
    submitForm.appendChild(newElement);
}
/*
 * 返回值：  true 表示  date1> =  date2
 */
function compareDate(date1,date2){
	var year1=date1.split("-")[0];
	var month1=date1.split("-")[1];
	var day1=date1.split("-")[2];
	
	var year2=date2.split("-")[0];
	var month2=date2.split("-")[1];
	var day2=date2.split("-")[2];
	
	if(year1>year2){
		return true;
	}else{
		if(year1<year2){
			return false;
		}
		else{
			if(month1>month2){
				return true;
			}else{
				if(month1<month2){
					return false;
				}else{
					if(day1>day2||day1==day2){
						return true;
					}else{
						return false;
					}
				}
			}
			
		}
	}
	
}
//分页专用函数
//itemSum  总条目数
function pagination(itemNum,_url){
	var pageIndex = 0;     //页面索引初始值   
	var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可   
	getTable(0,pageSize,_url);    //Load事件，初始化表格数据，页面索引为0（第一页）
	$("#Pagination").pagination(itemNum, {
        callback:function(index){ getTable(index,pageSize,_url);},  //PageCallback() 为翻页调用次函数。
        //这里如果用默认的#，会回到首页
        link_to: "javascript:void(0)",
        prev_text: "上一页",
        next_text: "下一页 ",
        items_per_page:pageSize,
        num_edge_entries: 2,       //两侧首尾分页条目数
        num_display_entries: 6,    //连续分页主体部分分页条目数
        current_page: pageIndex,   //当前页索引
    });
}

//console.log(itemNum);
/*var pageIndex = 0;     //页面索引初始值   
var pageSize = 15;     //每页显示条数初始化，修改显示条数，修改这里即可   
$(function () {
       //分页，PageCount是总条目数，这是必选参数，其它参数都是可选
       $("#Pagination").pagination(itemNum, {
           callback: PageCallback,  //PageCallback() 为翻页调用次函数。
           //这里如果用默认的#，会回到首页
           link_to: "javascript:void(0)",
           prev_text: "上一页",
           next_text: "下一页 ",
           items_per_page:pageSize,
           num_edge_entries: 2,       //两侧首尾分页条目数
           num_display_entries: 6,    //连续分页主体部分分页条目数
           current_page: pageIndex,   //当前页索引
       });
       getTable(0);    //Load事件，初始化表格数据，页面索引为0（第一页）
 });*/
//翻页调用   
/* function PageCallback(index) {   
	 getTable(index,pageSize,_url);
} */
//请求数据   
function getTable(pageIndex,pageSize,_url) {  
	$.ajax({   
 	   url: _url,      //提交到一般处理程序请求数据   
        type: "POST",  
        data: {
     	   pageIndex:pageIndex,
     	   pageSize:pageSize,
     	     siteName: $("#hidsitename").val(),
	    	 userName:$("#hidusername").val(),
	    	 recordType:$("#hidrecordtype").val(),
	    	 startTime:$("#hidstartdate").val(),
	    	 endTime:$("#hidenddate").val(),
	    	 itemNum:$("#itemnum").val(),
        },        //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                   
        success: function(data) {
        	showWorkInfo(data);
        },
       // error: alert("网络错误"),
    }); 
}     

function showWorkInfo(data){
		console.log(data);
		$("tbody").empty();        //移除Id为Result的表格里的行，从第二行开始（这里根据页面布局不同页变）   
		for(var j=0;j<data.jobInfoList.length;j++){
    		$("tbody").append(
    				'<tr>'+
			    		'<td id="sitename'+j+'">'+data.jobInfoList[j].siteName+'</td>'+
			    		'<td id="username'+j+'">'+data.jobInfoList[j].staffName+'</td>'+
			    		'<td id="date'+j+'">'+data.jobInfoList[j].date+'</td>'+
			    		'<td id="time'+j+'">'+data.jobInfoList[j].time+'</td>'+
			    		'<td id="type'+j+'">'+data.jobInfoList[j].type+'</td>'+
			    		'<td>'+
			    			'<button class="btn btn-primary" onclick="showJobInfo('+j+')">查看</button>'+
			    		'</td>'+'<input type="hidden" id="jobinfoid'+j+'" value="'+data.jobInfoList[j].id+'"/>'+
		    		'</tr>');
    	}
}



(function($){  
    jQuery.fn.select = function(options){  
        return this.each(function(){  
            var $this = $(this);  
            var $shows = $this.find(".shows");  
            var $selectOption = $this.find(".selectOption");  
            var $el = $this.find("ul > li");  
                                      
            $this.click(function(e){  
                $(this).toggleClass("zIndex");  
                $(this).children("ul").toggleClass("dis");  
                e.stopPropagation();  
            });  
              
            $el.bind("click",function(){  
                var $this_ = $(this);  
                   
                $this.find("span").removeClass("gray");  
                $this_.parent().parent().find(".selectOption").text($this_.text());  
            });  
              
            $("body").bind("click",function(){  
                $this.removeClass("zIndex");  
                $this.find("ul").removeClass("dis");      
            })  
              
        //eahc End    
        });  
          
    }  
      
})(jQuery);

function showStaff(rootPath){
	
	var thisinput=$("#taskstaff");      // 将this保存到变量
	console.log(thisinput);
	var thisul=thisinput.parent().find('ul'); // 从this的父元素中查找到ul表单，并保存到变量 
	$.ajax({
		url:rootPath+"getAllUser",
		type: "GET",
		success:function(data){
			if(data.length!=0){
				for(var len=0;len<data.length; len++){
					var $li="<li>"+data[len]+"<\li>";
					$("#taskstafful").append($li);
				}
			}
		},
		error:function(){
			alert("通信错误");
		},
	});
	$("#taskstafful").toggle();
	//$("#taskstafful").fadeIn(300).hover(function(){},function(){$(this).fadeOut(300)}); /* 点击$('.i_select input')后，ul列表淡入，鼠标经过后离开之后，ul列表淡出 */
	$("#taskstafful").find('li').click(function(){
		thisinput.val($(this).text()); //点击li后，将input的值更改为当前li的值
		$("#taskstafful").toggle();
	});   			
}

