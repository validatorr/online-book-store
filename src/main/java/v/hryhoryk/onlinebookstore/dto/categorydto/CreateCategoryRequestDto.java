package v.hryhoryk.onlinebookstore.dto.categorydto;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequestDto(
        @NotBlank(message = "Category name can't be blank")
        String name,
        @NotBlank(message = "Category description can't be blank")
        String description
) {
}
