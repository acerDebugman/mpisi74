<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
</head>
<body>

<input id="optEdit" name="optEdit" value="${optEdit}" type="hidden" />
<input id="optSave" name="optSave" value="${optSave}" type="hidden" />
<input id="optAll" name="optAll" value="${optAll}" type="hidden" />
<input id="optApproval" name="optApproval" value="${optApproval}" type="hidden" />
<input id="budgetStatus" name="budgetStatus" value="${budgetStatus}" type="hidden" />
<input id="pageType" name="pageType" value="${pageType}" type="hidden" />

<s:if test="pageType != 'view'">
<table cellspacing="1" border="0" width="1020px" style="background-color:White;border-width:0px;margin-top:5px;">
    <tr>
        <td align="center">
<s:if test="optSave == 1 && budgetStatus == 0">
            <input type="button" onclick="confirmBudget('0')" value="Save Budget"/>
            <input id="departmentSave" type="button" onclick="confirmBudget('1')" value="Send to Accounting" />
</s:if>
<s:else>
            <input type="button" onclick="confirmBudget('0')" value="Save Budget" disabled="disabled"/>
            <input id="departmentSave" type="button" onclick="confirmBudget('1')" value="Send to Accounting"  disabled="disabled"/>
</s:else>
<s:if test="optApproval == 1 && budgetStatus == 1">
            <input id="approve" type="button" onclick="confirmBudget2('2')" value="Approve" />
            <input id="notApprove" type="button" onclick="confirmBudget2('0')" value="Not Approve" />
</s:if>
<s:else>
            <input id="approve" type="button" onclick="confirmBudget2('2')" value="Approve" disabled="disabled"/>
            <input id="notApprove" type="button" onclick="confirmBudget2('0')" value="Not Approve" disabled="disabled"/>
</s:else>
        </td>
    </tr>
    <tr>
        <td style="color:red;"><s:property value="errMsg"/></td>
    </tr>
</table>
</s:if>

<table cellspacing="1" border="0" style="background-color:White;border-width:0px;margin-top:5px;width:1400px;">
    <tr class="table_title" align="center">
        <th scope="col" width="50px">No.</th>
        <th scope="col" width="200px">Name</th>
        <th scope="col" width="100px">Main Class</th>
        <th scope="col" width="200px">Type</th>
        <th scope="col" width="100px">Month</th>
        <th scope="col" width="80px">Limit</th>
        <th scope="col" width="120px">Budget Amount</th>
        <th scope="col" width="130px">Additional Budget</th>
        <th scope="col" width="120px">Expenditure</th>
        <th scope="col" width="100px">Status</th>
        <th scope="col">-</th>
    </tr>
</table>

<div id="departmentBudgetInfoDiv" style="height:600px;width:1400px;border:0px solid red;overflow-y:scroll;">
<%-- <s:if test="pageType == 'view' && budgetStatus == 0">
 <div style="color:red;font-size:30pt;margin-top:30px;text-align:center;">No Data</div>
</s:if>
<s:else> --%>
<table cellspacing="1" border="0" style="background-color:White;border-width:0px;">
<s:iterator id="mp4003InfoList" value="mp4003InfoList" status="st">
   <tr class="row">
       <td width="50px" align="center">${st.index + 1}</td>
<s:if test="MP4003_ACCOUNT_DESC != ''">
       <td width="200px" align="left" style="text-decoration:underline;" onclick="Open('<s:property value="MP4003_ACCOUNT_DESC"/>')"><s:property value="MP4003_ACCOUNTING_NAME"/></td>
</s:if>
<s:else>
       <td width="200px" align="left"><s:property value="MP4003_ACCOUNTING_NAME"/></td>
</s:else>
       <td width="100px" align="center"><s:property value="MP4003_TYPE_NAME"/></td>
       <td width="200px" align="left"><s:property value="MP4003_ACCOUNT_TYPE_NAME"/></td>
       <td width="100px" align="center"><s:property value="MP4003_MONTH_NAME"/></td>
       <td width="80px" align="right"><s:property value="MP4003_AMOUNT_MAX"/></td>
<s:if test="pageType != 'view' && optEdit == 1 && MP4003_STATUS == 0">
       <td width="120px" align="right">
           <input id="${st.index + 1}" onkeyup="checkLimit('${st.index + 1}','<s:property value="MP4003_AMOUNT_MAX"/>')"  name="amountArray" value="${amountArray[st.index]}" type="text" onmouseover="changestyle('${st.index + 1}')" onmouseout="lostFocus('${st.index + 1}','<s:property value="MP4003_AMOUNT_MAX"/>')" readonly="readonly" class="textInput2" style="width:100%;text-align:right;"/>
       </td>
</s:if>
<s:else>
       <td width="120px" align="right"><s:property value="MP4003_AMOUNT"/></td>
</s:else>
       <td width="130px" align="right" style="color:red;"><s:property value="MP4003_AMOUNT3"/></td>
       <td width="120px" align="right" style="color:green;"><s:property value="MP4003_AMOUNT4"/></td>
       <td width="100px" align="center"><s:property value="MP4003_STATUS_NAME"/></td>
       <td width="150px" align="right">
<s:if test="pageType != 'view' && MP4003_STATUS == 2">
           <input type="button" onclick="superaddBudget('<s:property value="MP4003_SEQ"/>','<s:property value="MP4003_ACCOUNTING_NUM"/>','<s:property value="MP4003_ACCOUNTING_NAME"/>')" name="addBtn" value="Apply Addition" id="addBtn"/>
</s:if>
<s:else>
           <input type="button" onclick="superaddBudget('<s:property value="MP4003_SEQ"/>','<s:property value="MP4003_ACCOUNTING_NUM"/>','<s:property value="MP4003_ACCOUNTING_NAME"/>')" name="addBtn" value="Apply Addition" id="addBtn" disabled="disabled"/>
</s:else>
       </td>
   </tr>
</s:iterator>
</table>
<%-- </s:else> --%>
</div>

<table cellspacing="1" border="0" style="background-color:White;border-width:0px;margin-top:0px;width:1400px;">
    <tr class="row" align="center">
<s:if test="pageType == 'view' && budgetStatus == 0">
        <td colspan="6" width="670px" align="right">0</td>
        <td width="130px" align="right">0</td>
        <td width="120px" align="right">0</td>
        <td width="272px">&nbsp;</td>
</s:if>
<s:else>
        <td colspan="6" width="670px" align="right"><s:property value="budgetAmount1"/></td>
        <td width="130px" align="right"><s:property value="budgetAmount3"/></td>
        <td width="120px" align="right"><s:property value="budgetAmount4"/></td>
        <td width="272px">&nbsp;</td>
</s:else>
    </tr>
</table>

</body>
</html>

<script type="text/javascript">
//alert("change:" + document.getElementById("budgetStatus").value + "|" + document.getElementById("optApproval").value);
</script>