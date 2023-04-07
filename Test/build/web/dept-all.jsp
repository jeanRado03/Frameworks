<%@page import="java.util.Vector"%>
<%@page import="java.util.Map"%>
<%@page import="test.Dept"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Dept-all</title>
  </head>
  <body>
    <h1>Tongasoa</h1>
    <h1>Listes des Employes</h1>
        <% 
            String employer = (String)request.getAttribute("cle");
            Vector dept = (Vector)request.getAttribute(employer);
            
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>id</th>
                    <th>Nom</th>
                </tr>
            </thead>
            <tbody>
                    <tr>
                        <td><% out.print(employer); %></td>
                        <td><% out.print(((Dept)dept.get(0)).getId()); %></td>
                    </tr>
                    
        </table>
  </body>
</html>
