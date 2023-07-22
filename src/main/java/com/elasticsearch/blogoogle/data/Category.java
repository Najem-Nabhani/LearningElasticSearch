package com.elasticsearch.blogoogle.data;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "categories")
public class Category {
    @Id
    @With
    private String id;

    @With
    @Field(type = FieldType.Search_As_You_Type)
    private String name;

    @Override
    public String toString() {
        return String.format("Category{%s}", name);
    }
}
