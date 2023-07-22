package com.elasticsearch.blogoogle.service;

import com.elasticsearch.blogoogle.data.Category;
import com.elasticsearch.blogoogle.repository.CategoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Log4j2
@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Page<Category> getAllCategories() {
        return categoryRepository.findAll(PageRequest.ofSize(50));
    }
    public List<Category> addIdToCategories(List<Category> categories) {
        return categories.stream()
                .map(category -> category.withId(category.getName().trim().toLowerCase(Locale.US)))
                .toList();
    }

    public void saveAllCategoriesWithoutDuplicates(List<Category> categories) {
        List<Category> newCategoryItems = categories.stream()
                .filter(category -> categoryRepository.findById(category.getId()).isEmpty())
                .toList();

        List<Category> createdCategories = (List<Category>) categoryRepository.saveAll(newCategoryItems);

        log.info("Successfully created categories: {}", Arrays.toString(createdCategories.toArray()));
    }
}
