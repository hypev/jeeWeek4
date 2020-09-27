<%@ page import="db.DBManager" %>

<%
    String navBarName = DBManager.getWordDefinition("navBarName", lang);
    String navBarSearch = DBManager.getWordDefinition("navBarSearch", lang);
    String navBarSignOut = DBManager.getWordDefinition("navBarSignOut", lang);
%>

<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
    <span class="navbar-brand col-md-3 col-lg-2 mr-0 px-3"><%=navBarName%></span>
    <button class="navbar-toggler position-absolute d-md-none collapsed"
            type="button" data-toggle="collapse" data-target="#sidebarMenu" aria-controls="sidebarMenu"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <input class="form-control form-control-dark w-100" type="text" placeholder="<%=navBarSearch%>"
           aria-label="Search" name="k" form="search">
    <form method="get" action="/admin/news/search" id="search"></form>
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="/"><%=navBarSignOut%></a>
        </li>
    </ul>
</nav>
