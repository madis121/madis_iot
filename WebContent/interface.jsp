<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Interface</title>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script>
	$(document).ready(function() {
		$("#save").click(function(event) {
		    event.preventDefault();
		    httpPost(getFormData('#config'));
		});
		
		function httpPost(value) {
			$.ajax({
				url : 'configServlet',
				type : 'POST',
				data : JSON.stringify(value),
				success : function() {
				}
			});
		}
		
		function getFormData(value) {
			var data = {};
			var heaterSwitchVal = $(value).find('#heaterSwitch')[0].value;
			var lightSwitchVal = $(value).find('#lightSwitch')[0].value;
			var startTimeVal = $(value).find('#startTime')[0].value;
			var endTimeVal = $(value).find('#endTime')[0].value;
			var temperature = $(value).find('#temperature')[0].value;
			var lighting = $(value).find('#lighting')[0].value;
			var heaterSwitch;
			var lightSwitch;
			var startTime = '';
			var endTime = '';
			
			var timeRegex = new RegExp('^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$');
			
			if (heaterSwitchVal == 'on') {
				heaterSwitch = true;
			} else {
				heaterSwitch = false;
			}
			
			if (lightSwitchVal == 'on') {
				lightSwitch = true;
			} else {
				lightSwitch = false;
			}
			
			if (timeRegex.test(startTimeVal)) {
				startTime = startTimeVal;
			}
			
			if (timeRegex.test(endTimeVal)) {
				endTime = endTimeVal;
			} 
			
			data = {heaterSwitch:heaterSwitch, lightSwitch:lightSwitch, startTime:startTime, endTime:endTime, temperature:temperature, lighting:lighting};
			console.log(data);
			return data;
		}
	});
</script>
</head>
<body>
	<form id="config" action="sensorServlet" method="post">
		<p>
			<span>Heater: </span>
			<select id="heaterSwitch">
				<option value="on">On</option>
				<option value="off">Off</option>
			</select>
		</p>
		<p>
			<span>Lights: </span>
			<select id="lightSwitch">
				<option value="on">On</option>
				<option value="off">Off</option>
			</select>
		</p>
		<p>
			<span>Start: </span>
			<input type="text" id="startTime" size="5"/>
			<span>End: </span>
			<input type="text" id="endTime" size="5"/>
		</p>
		<p>
			<span>Temperature: </span>
			<input type="number" id="temperature"/>
		</p>
		<p>
			<span>Lighting: </span>
			<input type="number" id="lighting"/>
		</p>
		<button type="submit" id="save">Save</button>
	</form>
</body>
</html>