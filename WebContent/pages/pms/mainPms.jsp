<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />

<title>Procurement Management System</title>

<style type="text/css"> 
.navPoint
{
    font-family: Webdings;
    font-size:9pt;
    color:white;
    cursor:pointer;
}
p{
   font-size:9pt;
}
.font_size {  font-family: "Verdana", "Arial", "Helvetica", "sans-serif"; font-size: 12px; font-weight: normal; font-variant: normal; text-transform: none}
</style>

<script type="text/javascript">
$(document).ready(function() {
    var _prePage = $("#prePage").val();
    var group = $("#func0001002").val();
	if(_prePage != ""){
		//document.getElementById("mainFrame").src=_prePage;//用来保存用户上次登陆的界面
	}
	// 判断是否为HR主管
	if(group == 1){
		var popstyle="dialogTop:300px;dialogLeft:300px;help:no;center:yes;dialogHeight:600px;dialogWidth:950px;status:no;resizable:yes;scroll:no;";
		window.showModalDialog("tmpEmployeeView.action", window, popstyle);
	}
});
</script>

</head>
<body scroll="no" leftmargin="0" topmargin="0" marginheight="0" marginwidth="0">
<form>
    <input id="prePage" name="prePage" value="${prePage}" type="hidden"></input>
    <input id="group" name="group" value="${group}" type="hidden"></input>
    <input id="func0001002" name="func0001002" value="${func0001002}" type="hidden"></input>
    
    <table border="0" cellspacing="0" cellpadding="0" width="100%" height="100%">
        <tr>
            <td colspan="3" height="20">
                <table border="0" cellpadding="0" cellspacing="0" width="100%" height="20">
                    <tr>
                        <td class="" style="font-family: Arial;	font-size:8pt;background-color:#FFFFFF;color:#000000;" align="center">&nbsp;</td>
                        <td style="cursor:pointer;border-left:0px solid #000000;" align="right">
                            <font color="#000000" style="margin-right:20px;"><s:property value="mp1001.MP1001_EMPLOYEE_NUM"/></font>&nbsp;
                            <font color="#000000" style="margin-right:20px;"><s:property value="mp1001.MP1001_PREFERED_NAME"/></font>
                        </td>
                        <td align="right" width="300px" bgcolor="#FFFFFF">
                            <table border="0" cellpadding="0" cellspacing="0" width="100%" style="">
                                <tr>
                                    <td style="cursor:pointer;border-left:1px solid #000000;" onClick="javascript:showPopWin('About','about.aspx',510, 170, null,false)">&nbsp;<img src="images/icon/info.gif" style="margin-bottom: -3px" />&nbsp;<font color="#000000">Version</font></td>
                                    <td style="cursor:pointer;border-left:1px solid #000000;" onClick="javascript: window.mainFrame.location.href='../../bulletinList.action'">&nbsp;<img src="images/icon/house.gif" style="margin-bottom: -3px" />&nbsp;<font color="#000000">Home</font></td>
                                    <td style="cursor:pointer;border-left:1px solid #000000;" onClick="javascript: window.top.location.href = '../../logout.action'">&nbsp;<img src="images/icon/logout.gif" style="margin-bottom: -3px" />&nbsp;<font color="#000000">Logout</font></td>
                                    <td style="cursor:pointer;border-left:1px solid #000000;" onClick="javascript:window.open('');">&nbsp;<img src="images/icon/help.gif" style="margin-bottom: -3px" />&nbsp;<font color="#000000">Help</font></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>

        <tr>
            <td width="100%" height="50" colspan="3" style="border-bottom: 1px solid #000000">
                <table height="49" border="0" cellspacing="0" cellpadding="0" width="100%" class="font_size">
                    <tr>
                        <td width="192">
                            <img border="0" src="images/logo/logo.gif" id="logo1" alt="Logo" style="cursor: hand" />
                        </td>
                        <td width="1343" valign="bottom" align="left" style="background-image: url(images/logo/top-gray.gif); background-repeat: no-repeat; background-position: right top">
                            <!--<img border="0" src="images/logo1.gif" id="logo2" alt="Logo1" style="cursor: hand;padding-bottom:2px" />-->
                            <div class="top_test" style="font-size:16px; color:#666666; padding-bottom:15px;"><em>We provide services and beyond ... </em></div>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        
        <tr>
            <td id="frmTitle" name="frmTitle" nowrap="nowrap" valign="middle" align="center" width="198" style="border-right: 1px solid #000000;">
                <iframe name="BoardTitle" style="padding-top:0px; height: 100%; visibility: inherit; width: 198; z-index: 2" scrolling="auto" frameborder="0" src="pages/pms/loadPmsMenu.action"></iframe>
            </td>
            <td style="width: 10pt" bgcolor="#7898A8" width="10" title="关闭/打开左栏" class="navPoint">
                <table border="0" cellpadding="0" cellspacing="0" width="11" height="100%" align="right">
                    <tr>
                        <td valign="middle" align="right"  class="middleCss">
                            <img border="0" src="images/menu//close.gif" id="menuimg" alt="隐藏左栏" onMouseOver="javascript: menuonmouseover();" onmouseout="javascript: menuonmouseout();" onClick="javascript:switchSysBar()" style="cursor: hand" width="11" height="76" />
                        </td>
                    </tr>
                </table>
            </td>
            <td style="width: 100%">
                <iframe id="mainFrame" name="mainFrame" style="height: 100%; visibility: inherit; width: 100%; z-index: 1" scrolling="auto" frameborder="0" src=""></iframe>
            </td>
        </tr>
        
        <tr>
            <td colspan="3" height="20">
                <table border="0" cellpadding="0" cellspacing="0" width="100%" height="20">
                    <tr>
                        <td class="down_text" align="center">
                            Powered By <a href="http://www.mpisi.com" target="_blank"><font color="#ffffff">Mpisi.com</font></a>Trading74(pty) ltd.
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
 
