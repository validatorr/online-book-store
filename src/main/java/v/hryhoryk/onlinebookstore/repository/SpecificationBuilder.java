package v.hryhoryk.onlinebookstore.repository;

import org.springframework.data.jpa.domain.Specification;
import v.hryhoryk.onlinebookstore.dto.BookSearchParameters;

public interface SpecificationBuilder<T> {
    Specification<T> build(BookSearchParameters searchParameters);
}
