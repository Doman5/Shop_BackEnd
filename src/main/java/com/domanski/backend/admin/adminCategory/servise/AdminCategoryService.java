package com.domanski.backend.admin.adminCategory.servise;

import com.domanski.backend.admin.adminCategory.dto.AdminCategoryDto;
import com.domanski.backend.admin.adminCategory.model.AdminCategory;
import com.domanski.backend.admin.adminCategory.repository.AdminCategoryRepository;
import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {
    private final Long EMPTY_ID = null;

    private final AdminCategoryRepository adminCategoryRepository;

    public List<AdminCategory> getAllCategories() {
        return adminCategoryRepository.findAll();
    }

    public AdminCategory getCategoryById(Long id) {
        return adminCategoryRepository.findById(id).orElseThrow();
    }

    public AdminCategory createCategory(AdminCategoryDto adminCategoryDto) {
        return adminCategoryRepository.save(mapAdminCategory(adminCategoryDto, EMPTY_ID));
    }

    public AdminCategory editCategory(Long id, AdminCategoryDto adminCategoryDto) {
        return adminCategoryRepository.save(mapAdminCategory(adminCategoryDto, id));
    }

    public void deleteCategory(Long id) {
        adminCategoryRepository.deleteById(id);
    }


    private AdminCategory mapAdminCategory(AdminCategoryDto categoryDto, Long id) {
        return AdminCategory.builder()
                .id(id)
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .slug(slugifyCategoryName(categoryDto.getSlug()))
                .build();
    }

    private String slugifyCategoryName(String slug) {
        Slugify slg = new Slugify();
        return slg.withCustomReplacement("_","-")
                .slugify(slug);
    }
}
