package servlets.admin.publications;

import db.DBManager;
import db.Publication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/publications/edit")
public class EditPublicationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            Long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Double rating = Double.parseDouble(request.getParameter("rating"));
            if (DBManager.updatePublication(new Publication(id, name, description, rating))) {
                response.sendRedirect("/admin/publications?success");
            } else throw new Exception("Editing Error");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/admin/publications?error");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
