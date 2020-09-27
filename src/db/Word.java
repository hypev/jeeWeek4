package db;

public class Word {
    private Long id;
    private String word;
    private Dictionary dictionary;
    private Language language;

    public Word() { }
    public Word(Long id, String word, Dictionary dictionary, Language language) {
        this.id = id;
        this.word = word;
        this.dictionary = dictionary;
        this.language = language;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getWord() { return word; }
    public void setWord(String word) { this.word = word; }
    public Dictionary getDictionary() { return dictionary; }
    public void setDictionary(Dictionary dictionary) { this.dictionary = dictionary; }
    public Language getLanguage() { return language; }
    public void setLanguage(Language language) { this.language = language; }
}
