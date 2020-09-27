package db;

import java.sql.Timestamp;

public class News {
    private Long id;
    private String title;
    private String shortContent;
    private String content;
    private Timestamp postDate;
    private String imgUrl;
    private Language language;
    private Publication publication;

    public News() { }
    public News(Long id, String title, String shortContent, String content, Timestamp postDate, String imgUrl, Language language, Publication publication) {
        this.id = id;
        this.title = title;
        this.shortContent = shortContent;
        this.content = content;
        this.postDate = postDate;
        this.imgUrl = imgUrl;
        this.language = language;
        this.publication = publication;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getShortContent() { return shortContent; }
    public void setShortContent(String shortContent) { this.shortContent = shortContent; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Timestamp getPostDate() { return postDate; }
    public void setPostDate(Timestamp postDate) { this.postDate = postDate; }
    public String getImgUrl() { return imgUrl; }
    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }
    public Language getLanguage() { return language; }
    public void setLanguage(Language language) { this.language = language; }
    public Publication getPublication() { return publication; }
    public void setPublication(Publication publication) { this.publication = publication; }
}
