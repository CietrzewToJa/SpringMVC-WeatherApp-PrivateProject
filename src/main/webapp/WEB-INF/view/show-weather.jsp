<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aktualna Pogoda</title>
</head>
<body>
	<h1 align="center">Aktualna pogoda w: ${requestInfo.cityName}</h1>

	<div align="center">
		<table>

			<tr>
				<td align="right">Niebo</td>
				<td align="center">${weather.weather[0].description}</td>
			</tr>
			<tr>
				<td align="right">Temp.</td>
				<td align="center">${weather.main.temp}</td>
			</tr>
			<tr>
				<td align="right">Temp. odczuwalna</td>
				<td align="center">${weather.main.feels_like}</td>
			</tr>
			<tr>
				<td align="right">Ciśnienie</td>
				<td align="center">${weather.main.pressure}</td>
			</tr>
			<tr>
				<td align="right">Wilgotność</td>
				<td align="center">${weather.main.humidity}</td>
			</tr>
			<tr>
				<td align="right">Wiatr</td>
				<td align="center">${weather.wind.speed}</td>
			</tr>
			<tr>
				<td align="right">Deszcz</td>
				<td align="center">${weather.rain.oneHour}</td>
			</tr>
			<tr>
				<td align="right">Śnieg</td>
				<td align="center">${weather.snow.oneHour}</td>
			</tr>

		</table>
	</div>



</body>
</html>