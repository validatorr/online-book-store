package v.hryhoryk.onlinebookstore.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import v.hryhoryk.onlinebookstore.dto.bookdto.BookSearchParameters;

public interface SpecificationBuilder<T> {
    Specification<T> build(BookSearchParameters searchParameters);
}
