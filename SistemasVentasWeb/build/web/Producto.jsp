<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Modelo.Producto" %>
<%@ page import="Modelo.Empleado" %>
<%
    HttpSession sesion = (HttpSession)request.getSession();
    Empleado emp = (Empleado)sesion.getAttribute("usuario");
    if (emp == null) {
        response.sendRedirect("index.jsp");
    }else{
            
%>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
<%}%>