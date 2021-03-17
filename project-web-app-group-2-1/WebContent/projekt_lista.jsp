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
<%@include file="header.jsp"%>
	<h1>Lista projektów</h1>
	<form action="ProjektPobierz" method="GET">
		<p><input type="text" name="szukajNazwaLubOpis" /> <input name="btn_szukaj" value="Szukaj" type="submit"> 
		<p>Rozmiar strony: <select id="ilosc" name="ilosc">
		    <option value="5">5</option>
		    <option value="6">6</option>
		    <option value="7">7</option>
		    <option value="8">8</option>
		    <option value="9">9</option>
		    <option value="10">10</option>
  		</select>
	</form>
	
	<c:if test="${previousPage>=0 }">
		<c:url value="ProjektPobierz" var="previousPageAhref">
				<c:param name="szukajNazwaLubOpis" value="${szukajNazwaLubOpis}" />
				<c:param name="btn_szukaj" value="${btn_szukaj}" />
				<c:param name="page" value="${previousPage}" />
				<c:param name="ilosc" value="${amountOfItems}" />
				
		</c:url>
		<a href="${previousPageAhref}">Poprzednia strona</a>
	</c:if>
	<c:if test="${nextPage>0 }">
		<c:url value="ProjektPobierz" var="nextPageAhref">
				<c:param name="szukajNazwaLubOpis" value="${szukajNazwaLubOpis}" />
				<c:param name="btn_szukaj" value="${btn_szukaj}" />
				<c:param name="page" value="${nextPage}" />
				<c:param name="ilosc" value="${amountOfItems}" />
				
		</c:url>
		<a href="${nextPageAhref}">Następna strona</a>
	</c:if>
	
	
	<table>
		<tr>
			<th>Lp.</th>
			<th>Id</th>
			<th>Nazwa</th>
			<th>Opis</th>
			<th>Utworzony</th>
			<th>Data obrony</th>
			<th>Edycja</th>
		</tr>

		<c:forEach var="projekt" items="${requestScope.projekty}" varStatus="info">
			<tr>
				<td>${info.count}.</td>
				<td><c:out value="${projekt.projektId}" /></td>
				<td><c:out value="${projekt.nazwa}" /></td>
				<td><c:out value="${projekt.opis}" /></td>
				<javatime:format value="${projekt.dataczasUtworzenia}" var="fmtDataczasUtworzenia" pattern="yyyy-MM-dd hh:mm:ss" />
				<td><c:out value="${fmtDataczasUtworzenia}" /></td>
				<javatime:format value="${projekt.dataOddania}" var="fmtDataOddania" pattern="yyyy-MM-dd" />
				<td><c:out value="${fmtDataOddania}" /></td>
				<c:url value="/pages/zadania.jsp" var="linkZadaniaProjektu">
					<c:param name="x_projekt_id" value="${projekt.projektId}" />
				</c:url>
				<c:url value="ProjektUsun" var="usunProjekt">
					<c:param name="szukajNazwaLubOpis" value="${szukajNazwaLubOpis}" />
					<c:param name="btn_szukaj" value="${btn_szukaj}" />
					<c:param name="page" value="${page}" />
					<c:param name="ilosc" value="${amountOfItems}" />
					<c:param name="projektId" value="${projekt.projektId}" />
				</c:url>
				<c:url value="ProjektStareDane" var="stareDaneProjekt">
					<c:param name="szukajNazwaLubOpis" value="${szukajNazwaLubOpis}" />
					<c:param name="btn_szukaj" value="${btn_szukaj}" />
					<c:param name="page" value="${page}" />
					<c:param name="ilosc" value="${amountOfItems}" />
					<c:param name="projektId" value="${projekt.projektId}" />
				</c:url>
				<td>
					<p><a href='<c:out value="${linkZadaniaProjektu}" />'>Zadania</a>
					<p><a href='<c:out value="${usunProjekt}" />'>Usun</a>
					<p><a href='<c:out value="${stareDaneProjekt}" />'>Edytuj</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>