package com.elasticsearch.blogoogle.controller;

import com.elasticsearch.blogoogle.data.Blog;
import com.elasticsearch.blogoogle.data.Category;
import com.elasticsearch.blogoogle.service.BlogService;
import com.elasticsearch.blogoogle.service.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Log4j2
@org.springframework.web.bind.annotation.RestController
@RequestMapping("blogoogle")
public class RestController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/blogs/{title}", produces = "application/json")
    public @ResponseBody Page<Blog> findBlogByTitlePrefix(@PathVariable String title) {
        Page<Blog> blogs = blogService.findBlogsByTitlePrefix(title);

        if (blogs.isEmpty()) {
            handleError(HttpStatus.NOT_FOUND, String.format("No blogs found starting with: %s", title));
        }

        log.info("[findBlogByTitle] Found these blogs {} starting with title: {}", blogs, title);

        return blogs;
    }

    @GetMapping(value = "/blogs", produces = "application/json")
    public @ResponseBody Page<Blog> findBlogsByCategory(@RequestParam String category) {
        Page<Blog> blogs = blogService.findBlogByCategoryName(category);

        if (blogs.isEmpty()) {
            handleError(HttpStatus.NOT_FOUND, String.format("No blogs found with category %s", category));
        }

        log.info("[findBlogsByCategory] Found these blogs {} for query with category: {}", blogs, category);

        return blogs;
    }

    @GetMapping(value = "/categories", produces = "application/json")
    public @ResponseBody Page<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping(value = "/blogs", produces = "application/json")
    public @ResponseBody String createBlogPost(@RequestBody Blog blog) {
        log.info("[createBlogPost] Received blog payload {}", blog);
        if (blogService.checkIfBlogExists(blog)) {
            handleError(HttpStatus.BAD_REQUEST, "Blog with this title already exists");
        }

        List<Category> categories = categoryService.addIdToCategories(blog.getCategories());
        categoryService.saveAllCategoriesWithoutDuplicates(categories);

        return blogService.createBlog(blog, categories);
    }

    private void handleError(final HttpStatus status, final String errorMessage) {
        log.error(errorMessage);
        throw new ResponseStatusException(status, errorMessage);
    }
}
