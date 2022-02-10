<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.io.*" %>
<%@page import="java.net.*" %>
<%@page import="java.util.*" %>
<%@page import="java.util.List" %> 
<%@page import="java.util.ArrayList" %>
<%@page language="java" import="com.IndusAppWebServer.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Catalog</title>
</head>
<body>
<% List<String> catalog = (ArrayList<String>) request.getAttribute("list"); %>
<% for(int i=0;i<catalog.size();i++){
out.println(catalog.get(i) );} %>
</body>
</html>