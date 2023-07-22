package com.elasticsearch.blogoogle.data;

import com.elasticsearch.blogoogle.constants.Constants;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.elasticsearch.core.join.JoinField;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Document(indexName = "blogs")
public class Blog {
    @Id
    private String id;

    @Field(type = FieldType.Search_As_You_Type)
    private String title;

    @Field(type = FieldType.Text)
    private String story;

    @Field(name = "categories", type = FieldType.Nested, includeInParent = true)
    private List<Category> categories;

    @Override
    public String toString() {
        return String.format("Blog{%s}", id);
    }
}