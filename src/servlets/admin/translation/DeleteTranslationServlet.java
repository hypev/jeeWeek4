package servlets.admin.translation;

import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/translation/delete")
public class DeleteTranslationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            if (DBManager.deleteDictionary(id)) {
                response.sendRedirect("/admin/translation?success");
            } else throw new Exception("Deleting Error");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/admin/translation?error");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
