<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script src="js/jquery1.4.2.js" type="text/javascript" ></script>
<script src="js/jquery.form.js" type="text/javascript" ></script>

<script type="text/javascript">
function createExcelDoc1(){
/*  	$.ajax({
		url:"createEmployeeInfoExcelDocument.action",
		type:"post",
		dataType:"html",
		//data: params,
		error:function(ex){
			alert("failed:" + ex.responseText);
		},
		success:function(data){
			alert("Success");
		}
	}); */
	var options = {
			url : "createEmployeeInfoExcelDocument.action?pageInitType=1",
			type : "post",
			dataType : "html",
			success : function(msg) {
				alert("hello");
			}
		};
		$("#from1").ajaxSubmit(options);
}
function createExcelDoc2(){
    var options = {
	    url : "createEmployeeInfoExcelDocument.action?pageInitType=2",
		type : "post",
		dataType : "html",
		success : function(msg) {
			//alert("hello");
		}
	};
	$("#from1").ajaxSubmit(options);
}
function createExcelDoc3(){
    var options = {
	    url : "createEmployeeInfoExcelDocument.action?pageInitType=3",
		type : "post",
		dataType : "html",
		success : function(msg) {
			//alert("hello");
		}
	};
	$("#from1").ajaxSubmit(options);
}
function createExcelDoc4(){
    var options = {
	    url : "createEmployeeInfoExcelDocument2.action",
		type : "post",
		dataType : "html",
		success : function(msg) {
			alert("hello");
		}
	};
	$("#from1").ajaxSubmit(options);
}
</script>

</head>
<body>
<form id="from1" method="post" enctype="multipart/form-data">
<input type="button" onclick="createExcelDoc1()" value="Export To Excel(Sorted by Employee Number)" />
<input type="button" onclick="createExcelDoc2()" value="Export To Excel(Sorted by Department)" />
<input type="button" onclick="createExcelDoc3()" value="Export To Excel(Dimission)" />
<input type="button" onclick="createExcelDoc4()" value="Export To Excel(Departmental Transfers)" />
</form>

</body>
</html>