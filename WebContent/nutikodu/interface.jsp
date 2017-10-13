<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Maakodu ilmajaam</title>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.0/Chart.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/chartjs-plugin-annotation/0.5.5/chartjs-plugin-annotation.js"></script>
<script>
	$(document).ready(function() {
		var temperatureArray = [];
		var lightingArray = [];
		var timestampArray = [];
		
		// stops redirecting when submitting
		$("#save").click(function(event) {
		    event.preventDefault();
		    httpPost(getFormData('#config'));
		    location.reload();
		});
		
		// post
		function httpPost(value) {
			$.ajax({
				url : 'configServlet',
				type : 'POST',
				data : JSON.stringify(value),
				success : function() {
				}
			});
		}
		
		// collects form data
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
			return data;
		}
		
		var fetchConfig = $.get("configDataServlet", function(data) {
			var json = JSON.parse(data);
			console.log(json);
			
			if (json.heaterSwitch) {
				$('#heaterSwitch').val('on');
			} else {
				$('#heaterSwitch').val('off');
			}
			
			if (json.lightSwitch) {
				$('#lightSwitch').val('on');
			} else {
				$('#lightSwitch').val('off');
			}
			
			if (json.startTime) {
				var dt = new Date(json.startTime);
				var time = dt.toLocaleTimeString([], {hour: '2-digit', minute:'2-digit', hour12: false});
				$('#startTime').val(time);
			}
			
			if (json.endTime) {
				var dt = new Date(json.endTime);
				var time = dt.toLocaleTimeString([], {hour: '2-digit', minute:'2-digit', hour12: false});
				$('#endTime').val(time);
			}
			
			if (json.temperature) {
				$('#temperature').val(json.temperature);
			}
			
			if (json.lighting) {
				$('#lighting').val(json.lighting);
			}
		});
		
		// get sensor data from db
		var fetchSensorData = $.get("sensorDataServlet", function(data) {
			  var json = JSON.parse(data);
			  console.log(json);

			  $.each(json.sensorData, function (index, value) {
				  temperatureArray.push(value.temperature);
				  lightingArray.push(value.lighting);
				  //timestampArray.push(value.dateTime);
				  if (value.dateTime) {
					  var dt = new Date(value.dateTime);
					  var date = dt.toLocaleDateString();
					  var time = dt.toLocaleTimeString([], {hour12: false});
					  timestampArray.push(dt.getDate() + "/" + dt.getMonth() + "/" + dt.getFullYear() + " " + time);
				  }
			  });
		});
		
		fetchSensorData.done(function() {			
			// chartjs start
			var temperatureCtx = document.getElementById("temperatureChart").getContext('2d');
			var temperatureChart = new Chart(temperatureCtx, {
				type: 'line',
				data: {
					labels: timestampArray,
					datasets: [{
						label: 'Temperatuur',
						data: temperatureArray,
						fill: false,
						backgroundColor: [
							'rgba(255, 99, 132, 0.2)',
							'rgba(54, 162, 235, 0.2)',
							'rgba(255, 206, 86, 0.2)',
							'rgba(75, 192, 192, 0.2)',
							'rgba(153, 102, 255, 0.2)',
							'rgba(255, 159, 64, 0.2)'
						],
						borderColor: [
							'rgba(255,99,132,1)',
							'rgba(54, 162, 235, 1)',
							'rgba(255, 206, 86, 1)',
							'rgba(75, 192, 192, 1)',
							'rgba(153, 102, 255, 1)',
							'rgba(255, 159, 64, 1)'
						],
						borderWidth: 1
					}]
				},
				options: {
					responsive: false,
					/*
				    annotation: {
					  annotations: [{
						type: 'line',
						mode: 'horizontal',
						scaleID: 'y-axis-0',
						value: 30,
						borderColor: 'rgb(75, 192, 192)',
						borderWidth: 4,
						label: {
						  enabled: false,
						  content: 'Test label'
						}
					  }]
					},
					*/
					scales: {
						yAxes: [{
							ticks: {
								beginAtZero:false
							}
						}]
					}
				}
			});
			
			var lightingCtx = document.getElementById("lightingChart").getContext('2d');
			var lightingChart = new Chart(lightingCtx, {
				type: 'line',
				data: {
					labels: timestampArray,
					datasets: [{
						label: 'Valgus',
						data: lightingArray,
						fill: false,
						backgroundColor: [
							'rgba(255, 99, 132, 0.2)',
							'rgba(54, 162, 235, 0.2)',
							'rgba(255, 206, 86, 0.2)',
							'rgba(75, 192, 192, 0.2)',
							'rgba(153, 102, 255, 0.2)',
							'rgba(255, 159, 64, 0.2)'
						],
						borderColor: [
							'rgba(255,99,132,1)',
							'rgba(54, 162, 235, 1)',
							'rgba(255, 206, 86, 1)',
							'rgba(75, 192, 192, 1)',
							'rgba(153, 102, 255, 1)',
							'rgba(255, 159, 64, 1)'
						],
						borderWidth: 1
					}]
				},
				options: {
					responsive: false,
					/*
				    annotation: {
					  annotations: [{
						type: 'line',
						mode: 'horizontal',
						scaleID: 'y-axis-0',
						value: 50,
						borderColor: 'rgb(75, 192, 192)',
						borderWidth: 4,
						label: {
						  enabled: false,
						  content: 'Test label'
						}
					  }]
					},
					*/
					scales: {
						yAxes: [{
							ticks: {
								beginAtZero:false
							}
						}]
					}
				}
			});
			// chartjs end
		});
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
	<canvas id="temperatureChart" width="800" height="600"></canvas>
	<canvas id="lightingChart" width="800" height="600"></canvas>
</body>
</html>