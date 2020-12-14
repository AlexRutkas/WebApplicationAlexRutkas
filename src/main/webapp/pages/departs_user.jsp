<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@page import="java.sql.*" %>
<%@ page import="db.ConnectionFactory" %>
<%@ page import="java.util.Locale" %>

<fmt:setBundle basename="message"/>

<html>

<head>
    <title><fmt:message key="site_title"/></title>
    <style>
        @import "../bootstrap/css/bootstrap.css";
        @import "../bootstrap/css/bootstrap.min.css";
    </style>
</head>
<body>



<div style="padding: 20px">
    <h1><fmt:message key="site_title"/></h1>
    <div class="row">
        <div class="col-sm-10">
            <div class="panel-body">
                <table border="3" cellpadding="20" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th width="10%"><fmt:message key="entity.number"/></th>
                        <th width="20%"><fmt:message key="entity.title"/></th>
                        <th width="30%"><fmt:message key="entity.descr"/></th>
                        <th width="40%"><fmt:message key="entity.emploeers"/></th>
                    </tr>

                        <%
                        Connection connection = ConnectionFactory.getConnection();
                        ResultSet resultSet;

                        try {

                    resultSet = connection.createStatement()
                           .executeQuery("select d.num as numd, d.name as title, d.descr, e.name, e.surname, p.name as posn from departments d left join emploeers e on d.id = e.departmentid " +
                            " left join positions p on p.id = e.positionid order by numd");
                    int i;
                    boolean f=resultSet.next();
                            while (f) {
                              i=  resultSet.getInt("numd");
                                           %>
                    <tr>
                        <td align="center"><%=resultSet.getInt("numd")%>
                        </td>
                        <td><%=resultSet.getString("title")%>
                        </td>
                        <td><%=resultSet.getString("descr")%>
                        </td>

                        <td align="center">
                        <%
                        while (i == resultSet.getInt("numd")) {
                         String s=resultSet.getString("name");
                         if(s!=null)  out.println(s+"  "+resultSet.getString("surname")+" "+resultSet.getString("posn")+", ");
                         f=resultSet.next();
                             }
                            %>

                        </td>

                    </tr>

                        <%
                            }
                            connection.close();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    %>
                </table>
            </div>
        </div>
    </div>


    <form action="/logout" method="get">
        <input type="submit" value=<fmt:message key="user.logout"/>
               name="Submit" id="frm1_submit" />
    </form>

</div>
</body>
</html>
