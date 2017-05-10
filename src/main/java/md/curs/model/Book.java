package md.curs.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by MG
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "book.findByGenreAndTitle", query = "FROM Book b where b.genre in :genre AND b.name LIKE :name")
})
public class Book {
    @Id
    private Long id;

    private String name;
    private BigDecimal price;

    @Embedded
    private BookAuthor author;

    @ElementCollection
    @CollectionTable(name = "book_alias_names",
            joinColumns = {@JoinColumn(name = "book_id")}
    )
    private List<String> aliasNames;

    @ElementCollection
    private List<BookAuthor> coAuthors;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    @Enumerated(EnumType.STRING)
    private BookGenre genre;

    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date changedAt;

    @Version
    private Long version;

    @Transient
    private String description;

    @PrePersist
    private void prePersist() {
        this.createDate = new Date();
    }

    @PreUpdate
    private void preUpdate() {
        this.changedAt = new Date();
    }
    
    @PostPersist
    @PostUpdate
    @PostLoad
    private void updateDescription() {
        this.description = this.author.getAuthorName() + ": " + this.name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BookAuthor getAuthor() {
        return author;
    }

    public void setAuthor(BookAuthor author) {
        this.author = author;
    }

    public List<String> getAliasNames() {
        return aliasNames;
    }

    public void setAliasNames(List<String> aliasNames) {
        this.aliasNames = aliasNames;
    }

    public List<BookAuthor> getCoAuthors() {
        return coAuthors;
    }

    public void setCoAuthors(List<BookAuthor> coAuthors) {
        this.coAuthors = coAuthors;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(Date changedAt) {
        this.changedAt = changedAt;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
