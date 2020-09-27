package servlets.admin.publications;

import db.DBManager;
import db.Language;
import db.Publication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/publications/add")
public class AddPublicationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Double rating = Double.parseDouble(request.getParameter("rating"));
            if (DBManager.addPublication(new Publication(null, name, description, rating))) {
                response.sendRedirect("/admin/publications?success");
            } else throw new Exception("Adding Error");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/admin/publications?error");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
