<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Apply Leave</title>
<base target="_self"/>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="css/wbox.css" rel="stylesheet" type="text/css"></link>

<script src="js/calendar.js" type="text/javascript" ></script>
<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery1.4.2.js" type="text/javascript" ></script>

<script src="js/jquery.pager.js" type="text/javascript" ></script>
<script src="js/wbox.js" type="text/javascript" ></script>

<base href="<%=basePath%>">

<script type="text/javascript">
var closeWinFunc = window.close;
window.close = function() {
    window.open("", "_self");
    closeWinFunc();
}
</script>

<script type="text/javascript">
var saveStatus = "false";

$(document).ready(function(){
	var leaveNum = document.getElementById("MP2001_NUM").value;
	if(leaveNum != ""){
		if(document.getElementById("majorList")!=null){
			document.getElementById("majorList").disabled = false;
		}
		if(document.getElementById("leaveType") != null){
			document.getElementById("leaveType").disabled = false;
		}
		if(document.getElementById("fromDay")!=null){
			document.getElementById("fromDay").disabled = false;
		}
		if(document.getElementById("toDay")!=null){
			document.getElementById("toDay").disabled = false;
		}
		if(document.getElementById("workingHours1")!=null){
			document.getElementById("workingHours1").disabled = false;
		}
		if(document.getElementById("workingMinute1")!=null){
			document.getElementById("workingMinute1").disabled = false;
		}
		if(document.getElementById("workingHours2")!=null){
			document.getElementById("workingHours2").disabled = false;
		}
		if(document.getElementById("workingMinute2")!=null){
			document.getElementById("workingMinute2").disabled = false;
		}
		if(document.getElementById("save")!=null){
			document.getElementById("save").disabled = false;
		}
	}

	/*var _actType = document.getElementById("actingType").value;
	if(_actType == "acting"){
		document.getElementById("radio2").checked = true;
		document.getElementById("radio1").checked = false;
		document.getElementById("applyRow").style.display = "block";
	}else if(_actType == "self"){
		document.getElementById("radio2").checked = false;
		document.getElementById("radio1").checked = true;
		document.getElementById("applyRow").style.display = "none";
	}*/
// 	$("#commenttx").change(function(){
// 		var commentObj = document.getElementById("commenttx").value;
// 	    if(commentObj == ""){
// 	    	document.getElementById("save").disabled = true;
// 	    }else{
// 	    	document.getElementById("save").disabled = false;
	    	
// 	    	if(document.getElementById("actingPerson")!=null && document.getElementById("actingPerson").value == "" ){
// 	    		document.getElementById("save").disabled = true;
// 	    	}
// 	    	if(document.getElementById("leaveType")!=null && document.getElementById("leaveType").value == "0" ){
// 	    		document.getElementById("save").disabled = true;
// 	    	}
// 	    	if(document.getElementById("majorList")!=null && document.getElementById("majorList").disabled == false && document.getElementById("majorList").value == "0" ){
// 	    		document.getElementById("save").disabled = true;
// 	    	}
// 	    	if(document.getElementById("fromDay")!=null && document.getElementById("fromDay").value == "" ){
// 	    		document.getElementById("save").disabled = true;
// 	    	}
// 	    	if(document.getElementById("toDay")!=null && document.getElementById("toDay").value == "" ){
// 	    		document.getElementById("save").disabled = true;
// 	    	}
// 	    	if(saveStatus == "true"){
// 	    		document.getElementById("save").disabled = true;
// 	    	}
// 	    }
// 	});
	
	//var multiDayCal = false;
	$("#commenttx").keydown(function(){
		var fromDate = $("#fromDay").val();
		var toDate = $("#toDay").val();
		var val = $("input[name=dayTypeChoose]:checked").val();
		if(val == 'singleDay'){
			if(fromDate != toDate){
				alert("You apply one single day,please make sure from date and to date is the same day!!");
			}
		}
		
		//if(!multiDayCal && val == "multiDays"){
		if(val == "multiDays"){
			/* $("#workingHours1").val("09");
			$("#workingMinute1").val("09");
			$("#workingHours2").val("09");
			$("#workingMinute2").val("09");
			
	    	var hour1 = document.getElementById("workingHours1").value;
			var hour2 = document.getElementById("workingHours2").value;
			var minute1 = document.getElementById("workingMinute1").value;
			var minute2 = document.getElementById("workingMinute2").value;
		
			alert(hour1 + " " + hour2 + " " + minute1 + " " + minute2); */
		
			calcTime();
			//multiDayCal = true;
		}
	});
	
	$("#commenttx").keyup(function(){
		if(document.getElementById("save") != null){
			var commentObj = document.getElementById("commenttx").value;
		    if(commentObj == "" || commentObj.length < 10){
		    	document.getElementById("save").disabled = true;
		    }else{
		    	document.getElementById("save").disabled = false;
		    	
		    	if(document.getElementById("actingPerson")!=null && document.getElementById("actingPerson").value == "" ){
		    		document.getElementById("save").disabled = true;
		    	}
		    	if(document.getElementById("leaveType")!=null && document.getElementById("leaveType").value == "0" ){
		    		document.getElementById("save").disabled = true;
		    	}
		    	if(document.getElementById("majorList")!=null && document.getElementById("majorList").disabled == false && document.getElementById("majorList").value == "0" ){
		    		document.getElementById("save").disabled = true;
		    	}
		    	if(document.getElementById("fromDay")!=null && document.getElementById("fromDay").value == "" ){
		    		document.getElementById("save").disabled = true;
		    	}
		    	if(document.getElementById("toDay")!=null && document.getElementById("toDay").value == "" ){
		    		document.getElementById("save").disabled = true;
		    	}
		    	if(saveStatus == "true"){
		    		document.getElementById("save").disabled = true;
		    	}
		    }
		}
	});
	
	// 代理人改变
	$("#actingPerson").change(function(){
		document.getElementById("applyPersonName1").firstChild.nodeValue = "";
		document.getElementById("actingPersonName1").firstChild.nodeValue = "";
		var person = document.getElementById("actingPerson").value;
		var applyPerson = document.getElementById("applyPerson").value;
		var _type = document.getElementById("actingType").value;
		var params=$("#actingPerson").serialize();
		
		person = person.toUpperCase();
		applyPerson = applyPerson.toUpperCase();
		document.getElementById("actingPerson").value = person;
		document.getElementById("applyPerson").value = applyPerson;
		$.ajax({
			url:"loadActingPerson.action?employeeNum="+person + "&actingType=" + _type + "&personType=actingPerson&actingPersionNum=" + applyPerson,
			type:"post",
			dataType:"html",
			//data: params,
			error:function(ex){
				alert("failed:" + ex.responseText);
				document.getElementById("save").disabled = true;
				//document.getElementById("leaveType").style.disabled= "true";
			},
			success:function(data){
				if(data == "invalid employee number"){
					changeProperty("leaveType",true);
					disableAll();
				}else if(data == "acting person can not be yourself"){
					changeProperty("leaveType",true);
					disableAll();
				}else if(data == "acting person and apply persion are the same."){
					disableAll();
				}else if(data == "must be in the same department"){
					disableAll();
				}else{
					changeProperty("leaveType",false);
				}
				document.getElementById("actingPersonName").firstChild.nodeValue = "\"" + data + "\"";
			}
		});
		return false;
	});
	// 申请人改变
	$("#applyPerson").change(function(){
		document.getElementById("applyPersonName1").firstChild.nodeValue = "";
		document.getElementById("actingPersonName1").firstChild.nodeValue = "";
		var person = document.getElementById("actingPerson").value;
		var applyPerson = document.getElementById("applyPerson").value;
		var _type = document.getElementById("actingType").value;
		var params=$("#applyPerson").serialize();
		
		person = person.toUpperCase();
		applyPerson = applyPerson.toUpperCase();
		document.getElementById("actingPerson").value = person;
		document.getElementById("applyPerson").value = applyPerson;
		$.ajax({
			url:"loadActingPerson.action?employeeNum="+applyPerson + "&actingType=" + _type + "&personType=applyPerson&actingPersionNum=" + person,
			type:"post",
			dataType:"html",
			//data: params,
			error:function(ex){
				alert("failed:" + ex.responseText);
				document.getElementById("save").disabled = true;
				//document.getElementById("leaveType").style.disabled= "true";
			},
			success:function(data){
				var result = data.split("|");
				
				if(result[0] == "invalid employee number"){
					changeProperty("leaveType",true);
					disableAll();
				}else if(result[0] == "acting person can not be yourself"){
					changeProperty("leaveType",true);
					disableAll();
				}else if(result[0] == "acting person and apply persion are the same."){
					disableAll();
				}else if(result[0] == "must be in the same department"){
					disableAll();
				}else{
					changeProperty("leaveType",false);
				}

				document.getElementById("applyPersonName").firstChild.nodeValue = "\"" + result[0] + "\"";
				
				document.getElementById("annualDays").value = result[1];
				document.getElementById("sickDays").value = result[2];
				document.getElementById("familyDays").value = result[3];
				document.getElementById("maternityDays").value = result[4];
				document.getElementById("study").value = result[5];
				
				var majorList = result[6];
				var majorListObj = document.getElementById("majorList");
				majorListObj.options.length = 0;
				var opsDef = document.createElement("option");
				opsDef.value = "0";
				opsDef.innerHTML = "Please Select";
				majorListObj.appendChild(opsDef);
				
				if(majorList != ""){					
					var majorResult = majorList.split(";");

					for(var i=0,j=majorResult.length; i<j; i++){
						var _items = majorResult[i].split(":");
						var _key = _items[0];
						var _value = _items[1];
						
						var ops = document.createElement("option");
						ops.value = _key;
						ops.innerHTML = _value;
						majorListObj.appendChild(ops);
					}
				}
			}
		});
		return false;
	});
	// 科目改变年
	$("#majorList").change(function(){
		var majorSeq = document.getElementById("majorList").value;
		
		$.ajax({
			url:"loadMajorDays.action?mp2004Seq="+majorSeq,
			type:"post",
			dataType:"html",
			//data: params,
			error:function(ex){
				alert("failed:" + ex.responseText);
				document.getElementById("save").disabled = true;
				//document.getElementById("leaveType").style.disabled= "true";
			},
			success:function(data){
				document.getElementById("mp2004MajorDays").value = data;
				var obj = document.getElementById("leftDays");
				obj.innerText = "May apply for the number of days:" + Math.floor(data/8) + " days " + data%8 + " hours";
				
			}
		});
		return false;
	});
	
	//defualt is single day, so hide to day
	//$("#toDay").hide();
});
</script>

