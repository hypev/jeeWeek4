<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.jsoup.Jsoup" %>
<%@ page import="db.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String lang = DBManager.getLangCookie(request);
    String title = (String)request.getAttribute("title");
    News news = (News)request.getAttribute("news");
    SocialNetworks socialNetworks = (SocialNetworks)request.getAttribute("socialNetworks");
    String newsDetailsAt = DBManager.getWordDefinition("newsDetailsAt", lang);
    String newsDetailsBy = DBManager.getWordDefinition("newsDetailsBy", lang);
    String newsDetailsArchives = DBManager.getWordDefinition("newsDetailsArchives", lang);
    String newsDetailsFollowUs = DBManager.getWordDefinition("newsDetailsFollowUs", lang);
    String newsDetailsAbout = DBManager.getWordDefinition("newsDetailsAbout", lang);
    String newsDetailsRating = DBManager.getWordDefinition("newsDetailsRating", lang);
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
      <div class="row mt-5">

        <div class="col-8">
          <h3 class="font-weight-bold txt-<%=styleName%>"><%=news.getTitle()%></h3>
          <p class="pt-2"><small class="text-muted txt-muted-<%=styleName%>"><%=newsDetailsAt%> <%=new SimpleDateFormat("dd/MM/yyyy HH:mm").format(news.getPostDate())%> <%=newsDetailsBy%> <a href="/?p=<%=news.getPublication().getId()%>" class="link-<%=styleName%>"><%=news.getPublication().getName()%></a> </small></p>
          <img src="<%=news.getImgUrl()%>" class="img-fluid rounded" alt="Responsive image">
          <div class="card mt-4 crd-<%=styleName%>">
            <div class="card-body">
              <h6 class="font-weight-bold"><%=news.getShortContent()%></h6>
              <p><%=news.getContent()%></p>
            </div>
          </div>
        </div>

        <div class="col-4">
          <div class="jumbotron jumbotron-fluid  jumbo-<%=styleName%> rounded pt-4 pb-4 pl-3 pr-3">
            <div class="container">
              <h1 class="font-weight-bold font-italic"><%=newsDetailsAbout%> - <%=news.getPublication().getName()%></h1>
              <p><%=news.getPublication().getDescription()%></p>
              <h5 class="font-weight-bold font-italic"><%=newsDetailsRating%>: <%=news.getPublication().getRating()%></h5>
            </div>
          </div>
<%--          <div class="p4 mt-5">--%>
<%--            <h5 class="font-weight-bold font-italic"><%=newsDetailsArchives%></h5>--%>
<%--            <ul class="list-group" style="list-style: none">--%>
<%--              <li>--%>
<%--                <a class="badge badge-primary" href="#" target="_blank">September, 2020</a>--%>
<%--              </li>--%>
<%--            </ul>--%>
<%--          </div>--%>
          <div class="p4 mt-5">
            <h5 class="font-weight-bold font-italic txt-<%=styleName%>"><%=newsDetailsFollowUs%></h5>
            <ul class="list-group" style="list-style: none">
              <li>
                <a class="badge badge-primary bdg-<%=styleName%>" href="<%=socialNetworks.getInstagram()%>" target="_blank">Instagram</a>
              </li>
              <li>
                <a class="badge badge-primary bdg-<%=styleName%>"  href="<%=socialNetworks.getTwitter()%>" target="_blank">Twitter</a>
              </li>
              <li>
                <a class="badge badge-primary bdg-<%=styleName%>" href="<%=socialNetworks.getFacebook()%>" target="_blank">Facebook</a>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <p class="text-center mt-3 pt-5 pb-3 txt-<%=styleName%>">Copyright &copy; 2020</p>
    </div>

  </body>
</html>
