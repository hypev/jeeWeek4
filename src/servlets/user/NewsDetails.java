package servlets.user;

import db.*;
import org.jsoup.Jsoup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/news")
public class NewsDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            News news = DBManager.getNews(id);
            SocialNetworks socialNetworks = DBManager.getSocialNetworks(news.getPublication());
            if (socialNetworks == null) {
                if(DBManager.addSocialNetworks(news.getPublication())) {
                    socialNetworks = DBManager.getSocialNetworks(news.getPublication());
                }
            }
            request.setAttribute("news", news);
            request.setAttribute("title", news.getPublication().getName() + " | " + news.getTitle());
            request.setAttribute("socialNetworks", socialNetworks);
            request.getRequestDispatcher("/user/newsDetails.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }
}
