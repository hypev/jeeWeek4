<%@ page import="java.util.ArrayList" %>
<%@ page import="db.Language" %>
<%@ page import="db.Publication" %>
<%@ page import="db.News" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String lang = DBManager.getLangCookie(request);
    String success = request.getParameter("success");
    String error = request.getParameter("error");
    ArrayList<News> news = (ArrayList<News>)request.getAttribute("news");
    ArrayList<Language> languages = (ArrayList<Language>)request.getAttribute("languages");
    ArrayList<Publication> publications = (ArrayList<Publication>)request.getAttribute("publications");
    String adminPanelWord = DBManager.getWordDefinition("adminPanel", lang);
    String newsWord = DBManager.getWordDefinition("news", lang);
    String languagesWord = DBManager.getWordDefinition("languages", lang);
    String publicationsWord = DBManager.getWordDefinition("publications", lang);
    String translationWord = DBManager.getWordDefinition("translation", lang);
%>

<html>
<head>
    <title><%=adminPanelWord%> | <%=newsWord%></title>
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
                    <h1 class="h2"><%=newsWord%></h1>
                    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#addModal">
                        + Add new
                    </button>
                </div>
            <%  if (success != null) {  %>
                <%@include file="../../includes/alertSuccess.jsp"%>
            <%  } else if (error != null) { %>
                <%@include file="../../includes/alertError.jsp"%>
            <%  }   %>

            <%  if (news != null && news.size() != 0) {   %>
                <table class="table table-hover container">
                    <thead>
                    <tr class="row">
                        <th scope="col" class="col-md-1">ID</th>
                        <th scope="col" class="col-md-4">Title</th>
                        <th scope="col" class="col-md-1">Language</th>
                        <th scope="col" class="col-md-2">Added Date</th>
                        <th scope="col" class="col-md-1">Publication</th>
                        <th scope="col" class="col-md-1">Details</th>
                        <th scope="col" class="col-md-2">Operations</th>
                    </tr>
                    </thead>
                    <tbody>
                <%  for (News n : news) {   %>
                    <tr class="row">
                        <th scope="row" class="col-md-1"><%=n.getId()%></th>
                        <td class="col-md-4"><%=n.getTitle()%></td>
                        <td class="col-md-1"><%=n.getLanguage().getName()%></td>
                        <td class="col-md-2"><%=new SimpleDateFormat("dd/MM/yyyy HH:mm").format(n.getPostDate())%></td>
                        <td class="col-md-1"><%=n.getPublication().getName()%></td>
                        <td class="col-md-1"><a href="/news?id=<%=n.getId()%>" class="btn btn-primary">Details</a></td>
                        <td class="col-md-2">
                            <button onclick="editModalFill(<%=n.getId()%>, `<%=n.getTitle()%>`, `<%=n.getShortContent()%>`, `<%=n.getContent()%>`, `<%=n.getImgUrl()%>`, <%=n.getLanguage().getId()%>, <%=n.getPublication().getId()%>)"
                                    type="button" class="btn btn-primary" data-toggle="modal" data-target="#editModal">
                                Edit
                            </button>
                            <button onclick="deleteModalFill(<%=n.getId()%>)" type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal">
                                Delete
                            </button>
                        </td>
                    </tr>
                <%  }   %>
                    </tbody>
                </table>

            <%  } else {    %>
                <h1>News List is empty.</h1>
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
