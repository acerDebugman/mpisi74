<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>公告信息管理</title>
<meta http-equiv="Pragma" content="no-cache">    
<meta http-equiv="Cache-Control" content="no-cache">    
<meta http-equiv="Expires" content="0">  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="css/wbox.css" rel="stylesheet" type="text/css"></link>

<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery1.4.2.js" type="text/javascript" ></script>
<script src="js/jquery.pager.js" type="text/javascript" ></script>
<script src="js/wbox.js" type="text/javascript" ></script> 

</head>
<body bgColor="#FFFFFF" topMargin="5">
<div>
<s:form action="addBulletin" method="post" theme="simple">
    <input id="optSave" name="optSave" value="${optSave}" type="hidden" />
    <input id="optCancel" name="optCancel" value="${optCancel}" type="hidden" />
    <s:hidden name="seq" value="%{mp0005.MP0005_SEQ}"></s:hidden>
    
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
    	<tr>
    		<td>
    			<!-- 头部菜单 Start -->
    			<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
    				<tr>
    					<td class='menubar_title'><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Bulletin Management</td>
    					<td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
    				</tr>
    				<tr>
    					<td height='27px' class='menubar_function_text'>Operation Function：Add Bulletin</td>
    					<td class='menubar_menu_td' align='right'>
    						<table border="0" cellspacing="0" cellpadding="0">
    							<tr>
    								<td class="menubar_button" id="button_0"><img border="0" align="texttop" src="images/ICON/Edit.gif">&nbsp;</td>
    							</tr>
    						</table>
    				    </td>
    				</tr>
    				<tr>
    					<td height='5px' colspan='2'></td>
    				</tr>
    			</table>
    			<!-- 头部菜单 End -->
    
    			<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                    <tr>
                        <td class="table_body table_body_NoWidth">Title</td>
                        <td class="table_none table_none_NoWidth">
                            <s:textfield name="mp0005.MP0005_TITLE" styleClass="text_input" style="width:99%;height:22px;"></s:textfield>
                        </td>
                        <td class="table_body table_body_NoWidth">Type</td>
                        <td class="table_none table_none_NoWidth">
                            <s:select name="mp0005.MP0005_TYPE" list="#{1:'Lastest News',2:'Notice Board',3:'Published Policy'}"  listKey="key" listValue="value"  headerKey="1"></s:select>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_body table_body_NoWidth">Content</td>
                        <td class="table_none table_none_NoWidth" colspan="3">
                            <s:textarea name="mp0005.MP0005_CONTENT" rows="20" style="width:99.5%"></s:textarea>
                        </td>
                    </tr>
    			</table>
    	    </td>
    	</tr>
    	
    	<tr>
            <td class="menubar_button" id="button_0" align="center">
<s:if test="optSave==1">
                <input type="submit" name="save" value="Save" />
</s:if>
<s:else>
                <input type="submit" name="save" value="Save" disabled="disabled" />
</s:else>
<s:if test="optCancel==1">
                <input type="button" onclick="window.location.href='bulletinList.action'" name="cancel" value="Cancel" />
</s:if>
<s:else>
                <input type="button" onclick="window.location.href='bulletinList.action'" name="cancel" value="Cancel" disabled="disabled"/>
</s:else>
            </td>
    	</tr>
    </table>
</s:form>
</div>
</body>
</html>