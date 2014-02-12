<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<base target="_blank"/>
<title>Human Resources Management System</title>

<style type="text/css">
.menuButtonCell{width:142px;height:135px;margin:5pt 5pt 5pt 5pt;}
.menuButton{position:relative;color:#FFFFFF;font-style:bold;font-weight:600;font-family:Arial;font-size:11pt;text-decoration: none;width:132px;height:125px;background-image:url(images/logo/MenuButtonOff.gif);cursor: pointer;}
.menuTextOff{border-bottom:none;position:absolute;top:30px;left:18px;}
.menuTextOn{border-bottom:2px solid #EE2222;position:absolute;top:30px;left:18px;}
.menuLabel2{position:relative;border: 1px solid #4387E9;padding:3px;background-color:#CCFFFF;margin-right:15px;margin-top:5px;margin-bottom:20px;height:32px;width:150px;padding:0pt 0pt 0pt 3pt;}
.menuArrowL{position:absolute;left:5px;top:2px;}
.menuLabelText{position:absolute;text-align:center;left:26px;top:8px;width:90px;}
.menuArrowR{position:absolute;left:124px;top:2px;}
</style>

<SCRIPT LANGUAGE="JavaScript">
function changeMenuOn( obj ){
	obj.style.backgroundImage="url(images/logo/MenuButtonOn.gif)";
	obj.firstChild.className="menuTextOn"; 
}
function changeMenuOff( obj ){
	obj.style.backgroundImage="url(images/logo/MenuButtonOff.gif)";
	obj.firstChild.className="menuTextOff";
}
</SCRIPT>

</head>
<body scroll="no" leftmargin="0" topmargin="0" marginheight="0" marginwidth="0">
<form>   

<table border="0" cellspacing="0" cellpadding="0" width="100%" height="100%">
    <tr>
        <td colspan="3" height="100" style="border-bottom: 1px solid #000000;filter:progid:DXImageTransform.Microsoft.Gradient(gradientType='1',startColorstr='#ffffff',endColorstr='#7FFFD4');">
            <div style="border:0px solid blue;float:left;"><img border="0" src="images/logo/logo.png" id="logo1" alt="Logo" style="cursor: hand" /></div>
            
			<div id='topIcon' style="border:0px solid blue;float:right;margin-top:20px;">
				<a onclick="window.frames['mainFrame'].location='personalScheduleMonthInit.action'"><img src='images/login/home.gif' border='0' alt='MY Desktop'></a>
				<a onClick="window.frames['mainFrame'].location='personalScheduleMonthInit.action'"><img src='images/login/refresh.gif'  border='0' alt='Refresh Main Page'></a>
				<a onclick=""><img src='images/login/msg.gif' border='0' alt='Internal Message' id='mymsg'></a>
				<a onclick="window.frames['mainFrame'].location='personalScheduleMonthInit.action'"><img src="images/login/calendar.gif" border='0' alt='Work Calendar'></a>
				<a onclick=""><img src='images/login/help.gif' border='0' alt='Help'></a>
				<a href="login.jsp" target='_top' onclick="return window.confirm('Are you sure you want to exit system?');"><img src="images/login/exit.gif" border='0' alt='Exit System'></a>
			</div>
        </td>
    </tr>
    
    <tr>
        <td style="width:10%;vertical-align:top;filter:progid:DXImageTransform.Microsoft.Gradient(startColorStr='#D6E7F6', endColorStr='#98B2E6', gradientType='0');">
			<div class="menuLabel2" style="margin-top:30px;"> 
				<img class="menuArrowL" src="images/logo/MenuArrow.gif">
				<span class="menuLabelText"> MENU </span>
				<img class="menuArrowR" src="images/logo/MenuArrow.gif" >
			</div>
        </td>
        <td style="width:80%;vertical-align:top;filter:progid:DXImageTransform.Microsoft.Gradient(startColorStr='#D6E7F6', endColorStr='#98B2E6', gradientType='0');">
			<table border=0 cellspacing=0 cellpadding=0 bordercolordark="#ffffff" bordercolor="#4387E9" style="margin-top:100px;margin-left:100px;">
				<tr>
					<td class="menuButtonCell">
						<a href="mainPageInit.action">
							<div class="menuButton" onmouseover="changeMenuOn(this);" onmouseout="changeMenuOff(this)" ><span class="menuTextOff">Human Resources</span></div>
						</a>
					</td>
					<td class="menuButtonCell">
						<a href="pages/pms/mainPmsInit.action">
							<div class="menuButton" onmouseover="changeMenuOn(this);" onmouseout="changeMenuOff(this)" ><span class="menuTextOff">Procurement Online</span></div>
						</a>
					</td>
					<td class="menuButtonCell">
						<a href="pages/performance/performanceMngInit.action">
							<div class="menuButton" onmouseover="changeMenuOn(this);" onmouseout="changeMenuOff(this)" ><span class="menuTextOff">Performance Appraisal</span></div>
						</a>
					</td>
					<td class="menuButtonCell">
						<a href="pages/performance2/performance2MngInit.action">
							<div class="menuButton" onmouseover="changeMenuOn(this);" onmouseout="changeMenuOff(this)" ><span class="menuTextOff">Performance Appraisal(Department)</span></div>
						</a>
					</td>
					<td class="menuButtonCell">
						<a href="pages/itService/itServiceMngInit.action">
							<div class="menuButton" onmouseover="changeMenuOn(this);" onmouseout="changeMenuOff(this)" ><span class="menuTextOff">IT Service</span></div>
						</a>
					</td>
				</tr>
			</table>
        </td>
        <td style="width:10%;filter:progid:DXImageTransform.Microsoft.Gradient(startColorStr='#D6E7F6', endColorStr='#98B2E6', gradientType='0');">&nbsp;</td>
    </tr>
    
    <tr>
        <td colspan="3" height="20">
            <table border="0" cellpadding="0" cellspacing="0" width="100%" height="20">
                <tr>
                    <td class="down_text" align="center">Powered By <a href="http://www.mpisi.com" target="_blank"><font color="#ffffff">Mpisi.com</font></a>Trading74(pty) ltd.</td>
                </tr>
            </table>
        </td>
    </tr>
</table>

</form>
</body>
</html>