<script type="text/javascript">
/*
 * 假期申请用共通方法
 */
// 代理别人请假时用
function disableAll(){
	if(document.getElementById("majorList")!=null){
		document.getElementById("majorList").disabled = true;
		document.getElementById("majorList").value = "0";
	}
	if(document.getElementById("leaveType")!=null){
		document.getElementById("leaveType").disabled = true;
		document.getElementById("leaveType").value = "0";
	}
	if(document.getElementById("fromDay")!=null){
		document.getElementById("fromDay").disabled = true;
		document.getElementById("fromDay").value = "";
	}
	if(document.getElementById("toDay")!=null){
		document.getElementById("toDay").disabled = true;
		document.getElementById("toDay").value = "";
	}
	if(document.getElementById("workingHours1")!=null){
		document.getElementById("workingHours1").disabled = true;
		document.getElementById("workingHours1").value = "";
	}
	if(document.getElementById("workingMinute1")!=null){
		document.getElementById("workingMinute1").disabled = true;
		document.getElementById("workingMinute1").value = "";
	}
	if(document.getElementById("workingHours2")!=null){
		document.getElementById("workingHours2").disabled = true;
		document.getElementById("workingHours2").value = "";
	}
	if(document.getElementById("workingMinute2")!=null){
		document.getElementById("workingMinute2").disabled = true;
		document.getElementById("workingMinute2").value = "";
	}
	if(document.getElementById("save")!=null){
		document.getElementById("save").disabled = true;
	}
}
function showApplyFor(obj){
	var _type = document.getElementById("applyRow");
	if(obj == "true"){
		_type.style.display = "block";
		document.getElementById("actingType").value = "acting";
		document.getElementById("applyPerson").value = "";
		document.getElementById("actingPerson").value = "";
		
		disableAll();
	}else{
		document.getElementById("applyPerson").value = "";
		document.getElementById("actingPerson").value = "";
		
		_type.style.display = "none";
		document.getElementById("actingType").value = "self";
		
		disableAll();
	}
}
// 计算请假时间
function calcTime(){
	document.getElementById("warnMessage").firstChild.nodeValue = "";
	if(document.getElementById("fromDay") && document.getElementById("toDay")){
		var _fromDay = document.getElementById("fromDay").value;
		var _toDay = document.getElementById("toDay").value;
		
		var _hour1 = document.getElementById("workingHours1").value;
		var _hour2 = document.getElementById("workingHours2").value;
		
		var _minute1 = document.getElementById("workingMinute1").value;
		var _minute2 = document.getElementById("workingMinute2").value;
		
		var _value = compareDate(_fromDay,_toDay);

		if(_value == false){
			document.getElementById("warnMessage").firstChild.nodeValue = "The start date or the finish date is error.";
			document.getElementById("save").disabled = true;
		}else if(_fromDay!="" && _toDay!="" && _hour1!="" && _hour2!=""){
			var _countDays = DateDiff(_fromDay,_toDay);
/* 			if(_hour2 > 8 || _hour2 == 8 && _minute2 > 0){
				_countDays = _countDays + 1;
			} */
			var _countHours = _hour2 - _hour1;

			var _time1 = parseInt(_hour1,10) + _minute1/60;
			var _time2 = parseInt(_hour2,10) + _minute2/60;
			
			if(_time1 <= 13 && _time2 > 13 && _fromDay == _toDay){
				_countHours = _countHours - 0.5;
			}else if(_fromDay != _toDay){
				if(_time2 > 13 && _time1 <= 13){
					_countHours = _countHours - 0.5;
				}else if(_time2 <= 13 && _time1 > 13){
					_countHours = _countHours + 0.5;
				}
			}

			var _weekEndDays = getWeeks(_fromDay, _toDay, _countDays);
		    var _totalTime = ((_countDays - _weekEndDays)*8 + _countHours)*60 + (_minute2 - _minute1);
		    var _totalHour = Math.floor(_totalTime/60);

		    document.getElementById("days").value = Math.floor(_totalHour/8);
		    document.getElementById("Hours").value = _totalHour%8;
		    document.getElementById("Minutes").value = _totalTime%60;

		    // 判断是否启用保存按钮
			var annualDays = document.getElementById("annualDays").value;
			var sickDays = document.getElementById("sickDays").value;
			var familyDays = document.getElementById("familyDays").value;
			var study = document.getElementById("mp2004MajorDays").value;
			var maternityDays = document.getElementById("maternityDays").value;
			
			var type = document.getElementById("leaveType").value;

			// 请假的最小单位为1小时，如果有半小时的则校验不通过
			if(_totalTime%60 == 30){
				saveStatus = "true";
				document.getElementById("save").disabled = true;
				document.getElementById("warnMessage").innerText = "The leave time cannot be half hour, the minimum unit is one hour.";
				//alert("The leave time cannot be half hour, the minimum unit is one hour.");
			}else{
				saveStatus = "false";
				document.getElementById("warnMessage").innerText = "";
				var commentObj = document.getElementById("commenttx").value;
				if(1 == type && _totalTime <= annualDays*60){//Annual
					document.getElementById("save").disabled = false;
				}else if(2 == type && _totalTime <= sickDays*60){//Sick
					//document.getElementById("save").disabled = false;
				    if(commentObj == ""){
				    	document.getElementById("save").disabled = true;
				    }else{
				    	document.getElementById("save").disabled = false;
				    }
				}else if(3 == type && _totalTime <= familyDays*60){//Family
					//document.getElementById("save").disabled = false;
				    if(commentObj == ""){
				    	document.getElementById("save").disabled = true;
				    }else{
				    	document.getElementById("save").disabled = false;
				    }
				}else if(4 == type && _totalTime <= study*60){//Study
					document.getElementById("save").disabled = false;
				}else if(5 == type && _totalTime <= maternityDays*60){//Maternity
					document.getElementById("save").disabled = false;
				}else if(6 == type){//unpaid
					document.getElementById("save").disabled = false;
				}else if(7 == type){//official business
					//document.getElementById("save").disabled = false;
				    if(commentObj == ""){
				    	document.getElementById("save").disabled = true;
				    }else{
				    	document.getElementById("save").disabled = false;
				    }
				}else if(8 == type){//other
					//document.getElementById("save").disabled = false;
				    if(commentObj == ""){
				    	document.getElementById("save").disabled = true;
				    }else{
				    	document.getElementById("save").disabled = false;
				    }
				}else{
					document.getElementById("save").disabled = true;
				}
			}
		}
	}
}
//速度太慢，该方法废弃
function isHoliday(holidayArr,checkDate){
	var ret = false;
	for(i=0,j=holidayArr.length; i<j; i++){
		if(holidayArr[i] == checkDate){
			ret = true;
			break;
		}
	}
	return ret;
}
// 比较日期大小
function compareDate(fromDate,endDate){
	var ret = true;
	var _from = new Date(fromDate.replace(/-/g,"/"));
	var _to = new Date(endDate.replace(/-/g,"/"));
	var _fromH = document.getElementById("workingHours1").value;
	var _toH = document.getElementById("workingHours2").value;
	var _fromM = document.getElementById("workingMinute1").value;
	var _toM = document.getElementById("workingMinute2").value;
	
	var _fromTimes = _from.getTime();
	var _toTimes = _to.getTime();
	
	if(_fromTimes > _toTimes){
		ret = false;
	}else if(_fromTimes == _toTimes && parseInt(_fromH,10) > parseInt(_toH,10)){
		ret = false;
	}else if(_fromTimes == _toTimes && parseInt(_fromH,10) == parseInt(_toH,10) && parseInt(_fromM) >= parseInt(_toM) ){
		ret = false;
	}
	return ret;
}
//返回两个日期相差的周数
function getWeeks(_from,_to,_days){
//alert("GetWeeks:" + _from + "|" + _to + "|" + _days);
	var  Weekdaycount=0;
	var  noWeekdaycount=0;
	var _holiday = document.getElementById("holidayInfo").value.replace(/-/g,"/");
	var _departmentId = document.getElementById("departmentID").value;
	var _positionId = document.getElementById("positionLev").value;
	
	_from = _from.replace(/-/g,"/");
	
	for (var i=1;i<=_days;i++){
		var myweekday=new Date(_from).getDay();
		if((myweekday == 0) || (myweekday == 6)){
			if(_departmentId == '12' || _positionId == '136'){
				//nothing to do
			}else{
				Weekdaycount++;
			}
		}else{
			// 判断是否南非假日
			var checkDate = _from.replace(/-/g,"/");
			if(_holiday.indexOf(checkDate) < 0){
				noWeekdaycount++;
			}else{
				Weekdaycount++;
			}
		}
		var a = new Date(_from.replace(/-/g,"/"));
		a = a.valueOf();
		a=a+(24*60*60*1000);
		a = new Date(a);
		//_from=a.getFullYear()+"/"+(a.getMonth()+ 1)+"/"+a.getDate();
		_from=a.getFullYear();
		if(a.getMonth() < 9){
			_from += "/"+"0" + (a.getMonth()+ 1);
		}else{
			_from += "/"+(a.getMonth()+ 1);
		}
		if(a.getDate() < 10){
			_from += "/"+"0"+a.getDate();
		}else{
			_from += "/"+a.getDate();
		}
	}

//alert("WeekDaycount:" + Weekdaycount);
	
	return Weekdaycount;
}
//計算天數的函數
function  DateDiff(beginDate,  endDate){    //beginDate和endDate都是2007-8-10格式
	//alert("Date Diff:" + beginDate + "|" + endDate);
    var  arrbeginDate,  Date1,  Date2, arrendDate,  iDays;
    arrbeginDate=  beginDate.split("-");
    Date1=  new  Date(arrbeginDate[1]  +  '-'  +  arrbeginDate[2]  +  '-'  +  arrbeginDate[0]);   //轉換為2007-8-10格式
   arrendDate=  endDate.split("-");
    Date2=  new  Date(arrendDate[1]  +  '-'  +  arrendDate[2]  +  '-'  +  arrendDate[0]);
    iDays  =  parseInt(Math.abs(Date1-  Date2)  /  1000  /  60  /  60  /24); //轉換為天數 

    return  iDays;
}
// 检查是否加备注信息
function checkComment(){
	var comment = document.getElementById("commenttx").value;
	if(comment==""){
		
		return "false";
	}
}

