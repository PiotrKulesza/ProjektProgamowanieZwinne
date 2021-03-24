<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
</style>
</head>
<body>
	<h1>Dodaj studenta</h1>
	<h2>Górna lista przedstawia studentów dodanych</h2>
	<h2>Dolna lista przedstawia studentów których możęsz dodać</h2>
	
	<h3>Lista studentów dodanych</h3>
	
	
	
	<h3>Lista studentów do dodania</h3>
	
	<table>
		<tr>
			<th>Lp.</th>
			<th>Id</th>
			<th>Imię</th>
			<th>Nazwisko</th>
			<th>Indeks</th>
			<th>Edycja</th>
		</tr>

		<c:forEach var="student" items="${requestScope.students}" varStatus="info">
			<tr>
				<td>${info.count}.</td>
				<td><c:out value="${student.studentId}" /></td>
				<td><c:out value="${student.imie}" /></td>
				<td><c:out value="${student.nazwisko}" /></td>
				<td><c:out value="${student.nrIndeksu}" /></td>
				
				<c:url value="ZadanieZmianaKolejnosci" var="kolejnoscUp">
					<c:param name="szukajNazwaLubOpis" value="${szukajNazwaLubOpis}" />
					<c:param name="page" value="${page}" />
					<c:param name="ilosc" value="${amountOfItems}" />
					<c:param name="projektId" value="${projekt.projektId}" />
					<c:param name="zadanieId" value="${zadanie.zadanieId}" />
					<c:param name="kolejnosc" value="kolejnoscUp" />
				</c:url>
				<td>
					<p>Dodaj
				</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>