<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>公告信息</title>
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

<style type="text/css">
.td1{
border-bottom:3px solid #000000;
}
.td2{
border-bottom:1px solid #000000;
BACKGROUND-COLOR: #B5C7E3;
}
.td3{
border-bottom:1px dashed #000000;
}
.td4{
display:none;
}
</style>

</head>

<body bgColor="#FFFFFF" topMargin="5" >

<form name="form1" method="post"> 
    <input id="group" name="group" value="${group}" type="hidden" />
    <input id="departmentID" name="departmentID" value="${departmentID}" type="hidden" />

    <input id="optEdit" name="optEdit" value="${optEdit}" type="hidden" />
    <input id="optDel" name="optDel" value="${optDel}" type="hidden" />
    <input id="optAll" name="optAll" value="${optAll}" type="hidden" />
    <input id="optDepartment" name="optDepartment" value="${optDepartment}" type="hidden" />
    <input id="optPersonal" name="optPersonal" value="${optPersonal}" type="hidden" />

    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
            <td>
                <!-- 头部菜单 Start -->
                <table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
                  <tr>
                    <td class='menubar_title td1'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3' />&nbsp;Company Published Information</td>
                    <td class='menubar_readme_text td1' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;Help？</td>
                  </tr>
                  
                  <tr>
                    <td height='5px' colspan='2'></td>
                  </tr>
                </table>
                <!-- 头部菜单 End -->
                
	            <!-- 选项卡 Start -->
	            <TABLE cellSpacing=0 cellPadding=0 width='100%' align=center border=0>   
	            <TBODY>   
	                <TR>     
	                    <TD style='PADDING-LEFT: 2px; HEIGHT: 22px' background='images/menu/tab_top_bg.gif'>
	                        <TABLE cellSpacing=0 cellPadding=0 border=0>
	                            <TBODY>
	                                <TR>
	                                    <!--按钮　Start-->
	                                    <TD >
	                                        <TABLE height=22 cellSpacing=0 cellPadding=0 border=0>
	                                            <TBODY>
	                                                   <TR>
	                                                     <TD width=3><IMG id=tabImgLeft__0 height=22 src='images/menu/tab_unactive_left.gif'  width=3></TD>
	                                                     <TD class=tab id=tabLabel__0 onclick='javascript:tabClick(0,3)' background='images/menu/tab_unactive_bg.gif' UNSELECTABLE='on'>Lastest News</TD>
	                                                     <TD width=3><IMG id=tabImgRight__0 height=22 src='images/menu/tab_unactive_right.gif' width=3></TD>
	                                                   </TR>
	                                            </TBODY>
	                                        </TABLE>
	                                        </TD>
	                                        <TD >
	                                            <TABLE height=22 cellSpacing=0 cellPadding=0 border=0>
	                                                <TBODY>
	                                                       <TR>
	                                                         <TD width=3><IMG id=tabImgLeft__1 height=22 src='images/menu/tab_unactive_left.gif'  width=3></TD>
	                                                         <TD class=tab id=tabLabel__1 onclick='javascript:tabClick(1,3)' background='images/menu/tab_unactive_bg.gif' UNSELECTABLE='on'>Notice Board</TD>
	                                                         <TD width=3><IMG id=tabImgRight__1 height=22 src='images/menu/tab_unactive_right.gif' width=3></TD>
	                                                       </TR>
	                                                </TBODY>
	                                            </TABLE>
	                                        </TD>
	                                        <TD >
	                                            <TABLE height=22 cellSpacing=0 cellPadding=0 border=0>
	                                                <TBODY>
	                                                       <TR>
	                                                         <TD width=3><IMG id=tabImgLeft__2 height=22 src='images/menu/tab_unactive_left.gif'  width=3></TD>
	                                                         <TD class=tab id=tabLabel__2 onclick='javascript:tabClick(2,3)' background='images/menu/tab_unactive_bg.gif' UNSELECTABLE='on'>Published Policy</TD>
	                                                         <TD width=3><IMG id=tabImgRight__2 height=22 src='images/menu/tab_unactive_right.gif' width=3></TD>
	                                                       </TR>
	                                                </TBODY>
	                                            </TABLE>
	                                        </TD>
	                                        <!--按钮 End-->
	                                    </TR>
	                            </TBODY>
	                        </TABLE>
	                    </TD>
	                </TR>
	                <TR>
	                <TD bgColor=#ffffff>           
	                    <TABLE cellSpacing=0 cellPadding=0 width='100%' border=0>
	                    <TBODY>
	                    <TR>
	                        <TD width=1 background='images/menu/tab_bg.gif'><IMG  height=1 src='images/menu/tab_bg.gif'  width=1></TD>
	                        <TD style='PADDING-RIGHT: 15px; PADDING-LEFT: 15px; PADDING-BOTTOM: 15px; PADDING-TOP: 15px; HEIGHT: 100px' vAlign=top>
	                            <!--内容框Start-->
	                            <DIV id='tabContent__0' style='display:none;'>
					                <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
					                    <tr>
					                        <td class="td4">ID</td>
					                        <td class="td2" style="width:200px;" align="center">Issuance Time</td>
					                        <td class="td2" align="center">Title</td>
					                        <td class="td2" style="width:200px;" align="center">Department</td>
					                        <td class="td2" style="width:200px;" align="center">Author</td>