// 改变对象属性
function changeProperty(obj,flag){
	document.getElementById(obj).disabled = flag;
	
	if(obj == "leaveType"){
		document.getElementById(obj).disabled = flag;
		var _type = document.getElementById("leaveType");
		if(_type.value == "0"){
			changeProperty("workingHours1",true);
			changeProperty("workingMinute1",true);
			changeProperty("workingHours2",true);
			changeProperty("workingMinute2",true);
		}
	}else{
		document.getElementById(obj).disabled = flag;
	}
}
</script>

<script type="text/javascript">
//选择请假的类型后，自动计算还有多少假期可以使用
function showLeftDays(){
	var annualDays = document.getElementById("annualDays").value;
	var sickDays = document.getElementById("sickDays").value;
	var familyDays = document.getElementById("familyDays").value;
	var study = document.getElementById("study").value;
	var maternityDays = document.getElementById("maternityDays").value;
	
	var obj = document.getElementById("leftDays");
	var type = document.getElementById("leaveType").value;
	
	if(type == 0){
		if(document.getElementById("majorList")!=null){
			document.getElementById("majorList").disabled = true;
			document.getElementById("majorList").value = "0";
		}
		if(document.getElementById("fromDay")!=null){
			document.getElementById("fromDay").disabled = true;
			document.getElementById("fromDay").value = "";
		}
		if(document.getElementById("toDay")!=null){
			document.getElementById("toDay").disabled = true;
			document.getElementById("toDay").value = "";
		}
		if(document.getElementById("workingHours1")!=null){
			document.getElementById("workingHours1").disabled = true;
			document.getElementById("workingHours1").value = "";
		}
		if(document.getElementById("workingMinute1")!=null){
			document.getElementById("workingMinute1").disabled = true;
			document.getElementById("workingMinute1").value = "";
		}
		if(document.getElementById("workingHours2")!=null){
			document.getElementById("workingHours2").disabled = true;
			document.getElementById("workingHours2").value = "";
		}
		if(document.getElementById("workingMinute2")!=null){
			document.getElementById("workingMinute2").disabled = true;
			document.getElementById("workingMinute2").value = "";
		}
		if(document.getElementById("save")!=null){
			document.getElementById("save").disabled = true;
		}
	}else if(type == 4){
		document.getElementById("majorList").disabled = false;

		document.getElementById("fromDay").disabled = true;
		document.getElementById("toDay").disabled = true;
		document.getElementById("workingHours1").disabled = true;
		document.getElementById("workingMinute1").disabled = true;
		document.getElementById("workingHours2").disabled = true;
		document.getElementById("workingMinute2").disabled = true;
		document.getElementById("save").disabled = true;
		
		document.getElementById("majorList").value = "0";
		document.getElementById("fromDay").value = "";
		document.getElementById("toDay").value = "";
		document.getElementById("workingHours1").value = "";
		document.getElementById("workingMinute1").value = "";
		document.getElementById("workingHours2").value = "";
		document.getElementById("workingMinute2").value = "";
	}else{
		changeProperty("majorList",true);
		changeProperty("fromDay",false);
		changeProperty("toDay",false);
		changeProperty("workingHours1",false);
		changeProperty("workingMinute1",false);
		changeProperty("workingHours2",false);
		changeProperty("workingMinute2",false);
		
		document.getElementById("majorList").value = "0";
	}
	
	document.getElementById("save").disabled = true;
	
	document.getElementById("fromDay").value="";
	document.getElementById("toDay").value="";
	
	document.getElementById("workingHours1").value="08";
	document.getElementById("workingHours2").value="08";
	document.getElementById("workingMinute1").value="00";
	document.getElementById("workingMinute2").value="00";
	
	document.getElementById("days").value="0";
	document.getElementById("Hours").value="0";
	document.getElementById("Minutes").value="0";

	if(1 == type){//Annual
		//var _hour1 = Math.floor(annualDays/60);
		//obj.innerText = "May apply for the number of days:" + Math.floor(_hour1/8) + " days " + _hour1%8 + " hours" + annualDays%60 + " minutes";
		obj.innerText = "May apply for the number of days:" + Math.floor(annualDays/8) + " days " + annualDays%8 + " hours";
	}else if(2 == type){//Sick
		//var _hour2 = Math.floor(sickDays/60);
		//obj.innerText = "May apply for the number of days:" + Math.floor(_hour2/8) + " days " + _hour2%8 + " hours" + sickDays%60 + " minutes";
		obj.innerText = "May apply for the number of days:" + Math.floor(sickDays/8) + " days " + sickDays%8 + " hours";
	}else if(3 == type){//Family
		//var _hour3 = Math.floor(familyDays/60);
		//obj.innerText = "May apply for the number of days:" + Math.floor(_hour3/8) + " days " + _hour3%8 + " hours" + familyDays%60 + " minutes";
		obj.innerText = "May apply for the number of days:" + Math.floor(familyDays/8) + " days " + familyDays%8 + " hours";
	}else if(4 == type){//Study
		//var _hour4 = Math.floor(study/60);
		//obj.innerText = "May apply for the number of days:" + Math.floor(_hour4/8) + " days " + _hour4%8 + " hours" + study%60 + " minutes";
		obj.innerText = "May apply for the number of days:" + Math.floor(study/8) + " days " + study%8 + " hours";
		// 显示科目信息
		document.getElementById("majorList").style.display = "block";
	}else if(5 == type){//Maternity
		//var _hour5 = Math.floor(maternityDays/60);
		//obj.innerText = "May apply for the number of days:" + Math.floor(_hour5/8) + " days " + _hour5%8 + " hours" + maternityDays%60 + " minutes";
		obj.innerText = "May apply for the number of days:" + Math.floor(maternityDays/8) + " days " + maternityDays%8 + " hours";
	}else{
		obj.innerText = "";
	}
}
function showMjorLeftDays(){
	var type = document.getElementById("majorList").value;

	if(type == 0){
		changeProperty("fromDay",true);
		changeProperty("toDay",true);
		changeProperty("workingHours1",true);
		changeProperty("workingMinute1",true);
		changeProperty("workingHours2",true);
		changeProperty("workingMinute2",true);
	}else{
		changeProperty("fromDay",false);
		changeProperty("toDay",false);
		changeProperty("workingHours1",false);
		changeProperty("workingMinute1",false);
		changeProperty("workingHours2",false);
		changeProperty("workingMinute2",false);
	}
	
	document.getElementById("save").disabled = true;
	
	document.getElementById("fromDay").value="";
	document.getElementById("toDay").value="";
	
	document.getElementById("workingHours1").value="08";
	document.getElementById("workingHours2").value="08";
	document.getElementById("workingMinute1").value="00";
	document.getElementById("workingMinute2").value="00";
	
	document.getElementById("days").value="0";
	document.getElementById("Hours").value="0";
	document.getElementById("Minutes").value="0";
}

