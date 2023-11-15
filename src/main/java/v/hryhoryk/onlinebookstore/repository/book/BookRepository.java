package v.hryhoryk.onlinebookstore.repository.book;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import v.hryhoryk.onlinebookstore.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    @Query(value = "from Book b join fetch b.categories c where c.id = :id")
    List<Book> findBooksByCategoryId(Long id);

    @Query(value = "from Book b join fetch b.categories where b.isDeleted = false")
    List<Book> getBooksWithCategories(Pageable pageable);
}