<s:if test="(optAll == 1 || optDepartment == 1) && (optEdit==1 || optDel==1)" >
					                        <td class="td2" style="width:100px;" align="center">-</td>
</s:if>
					                    </tr>
					<s:iterator value="newBulletinInfo" status="st">
					                    <tr onMouseDown="showContent()">
					                        <td class="td4"><s:property value="MP0005_SEQ"></s:property></td>
					                        <td class="td3" align="center"><s:property value="MP0005_ADDTIME"></s:property></td>
					                        <td class="td3"><a href='#' onmouseover="showContent(<s:property value="MP0005_SEQ"/>,'<s:property value="MP0005_TITLE"/>','<s:property value="MP0005_CONTENT"/>')" id=<s:property value="MP0005_SEQ"/>><s:property value="MP0005_TITLE"></s:property></td>
					                        <td class="td3" align="center"><s:property value="MP0005_DEPART_NAME"></s:property></td>
					                        <td class="td3" align="center"><s:property value="MP0005_AUTHOR_NAME"></s:property></td>
<s:if test="(optAll == 1 || optDepartment == 1) && (optEdit==1 || optDel==1)" >
					                        <td class="td3" align="center">
					                            <s:if test="optAll==1">
					                                <s:if test="optEdit==1">
					                                    <a href="initBulletin.action?type=Edit&seq=<s:property value="MP0005_SEQ"/>" >Edit</a>
					                                </s:if>
					                                <s:elseif test="optDel==1">
					                                    <a href="delBulletin.action?type=Del&seq=<s:property value="MP0005_SEQ"/>" >Delete</a>
					                                </s:elseif>
					                            </s:if>
					                            <s:elseif test="optDepartment==1">
					                                <s:if test="optEdit==1 && MP0005_DEPART_ID == departmentID">
					                                    <a href="initBulletin.action?type=Edit&seq=<s:property value="MP0005_SEQ"/>" >Edit</a>
					                                </s:if>
					                                <s:elseif test="optDel==1 && MP0005_DEPART_ID == departmentID">
					                                    <a href="delBulletin.action?type=Del&seq=<s:property value="MP0005_SEQ"/>" >Delete</a>
					                                </s:elseif>
					                            </s:elseif>
					                            <s:else>-</s:else>
					                        </td>
</s:if>
					                    </tr>
					</s:iterator>
					                </table>
	                            </DIV>
	                            <!--内容框End-->
	                            
	                            <!--内容框Start-->
	                            <DIV id='tabContent__1' style='display:none'>
					                <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
					                    <tr>
					                        <td class="td4">ID</td>
					                        <td class="td2" style="width:200px;" align="center">Issuance Time</td>
					                        <td class="td2" align="center">Title</td>
					                        <td class="td2" style="width:200px;" align="center">Department</td>
					                        <td class="td2" style="width:200px;" align="center">Author</td>
