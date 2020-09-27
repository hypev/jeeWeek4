package servlets.user;

import db.DBManager;
import db.Language;
import db.News;
import db.Publication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/")
public class HomePageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ArrayList<News> news = new ArrayList<>();
            String title = "", jumboTitle = "", jumboDescr = "";
            String publicator = request.getParameter("p");
            String key = request.getParameter("k");
            String lang = DBManager.getLangCookie(request);

            if (publicator != null) {
                Publication publication = DBManager.getPublication(Long.parseLong(publicator));
                news = DBManager.getAllNewsByPublication(publication);
                title = publication.getName() + " | " + DBManager.getWordDefinition("news", lang);
                jumboTitle = DBManager.getWordDefinition("searchingJumboTitle", lang);
                jumboDescr = DBManager.getWordDefinition("searchingJumboDesc", lang) + ": " + publication.getName();
            } else if (key != null) {
                news = DBManager.getAllNewsByKeyword(key);
                title = DBManager.getWordDefinition("searchingJumboTitle", key) + ": " + key;
                jumboTitle = DBManager.getWordDefinition("searchingJumboTitle", lang);
                jumboDescr = DBManager.getWordDefinition("searchingJumboDesc", lang) + ": " + key;
            } else {
                news = DBManager.getAllNewsByLanguage(lang);
                title = DBManager.getWordDefinition("navBarName", lang);
                jumboTitle = DBManager.getWordDefinition("homePageJumboTitle", lang);
                jumboDescr = DBManager.getWordDefinition("homePageJumboDesc", lang);
            }

            request.setAttribute("news", news);
            request.setAttribute("title", title);
            request.setAttribute("jumboTitle", jumboTitle);
            request.setAttribute("jumboDescr", jumboDescr);
            request.getRequestDispatcher("/user/homepage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }
}