function chooseDayType(){
	var val = $("input[name=dayTypeChoose]:checked").val();
	//alert(val);
	if('singleDay' == val){
		/* document.getElementById("workingHours1").style.display = "block";
		document.getElementById("workingMinute1").style.display = "block";
		document.getElementById("workingHours2").style.display = "block";
		document.getElementById("workingMinute2").style.display = "block"; 
		changeProperty("workingHours1",true);
		changeProperty("workingMinute1",true);
		changeProperty("workingHours2",true);
		changeProperty("workingMinute2",true);
		*/
		//$("#fromDay").val("");
		//$("#to").val("");
		$("#workingHours1").val("08");
		$("#workingMinute1").val("00");
		$("#workingHours2").val("09");
		$("#workingMinute2").val("00");
		
		//$("#toDay").hide();
		$("#labelHours1").show();
		$("#labelHours2").show();
		$("#workingHours1").show();
		$("#workingMinute1").show();
		$("#workingHours2").show();
		$("#workingMinute2").show();
		
		document.getElementById("workingHours1").disabled = false;
		document.getElementById("workingMinute1").disabled = false;
		document.getElementById("workingHours2").disabled = false;
		document.getElementById("workingMinute2").disabled = false;
	}
	if('multiDays' == val){
		//$("#toDay").val("");
		$("#workingHours1").val("16");
		$("#workingMinute1").val("30");
		$("#workingHours2").val("16");
		$("#workingMinute2").val("30");
		
		//$("#toDay").show();
		$("#labelHours1").hide();
		$("#labelHours2").hide();
		$("#workingHours1").hide();
		$("#workingMinute1").hide();
		$("#workingHours2").hide();
		$("#workingMinute2").hide();
		
		/* document.getElementById("workingHours1").disabled = true;
		document.getElementById("workingMinute1").disabled = true;
		document.getElementById("workingHours2").disabled = true;
		document.getElementById("workingMinute2").disabled = true; */
	}
}