<s:if test="(optAll == 1 || optDepartment == 1) && (optEdit==1 || optDel==1)" >
					                        <td class="td2" style="width:100px;" align="center">-</td>
</s:if>
					                    </tr>
					<s:iterator value="DailyBulletinInfo" status="st">
					                    <tr onMouseDown="showContent()">
					                        <td class="td4"><s:property value="MP0005_SEQ"></s:property></td>
					                        <td class="td3" align="center"><s:property value="MP0005_ADDTIME"></s:property></td>
					                        <td class="td3"><a href='#' onmouseover="showContent(<s:property value="MP0005_SEQ"/>,'<s:property value="MP0005_TITLE"/>','<s:property value="MP0005_CONTENT"/>')" id=<s:property value="MP0005_SEQ"/>><s:property value="MP0005_TITLE"></s:property></td>
					                        <td class="td3" align="center"><s:property value="MP0005_DEPART_NAME"></s:property></td>
					                        <td class="td3" align="center"><s:property value="MP0005_AUTHOR_NAME"></s:property></td>
<s:if test="(optAll == 1 || optDepartment == 1) && (optEdit==1 || optDel==1)" >
					                        <td class="td3" align="center">
					                            <s:if test="optAll==1">
					                                <s:if test="optEdit==1">
					                                    <a href="initBulletin.action?type=Edit&seq=<s:property value="MP0005_SEQ"/>" >Edit</a>
					                                </s:if>
					                                <s:elseif test="optDel==1">
					                                    <a href="delBulletin.action?type=Del&seq=<s:property value="MP0005_SEQ"/>" >Delete</a>
					                                </s:elseif>
					                            </s:if>
					                            <s:elseif test="optDepartment==1">
					                                <s:if test="optEdit==1 && MP0005_DEPART_ID == departmentID">
					                                    <a href="initBulletin.action?type=Edit&seq=<s:property value="MP0005_SEQ"/>" >Edit</a>
					                                </s:if>
					                                <s:elseif test="optDel==1 && MP0005_DEPART_ID == departmentID">
					                                    <a href="delBulletin.action?type=Del&seq=<s:property value="MP0005_SEQ"/>" >Delete</a>
					                                </s:elseif>
					                            </s:elseif>
					                            <s:else>-</s:else>
					                        </td>
</s:if>
					                    </tr>
					</s:iterator>
					                </table>
	                            </DIV>
	                            <!--内容框End-->
	                            
	                            <!--内容框Start-->
	                            <DIV id='tabContent__2' style='display:none'>
					                <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
					                    <tr>
					                        <td class="td4">ID</td>
					                        <td class="td2" style="width:200px;" align="center">Issuance Time</td>
					                        <td class="td2" align="center">Title</td>
					                        <td class="td2" style="width:200px;" align="center">Department</td>
					                        <td class="td2" style="width:200px;" align="center">Author</td>
<s:if test="(optAll == 1 || optDepartment == 1) && (optEdit==1 || optDel==1)" >
					                        <td class="td2" style="width:100px;" align="center">-</td>
</s:if>
					                    </tr>
					<s:iterator value="policyBulletinInfo" status="st">
					                    <tr onMouseDown="showContent()">
					                        <td class="td4"><s:property value="MP0005_SEQ"></s:property></td>
					                        <td class="td3" align="center"><s:property value="MP0005_ADDTIME"></s:property></td>
					                        <td class="td3"><a href='#' onmouseover="showContent(<s:property value="MP0005_SEQ"/>,'<s:property value="MP0005_TITLE"/>','<s:property value="MP0005_CONTENT"/>')" id=<s:property value="MP0005_SEQ"/>><s:property value="MP0005_TITLE"></s:property></td>
					                        <td class="td3" align="center"><s:property value="MP0005_DEPART_NAME"></s:property></td>
					                        <td class="td3" align="center"><s:property value="MP0005_AUTHOR_NAME"></s:property></td>
