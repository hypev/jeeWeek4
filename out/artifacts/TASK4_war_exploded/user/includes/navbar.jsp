<%@ page import="java.util.ArrayList" %>
<%@ page import="db.Language" %>
<%@ page import="db.Publication" %>
<%@ page import="db.DBManager" %>

<%
    ArrayList<Language> languages = DBManager.getAllLanguages();
    ArrayList<Publication> publications = DBManager.getAllPublications();
    String navBarName = DBManager.getWordDefinition("navBarName", lang);
    String navBarSearch = DBManager.getWordDefinition("navBarSearch", lang);
    String navBarSignUp = DBManager.getWordDefinition("navBarSignUp", lang);
    String style = DBManager.getWordDefinition("style", lang);
    String def = DBManager.getWordDefinition("default", lang);
    String light = DBManager.getWordDefinition("light", lang);
    String dark = DBManager.getWordDefinition("dark", lang);
    String monochrome = DBManager.getWordDefinition("monochrome", lang);
    String monochromeInverse = DBManager.getWordDefinition("monochromeInverse", lang);
    String large = DBManager.getWordDefinition("large", lang);
    String small = DBManager.getWordDefinition("small", lang);
%>

<nav class="navbar navbar-expand-lg bg-<%=styleName%>">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="dropdown">
        <button class="btn bt-<%=styleName%> dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <%=style%>
        </button>
        <div class="dropdown-menu bg-<%=styleName%>" aria-labelledby="dropdownMenu2">
            <form method="post" action="/t" id="t" style="margin: 0"></form>
            <button class="dropdown-item txt-<%=styleName%>" name="btn" form="t" value="default"><%=def%></button>
            <button class="dropdown-item txt-<%=styleName%>" name="btn" form="t" value="light"><%=light%></button>
            <button class="dropdown-item txt-<%=styleName%>" name="btn" form="t" value="dark"><%=dark%></button>
            <button class="dropdown-item txt-<%=styleName%>" name="btn" form="t" value="monochrome"><%=monochrome%></button>
            <button class="dropdown-item txt-<%=styleName%>" name="btn" form="t" value="monochromeInverse"><%=monochromeInverse%></button>
            <button class="dropdown-item txt-<%=styleName%>" name="btn" form="t" value="large"><%=large%></button>
            <button class="dropdown-item txt-<%=styleName%>" name="btn" form="t" value="small"><%=small%></button>
            <button class="dropdown-item txt-<%=styleName%>" name="btn" form="t" value="facebook">Facebook</button>
            <button class="dropdown-item txt-<%=styleName%>" name="btn" form="t" value="instagram">Instagram</button>
        </div>
    </div>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto col-md-5">

    <%  if (languages != null && languages.size() != 0) {   %>
        <%  for(Language l : languages) { %>
            <li class="nav-item">
                <form method="post" action="/l" id="l<%=l.getId()%>" style="margin: 0;">
                    <input name="code" value="<%=l.getCode()%>" type="hidden">
                </form>
                <button form="l<%=l.getId()%>" class="nav-link txt-<%=styleName%>" style="outline: none; border: none; background:transparent;"><%=l.getCode()%></button>
            </li>
        <%  }   %>
    <%  }   %>

        </ul>
        <a href="/" class="navbar-brand font-weight-bold txt-<%=styleName%> text-center"><%=navBarName%></a>
        <form class="form-inline my-2 my-lg-0 ml-auto col-md-5 d-flex align-items-center justify-content-end" method="get" action="/">
            <input class="form-control mr-sm-2" type="search" placeholder="<%=navBarSearch%>" aria-label="Search" name="k">
            <a href="/admin/languages" class="btn bt-<%=styleName%> ml-3"><%=navBarSignUp%></a>
        </form>
    </div>
</nav>
<%  if (publications != null && publications.size() != 0) { %>
<div class="btn-group d-flex justify-content-center align-items-center" role="group" aria-label="Basic example">
<%  for (Publication p : publications) {    %>
    <a href="/?p=<%=p.getId()%>" class="btn bt-<%=styleName%>"><%=p.getName()%></a>
<%  }   %>
</div>
<%  }   %>