function joeTest(){
	alert($("#toDay").val());
}

</script>
<script>

</script>
</head>
<body>
<s:actionmessage cssStyle="list-style-type:none;" escape="false"/>
	<div>
		<s:form id="from2" action="shiftworkLeaveSubmit" method="post" theme="simple">
		    <input id="type" name="type" value="${type}" type="hidden" />
		    <input id="roleGroup" name="roleGroup" value="${roleGroup}" type="hidden" />
		    <input id="MP2001_NUM" name="MP2001_NUM" value="${MP2001_NUM}" type="hidden" />
		    <input id="positionLev" name="positionLev" value="${positionLev}" type="hidden" />
		    <input id="departmentID" name="departmentID" value="${departmentID}" type="hidden" />
		    <input id="annualDays" name="annualDays" value="${annualDays}" type="hidden" />
		    <input id="sickDays" name="sickDays" value="${sickDays}" type="hidden" />
		    <input id="familyDays" name="familyDays" value="${familyDays}" type="hidden" />
		    <input id="maternityDays" name="maternityDays" value="${maternityDays}" type="hidden" />
		    <input id="study" name="study" value="${study}" type="hidden" />
		    <input id="holidayInfo" name="holidayInfo" value="${holidayInfo}" type="hidden" />
		    <input id="actingType" name="actingType" value="${actingType}" type="hidden" />
		    <input id="personType" name="personType" value="${personType}" type="hidden" />
		    <input id="mp2004MajorDays" name="mp2004MajorDays" value="${mp2004MajorDays}" type="hidden" />
		    
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td>
						<!-- 头部菜单 Start -->
						<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
							<tr>
								<td class='menubar_title'><img border='0' src='images/icon/config.gif' hspace='3' vspace='3' />&nbsp;Shift Work Leave Management</td>
								<td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' border='0' />&nbsp;help？</td>
							</tr>
							<tr>
								<td height='27px' class='menubar_function_text'>Operation Function：Shift Work Leave Management</td>
								<td class='menubar_menu_td' align='right'>
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class="menubar_button" id="button_0"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height='5px' colspan='2'></td>
							</tr>
						</table>
						<!-- 头部菜单 End -->

                        <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" style="padding-bottom:50px;">
