<%-- 
    Document   : report
    Created on : Feb 17, 2016, 4:38:05 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="styles/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="styles/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="styles/css/mystyle.css" />
    </head>
    <body>

        <div class="container">
            <h1>Generate Report</h1>
            <form action="report">
                <div class="row">
                    <div class="col-sm-3">
                        <input id="startDate" type="Date" name="startDate" class="form-control"/>   </div>        <div class="col-sm-1">   
                            To    </div>  <div class="col-sm-3">             
                            <input id="endDate" type="Date"  name="endDate" class="form-control"/> </div>  <div class="col-sm-3">
                            <input type="submit" class="btn btn-success" value="GENERATE REPORT"/></div>
                </div>
            </form>
            <table class="table table-bordered">
               
                <tr><th>ProjectName</th><th>Done By</th><th>Start Date</th><th>End Date</th><th>Actual EndDate</th><th>Client</th></tr>
                 <c:forEach var="item" items="${reports}">
                    <tr> <td>${item.projectName}</td>   <td>${item.fname} ${item.surname}</td>  <td>${item.startDate}</td> <td>${item.endDate}</td>  <td>${item.act_endDate}</td>  <td>${item.client}</td>    </tr>
                </c:forEach>
            </table>
           <button onclick="myFunction()" class="glyphicon-print">Print </button>
        </div>
       
        <script>
            function myFunction(){
                window.print();
            }
        </script>
     
    </body>
</html>
