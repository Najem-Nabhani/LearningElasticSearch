package com.elasticsearch.blogoogle.repository;

import com.elasticsearch.blogoogle.data.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BlogRepository extends ElasticsearchRepository<Blog, String> {

    @Query("{\"match_phrase_prefix\":{\"title\":{\"query\":\"?0\"}}}")
    Page<Blog> findByTitlePrefix(String titlePrefix, Pageable pageable);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"categories.name\": \"?0\"}}]}}")
    Page<Blog> findByCategoryName(String category, Pageable pageable);
}
