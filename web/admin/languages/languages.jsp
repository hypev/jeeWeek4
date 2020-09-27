<%@ page import="java.util.ArrayList" %>
<%@ page import="db.Language" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String lang = DBManager.getLangCookie(request);
    String success = request.getParameter("success");
    String error = request.getParameter("error");
    ArrayList<Language> languages = (ArrayList<Language>)request.getAttribute("languages");
    String adminPanelWord = DBManager.getWordDefinition("adminPanel", lang);
    String languagesWord = DBManager.getWordDefinition("languages", lang);
    String publicationsWord = DBManager.getWordDefinition("publications", lang);
    String newsWord = DBManager.getWordDefinition("news", lang);
    String translationWord = DBManager.getWordDefinition("translation", lang);
%>

<html>
<head>
    <title><%=adminPanelWord%> | <%=languagesWord%></title>
    <%@include file="../../includes/head.jsp"%>
    <%@include file="../includes/dashboard.jsp"%>
</head>
<body>
    <%@include file="../includes/navbar.jsp"%>
    <div class="container-fluid">
        <div class="row">
            <%@include file="../includes/sidebar.jsp"%>
            <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2">
                        <%=languagesWord%>
                    </h1>
                    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#addModal">
                        + Add new
                    </button>
                </div>

            <%  if (success != null) {  %>
                <%@include file="../../includes/alertSuccess.jsp"%>
            <%  } else if (error != null) { %>
                <%@include file="../../includes/alertError.jsp"%>
            <%  }   %>

            <%  if (languages != null && languages.size() != 0) {   %>
                <table class="table table-hover container">
                    <thead>
                        <tr class="row">
                            <th scope="col" class="col-md-1">ID</th>
                            <th scope="col" class="col-md-5">Name</th>
                            <th scope="col" class="col-md-4">Code</th>
                            <th scope="col" class="col-md-2">Operations</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%  for (Language l : languages) {  %>
                        <tr class="row">
                            <th scope="row" class="col-md-1"><%=l.getId()%></th>
                            <td class="col-md-5"><%=l.getName()%></td>
                            <td class="col-md-4"><%=l.getCode()%></td>
                            <td class="col-md-2">
                                <button onclick="editModalFill(<%=l.getId()%>,`<%=l.getName()%>`,`<%=l.getCode()%>`)"
                                        type="button" class="btn btn-primary" data-toggle="modal" data-target="#editModal">
                                    Edit
                                </button>
                                <button onclick="deleteModalFill(<%=l.getId()%>)"
                                        type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal">
                                    Delete
                                </button>
                            </td>
                        </tr>
                    <%  } %>
                    </tbody>
                </table>

            <%  } else {    %>
                <h1>Language List is empty.</h1>
            <%  }   %>
            </main>
        </div>
    </div>

    <%@include file="addModal.jsp"%>
    <%@include file="editModal.jsp"%>
    <%@include file="deleteModal.jsp"%>
    <%@include file="script.jsp"%>
</body>
</html>