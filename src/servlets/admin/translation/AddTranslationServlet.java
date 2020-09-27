package servlets.admin.translation;

import db.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/translation/add")
public class AddTranslationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String name = request.getParameter("name");
            if (DBManager.addDictionary(new Dictionary(null, name))) {
                Dictionary dictionary = DBManager.getDictionary(name);
                for (Language l : DBManager.getAllLanguages()) {
                    String word = request.getParameter("word" + l.getCode());
                    if (DBManager.addWord(new Word(null, word, dictionary, l)))
                        continue;
                    else throw new Exception("Adding Word Error");
                }
            } else throw new Exception("Adding Dictionary Error");
            response.sendRedirect("/admin/translation?success");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/admin/translation?error");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
