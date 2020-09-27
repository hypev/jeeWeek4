package db;

public class SocialNetworks {
    private Long id;
    private Publication publication;
    private String instagram;
    private String twitter;
    private String facebook;

    public SocialNetworks() { }
    public SocialNetworks(Long id, Publication publication, String instagram, String twitter, String facebook) {
        this.id = id;
        this.publication = publication;
        this.instagram = instagram;
        this.twitter = twitter;
        this.facebook = facebook;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Publication getPublication() { return publication; }
    public void setPublication(Publication publication) { this.publication = publication; }
    public String getInstagram() { return instagram; }
    public void setInstagram(String instagram) { this.instagram = instagram; }
    public String getTwitter() { return twitter; }
    public void setTwitter(String twitter) { this.twitter = twitter; }
    public String getFacebook() { return facebook; }
    public void setFacebook(String facebook) { this.facebook = facebook; }
}
