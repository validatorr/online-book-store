package v.hryhoryk.onlinebookstore.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import v.hryhoryk.onlinebookstore.exceptions.DataProcessingException;
import v.hryhoryk.onlinebookstore.model.Book;

@RequiredArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepository {
    private final EntityManagerFactory factory;

    @Override
    public Book save(Book book) {
        EntityTransaction transaction = null;
        try (EntityManager manager = factory.createEntityManager()) {
            transaction = manager.getTransaction();
            transaction.begin();
            manager.persist(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add book to DB: " + book, e);
        }
    }

    @Override
    public List<Book> findAll() {
        try (EntityManager manager = factory.createEntityManager()) {
            return manager.createQuery("from Book", Book.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find any book from DB...", e);
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        try (EntityManager manager = factory.createEntityManager()) {
            return Optional.ofNullable(manager.find(Book.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't find book by this id: " + id, e);
        }
    }
}
