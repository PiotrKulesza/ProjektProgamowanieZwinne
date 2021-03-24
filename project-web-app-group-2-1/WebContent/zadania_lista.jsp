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
<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
</style>
<body>


<h1>Lista zadań</h1>

	<c:url value="zadania_dodaj.jsp" var="linkZadaniaDodaj">
		<c:param name="szukajNazwaLubOpis" value="${szukajNazwaLubOpis}" />
		<c:param name="page" value="${page}" />
		<c:param name="ilosc" value="${amountOfItems}" />
		<c:param name="projektId" value="${projekt.projektId}" />
	</c:url>
	<p><a href='<c:out value="${linkZadaniaDodaj}" />'>Dodaj</a>
	
				
	<table>
		<tr>
			<th>Lp.</th>
			<th>Id</th>
			<th>Nazwa</th>
			<th>Opis</th>
			<th>Utworzony</th>
			<th>Kolejność</th>
			<th>Edycja</th>
		</tr>

		<c:forEach var="zadanie" items="${projekt.zadania}" varStatus="info">
			<tr>
				<td>${zadanie.kolejnosc}.</td>
				<td><c:out value="${zadanie.zadanieId}" /></td>
				<td><c:out value="${zadanie.nazwa}" /></td>
				<td><c:out value="${zadanie.opis}" /></td>
				<javatime:format value="${zadanie.dataczasDodania}" var="fmtDataczasDodania" pattern="yyyy-MM-dd hh:mm:ss" />
				<td><c:out value="${fmtDataczasDodania}" /></td>
				<td>
				<c:url value="ZadanieZmianaKolejnosci" var="kolejnoscUp">
					<c:param name="szukajNazwaLubOpis" value="${szukajNazwaLubOpis}" />
					<c:param name="page" value="${page}" />
					<c:param name="ilosc" value="${amountOfItems}" />
					<c:param name="projektId" value="${projekt.projektId}" />
					<c:param name="zadanieId" value="${zadanie.zadanieId}" />
					<c:param name="kolejnosc" value="kolejnoscUp" />
				</c:url>
				<c:url value="ZadanieZmianaKolejnosci" var="kolejnoscDown">
					<c:param name="szukajNazwaLubOpis" value="${szukajNazwaLubOpis}" />
					<c:param name="page" value="${page}" />
					<c:param name="ilosc" value="${amountOfItems}" />
					<c:param name="projektId" value="${projekt.projektId}" />
					<c:param name="zadanieId" value="${zadanie.zadanieId}" />
					<c:param name="kolejnosc" value="kolejnoscDown" />
				</c:url>
					<c:if test="${not info.first}"><p><a href='<c:out value="${kolejnoscUp}" />'>Kolejność w górę</a></c:if>
					<c:if test="${not info.last}"><p><a href='<c:out value="${kolejnoscDown}" />'>Kolejność w dół</a></c:if>
				</td>
				<c:url value="ZadanieUsun" var="usunZadanie">
					<c:param name="szukajNazwaLubOpis" value="${szukajNazwaLubOpis}" />
					<c:param name="page" value="${page}" />
					<c:param name="ilosc" value="${amountOfItems}" />
					<c:param name="projektId" value="${projekt.projektId}" />
					<c:param name="zadanieId" value="${zadanie.zadanieId}" />
				</c:url>
				<c:url value="ZadanieStareDane" var="zadanieStareDane">
					<c:param name="szukajNazwaLubOpis" value="${szukajNazwaLubOpis}" />
					<c:param name="page" value="${page}" />
					<c:param name="ilosc" value="${amountOfItems}" />
					<c:param name="projektId" value="${projekt.projektId}" />
					<c:param name="zadanieId" value="${zadanie.zadanieId}" />
				</c:url>
				<td>
					<p><a href='<c:out value="${usunZadanie}" />'>Usun</a>
					<p><a href='<c:out value="${zadanieStareDane}" />'>Edytuj</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>