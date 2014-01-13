<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>应用软体申请表格</title>
<base target="_self" />

<style type="text/css">
</style>

</head>
<body>
<form id="form1" action="addSystemApplyForm.action" method="post" >

<div align="center" style="border:0px solid #2C5298;width:1000px;float:left;font-size:15pt;font-weight:bold;margin-bottom:5px;">应用软体开发申请表</div>

<div align="center" style="border:0px solid #2C5298;width:1000px;height:660px;float:left;">
    <div style="background-color:#9AADCD;width:150px;height:30px;float:left;margin-bottom:1px;">编号:</div>
    <div style="height:30px;float:left;margin-bottom:1px;">
        <input id="it0002Num" name="it0002.IT0002_NUM" value="${it0002.IT0002_NUM}" type="text" style="width:343px;border:none;font-size:20px;color:red;" readonly="readonly"/>
    </div>
    <div style="background-color:#9AADCD;width:150px;height:30px;float:left;margin-bottom:1px;">申请时间:</div>
    <div style="width:350px;height:30px;float:left;margin-bottom:1px;">
        <input id="it0002ApplyDate" name="it0002.IT0002_APPLY_DATE" value="${it0002.IT0002_APPLY_DATE}" type="text" style="width:343px;border:none;font-size:20px;color:red;" readonly="readonly"/>
    </div>
    
    <div style="background-color:#9AADCD;width:150px;height:30px;float:left;margin-bottom:1px;">申请功能名称:</div>
    <div style="width:849px;height:30px;float:left;margin-bottom:1px;">
        <input id="it0002FunctionName" name="it0002.IT0002_FUNCTION_NAME" value="${it0002.IT0002_FUNCTION_NAME}" type="text" style="width:849px;height:28px;border:none;border-bottom-style:solid;border-bottom-width:1px;vertical-align:bottom;line-height:30px;"/>
    </div>

    <div style="background-color:#9AADCD;width:150px;height:30px;float:left;margin-bottom:1px;">申请部门:</div>
    <div style="height:30px;float:left;margin-bottom:1px;">
        <input id="it0002ApplyDepartment" name="it0002.IT0002_APPLY_DEPARTMENT" value="${it0002.IT0002_APPLY_DEPARTMENT}" type="text" style="width:343px;height:28px;border:none;border-bottom-style:solid;border-bottom-width:1px;vertical-align:bottom;line-height:30px;"/>
    </div>
    <div style="background-color:#9AADCD;width:150px;height:30px;float:left;margin-bottom:1px;">相关部门:</div>
    <div style="width:350px;height:30px;float:left;margin-bottom:1px;">
        <input id="it0002OtherDepartment" name="it0002.IT0002_OTHER_DEPARTMENT" value="${it0002.IT0002_OTHER_DEPARTMENT}" type="text" style="width:350px;height:28px;border:none;border-bottom-style:solid;border-bottom-width:1px;vertical-align:bottom;line-height:30px;"/>
    </div>

    <div style="background-color:#9AADCD;width:150px;height:30px;float:left;margin-bottom:1px;">申请人:</div>
    <div style="width:345x;height:30px;float:left;margin-bottom:1px;">
        <input id="it0002ApplyReason" name="it0002.IT0002_APPLY_PERSON" value="${it0002.IT0002_APPLY_PERSON}" type="text" style="width:343px;height:28px;border:none;border-bottom-style:solid;border-bottom-width:1px;vertical-align:bottom;line-height:30px;"/>
    </div>
    <div style="background-color:#9AADCD;width:150px;height:30px;float:left;margin-bottom:1px;">负责人:</div>
    <div style="width:350px;height:30px;float:left;margin-bottom:1px;">
        <input id="it0002Superintend" name="it0002.IT0002_SUPERINTEND" value="${it0002.IT0002_SUPERINTEND}" type="text" style="width:350px;height:28px;border:none;border-bottom-style:solid;border-bottom-width:1px;vertical-align:bottom;line-height:30px;"/>
    </div>

    <div style="background-color:#9AADCD;width:150px;height:30px;float:left;margin-bottom:2px;">预计完成日期:</div>
    <div style="width:345x;height:30px;float:left;margin-bottom:2px;">
        <input id="it0002ScheduledTime" name="it0002.IT0002_SCHEDULED_TIME" value="${it0002.IT0002_SCHEDULED_TIME}" type="text" style="width:343px;height:28px;border:none;border-bottom-style:solid;border-bottom-width:1px;vertical-align:bottom;line-height:30px;"/>
    </div>
    <div style="background-color:#9AADCD;width:150px;height:30px;float:left;margin-bottom:2px;">费用归属部门:</div>
    <div style="border-bottom-style:solid; border-bottom-width:0px;border-bottom-color:#2C5298;width:350px;height:30px;float:left;margin-bottom:2px;">
        <input id="it0002ExpenseDepartment" name="it0002.IT0002_EXPENSE_DEPARTMENT" value="${it0002.IT0002_EXPENSE_DEPARTMENT}" type="text" style="width:350px;height:28px;border:none;border-bottom-style:solid;border-bottom-width:1px;vertical-align:bottom;line-height:30px;"/>
    </div>
    
    <div style="background-color:#9AADCD;width:150px;height:100px;float:left;margin-bottom:1px;">需求原因:</div>
    <div style="width:848px;height:100px;float:left;margin-bottom:1px;">
        <s:textarea id="it0002ApplyReason" name="it0002.IT0002_APPLY_REASON" style="width:846px;height:93px;" theme="simple"></s:textarea>
    </div>
    
    <div style="background-color:#9AADCD;width:150px;height:100px;float:left;margin-bottom:1px;">功能详述:</div>
    <div style="width:848px;height:100px;float:left;margin-bottom:1px;">
        <s:textarea id="it0002FunDesc" name="it0002.IT0002_FUNCTION_DESCRIPTION" style="width:846px;height:93px;" theme="simple"></s:textarea>
    </div>
    
    <div style="background-color:#9AADCD;width:150px;height:100px;float:left;margin-bottom:1px;">ＩＴ部技术负责人分析意见:</div>
    <div style="width:848px;height:100px;float:left;margin-bottom:1px;">
        <s:textarea id="it0002SEAdvice" name="it0002.IT0002_SE_ADVICE" style="width:846px;height:68px;" theme="simple"></s:textarea>
        <label>签字:</label><input id="it0002SESign" name="it0002.IT0002_SE_SIGN" value="${it0002.IT0002_SE_SIGN}" type="text" style="width:200px;border:none;border-bottom-style:solid; border-bottom-width:1px;border-bottom-color:black;color:red;"/>
        <label>日期:</label><input id="it0002SeSignDate" name="it0002.IT0002_SE_SIGN_DATE" value="${it0002.IT0002_SE_SIGN_DATE}" type="text" style="width:200px;border:none;border-bottom-style:solid; border-bottom-width:1px;"/>
    </div>
    
    <div style="background-color:#9AADCD;width:150px;height:100px;float:left;margin-bottom:1px;">ＩＴ部主管意见:</div>
    <div style="width:848px;height:100px;float:left;margin-bottom:1px;">
        <s:textarea id="it0002ITManagerAdvice" name="it0002.IT0002_IT_MANAGER_ADVICE" style="width:846px;height:68px;" theme="simple"></s:textarea>
        <label>签字:</label><input id="it0002ITManagerSign" name="it0002.IT0002_IT_MANAGER_SIGN" value="${it0002.IT0002_IT_MANAGER_SIGN}" type="text" style="width:200px;border:none;border-bottom-style:solid; border-bottom-width:1px;border-bottom-color:black;color:red;"/>
        <label>日期:</label><input id="it0002ITManagerSignDate" name="it0002.IT0002_IT_MANAGER_SIGN_DATE" value="${it0002.IT0002_IT_MANAGER_SIGN_DATE}" type="text" style="width:200px;border:none;border-bottom-style:solid; border-bottom-width:1px;"/>
    </div>

    <div style="background-color:#9AADCD;width:150px;height:100px;float:left;margin-bottom:1px;">申请部门主管审批:</div>
    <div style="width:848px;height:100px;float:left;margin-bottom:1px;">
        <s:textarea id="it0002Num" name="it0002.IT0002_APPLY_DEP_MANAGER_ADVICE" style="width:846px;height:68px;" theme="simple"></s:textarea>
        <label>签字:</label><input id="it0002ApplyDepMngSign" name="it0002.IT0002_APPLY_DEP_MANAGER_SIGN" value="${it0002.IT0002_APPLY_DEP_MANAGER_SIGN}" type="text" style="width:200px;border:none;border-bottom-style:solid; border-bottom-width:1px;border-bottom-color:black;color:red;"/>
        <label>日期:</label><input id="it0002ApplyDepMngSignDate" name="it0002.IT0002_APPLY_DEP_MANAGER_SIGN_DATE" value="${it0002.IT0002_APPLY_DEP_MANAGER_SIGN_DATE}" type="text" style="width:200px;border:none;border-bottom-style:solid; border-bottom-width:1px;"/>
    </div>
</div>
<div align="left" style="width:1000px;float:left;">备注<br/>1．涉及软件开发要求一律填写此申请表，并由申请单位负责人签字审批；<br/>2．此表统一编号，双方签字后作为文档备案，交IT部，申请单位保留复印件；<br/>3．涉及较大技术变动，需要双方单位当面协商，并组织主管领导讨论；<br/>4．IT部审核时应指定技术负责人。</div>

<div align="center" style="width:1000px;float:left;">
    <input id="save" type="submit" value="Save" />
    <input id="close" type="button" value="Close" />
</div>

</form>
</body>
</html>