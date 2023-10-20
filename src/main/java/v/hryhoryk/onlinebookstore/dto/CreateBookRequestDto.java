package v.hryhoryk.onlinebookstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateBookRequestDto(
        String title,
        String author,
        String isbn,
        BigDecimal price,
        String description,
        String coverImage
) {}
