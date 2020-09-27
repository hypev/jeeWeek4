package db;

public class Publication {
    private Long id;
    private String name;
    private String description;
    private double rating;

    public Publication() { }
    public Publication(Long id, String name, String description, double rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}
