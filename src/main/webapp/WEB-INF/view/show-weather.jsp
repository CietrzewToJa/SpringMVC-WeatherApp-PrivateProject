<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="/weather-app/URLToReachResourcesFolder/css/my-style-sheet.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">


<title>Aktualna Pogoda</title>
</head>
<body>

	<form action="/">
		<input type="submit" name="action"/>
	</form>



	<div class="container-fluid weather-section section">

		<div class="row">
			<h2 align="center">Weather in ${weather.city}</h2>
		</div>

		<div class="row justify-content-center">
			<table class="col-auto">
				<tr>
					<td class="left-column">Sky</td>
					<td>${weather.weather[0].description}</td>
				</tr>
				<tr>
					<td class="left-column">Temperature</td>
					<td>${weather.main.temp}</td>
				</tr>
				<tr>
					<td class="left-column">Sensed temperature</td>
					<td>${weather.main.feels_like}</td>
				</tr>
				<tr>
					<td class="left-column">Pressure</td>
					<td>${weather.main.pressure}</td>
				</tr>
				<tr>
					<td class="left-column">Humidity</td>
					<td>${weather.main.humidity}</td>
				</tr>
				<tr>
					<td class="left-column">Wind</td>
					<td>${weather.wind.speed}</td>
				</tr>
				<tr>
					<td class="left-column">Rain</td>
					<td>${weather.rain.oneHour}</td>
				</tr>
				<tr>
					<td class="left-column">Snow</td>
					<td>${weather.snow.oneHour}</td>
				</tr>

			</table>

			<form:form action="add-weather" method="POST"
				modelAttribute="weather">

				<form:hidden path="weather[0].description" />
				<form:hidden path="main.temp" />
				<form:hidden path="main.feels_like" />
				<form:hidden path="main.pressure" />
				<form:hidden path="main.humidity" />
				<form:hidden path="rain.oneHour" />
				<form:hidden path="wind.speed" />
				<form:hidden path="snow.oneHour" />
				<form:hidden path="city" />
				<form:hidden path="date" />

				<button type="submit" class="btn btn-success">
					<i class="material-icons d-flex justify-content-center">expand_more</i>
				</button>
				<a href="#lol">
					<button class="btn btn-success">
						<i class="material-icons d-flex justify-content-center">expand_more</i>
					</button>
				</a>

			</form:form>

		</div>
	</div>

	<div class="container-fluid base section" id="lol">

		<h2 class="dark">Saved weather from ${weather.city}</h2>


		<table class="table table-striped">

			<thead>
				<tr>
					<td>Date</td>
					<td>Sky</td>
					<td>Temperature</td>
					<td>Sensed temperature</td>
					<td>Pressure</td>
					<td>Humidity</td>
					<td>Wind</td>
					<td>Rain</td>
					<td>Snow</td>
				</tr>
			</thead>

			<c:forEach var="weather" items="${weatherList}">
				<tr>
					<td>${weather.date}</td>
					<td>${weather.weather[0].description}</td>
					<td>${weather.main.temp}</td>
					<td>${weather.main.feels_like}</td>
					<td>${weather.main.pressure}</td>
					<td>${weather.main.humidity}</td>
					<td>${weather.wind.speed}</td>
					<td>${weather.rain.oneHour}</td>
					<td>${weather.snow.oneHour}</td>
				</tr>
			</c:forEach>

		</table>

	</div>
</body>
</html>