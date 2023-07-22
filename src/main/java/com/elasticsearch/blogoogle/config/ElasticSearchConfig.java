package com.elasticsearch.blogoogle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.elasticsearch.blogoogle.repository")
@ComponentScan(basePackages = { "com.elasticsearch.blogoogle" })
public class ElasticSearchConfig extends ElasticsearchConfiguration {

    private static final String HOSTNAME = "localhost";
    private static final String PORT = "9200";

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(String.format("%s:%s", HOSTNAME, PORT))
                .build();

    }
}