<s:if test="MP2001_NUM != ''" >
                            <tr>
                                <td class="table_body">NO.</td>
                                <td class="table_none">
                                    <s:property value="mp2001.MP2001_NUM"></s:property>
                                </td>
                            </tr>
</s:if>
                            <tr>
                                <td class="table_body">Employee Number</td>
                                <td class="table_none">
                                    <s:property value="mp2001.MP2001_ACTING_APPLICATION_PERSON"></s:property>&nbsp;<s:property value="mp2001.MP2001_ACTING_APPLICATION_PERSON_NAME"></s:property>
                                </td>
                            </tr>
                            <tr>
                                <td class="table_body">Type of Application</td>
                                <td class="table_none">
                                    <input id="shiftWorkRadio" name="shiftWorkRadio" value="shiftwork" type="radio" checked="checked"/>Shift Work Application&nbsp;&nbsp;
                                </td>
                            </tr>
                            <tr id="applyRow" style="display:none;">
                                <td class="table_body">Apply for</td>
                                <td class="table_none">
<s:if test="MP2001_NUM == '' || type == 'edit'" >
                                    <input id="applyPerson" name="mp2001.MP2001_EMPLOYEE_NUM" maxlength="5" value="${mp2001.MP2001_EMPLOYEE_NUM}" type="text" class="text_input"/>
                                    <label id="applyPersonName" style="color:red;">&nbsp;</label>
                                    <label id="applyPersonName1" style="color:red;">&nbsp;</label>
                                    <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
                                        <s:param>mp2001.MP2001_EMPLOYEE_NUM</s:param>
                                    </s:fielderror>
