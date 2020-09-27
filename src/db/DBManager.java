package db;

import org.jsoup.Jsoup;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/task4?useUnicode=true&serverTimezone=UTC",
                    "root",
                    "");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ DBManager ]-------------");
        }
    }

    public static Language getLanguage(Long id) {
        Language language = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM languages " +
                    "WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                language = new Language(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ getLanguage() ]-------------");
        }
        return language;
    }
    public static Publication getPublication(Long id) {
        Publication publication = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM publications " +
                    "WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                publication = new Publication(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("rating")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ getPublication() ]-------------");
        }
        return publication;
    }
    public static News getNews(Long id) {
        News news = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM news " +
                    "WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                news = new News(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date"),
                        resultSet.getString("picture_url"),
                        getLanguage(resultSet.getLong("language_id")),
                        getPublication(resultSet.getLong("publication_id"))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ getNews() ]-------------");
        }
        return news;
    }
    public static SocialNetworks getSocialNetworks(Publication publication) {
        SocialNetworks socialNetworks = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM social_network_links " +
                    "WHERE publication_id=?");
            preparedStatement.setLong(1, publication.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                socialNetworks = new SocialNetworks(
                        resultSet.getLong("id"),
                        publication,
                        resultSet.getString("instagram"),
                        resultSet.getString("twitter"),
                        resultSet.getString("facebook")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ getSocialNetworks() ]-------------");
        }
        return socialNetworks;
    }
    public static String getWordDefinition(String dictName, String langCode) {
        String word = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM word_defenition " +
                    "WHERE dictionary_id=(SELECT id " +
                    "FROM dictionary " +
                    "WHERE name=?)" +
                    "AND language_id=(SELECT id " +
                    "FROM languages " +
                    "WHERE code=?)");
            preparedStatement.setString(1, dictName);
            preparedStatement.setString(2, langCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                word = resultSet.getString("word");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ getWordDefinition() ]-------------");
        }
        if (word == null || word.equals("")) return getWordDefinition(dictName, "ENG");
        return word;
    }
    public static Word getWord(Long id) {
        Word word = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM word_defenition " +
                    "WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                word = new Word(
                        resultSet.getLong("id"),
                        resultSet.getString("word"),
                        getDictionary(resultSet.getLong("dictionary_id")),
                        getLanguage(resultSet.getLong("language_id"))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ getWord() ]-------------");
        }
        return word;
    }
    public static Dictionary getDictionary(Long id) {
        Dictionary dictionary = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM dictionary " +
                    "WHERE id=? ");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                dictionary = new Dictionary(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ getDictionary() ]-------------");
        }
        return dictionary;
    }
    public static Dictionary getDictionary(String name) {
        Dictionary dictionary = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM dictionary " +
                    "WHERE name=?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                dictionary = new Dictionary(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ getDictionary() ]-------------");
        }
        return dictionary;
    }

    public static boolean addLanguage(Language language) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "INSERT INTO languages (name, code) " +
                    "VALUES (?, ?)");
            preparedStatement.setString(1, language.getName());
            preparedStatement.setString(2, language.getCode());
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ addLanguage() ]-------------");
        }
        return rows > 0;
    }
    public static boolean addPublication(Publication publication) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "INSERT INTO publications (name, description, rating) " +
                    "VALUES (?, ?, ?)");
            preparedStatement.setString(1, publication.getName());
            preparedStatement.setString(2, publication.getDescription());
            preparedStatement.setDouble(3, publication.getRating());
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ addPublication() ]-------------");
        }
        return rows > 0;
    }
    public static boolean addNews(News news) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "INSERT INTO news (title, short_content, content, post_date, picture_url, language_id, publication_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getShortContent());
            preparedStatement.setString(3, news.getContent());
            preparedStatement.setTimestamp(4, news.getPostDate());
            preparedStatement.setString(5, news.getImgUrl());
            preparedStatement.setLong(6, news.getLanguage().getId());
            preparedStatement.setLong(7, news.getPublication().getId());
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ addNews() ]-------------");
        }
        return rows > 0;
    }
    public static boolean addSocialNetworks(Publication publication) {
        int rows = 0;
        try {
            String instagramUrlParse = Jsoup.connect("https://www.google.com/search?q=" + publication.getName() + "+instagram").get()
                    .select(".r").select("a").get(0).attr("href");
            String twitterUrlParse = Jsoup.connect("https://www.google.com/search?q=" + publication.getName() + "+twitter").get()
                    .select(".r").select("a").get(0).attr("href");
            String facebookUrlParse = Jsoup.connect("https://www.google.com/search?q=" + publication.getName() + "+facebook").get()
                    .select(".r").select("a").get(0).attr("href");

            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "INSERT INTO social_network_links (publication_id, instagram, twitter, facebook) " +
                    "VALUES (?, ?, ?, ?)");
            preparedStatement.setLong(1, publication.getId());
            preparedStatement.setString(2, instagramUrlParse);
            preparedStatement.setString(3, twitterUrlParse);
            preparedStatement.setString(4, facebookUrlParse);
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ addSocialNetworks() ]-------------");
        }
        return rows > 0;
    }
    public static boolean addWord(Word word) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "INSERT INTO word_defenition (word, dictionary_id, language_id) " +
                    "VALUES (?, ?, ?)");
            preparedStatement.setString(1, word.getWord());
            preparedStatement.setLong(2, word.getDictionary().getId());
            preparedStatement.setLong(3, word.getLanguage().getId());
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ addWord() ]-------------");
        }
        return rows > 0;
    }
    public static boolean addDictionary(Dictionary dictionary) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "INSERT INTO dictionary (name) " +
                    "VALUES (?)");
            preparedStatement.setString(1, dictionary.getName());
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ addDictionary() ]-------------");
        }
        return rows > 0;
    }

    public static boolean updateLanguage(Language language) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "UPDATE languages " +
                    "SET name=?, code=? " +
                    "WHERE id=?");
            preparedStatement.setString(1, language.getName());
            preparedStatement.setString(2, language.getCode());
            preparedStatement.setLong(3, language.getId());
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ updateLanguage() ]-------------");
        }
        return rows > 0;
    }
    public static boolean updatePublication(Publication publication) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "UPDATE publications " +
                    "SET name=?, description=?, rating=? " +
                    "WHERE id=?");
            preparedStatement.setString(1, publication.getName());
            preparedStatement.setString(2, publication.getDescription());
            preparedStatement.setDouble(3, publication.getRating());
            preparedStatement.setLong(4, publication.getId());
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ updatePublication() ]-------------");
        }
        return rows > 0;
    }
    public static boolean updateNews(News news) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "UPDATE news " +
                    "SET title=?, short_content=?, content=?, post_date=?," +
                    "picture_url=?, language_id=?, publication_id=? " +
                    "WHERE id=?");
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getShortContent());
            preparedStatement.setString(3, news.getContent());
            preparedStatement.setTimestamp(4, news.getPostDate());
            preparedStatement.setString(5, news.getImgUrl());
            preparedStatement.setLong(6, news.getLanguage().getId());
            preparedStatement.setLong(7, news.getPublication().getId());
            preparedStatement.setLong(8, news.getId());
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ updateNews() ]-------------");
        }
        return rows > 0;
    }
    public static boolean updateWord(Word word) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "UPDATE word_defenition " +
                    "SET word=?, dictionary_id=?, language_id=? " +
                    "WHERE id=?");
            preparedStatement.setString(1, word.getWord());
            preparedStatement.setLong(2, word.getDictionary().getId());
            preparedStatement.setLong(3, word.getLanguage().getId());
            preparedStatement.setLong(4, word.getId());
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ updateWord() ]-------------");
        }
        return rows > 0;
    }
    public static boolean updateDictionary(Dictionary dictionary) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "UPDATE dictionary " +
                    "SET name=? " +
                    "WHERE id=?");
            preparedStatement.setString(1, dictionary.getName());
            preparedStatement.setLong(2, dictionary.getId());
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ updateDictionary() ]-------------");
        }
        return rows > 0;
    }

    public static boolean deleteLanguage(Long id) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "DELETE FROM languages WHERE id=?");
            preparedStatement.setLong(1, id);
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ deleteLanguage() ]-------------");
        }
        return rows > 0;
    }
    public static boolean deletePublication(Long id) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "DELETE FROM publications WHERE id=?");
            preparedStatement.setLong(1, id);
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ deletePublication() ]-------------");
        }
        return rows > 0;
    }
    public static boolean deleteNews(Long id) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "DELETE FROM news WHERE id=?");
            preparedStatement.setLong(1, id);
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ deleteNews() ]-------------");
        }
        return rows > 0;
    }
    public static boolean deleteWord(Long id) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "DELETE FROM word_defenition WHERE id=?");
            preparedStatement.setLong(1, id);
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ deleteWord() ]-------------");
        }
        return rows > 0;
    }
    public static boolean deleteDictionary(Long id) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "DELETE FROM dictionary WHERE id=?");
            preparedStatement.setLong(1, id);
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ deleteDictionary() ]-------------");
        }
        return rows > 0;
    }

    public static ArrayList<Language> getAllLanguages() {
        ArrayList<Language> languages = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM languages");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                languages.add(new Language(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ getAllLanguages() ]-------------");
        }
        return languages;
    }
    public static ArrayList<Publication> getAllPublications() {
        ArrayList<Publication> publications = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM publications");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                publications.add(new Publication(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("rating")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ getAllPublications() ]-------------");
        }
        return publications;
    }
    public static ArrayList<News> getAllNews() {
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM news " +
                    "ORDER BY post_date DESC");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                news.add(new News(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date"),
                        resultSet.getString("picture_url"),
                        getLanguage(resultSet.getLong("language_id")),
                        getPublication(resultSet.getLong("publication_id"))
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ getAllNews() ]-------------");
        }
        return news;
    }
    public static ArrayList<Word> getAllWords() {
        ArrayList<Word> words = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM word_defenition");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                words.add(new Word(
                        resultSet.getLong("id"),
                        resultSet.getString("word"),
                        getDictionary(resultSet.getLong("dictionary_id")),
                        getLanguage(resultSet.getLong("language_id"))
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ getAllWords() ]-------------");
        }
        return words;
    }
    public static ArrayList<Dictionary> getAllDictionaries() {
        ArrayList<Dictionary> dictionaries = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM dictionary " +
                    "ORDER BY id DESC");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dictionaries.add(new Dictionary(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ getAllDictinary() ]-------------");
        }
        return dictionaries;
    }

    public static ArrayList<News> getAllNewsByLanguage(String code) {
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM news " +
                    "WHERE language_id=(SELECT id " +
                                       "FROM languages " +
                                       "WHERE code=?) " +
                    "ORDER BY post_date DESC");
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                news.add(new News(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date"),
                        resultSet.getString("picture_url"),
                        getLanguage(resultSet.getLong("language_id")),
                        getPublication(resultSet.getLong("publication_id"))
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ getAllNewsByLanguage() ]-------------");
        }
        return news;
    }
    public static ArrayList<News> getAllNewsByPublication(Publication publication) {
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM news " +
                    "WHERE publication_id=? " +
                    "ORDER BY post_date DESC");
            preparedStatement.setLong(1, publication.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                news.add(new News(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date"),
                        resultSet.getString("picture_url"),
                        getLanguage(resultSet.getLong("language_id")),
                        publication
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ getAllNewsByPublication() ]-------------");
        }
        return news;
    }
    public static ArrayList<News> getAllNewsByKeyword(String key) {
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM news " +
                    "WHERE title LIKE '%" + key + "%' " +
                    "OR short_content LIKE '%" + key + "' " +
                    "OR content LIKE '%" + key + "%' " +
                    "ORDER BY post_date DESC");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                news.add(new News(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date"),
                        resultSet.getString("picture_url"),
                        getLanguage(resultSet.getLong("language_id")),
                        getPublication(resultSet.getLong("publication_id"))
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ getAllNewsByKeyword() ]-------------");
        }
        return news;
    }

    public static ArrayList<Word> getAllWordsByDictionary(Dictionary dictionary) {
        ArrayList<Word> words = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT * " +
                    "FROM word_defenition " +
                    "WHERE dictionary_id=?");
            preparedStatement.setLong(1, dictionary.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                words.add(new Word(
                        resultSet.getLong("id"),
                        resultSet.getString("word"),
                        getDictionary(resultSet.getLong("dictionary_id")),
                        getLanguage(resultSet.getLong("language_id"))
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------[ getAllWordsByDictionary() ]-------------");
        }
        return words;
    }

    public static String getLangCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String langCode = "ENG";
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("lang")) {
                    langCode = c.getValue();
                    break;
                }
            }
        }
        return langCode;
    }
    public static String getStyleCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String style = "default";
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("style")) {
                    style = c.getValue();
                    break;
                }
            }
        }
        return style;
    }
}
