<%-- 
    Document   : home
    Created on : Nov 24, 2015, 11:38:07 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>project managers backend </title>
        <link rel="stylesheet" type="text/css" href="styles/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="styles/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="styles/css/mystyle.css" />


    </head>
    <body>
        <c:if test="${username}==null">
            <%response.sendRedirect("/login.jsp");%>
        </c:if>

        <div class="container" >


            <nav class="navbar navbar-default" role="navigation">

                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#collapse" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">TechBiz</a>
                </div>
                <div class="collapse navbar-collapse"  id="collapse" >
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="addEmp" data-toggle="pill">DashBoard</a></li>
                        <li>
                            <form action="LoginServlet?action=admin" method="post"  >
                                <input type="hidden" value="${adminid}" name="admin"/>
                                <input type="submit" value="myAccount" />
                            </form>


                        </li>
                    </ul>  
                </div>


                <div class="navbar-text navbar-right">

                    <h3>Welcome ${username}<i> ${current} </i> <span class="glyphicon glyphicon-user"></span></h3>  
                </div>
            </nav>
        </div> <!-- container fluid -->




        <div class="container">
            <div class="row">
                <div class="col-sm-2">
                    <ul class="nav navbar nav-stacked">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                EMPLOYEES <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a data-toggle="modal" href="#addEmployee">Add Employee <span class="glyphicon glyphicon-plus"></span></li>
                                <li class="divider"></li>
                                <li><a href="addEmp">View Employees <span class="glyphicon glyphicon-folder-open"></span></a></li>
                            </ul>
                        </li>


                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                PROJECTS <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a data-toggle="modal" href="#addProject">Add Project <span class="glyphicon glyphicon-plus"></span></li>
                                <li class="divider"></li>
                                <li><a data-toggle="modal" href="#allocateProject">Allocate Project <span class="glyphicon glyphicon-folder-open"></span></a></li>
                            </ul>
                        </li>



                        <!--    <li><a data-toggle="modal" href="#addProject">Add Projects  <span class="glyphicon glyphicon-plus"></span></li> -->



                        <li><a href="report.jsp">Generate Report <span class="glyphicon glyphicon-record"></span></a></li>
                        <div>
                            <li><a href="LoginServlet?action=out">LogOut <span class="glyphicon glyphicon-log-out"></span></a></li>
                    </ul>
                </div>
                <div class="col-sm-10">
                    <div class="row">

                        <div class="col-sm-4">
                            <h2>Our Projects</h2>
                            <c:forEach var="item" items="${cprojects}">
                                <div class="mybox">
                                    <p>${item.projectName}   <t /> 
                                    <i>Arrived</i> ${item.arrivalDate} <i>Client</i> ${item.client}  </p>
                                </div>
                                <br />
                            </c:forEach>
                        </div>

                        <div class="col-sm-4">
                            <h2>In progress</h2>

                            <c:forEach var="item" items="${status}">


                                <c:if test="${item.isFinished != 1}">
                                    <div class="mybox">
                                        ${item.projectName}   <t /> 
                                        <i>due</i> ${item.endDate} <i>done by</i> ${item.fname} ${item.surname}
                                    </div>
                                    <br />
                                </c:if>
                                <%--  



                                    <p>${item.projectName}   <t /> 
                                     <i>due</i> ${item.endDate}  
                                    <form action="myAccount?action=adminchat" method="post">
                                        <input type="hidden" value="${item.projectid}" name="chatid" />
                                        <input type="hidden" value="${item.staffId}" name="projectownerid" />
                                        <input type="submit" value="more" class="btn btn-primary" />
                                    </form>
                                    </p>
                               </c:if> --%>


                            </c:forEach>


                        </div>
                        <div class="col-sm-4">
                            <%-- request.getAttribute("projects");  %>
                            <%= projects.getTeamLeader()  --%>

                            <h2>Finished</h2>
                            <c:forEach var="item" items="${status}">


                                <c:if test="${item.isFinished eq 1}">
                                    <div class="mybox">
                                        ${item.projectName}   <t /> 
                                        <i>due</i> ${item.endDate} <i>done by</i> ${item.fname} ${item.surname}
                                    </div>
                                    <br />
                                </c:if>



                            </c:forEach>


                        </div>
                    </div>

                </div>

            </div>
            <%-- <div class="row">
                 <div class="col-sm-12">
                     ${message}
                     <p> <%= (request.getAttribute("message") == null) ? ""
                 : request.getAttribute("message")%>

                        </p>
                                </div>
                            </div>--%>
        </div>

        <!-- Modal -->
        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="addProject" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Add Project</h4>
                    </div>
                    <form action="addProject?action=addproject" method="post">
                        <div class="modal-body">
                            <p>Project Name</p>
                            <input type="text" name="pname" placeholder="Project name" autocomplete="off" class="form-control placeholder-no-fix">
                        </div>

                        <div class="modal-body">
                            <p>Client</p>

                            <!-- <input type="text" name="type" placeholder="Project Type" autocomplete="off" class="form-control placeholder-no-fix"> -->
                            <select name="client" class="form-control placeholder-no-fix">
                                <option value="Safaricom">Safaricom</option>
                                <option value="Ericcson">Ericssont</option>
                                <option value="Huawei">Huawei</option>
                            </select>
                        </div>

                        <div class="modal-body">
                            <p>type</p>
                            <!-- <input type="text" name="type" placeholder="Project Type" autocomplete="off" class="form-control placeholder-no-fix"> -->
                            <select name="type" class="form-control placeholder-no-fix">
                                <option value="support">Support</option>
                                <option value="deployment">Deployment</option>
                                <option value="pm">Preventive Maintenance</option>
                            </select>
                        </div>


                        <div class="modal-footer">
                            <button data-dismiss="modal" class="btn btn-theme" type="button">Cancel</button>
                            <input class="btn btn-primary" type="submit">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- modal -->


        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="allocateProject" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Allocate Project</h4>
                    </div>
                    <form action="addProject" method="get">
                        <div class="modal-body">
                            <p>Project Name</p>

                            <select name="projectId" multiple="true" class="form-control"  title="" style="//width: 350px;" >

                                <c:forEach var="id" items="${cprojects}">

                                    <option value="${id.projectId}">${id.projectName} <i>arrived on</i>  ${id.arrivalDate} by ${id.client}</option> 

                                </c:forEach>

                            </select>


                        </div>

                        <div class="modal-body">
                            <p>Expected Start Date</p>
                            <input type="Date" name="startdate" autocomplete="off" class="form-control placeholder-no-fix">
                        </div>

                        <div class="modal-body">
                            <p>Expected End Date</p>
                            <input type="Date" name="endDate" placeholder="finish day" autocomplete="off" class="form-control placeholder-no-fix"> 
                        </div>

                        <div class="modal-body">
                            <p><u>Allocated Personnel</u></p>
                            <p>Team Leader</p>
                            <select id="show" name="staffId" class="form-control"  title="" style="//width: 350px;"  onchange="showothers(this)">
                                <option></option>
                                <c:forEach var="id" items="${staffDetails}">
                                    <option value="${id.staffId}">${id.fname} ${id.surname}</option> 
                                </c:forEach>
                            </select>
                        </div>

                        <div class="modal-body">
                            <p>Others(Only 2 more members)</p>
                            <select id="others" name="others" multiple="true" class="form-control" style="display: none" >
                                <c:forEach var="id" items="${staffDetails}">
                                    <option value="${id.staffId}">${id.fname} ${id.surname}</option> 
                                </c:forEach>
                            </select>
                        </div>

                        <div class="modal-footer">
                            <button data-dismiss="modal" class="btn btn-theme" type="button">Cancel</button>
                            <input class="btn btn-primary" type="submit">
                        </div>
                    </form>
                </div>
            </div>
        </div>




        <!-- Modal -->
        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="addEmployee" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Add Employee</h4>
                    </div>
                    <form action="addEmp" method="post">
                        <div class="modal-body">
                            <p>First Name</p>
                            <input type="text" name="fname" placeholder="firstname" autocomplete="off" class="form-control placeholder-no-fix">
                        </div>

                        <div class="modal-body">
                            <p>Surname</p>
                            <input type="text" name="surname" placeholder="surname" autocomplete="off" class="form-control placeholder-no-fix">
                        </div>

                        <div class="modal-body">
                            <p>doB</p>
                            <input type="Date" name="dob" autocomplete="off" class="form-control placeholder-no-fix">
                        </div>

                        <div class="modal-body">
                            <p>StaffId</p>
                            <input type="text" name="staffId" placeholder="staffid" autocomplete="on" class="form-control placeholder-no-fix">
                        </div>


                        <div class="modal-body">
                            <p>Email</p>
                            <input type="text" name="email" placeholder="email" autocomplete="off" class="form-control placeholder-no-fix">
                        </div>

                        <div class="modal-body">
                            <p>Gender</p>
                            <input type="radio" name="gender" value="Male" /> Male
                            <input type="radio" name="gender" value="Female" /> Female
                        </div>

                        <div class="modal-body">
                            <p>User type</p>
                            <input type="radio" name="usertype" value="admin" /> Admin
                            <input type="radio" name="usertype" value="user" /> User
                        </div>
                        <div class="modal-body">
                            <p>Marital Status</p>
                            <select name="status">
                                <option>Single</option>
                                <option>Married</option>
                            </select>
                        </div>




                        <div class="modal-footer">
                            <button data-dismiss="modal" class="btn btn-theme" type="button">Cancel</button>
                            <input class="btn btn-primary" type="submit">
                        </div>
                    </form>
                </div>
            </div>
        </div>



        <!-- modal -->




        <%--
         Project Team Leader

            <c:forEach items="${projects}" var="items" >
                <tr><td>${items.projectName}</td><td> ${items.teamLeader}</td> <td> <a href="view.jsp" >More on project</a></td></tr>
            </c:forEach>
            <script src="styles/js/bootstrap.js"></script>
<script src="styles/js/bootstrap.min.js"></script>--%>

        <script>
            function showothers(obj) {
                var selectBox = obj;
                var selected = selectBox.options[selectBox.selectedIndex].value;
                var selectother = document.getElementById("others");
                selectother.style.display = "block";
            }

        </script>

        <script src="styles/js/jquery.js"></script>
        <script src="styles/js/bootstrap.min.js" ></script>
        <script src="styles/js/chosen.jquery.js"></script>
        <script src="styles/js/script.js"></script>

    </body>
</html>

