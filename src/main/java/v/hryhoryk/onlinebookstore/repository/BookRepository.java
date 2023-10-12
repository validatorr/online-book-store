package v.hryhoryk.onlinebookstore.repository;

import java.util.List;
import v.hryhoryk.onlinebookstore.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
