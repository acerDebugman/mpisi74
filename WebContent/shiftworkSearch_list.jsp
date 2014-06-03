<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
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
    
    var fromDate = $('#fromDate').val();
	var toDate = $('#toDate').val();
	var employeeNum = $('#employeeNum').val();
	var employeeName = $('#employeeName').val();
	var branchSiteId = $('#branchSiteId').val();
	
    var param = {'fromDate' : fromDate,
				 'toDate' : toDate,
				 'employeeNum' : employeeNum,
				 'employeeName' : employeeName,
				 'branchSiteId' : branchSiteId,
				 'currentPageNum' : pageclickednumber
				 };
    
    document.getElementById("pageNum").value = pageclickednumber;
    $('#shiftWorkInfoList').load('shiftWorkSearch.action', param);
}
</script>
<body>

<input id="pageCount" name="pageCount" value="${pageBean.totalPage}" type="hidden" />
<input id="pageNum" name="pageNum" value="${currentPageNum}" type="hidden" />
<table id="tb1" class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
    <tr class="table_title" align="center">
        <th scope="col" width="150px">Employee Number</th>
        <th scope="col" width="150px">Employee Name</th>
        <th scope="col" width="120px">Date</th>
        <th scope="col" width="80px">Type</th>
        <th scope="col" width="120px">Branch Site</th>
    </tr>

<s:iterator value="shiftworkScheduleList" status="st">
    <tr id="<s:property value="MP2010_ID"/>" class="row" align="center" style="height:28px;" >
        <td><s:property value="MP2010_EMPLOYEE_NUM" /></td>
        <td><s:property value="employeeInfo.MP1001_PREFERED_NAME" /></td>
        <td><s:property value="MP2010_DATE.substring(0, 10)" /></td>
        <td><s:property value="MP2010_TYPE" /></td>
        <td><s:property value="MP2010_BRANCH_SITE" /></td>
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
