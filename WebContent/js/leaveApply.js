/*
 * 假期申请用共通方法
 */
// 代理别人请假时用
function showApplyFor(obj){
	var _type = document.getElementById("applyRow");
	if(obj == "true"){
		_type.style.display = "block";
		document.getElementById("actingType").value = "acting";
		document.getElementById("applyPerson").value = "";
	}else{
		_type.style.display = "none";
		document.getElementById("actingType").value = "self";
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
		    var _totalTime = ((_countDays - _weekEndDays)*8 + _countHours)*60 + (_minute2 - _minute1);;
		    var _totalHour = Math.floor(_totalTime/60);
		    
		    document.getElementById("days").value = Math.floor(_totalHour/8);
		    document.getElementById("Hours").value = _totalHour%8;
		    document.getElementById("Minutes").value = _totalTime%60;
		    
		    // 判断是否启用保存按钮
			var annualDays = document.getElementById("annualDays").value;
			var sickDays = document.getElementById("sickDays").value;
			var familyDays = document.getElementById("familyDays").value;
			var study = document.getElementById("study").value;
			var maternityDays = document.getElementById("maternityDays").value;
			
			var type = document.getElementById("leaveType").value;

			// 请假的最小单位为1小时，如果有半小时的则校验不通过
			if(_totalTime%60 == 30){
				document.getElementById("save").disabled = true;
				alert("Leave time can not have half an hour, the smallest unit is one hour.");
			}else{
				if(1 == type && _totalTime <= annualDays*60){//Annual
					document.getElementById("save").disabled = false;
				}else if(2 == type && _totalTime <= sickDays*60){//Sick
					document.getElementById("save").disabled = false;
				}else if(3 == type && _totalTime <= familyDays*60){//Family
					document.getElementById("save").disabled = false;
				}else if(4 == type && _totalTime <= study*60){//Study
					document.getElementById("save").disabled = false;
				}else if(5 == type && _totalTime <= maternityDays*60){//Maternity
					document.getElementById("save").disabled = false;
				}else if( 6 == type || 7 == type || 8 == type ){
					document.getElementById("save").disabled = false;
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
	var  Weekdaycount=0;
	var  noWeekdaycount=0;
	var _holiday = document.getElementById("holidayInfo").value.replace(/-/g,"/");

	for (var i=1;i<=_days;i++){
		var myweekday=new Date(_from).getDay();

		if((myweekday == 0) || (myweekday == 6)){
			Weekdaycount++;
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
	return Weekdaycount;
}
//計算天數的函數
function  DateDiff(beginDate,  endDate){    //beginDate和endDate都是2007-8-10格式
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
		alert("Comment is empty");
		return false;
	}
}
// 选择请假的类型后，自动计算还有多少假期可以使用
function showLeftDays(){
	var annualDays = document.getElementById("annualDays").value;
	var sickDays = document.getElementById("sickDays").value;
	var familyDays = document.getElementById("familyDays").value;
	var study = document.getElementById("study").value;
	var maternityDays = document.getElementById("maternityDays").value;
	
	var obj = document.getElementById("leftDays");
	var type = document.getElementById("leaveType").value;
	
	if(type == "0"){
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
		
		changeProperty("fromDay","disabled");
		changeProperty("toDay","disabled");
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
	}else if(5 == type){//Maternity
		//var _hour5 = Math.floor(maternityDays/60);
		//obj.innerText = "May apply for the number of days:" + Math.floor(_hour5/8) + " days " + _hour5%8 + " hours" + maternityDays%60 + " minutes";
		obj.innerText = "May apply for the number of days:" + Math.floor(maternityDays/8) + " days " + maternityDays%8 + " hours";
	}else{
		obj.innerText = "";
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