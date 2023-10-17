package v.hryhoryk.onlinebookstore.repository;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import v.hryhoryk.onlinebookstore.exceptions.DataProcessingException;
import v.hryhoryk.onlinebookstore.model.Book;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public BookRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Book save(Book book) {
        try {
            return sessionFactory.fromTransaction(s -> {
                s.persist(book);
                return book;
            });
        } catch (Exception e) {
            throw new DataProcessingException("Can't add book to DB: " + book, e);
        }
    }

    @Override
    public List<Book> findAll() {
        try {
            return sessionFactory.fromSession(s ->
                s.createQuery("from Book ", Book.class)
                        .getResultList());
        } catch (Exception e) {
            throw new DataProcessingException("Can't find any book from DB...", e);
        }
    }
}
