<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">

<link rel='stylesheet' type='text/css' href='dist/fullcalendar-1.6.4/fullcalendar/fullcalendar.css' />
<script type='text/javascript' src='dist/fullcalendar-1.6.4/lib/jquery.min.js'></script>
<script type='text/javascript' src='dist/fullcalendar-1.6.4/fullcalendar/fullcalendar.js'></script>

<script type="text/javascript" src="js/jquery.form.js"></script>


<title>test</title>
<script type="text/javascript">
$(document).ready(function(){
	//page now is ready, Initialize the calendar
	$('#calendar').fullCalendar({
		dayClick:function(){
			//alert("a day has been clicked!");
			//$("#calendar").fullCalendar('next');
		},
		header:{
			left:   'today prev,next',
    		center: 'title',
    		right:  'Week,Month'
		},
		defaultView:'Month',
		weekends:true,
//		weekNumbers:true,
		weekNumberCalculation:"iso",
		aspectRatio: 2.0,
		viewRender:function(view, element){
			//alert("viewRender");
		},
		selectable:true,
		//agenda
		allDaySlot:true,
		allDayText:'all-day',
		firstHour:6,
		minTime:6,
		maxTime:30,
		//event
		timeFormat:'H:mm{ - H:mm}',
		events:[
			{
				id:1,
				title:"joe",
				start:"2014-03-20 06:00:00",
				end:"2014-03-20 18:00:00",
				allDay:false
			},
			{
				id:2,
				title:"tst2",
				start:"2014-03-21 18:00:00",
				end:"2014-03-22 06:00:00",
				allDay:false
			},
			{
				id:3,
				title:"test 3",
				start:"2014-03-22 18:00:00",
				end:"2014-03-23 06:00:00",
				url:"www.google.com",
				allDay:false
			}
		],
		color:'yellow',
		textColor:'red',
		eventClick:function(calEvent, jsEvent, view){
			alert('Event | ' + calEvent.title);
			alert('jsEvent | ' + jsEvent.pageX + ',' + jsEvent.pageY);
			alert("View: " + view.name);
			
			$(this).css("border-color", "red");
			
			if(event.url){
				window.open(calEvent.url);
				return false;
			}
		},
		dayClick: function(date, allDay, jsEvent, view) {

			//window.open
			
	        if (allDay) {
	            alert('Clicked on the entire day: ' + date);
	        }else{
	            alert('Clicked on the slot: ' + date);
	        }
	
	        //alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
	
	        //alert('Current view: ' + view.name);
	
	        // change the day's background color just for fun
	        //$(this).css('background-color', 'red');
	
	    }
		
	});
});
function downloadMonthlyExcel(){
	//alert("download monthly excel !");
	//var param = {};
	//alert("ajax start");
	/*$.ajax({
		url:"exportShiftWorkExcelTemplate.action",
		dataType:"html",
		success:function(msg){}
	});*/
	var options = {
		url : "exportShiftWorkExcelTemplate.action",
		type : "post",
		dataType : "html",
		success : function(msg){}
	};
	$("#form0").ajaxSubmit(options);
	//alert("ajax end");
}
</script>
</head>
<body>
<form id="form0" name="form0" enctype="multipart/form-data">
	<input type="button" value="Export Next Month Excel" onclick="downloadMonthlyExcel()"/>
</form>

<form method="POST" enctype="multipart/form-data" action="">
  File to upload: <input type="file" name="upfile"><br/>
  Notes about the file: <input type="text" name="note"><br/>
  <br/>
  <input type="submit" value="Press"> to upload the file!
</form>

<div id='calendar' name='calendar'></div>
</body>
</html>