</s:if>
<s:else>
	                                    <s:property value="mp2001.MP2001_EMPLOYEE_NUM"></s:property>
</s:else>
                                </td>
                            </tr>
                            <tr>
                                <td class="table_body">Acting Person<span class="errorcss">*</span></td>
                                <td class="table_none">
<s:if test="MP2001_NUM == '' || type == 'edit'" >
	                                    <input id="actingPerson" name="mp2001.MP2001_ACTING_PERSON" maxlength="5" value="${mp2001.MP2001_ACTING_PERSON}" type="text" class="text_input"/>
	                                    <label id="actingPersonName" style="color:red;">&nbsp;</label>
	                                    <label id="actingPersonName1" style="color:red;">&nbsp;</label>
	                                    <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
	                                        <s:param>mp2001.MP2001_ACTING_PERSON</s:param>
	                                    </s:fielderror>
</s:if>
<s:else>
	                                    <s:property value="mp2001.MP2001_ACTING_PERSON"></s:property>
</s:else>
                                </td>
                            </tr>
                            <tr>
                                <td class="table_body">Type of leave<span class="errorcss">*</span></td>
                                <td class="table_none">
<s:if test="MP2001_NUM == '' || type == 'edit'" >
                                    <div style="float:left;">
                                        <s:select id="leaveType" name="mp2001.MP2001_LEAVE_TYPE" list="leaveTypeList" onchange="showLeftDays()" theme="simple" disabled="true"/>
                                    </div>
                                    <div style="float:left;margin-left:5px;">
                                        <s:select id="majorList" name="mp2001.MP2001_MAJOR_SEQ" list="majorList" onchange="showMjorLeftDays()" theme="simple" disabled="true" />
                                    </div>
                                    <span id="leftDays" style="color:red;"></span>
	                                <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
	                                    <s:param>mp2001.MP2001_LEAVE_TYPE</s:param>
	                                </s:fielderror>
	                                <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
	                                    <s:param>mp2001.MP2001_MAJOR_SEQ</s:param>
	                                </s:fielderror>
</s:if>
<s:else>
	                                    <s:property value="mp2001.MP2001_LEAVE_TYPE_NAME"></s:property>
</s:else>
                                </td>
                            </tr>
                            <tr>
                            	<td class="table_body">Days Apply<span class="errorcss">*</span></td>
                                <td class="table_none">
                                	<input id="days_0" type="radio" name="dayTypeChoose" checked="checked" onclick="chooseDayType()" value="singleDay"/>Single Day&nbsp;&nbsp;
                                	<input id="days_1" type="radio" name="dayTypeChoose" value="multiDays" onclick="chooseDayType()" />Several Days&nbsp;&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td class="table_body">From Date<span class="errorcss">*</span></td>
                                <td class="table_none">