<s:if test="(optAll == 1 || optDepartment == 1) && (optEdit==1 || optDel==1)" >
					                        <td class="td3" align="center">
					                            <s:if test="optAll==1">
					                                <s:if test="optEdit==1">
					                                    <a href="initBulletin.action?type=Edit&seq=<s:property value="MP0005_SEQ"/>" >Edit</a>
					                                </s:if>
					                                <s:elseif test="optDel==1">
					                                    <a href="delBulletin.action?type=Del&seq=<s:property value="MP0005_SEQ"/>" >Delete</a>
					                                </s:elseif>
					                            </s:if>
					                            <s:elseif test="optDepartment==1">
					                                <s:if test="optEdit==1 && MP0005_DEPART_ID == departmentID">
					                                    <a href="initBulletin.action?type=Edit&seq=<s:property value="MP0005_SEQ"/>" >Edit</a>
					                                </s:if>
					                                <s:elseif test="optDel==1 && MP0005_DEPART_ID == departmentID">
					                                    <a href="delBulletin.action?type=Del&seq=<s:property value="MP0005_SEQ"/>" >Delete</a>
					                                </s:elseif>
					                            </s:elseif>
					                            <s:else>-</s:else>
					                        </td>
</s:if>
					                    </tr>
					</s:iterator>
					                </table>
	                            </DIV>
	                            <!--内容框End-->
	                        </TD>
	                        <TD width=1 background='images/menu/tab_bg.gif'><IMG height=1 src='images/menu/tab_bg.gif'  width=1></TD>
	                    </TR>
	                    </TBODY>
	                    </TABLE>
	                </TD>
	                </TR>
	                <TR>
	                    <TD background='images/menu/tab_bg.gif' bgColor='#ffffff'><IMG height=1 src='images/menu/tab_bg.gif' width='1'></TD>
	                </TR>
	            </TBODY>
	            </TABLE>
	            <!--选项卡 End-->
	            
	            <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
	                <tr>
	                    <td align="right"></td>
	                </tr>
	            </table>
            </td>
        </tr>
        
    <script type="text/javascript">
            function tabClick(idx,count) {
              for (i_tr = 0; i_tr < count; i_tr++) {
                if (i_tr == idx) {
                  var tabImgLeft = document.getElementById('tabImgLeft__' + idx);
                  var tabImgRight = document.getElementById('tabImgRight__' + idx);
                  var tabLabel = document.getElementById('tabLabel__' + idx);
                  var tabContent = document.getElementById('tabContent__' + idx);
     
                  tabImgLeft.src = 'images/menu/tab_active_left.gif';
                  tabImgRight.src = 'images/menu/tab_active_right.gif';
                  tabLabel.style.backgroundImage = "url(images/menu/tab_active_bg.gif)";
                  tabContent.style.visibility = 'visible';
                  tabContent.style.display = 'block';
                  continue;
                }
                var tabImgLeft = document.getElementById('tabImgLeft__' + i_tr);
                var tabImgRight = document.getElementById('tabImgRight__' + i_tr);
                var tabLabel = document.getElementById('tabLabel__' + i_tr);
                var tabContent = document.getElementById('tabContent__' + i_tr);
     
                tabImgLeft.src = 'images/menu/tab_unactive_left.gif';
                tabImgRight.src = 'images/menu/tab_unactive_right.gif';
                tabLabel.style.backgroundImage = "url(/images/menu/tab_unactive_bg.gif)";
                tabContent.style.visibility = 'hidden';
                tabContent.style.display = 'none';
              }
              //document.getElementById('FrameWork_YOYO_LzppccSelectIndex').value=idx;
            }
            tabClick(0,3);
    </script>

    </table>
</form>
</div>

</body>
</html>