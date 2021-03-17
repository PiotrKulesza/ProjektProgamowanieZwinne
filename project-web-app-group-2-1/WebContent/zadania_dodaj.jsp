<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="header.jsp"%>
<h1>Proszę podać dane zadania</h1>

<form action="ZadaniaDodaj" method="POST" id="projektform">
	<table>
		<tr>
		<th>Nazwa</th> <th><input type="text" name="imie" /> </th>
		</tr>
		<tr>
		<th>Treść</th> <th><input type="text" name="nazwisko" /> </th>
		</tr>
		<tr>
		<th>Tag</th> <th><input type="text" name="nrIndeksu" /> </th>
		</tr>
		<tr>
		<th>Projekt</th> <th><input type="email" name="email" /> </th>
		</tr>
		<tr>
		</tr>
		</table>
	<input name="btn_zapisz" value="Zapisz" type="submit">
</form>


Nazwa zadania: ${zadanie.zadanieNazwa} <br/>
Treść Zadania: ${zadanie.zadanieTresc} <br/>
Tag zadania: ${zadanie.zadanieTag} <br/>
Projekt: ${zadanie.zadanieProjekt} <br/>
</body>
</html>