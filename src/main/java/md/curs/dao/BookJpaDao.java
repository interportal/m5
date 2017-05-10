package md.curs.dao;

import md.curs.model.Book;
import md.curs.model.BookGenre;
import md.curs.model.SimpleBook;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by MG
 */
@Repository
public class BookJpaDao {

    @PersistenceContext
    EntityManager em;

    public void adjustPrice(long bookId, BigDecimal increment) {
        Book book = em.find(Book.class, bookId);
        em.lock(book, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        BigDecimal currentPrice = book.getPrice();
        book.setPrice(currentPrice.multiply(increment));
    }

    public List<Book> findByGenre(BookGenre genre) {
        return em.createNamedQuery("book.findByGenreAndTitle", Book.class)
                .setParameter("genre", genre)
                .setParameter("name", "%")
                .getResultList();
    }

    public List<String> getAllBookNames() {
        return em.createQuery("SELECT b.name from Book b", String.class)
                .getResultList();
    }

    public List<SimpleBook> getAllBooks() {
        return em.createQuery("SELECT new md.curs.model.SimpleBook(b.id, b.name, b.author.authorName) from Book b",
                                SimpleBook.class)
                 .getResultList();
    }
}
