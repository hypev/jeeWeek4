<%@ page import="db.News" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.jsoup.Jsoup" %>
<%@ page import="org.jsoup.nodes.Document" %>
<%@ page import="org.jsoup.nodes.Element" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String lang = DBManager.getLangCookie(request);
    String title = (String)request.getAttribute("title");
    String jumboTitle = (String)request.getAttribute("jumboTitle");
    String jumboDescr = (String)request.getAttribute("jumboDescr");
    String homePageCardTime = DBManager.getWordDefinition("homePageCardTime", lang);
    String homePageCardLink = DBManager.getWordDefinition("homePageCardLink", lang);
    String noNews = DBManager.getWordDefinition("noNews", lang);
    ArrayList<News> news = (ArrayList<News>)request.getAttribute("news");
    String styleName = DBManager.getStyleCookie(request);
%>

<html>
<head>
    <title><%=title%></title>
    <%@include file="../includes/head.jsp"%>
</head>
<body class="bg-<%=styleName%>">
    <%@include file="includes/navbar.jsp"%>
    <div class="container">
        <div class="jumbotron jumbotron-fluid mt-3 mb-3 jumbo-<%=styleName%> rounded">
            <div class="container pl-5 pr-5">
                <h1 class="display-4 font-italic"><%=jumboTitle%></h1>
                <p class="lead"><%=jumboDescr%></p>
            </div>
        </div>
        <%  if (news != null && news.size() != 0) { %>
            <%  for(int i = 0; i < news.size(); i++) {  %>
                <div class="row d-flex flex-row justify-content-around align-items-start mt-5 mb-5">
                    <div class="card crd-<%=styleName%> col p-0 mr-3 ml-3">
                        <img src="<%=news.get(i).getImgUrl()%>" class="card-img-top w-100" alt="Img">
                        <div class="card-body">
                            <h6 class="text-primary link-<%=styleName%>"><%=news.get(i).getPublication().getName()%></h6>
                            <h3 class="card-title"><%=news.get(i).getTitle()%></h3>
                            <p class="card-text"><%=news.get(i).getShortContent()%></p>
                            <p class="card-text pt-2"><small class="text-muted txt-muted-<%=styleName%>"><%=homePageCardTime%> <%=new SimpleDateFormat("dd/MM/yyyy HH:mm").format(news.get(i).getPostDate())%></small></p>
                            <a href="/news?id=<%=news.get(i).getId()%>" class="card-link link-<%=styleName%>"><%=homePageCardLink%></a>
                        </div>
                    </div>
                <%  if (i != news.size() - 1) { i++;    %>
                    <div class="card crd-<%=styleName%> col p-0 mr-3 ml-3">
                        <img src="<%=news.get(i).getImgUrl()%>" class="card-img-top w-100" alt="Img">
                        <div class="card-body">
                            <h6 class="text-primary link-<%=styleName%>"><%=news.get(i).getPublication().getName()%></h6>
                            <h3 class="card-title"><%=news.get(i).getTitle()%></h3>
                            <p class="card-text"><%=news.get(i).getShortContent()%></p>
                            <p class="card-text pt-2"><small class="text-muted txt-muted-<%=styleName%>"><%=homePageCardTime%> <%=new SimpleDateFormat("dd/MM/yyyy HH:mm").format(news.get(i).getPostDate())%></small></p>
                            <a href="/news?id=<%=news.get(i).getId()%>" class="card-link link-<%=styleName%>"><%=homePageCardLink%></a>
                        </div>
                    </div>
                <%  }   %>
            </div>
            <%  }   %>
        <%  } else {    %>
            <h1 class="text-center txt-<%=styleName%>"><%=noNews%></h1>
        <%  }   %>
        <p class="txt-<%=styleName%> text-center mt-3 pt-5 pb-3">Copyright &copy; 2020</p>
    </div>
</body>
</html>