<script type="text/javascript"> 
var DispClose = true;
function CloseEvent()
{
    if (DispClose)
    {
        return "是否离开当前页面?";
    }
}
window.onbeforeunload=CloseEvent;
rnd.today=new Date(); 
rnd.seed=rnd.today.getTime();  
function rnd() {
    rnd.seed = (rnd.seed*9301+49297) % 233280;  
    return rnd.seed/(233280.0); 
}; 

function rand(number) {  
    return Math.ceil(rnd()*number); 
}; 
    
function AlertMessageBox(Messages){
    DispClose = false; 
    alert(Messages);
    setTimeout("reload()",100)
    //window.location.reload();
    //window.location.href = location.href+"?"+rand(1000000);        
}
function reload(){
    window.location.href = location.href+"?"+rand(1000000);
}
    
var var_frmTitle = document.getElementById("frmTitle");
var var_menuimg  = document.getElementById("menuimg");
 
function switchSysBar(){
    if (var_frmTitle.style.display=="none") {
		var_frmTitle.style.display="";
		var_menuimg.src="images/menu/close.gif";
		var_menuimg.alt="隐藏左栏";
		}
	else {
		var_frmTitle.style.display="none";
		var_menuimg.src="images/menu/open.gif";
		var_menuimg.alt="开启左栏";
	 }
}
 function menuonmouseover(){
    if (var_frmTitle.style.display=="none") {
        var_menuimg.src="images/menu/open_on.gif";
    }
    else{
        var_menuimg.src="images/menu/close_on.gif";
    }
 }
 function menuonmouseout(){
    if (var_frmTitle.style.display=="none") {
 		var_menuimg.src="images/menu/open.gif";
    }
    else{
        var_menuimg.src="images/menu/close.gif";
    }
 }
if(top!=self)
{
    top.location.href = "";
}
</script>
