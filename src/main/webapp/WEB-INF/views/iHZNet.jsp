<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8" import="com.prebeg.ihznet.model.*,java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<raspored>
	<c:forEach var="putovanje" items="${raspored.putovanja}">
		<putovanje>
			<c:forEach items="${putovanje.linije}" var="linija">
				<linija>
					<odlazniKolodvor>${linija.odlazniKolodvor}</odlazniKolodvor>
					<dolazniKolodvor>${linija.dolazniKolodvor}</dolazniKolodvor>
					<vrijemeOdlaska>${linija.vrijemeOdlaska}</vrijemeOdlaska>
					<vrijemeDolaska>${linija.vrijemeDolaska}</vrijemeDolaska>
				</linija>
			</c:forEach>
		</putovanje>
	</c:forEach>

</raspored>
