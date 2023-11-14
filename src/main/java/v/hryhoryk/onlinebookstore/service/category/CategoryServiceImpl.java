package v.hryhoryk.onlinebookstore.service.category;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import v.hryhoryk.onlinebookstore.dto.categorydto.CategoryResponseDto;
import v.hryhoryk.onlinebookstore.dto.categorydto.CreateCategoryRequestDto;
import v.hryhoryk.onlinebookstore.exceptions.EntityNotFoundException;
import v.hryhoryk.onlinebookstore.mapper.CategoryMapper;
import v.hryhoryk.onlinebookstore.model.Category;
import v.hryhoryk.onlinebookstore.repository.category.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find category with id: " + id));
    }

    @Override
    public CategoryResponseDto createCategory(CreateCategoryRequestDto categoryDto) {
        Category categoryEntity = categoryMapper.toCategory(categoryDto);
        return categoryMapper.toDto(categoryRepository.save(categoryEntity));
    }

    @Override
    public CategoryResponseDto updateById(Long id, CreateCategoryRequestDto categoryDto) {
        Category categoryFromDb = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find category with id: " + id));
        categoryMapper.updateCategory(categoryDto, categoryFromDb);
        return categoryMapper.toDto(categoryRepository.save(categoryFromDb));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
