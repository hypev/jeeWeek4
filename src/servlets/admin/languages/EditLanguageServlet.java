package servlets.admin.languages;

import db.DBManager;
import db.Language;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/languages/edit")
public class EditLanguageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            Long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");
            String code = request.getParameter("code");
            if (DBManager.updateLanguage(new Language(id, name, code))) {
                response.sendRedirect("/admin/languages?success");
            } else throw new Exception("Editing Error");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/admin/languages?error");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