<s:if test="MP2001_NUM == '' || type == 'edit'" >
	                                    <input id="fromDay" name="mp2001.MP2001_FROM_DATETIME" value="${mp2001.MP2001_FROM_DATETIME}" type="text" style="width:70px;" class="text_input" onclick="calendar(this);" onchange="" disabled="disabled"/>
	                                    <span id="labelHours1">Time&nbsp;<s:select id="workingHours1" name="workingHours1" list="workingHoursFromList" style="height:20px;" theme="simple" onchange="calcTime()" disabled="true"/>:
	                                    <s:select id="workingMinute1" name="workingMinute1" list="workingMinuteList" style="height:20px;" theme="simple" onchange="calcTime()" disabled="true"/></span>
	                                    <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
	                                        <s:param>mp2001.MP2001_FROM_DATETIME</s:param>
	                                    </s:fielderror>
</s:if>
<s:else>	
	                                    <s:property value="mp2001.MP2001_FROM_DATETIME"></s:property>
	                                    <s:property value="workingHours1"></s:property>:<s:property value="workingMinute1"></s:property>
	                                    
</s:else>
                                </td>
                            </tr>
                            <tr>
                                <td class="table_body">To Date<span class="errorcss">*</span></td>
                                <td class="table_none">
<s:if test="MP2001_NUM == '' || type == 'edit'" >
	                                    <input id="toDay" name="mp2001.MP2001_TO_DATETIME" value="${mp2001.MP2001_TO_DATETIME}" type="text" style="width:70px;" class="text_input" onclick="calendar(this);" disabled="disabled"/>
	                                    <span id="labelHours2">Time&nbsp;<s:select id="workingHours2" name="workingHours2" list="workingHoursToList" theme="simple" onchange="calcTime()" disabled="true"/>:
	                                    <s:select id="workingMinute2" name="workingMinute2" list="workingMinuteList" style="height:20px;" theme="simple" onchange="calcTime()" disabled="true"/></span>
	                                    <label id="warnMessage" style="color:red;">&nbsp;</label>
	                                    <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
	                                        <s:param>mp2001.MP2001_TO_DATETIME</s:param>
	                                    </s:fielderror>
</s:if>
<s:else>
	                                    <s:property value="mp2001.MP2001_TO_DATETIME"></s:property>
	                                    <s:property value="workingHours2"></s:property>:<s:property value="workingMinute2"></s:property>
</s:else>
                                </td>
                            </tr>
                            <tr>
                                <td class="table_body">No. of days<span class="errorcss">*</span></td>
                                <td class="table_none">
<s:if test="MP2001_NUM == '' || type == 'edit'" >
	                                    <input id="days" name="days" value="${days}" type="text" class="text_input" onblur="" style="width:30px; border:solid 0 transparent;color:red;" readonly="readonly"/>Days
	                                    <input id="Hours" name="Hours" value="${Hours}" type="text" class="text_input" onblur="" style="width:30px;; border:solid 0 transparent;color:red;" readonly="readonly"/>Hours
	                                    <input id="Minutes" name="Minutes" value="${Minutes}" type="text" class="text_input" onblur="" style="width:30px;; border:solid 0 transparent;color:red;" readonly="readonly"/>Minutes
	                                    <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
	                                        <s:param>days</s:param>
	                                    </s:fielderror>
	                                    <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
	                                        <s:param>Hours</s:param>
	                                    </s:fielderror>
</s:if>
<s:else>
	                                    <s:property value="mp2001.MP2001_DAYS_D"></s:property>
</s:else>
                                </td>
                            </tr>
                            <tr id="managerApproval">
                                <td class="table_body">Comment<span class="errorcss">*</span></td>
                                <td class="table_none">
                                    <s:textarea id="commenttx" name="mp2001.MP2001_COMMENT" style="width:300px;height:60px;" theme="simple"></s:textarea>
                                    <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
                                        <s:param>mp2001.MP2001_COMMENT</s:param>
                                    </s:fielderror>
                                </td>
                            </tr>
							<tr>
							    <td colspan="2">
							        <div style="width:100%;color:red;border:solid 0px red;font-weight:bold;">
							            Tips:<br/>
							                                            ※  When the type of application is SICK   or  Family Resp  or  Official Business, Comments must be filled(The minimum length of comment is 10).<br/>
							                                            ※  The leave time cannot be half hour, the minimum unit is one hour.
							        </div>
							    </td>
							</tr>
                        </table>
					</td>
				</tr>

				<tr>
					<td class="menubar_button" id="button_0" align="center">
					    <s:if test="type == 'approval'" >
						    <s:submit name="Approval" value="Approval" align="left" method="approvalLeaveInfo" click="" theme="simple"/>
						    <s:submit name="notApproval" value="Not Approval" align="left" method="notApprovalLeaveInfo" onclick="" theme="simple"/>
					    </s:if>
					    <s:else>
					        <!--<s:submit id="save" name="save" value="save" align="left" method="addLeaveApply" theme="simple" disabled="true"/>-->
					        <s:submit id="save" name="save" value="save" align="left" method="shiftworkAddLeaveApply" theme="simple" disabled="true"/>
					    </s:else>
                        <input type="button" onclick="window.close()" name="close" value="Close" />
					</td>
				</tr>
			</table>
		</s:form>
	</div>

</body>
</html>
