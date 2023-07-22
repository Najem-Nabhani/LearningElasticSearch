package com.elasticsearch.blogoogle.controller;

import com.elasticsearch.blogoogle.constants.Constants;
import com.elasticsearch.blogoogle.data.Blog;
import com.elasticsearch.blogoogle.data.Category;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.stream.Collectors;

@Log4j2
@Controller
public class RenderController {

    @Autowired
    RestController restController;

    @GetMapping("/")
    public String homePage(Model model) {
        log.info("Rendering the home page");

        model.addAttribute("categories", restController.getAllCategories());

        return "home";
    }

    @GetMapping("/create-post")
    public String createPostPage() {
        log.info("Rendering the create post page");

        return "create-post";
    }

    @GetMapping("/submit-post")
    public String submitRedirect(@RequestParam String title,
                                 @RequestParam String story,
                                 @RequestParam String categories,
                                 Model model) {
        //TODO: refactor the following logic and move to other/helper class
        final Blog blog = new Blog();
        blog.setTitle(title);
        blog.setStory(story);
        blog.setCategories(Arrays.stream(categories.split(Constants.COMMA))
                .map(name -> new Category().withName(name))
                .collect(Collectors.toList()));
        try {
            model.addAttribute("successMessage", restController.createBlogPost(blog));
        } catch (ResponseStatusException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "filtered-blogs";
    }

    @GetMapping("/filtered-blogs/{titlePrefix}")
    public String searchByTitlePrefix(@PathVariable String titlePrefix, Model model) {
        log.info("Rendering the results page");

        try {
            model.addAttribute("blogs", restController.findBlogByTitlePrefix(titlePrefix));
        } catch (ResponseStatusException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "filtered-blogs";
    }

    @GetMapping("/filtered-blogs")
    public String searchByCategory(@RequestParam String category, Model model) {
        log.info("Rendering the results page");

        try {
            model.addAttribute("blogs", restController.findBlogsByCategory(category));
        } catch (ResponseStatusException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "filtered-blogs";
    }
}

