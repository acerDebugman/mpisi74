<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MPISI　HRMS</title>

<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<script src="js/jquery1.4.2.js" type="text/javascript" ></script>


<style type="text/css">
*{overflow:hidden; font-size:9pt;}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(images/bg.gif);
	background-repeat: repeat-x;
}
</style>

<script type="text/javascript">
function detectBrowser(){
	var sAgent = navigator.userAgent.toLowerCase();
	this.isIE = (sAgent.indexOf("msie")!=-1); //IE6.0-7
	this.isFF = (sAgent.indexOf("firefox")!=-1);//firefox
	this.isSa = (sAgent.indexOf("safari")!=-1);//safari
	this.isOp = (sAgent.indexOf("opera")!=-1);//opera
	this.isNN = (sAgent.indexOf("netscape")!=-1);//netscape
	this.isCh = (sAgent.indexOf("chrome")!=-1);//chrome
	this.isMa = this.isIE;//marthon
	this.isOther = (!this.isIE && !this.isFF && !this.isSa && !this.isOp && !this.isNN && !this.isSa);//unknown Browser
}

$(document).ready(function(){	
	$("#userName").change(function(){
		var userName = document.getElementById("userName").value;
		document.getElementById("userName").value = userName.toUpperCase();
	});
	
	var oBrowser = new detectBrowser();

	if (oBrowser.isIE) { 
		document.getElementById("loginBtn").style.display = "block";
		document.getElementById("warnMsg").innerHTML = "";
	} 
	if (oBrowser.isSa && !oBrowser.isCh) {
		document.getElementById("loginBtn").style.display = "none";
		document.getElementById("warnMsg").innerHTML = "Your Browser is Safari Please Choose IE Browser to Open the Website.";
	} 
	if (oBrowser.isOp) {
		document.getElementById("loginBtn").style.display = "none";
		document.getElementById("warnMsg").innerHTML = "Your Browser is Opera Please Choose IE Browser to Open the Website.";
	} 
	if (oBrowser.isCh && oBrowser.isSa) { 
		document.getElementById("loginBtn").style.display = "none";
		document.getElementById("warnMsg").innerHTML = "Your Browser is Chrom Please Choose IE Browser to Open the Website.";
	} 
	if(oBrowser.isFF) { 
		document.getElementById("loginBtn").style.display = "none";
		document.getElementById("warnMsg").innerHTML = "Your Browser is FireFox Please Choose IE Browser to Open the Website.";
	}
});
/* function Fkey(){
	try{
	    var WsShell = new ActiveXObject('WScript.Shell');
	    if(!WsShell){
	      alert('Could not get reference to WScript.Shell');
	    }
	    WsShell.SendKeys('{F11}');
	}catch (errorObject){
        alert('Error:\n' + errorObject.message);
    }
} */
</script>
</head>

<body>

<form method="post" action="loginCheck.action">
<!-- <a href="javascript:Fkey()">屏幕切换</a> -->
<table width="100%"  height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td height="561" style="background:url(images/login/lbg.gif)">
		        <table width="940" border="0" align="center" cellpadding="0" cellspacing="0">
		          <tr>
		            <td height="238" style="background:url(images/login/login012.jpg)"><div id="warnMsg" style="color:red;width:100%;align:center;font-size:18pt;margin-left:100px;"></div></td>
		          </tr>
		          <tr>
		            <td height="190">
			            <table width="100%" border="0" cellspacing="0" cellpadding="0">
			              <tr>
			                <td width="208" height="190" style="background:url(images/login/login02.jpg)">&nbsp;</td>
			                <td width="518" style="background:url(images/login/login03.jpg)">
				                <table width="320" border="0" align="center" cellpadding="0" cellspacing="0">
				                  <tr>
				                    <td width="40px" height="50"><img src="images/login/user.gif" width="30" height="30"/></td>
				                    <td width="60px" height="50">Account:<span style="color:red;">*</span></td>
				                    <td width="220px" height="50">
				                        <input id="userName" autocomplete="off" name="mp1001.MP1001_EMPLOYEE_NUM" maxlength="5" label="Username" style="width:164px; height:32px; line-height:34px; background:url(images/login/inputbg.gif) repeat-x; border:solid 1px #d1d1d1; font-size:9pt; font-family:Verdana, Geneva, sans-serif;"/>
				                        <br/>
				                        <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;"><s:param>mp1001.MP1001_EMPLOYEE_NUM</s:param></s:fielderror>
				                    </td>
				                  </tr>
				                  <tr>
				                    <td height="50"><img src="images/login/password.gif" width="28" height="32"/></td>
				                    <td height="50">Password:<span style="color:red;">*</span></td>
				                    <td height="50">
				                        <s:password name="mp1001.MP1001_PASSWORD" label="Password" maxlength="12" style="width:164px; height:32px; line-height:34px; background:url(images/login/inputbg.gif) repeat-x; border:solid 1px #d1d1d1; font-size:9pt;" theme="simple"></s:password>
				                        <br/>
				                        <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;"><s:param>mp1001.MP1001_PASSWORD</s:param></s:fielderror>
				                    </td>
				                  </tr>
				                  <tr>
				                    <td height="40">&nbsp;</td>
				                    <td height="40">&nbsp;</td>
				                    <td height="60">
				                        <s:submit id="loginBtn" label="" value="" style="width:95px;height:34px;background-image:url(images/login/login01.gif);border:solid 0px blue;" theme="simple"/>
				                    </td>
				                  </tr>
				                </table>
			                </td>
			                <td width="214" style="background:url(images/login/login04.jpg)" >&nbsp;</td>
			              </tr>
			            </table>
		            </td>
		          </tr>
		          <tr>
		            <td height="133" style="background:url(images/login/login05.jpg);border:solid 0px blue;">&nbsp;</td>
		          </tr>
		        </table>
	        </td>
	      </tr>
	    </table>
    </td>
  </tr>
  
  
  <tr>
      <td>
          <table border="0" cellpadding="0" cellspacing="0" width="100%" height="20">
              <tr>
                  <td class="down_text" align="center">
                      Copyright © 2011 - 2012 Mpisi Trading74(pty) ltd.. All Rights Reserved<br/>
                      Version: 2.1.0307 Release
                  </td>
              </tr>
          </table>
      </td>
  </tr>
</table>

</form>

</body>
</html>
