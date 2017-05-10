package md.curs.model;

/**
 * Created by MG
 */
public class SimpleBook {
    final private Long id;
    final private String name;
    final private String author;

    public SimpleBook(Long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }
}
