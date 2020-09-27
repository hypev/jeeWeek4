package servlets.admin.publications;

import db.DBManager;
import db.Publication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/publications/delete")
public class DeletePublicationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            if (DBManager.deletePublication(id)) {
                response.sendRedirect("/admin/publications?success");
            } else throw new Exception("Deleting Error");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/admin/publications?error");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
