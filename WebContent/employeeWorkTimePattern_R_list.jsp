<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
</html>
<head>
<script type="text/javascript">
window.document.onkeydown = keyStroke;
function keyStroke(){
	if (window.event.keyCode==13){
		window.event.keyCode=9;
	}
}
$(document).ready(function() {
    var _pageNum = $("#pageNum").val();
    if(_pageNum == 0){
    	_pageNum = 1;
    }
    $("#pager").pager({ pagenumber: _pageNum, pagecount: $("#pageCount").val(), buttonClickCallback: PageClick });
});

PageClick = function(pageclickednumber) {
    $("#pager").pager({ pagenumber: pageclickednumber, pagecount: $("#pageCount").val(), buttonClickCallback: PageClick });
    
	var empCode = $("#employeeCode").val();
	var wtpId = $("#selectWorkTimePatternId").val();

	var param = {'employeeCode' : empCode,
				 'workTimePatternId' : wtpId,
				 'currentPageNum' : pageclickednumber
				 };
    
    document.getElementById("pageNum").value = pageclickednumber;
    $('#employeeWorkTimePatternList').load('employeeWorkTimePattern_R_Search.action', param);
}
</script>
</head>
<body>
<input id="pageCount" name="pageCount" value="${pageBean.totalPage}" type="hidden" />
<input id="pageNum" name="pageNum" value="${currentPageNum}" type="hidden" />
<table id="tb1" class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
    <tr class="table_title" align="center">
        <th scope="col" width="150px">Employee Number</th>
        <th scope="col" width="150px">Employee Name</th>
        <th scope="col" width="120px">Worktime Pattern</th>
        <th scope="col" width="120px">Start Date</th>
        <th scope="col" width="80px">Initial Circle Day</th>
        <th scope="col" width="80px">Operation</th>
    </tr>

<s:iterator value="empWorktimePatternList" status="st" var="obj">
    <tr id="<s:property value='#obj.id' />" class="row" align="center" style="height:28px;" >
    	<td><s:property value="#obj.employee.MP1001_EMPLOYEE_NUM"/></td>
        <td><s:property value="#obj.employee.MP1001_PREFERED_NAME" /></td>
        <td><s:property value="#obj.workTimePattern.name" /></td>
        <td><s:date name="#obj.startDate" format="yyyy-MM-dd"/></td>
        <td><s:property value="#obj.initialCircleDayIdx" /></td>
        <td><input id="itemEdit" name="itemEdit" value="edit" type="button" onclick="employeeWTPedit(<s:property value='#obj.id' />)"/></td>
    </tr>
</s:iterator>
</table>

<table cellspacing="1" border="0" style="background-color:White;border-width:0px;" align="center">
    <tr class="">
      <td>
          <div id="pager" style="border: 1px solid #FFFFFF;"></div>
      </td>
    </tr>
<tr><td height="5"></td></tr>
</table>

</body>
</html>