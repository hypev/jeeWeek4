package servlets.admin.news;

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

@WebServlet(value = "/admin/news")
public class NewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ArrayList<News> news = DBManager.getAllNews();
            ArrayList<Language> languages = DBManager.getAllLanguages();
            ArrayList<Publication> publications = DBManager.getAllPublications();

            request.setAttribute("news", news);
            request.setAttribute("languages", languages);
            request.setAttribute("publications", publications);
            request.setAttribute("activeLink", "news");

            request.getRequestDispatcher("/admin/news/news.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }
}
