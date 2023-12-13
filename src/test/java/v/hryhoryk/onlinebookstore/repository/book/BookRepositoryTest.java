package v.hryhoryk.onlinebookstore.repository.book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import v.hryhoryk.onlinebookstore.model.Book;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    @Sql(scripts = {"classpath:database/categories/insert-one-category.sql",
            "classpath:database/books/insert-three-books.sql",
            "classpath:database/books_categories/insert-books-and-categories.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DisplayName("find all books by category id")
    void findBooksByCategoryId_WhenCategoryIdIsExisting_ShouldReturnListOfThreeBooks() {
        List<Book> actual = bookRepository.findBooksByCategoryId(1L);
        int expected = 3;
        assertThat(actual).hasSize(expected);
    }

    @Sql(scripts = {"classpath:database/categories/insert-one-category.sql",
            "classpath:database/books/insert-three-books.sql",
            "classpath:database/books_categories/insert-books-and-categories.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DisplayName("find all books by category id")
    @Test
    void findBooksByCategoryId_WhenCategoryIdIsNotExisting_ShouldReturnEmptyList() {
        List<Book> actual = bookRepository.findBooksByCategoryId(999L);
        assertThat(actual).isEmpty();
    }

    @Sql(scripts = {"classpath:database/categories/insert-one-category.sql",
            "classpath:database/books/insert-three-books.sql",
            "classpath:database/books_categories/insert-books-and-categories.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    @DisplayName("get all books with categories")
    void getBooksWithCategories_WhenThreeBooksHaveCategory_ShouldReturnListOfThreeBooks() {
        List<Book> actual = bookRepository.getBooksWithCategories(PageRequest.of(0, 10));
        int expected = 3;
        assertEquals(expected, actual.size());
    }

    @Sql(scripts = {
            "classpath:database/books/insert-three-books.sql",
            "classpath:database/categories/insert-one-deleted-category.sql",
            "classpath:database/books_categories/insert-books-and-categories.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    @DisplayName("get all books with categories")
    void getBooksWithCategories_WhenNoneOfBooksHaveCategory_ShouldReturnEmptyList() {
        List<Book> actual = bookRepository.getBooksWithCategories(PageRequest.of(0, 10));
        int expected = 0;
        assertThat(actual).hasSize(expected);
    }
}
