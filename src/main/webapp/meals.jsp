<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>


<html>
<head>
    <style>
        h2 {
            text-align: center;
        }

        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: center;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }

    </style>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Meals</title>
</head>

<body>
<h3><a href="index.html">Home</a></h3>

<h2>Meals</h2>

<div class="mainTable">
    <table id="ComputerParts">
        <tr>
            <th>Date/Time</th>
            <th>Meal description</th>
            <th>Calories</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>

        <c:forEach var="row" items="${mealList}">
            <tr>
                <td><javatime:format value="${row.dateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><c:out value="${row.description}"/></td>
                <td style="${row.exceed ? 'color:red' : 'color:green'}"><c:out value="${row.calories}"/></td>
                <td><a href="meals?action=edit&mealId=<c:out value="${row.id}"/>">Update</a></td>
                <td><a href="meals?action=delete&mealId=<c:out value="${row.id}"/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>