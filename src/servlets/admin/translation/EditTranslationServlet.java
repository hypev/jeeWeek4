package servlets.admin.translation;

import db.DBManager;
import db.Dictionary;
import db.Language;
import db.Word;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/translation/edit")
public class EditTranslationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            Long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");
            if (DBManager.updateDictionary(new Dictionary(id, name))) {
                Dictionary dictionary = DBManager.getDictionary(id);
                for (Language l : DBManager.getAllLanguages()) {
                    String wordStringId = request.getParameter("editId" + l.getId());
                    String word = request.getParameter("word" + l.getCode());
                    if (wordStringId.equals("")) {
                        if (DBManager.addWord(new Word(null, word, dictionary, l))) {
                            continue;
                        } else throw new Exception("Adding on Editing Error");
                    } else {
                        Long wordId = Long.parseLong(wordStringId);
                        if (DBManager.updateWord(new Word(wordId, word, dictionary, l)))
                            continue;
                        else throw new Exception("Editing Word Error");
                    }
                }
            } else throw new Exception("Editing Dictionary Error");
            response.sendRedirect("/admin/translation?success");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/admin/translation?error");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
