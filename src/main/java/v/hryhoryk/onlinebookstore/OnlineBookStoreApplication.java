package v.hryhoryk.onlinebookstore;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import v.hryhoryk.onlinebookstore.model.Book;
import v.hryhoryk.onlinebookstore.service.BookService;

@SpringBootApplication
public class OnlineBookStoreApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(OnlineBookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book firstBook = new Book();
            firstBook.setAuthor(
                    "Barbara Oakley");
            firstBook.setTitle(
                    "Learning how to learn");
            firstBook.setDescription(
                    "After this book your attitude to studying process will change forever!");
            firstBook.setPrice(
                    BigDecimal.valueOf(329));

            bookService.save(firstBook);

            System.out.println(bookService.findAll());
        };
    }

}
