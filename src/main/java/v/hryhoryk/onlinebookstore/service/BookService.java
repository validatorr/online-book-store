package v.hryhoryk.onlinebookstore.service;

import java.util.List;
import v.hryhoryk.onlinebookstore.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
