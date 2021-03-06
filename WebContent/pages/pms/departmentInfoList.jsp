<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../../images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="../../css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="../../css/Site_Css.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
var oldRowIndex = 0;
function changeRow(newRowIndex,departmentId){
	var oldRow = document.getElementById(oldRowIndex);
	var newRow = document.getElementById(newRowIndex);
	var parameterTypeObj = document.getElementById("parameterType");
	var parameterType = '';
	
	if(oldRow == newRow){
		return;
	}
	if(parameterTypeObj != null){
		parameterType = parameterTypeObj.value;
	}
	if(newRow != null){
		newRow.style.backgroundColor = 'red';
		newRow.style.color = 'white';
		if(oldRow != null){
			oldRow.style.backgroundColor = '#B5C7E3';
			oldRow.style.color = 'black';
		}
		oldRowIndex = newRowIndex;
	}

	if(departmentId != null && departmentId != ''){
		if(parameterType == 'budget'){
			window.parent.frames['mainFrame'].location='budgetSetInit.action?pageType=' + departmentId;
		}else if(parameterType == 'departmentMng'){
			window.parent.frames['mainFrame'].location='departmentAddInit.action?pageType=edit&mp0002Seq=' + departmentId;
		}else if(parameterType == 'departmentPositionMng'){
			window.parent.frames['mainFrame'].location='departmentPositionSetInit.action?mp0002Seq=' + departmentId;
		}
	}
}
</script>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginheight="0" marginwidth="0">
<form id="form2" name="form2" method="post">
<input id="parameterType" name="parameterType" value="${parameterType}" type="hidden" />

<table class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px;">
    <tr class="table_title" align="center">
        <th scope="col">Department</th>
    </tr>
<s:iterator value="departmentObjList" status="st">
   <tr class="row" align="center" style="height:28px;" onclick="changeRow(${st.index + 1},'<s:property value="MP0002_SEQ"/>')" >
       <td id="${st.index + 1}"><s:property value="MP0002_DEPARTMENT_NAME"/></td>
   </tr>
</s:iterator>
</table>

</form>
</body>
</html>