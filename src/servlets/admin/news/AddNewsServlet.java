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
import java.sql.Timestamp;

@WebServlet(value = "/admin/news/add")
public class AddNewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String title = request.getParameter("title");
            String shortContent = request.getParameter("shortContent");
            String content = request.getParameter("content");
            Timestamp postDate = new Timestamp(System.currentTimeMillis());
            String imgUrl = request.getParameter("imgUrl");
            Language language = DBManager.getLanguage(Long.parseLong(request.getParameter("languageId")));
            Publication publication = DBManager.getPublication(Long.parseLong(request.getParameter("publicationId")));
            if (DBManager.addNews(new News(null, title, shortContent, content, postDate, imgUrl, language, publication))) {
                response.sendRedirect("/admin/news?success");
            } else throw new Exception("Adding Error");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/admin/news?error");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
