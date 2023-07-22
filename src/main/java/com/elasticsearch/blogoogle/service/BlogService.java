package com.elasticsearch.blogoogle.service;

import com.elasticsearch.blogoogle.constants.Constants;
import com.elasticsearch.blogoogle.data.Blog;
import com.elasticsearch.blogoogle.data.Category;
import com.elasticsearch.blogoogle.repository.BlogRepository;
import com.elasticsearch.blogoogle.repository.CategoryRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Log4j2
@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Optional<Blog> findBlogByTitle(String title) {
        final String blogId = createBlogIdFromTitle(title);
        return blogRepository.findById(blogId);
    }

    public Page<Blog> findBlogsByTitlePrefix(String titlePrefix) {
        return blogRepository.findByTitlePrefix(titlePrefix, PageRequest.ofSize(Constants.NUMBER_OF_ELEMENTS_PER_PAGE));
    }

    public  Page<Blog> findBlogByCategoryName(String categoryName) {
        return blogRepository.findByCategoryName(categoryName, PageRequest.ofSize(Constants.NUMBER_OF_ELEMENTS_PER_PAGE));
    }

    public boolean checkIfBlogExists(Blog blog) {
        final String blogId = createBlogIdFromTitle(blog.getTitle());
        return blogRepository.existsById(blogId);
    }

    public String createBlog(Blog blog, List<Category> categories) {
        blog.setId(createBlogIdFromTitle(blog.getTitle()));
        blog.setCategories(categories);

        final Blog createdBlog = blogRepository.save(blog);

        log.info("Successfully created blog: {}", createdBlog);

        return createdBlog.getId();
    }

    /**
     * Creates a lowercase string id with hyphens instead of spaces
     * Given: This Is A Title
     * Returns: this-is-a-title
     *
     * @param title the original blog title
     * @return string id representing of the title
     */
    private static String createBlogIdFromTitle(final String title) {
        List<String> separatedStrings = Arrays.stream(title.split(Constants.SPACE)).toList();
        String combinedTitle = StringUtils.join(separatedStrings, Constants.HYPHEN);

        return combinedTitle.toLowerCase(Locale.US);
    }
}
