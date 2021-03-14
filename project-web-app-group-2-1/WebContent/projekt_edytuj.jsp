<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<h1>Proszę podać nowe dane projektu</h1>
	
	<c:url value="ProjektPobierz" var="returnToList">
		<c:param name="szukajNazwaLubOpis" value="${szukajNazwaLubOpis}" />
		<c:param name="btn_szukaj" value="btn_szukaj" />
		<c:param name="page" value="${previousPage}" />
		<c:param name="ilosc" value="${amountOfItems}" />	
	</c:url>
	<a href="${returnToList}">Powrót</a>

	<form action="ProjektEdytuj" method="POST" id="">
		<table>
			<tr>
			<th>Id:</th> <th><input type="text" name="projektId" value="${projekt.projektId }" readonly/> </th>
			</tr>
			<tr>
			<th>Nazwa:</th> <th><input type="text" name="nazwa" value="${projekt.nazwa }"/> </th>
			</tr>
			<tr>
			<th>Opis:</th>  <th><textarea rows="4" cols="50" name="opis" form="projektform"> ${projekt.opis }</textarea></th>
			</tr>
			<tr>
			<th>Data oddania:</th>  <th><input type="date" id="start" name="dataOddania" value="${projekt.dataOddania }"/> </th>
			</tr>
		</table>
		<input name="btn_zapisz" value="Zapisz" type="submit">
	</form>

	ID zmienionego projektu: ${projekt.projektId} <br/>
	Nazwa zmienionego projektu: ${projekt.nazwa} <br/>
	Opis zmienionego projektu: ${projekt.opis} <br/>

</body>
</html>