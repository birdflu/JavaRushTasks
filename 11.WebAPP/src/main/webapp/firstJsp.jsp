<%--
  Created by IntelliJ IDEA.
  User: birdflu
  Date: 21.11.2020
  Time: 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>First JSP</title>
</head>
<body>
<h1>Testing JSP</h1>
<p>
    <%@ page import="java.util.Date, logic.TestClass" %>
    <%
        java.util.Date now = new Date();
        String currentDate = "Current date : " + now;
        for (int i = 0; i < 10 ; i++) {
            out.println("<p>" + i + "</p>");
        }

        TestClass testClass = new TestClass();
    %>
    <%= "<p>" + currentDate + "</p> "%>
    <%= "<p>" + testClass.getInfo() + "</p> " %>
</p>
</body>
</html>
