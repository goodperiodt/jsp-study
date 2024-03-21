<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- 외부 태그 라이브러리 선언문 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Insert Your Title</title>
</head>
<body>
    <%
        int total = 0;
        for(int i=1; i<=10; i++) {
            total += i;
        }

        out.write("<h1>1~10까지의 누적 합계: " + total + "</h1>");
    %>

    <!--  jstl 변수 만들기 -->
    <c:set var="total" value="0"   />

    <!-- step은 1일 경우 생략이 가능하다 -->
    <c:forEach var="i" begin="1" end="10">
        <c:set var="total" value="${total + i}" />
    </c:forEach>

    <!-- el(expression language): jstl에서 선언한 변수 및 Controller가 전달한 객체 등을 꺼내서 표현할 때 사용하는 언어 -->
    <h1>1~10까지의 누적합계: ${total}</h1>

    <hr>

    <%-- 조건문 --%>
    <c:set var="age" value="15" />


    <%--
        <c:if test="${age>19}">
            <h2>성인입니다.</h2>
        </c:if>

        <c:if test="${age <= 19}">
            <h2>미성년자 입니다.</h2>
        </c:if>
    --%>

    <c:choose>
        <c:when test="${age>19}">
            <h2>성인입니다.</h2>
        </c:when>
        <c:when test="${age>16}">
            <h2>고등학생입니다.</h2>
        </c:when>
        <c:when test="${age>13}">
            <h2>중학생입니다.</h2>
        </c:when>
        <c:otherwise>
            <h2>중학생 미만입니다.</h2>
        </c:otherwise>
    </c:choose>





</body>
</html>