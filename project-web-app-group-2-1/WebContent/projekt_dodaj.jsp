<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="header.jsp"%>
	<h1>Proszę podać dane projektu</h1>

	<form action="ProjektDodaj" method="POST" id="projektform">
		<table>
			<tr>
			<th>Nazwa:</th> <th><input type="text" name="nazwa" /> </th>
			</tr>
			<tr>
			<th>Opis:</th>  <th><textarea rows="4" cols="50" name="opis" form="projektform"> Enter text here...</textarea></th>
			</tr>
			<tr>
			<th>Data oddania:</th>  <th><input type="date" id="start" name="dataOddania" value="2022-07-22"/> </th>
			</tr>
		</table>
		<input name="btn_zapisz" value="Zapisz" type="submit">
	</form>


	ID dodanego projektu: ${projekt.projektId} <br/>
	Nazwa dodanego projektu: ${projekt.nazwa} <br/>
	Opis dodanego projektu: ${projekt.opis} <br/>

</body>
</html>
