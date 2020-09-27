<%
    String activeLink = (String)request.getAttribute("activeLink");
    boolean langBool = false, publBool = false, newsBool = false, translBool = false;
    if (activeLink != null) {
        switch (activeLink) {
            case "languages": langBool = true; break;
            case "publications": publBool = true; break;
            case "news": newsBool = true; break;
            case "translation": translBool = true; break;
        }
    }
%>

<nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="sidebar-sticky pt-3">
        <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
            <span><%=adminPanelWord%></span>
            <a class="d-flex align-items-center text-muted" href="#" aria-label="Add a new report">
                <span data-feather="plus-circle"></span>
            </a>
        </h6>
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link <%=langBool?"active":""%>" href="<%=!langBool?"/admin/languages":"#"%>"><%=languagesWord%></a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=publBool?"active":""%>" href="<%=!publBool?"/admin/publications":"#"%>"><%=publicationsWord%></a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=newsBool?"active":""%>" href="<%=!newsBool?"/admin/news":"#"%>"><%=newsWord%></a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=translBool?"active":""%>" href="<%=!translBool?"/admin/translation":"#"%>"><%=translationWord%></a>
            </li>
        </ul>
    </div>
</nav>