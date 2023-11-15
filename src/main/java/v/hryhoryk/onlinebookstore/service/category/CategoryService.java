package v.hryhoryk.onlinebookstore.service.category;

import java.util.List;
import org.springframework.data.domain.Pageable;
import v.hryhoryk.onlinebookstore.dto.categorydto.CategoryResponseDto;
import v.hryhoryk.onlinebookstore.dto.categorydto.CreateCategoryRequestDto;

public interface CategoryService {
    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto getById(Long id);

    CategoryResponseDto createCategory(CreateCategoryRequestDto categoryDto);

    CategoryResponseDto updateById(Long id, CreateCategoryRequestDto categoryDto);

    void deleteById(Long id);
}
