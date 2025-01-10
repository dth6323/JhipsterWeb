package com.hrm.app.config;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.elasticsearch.support.HttpHeaders;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.hrm.app.repository")
public class ElasticSearchConfig extends ElasticsearchConfiguration {

    @Value("${spring.elasticsearch.uris}")
    private String elasticsearchUrls;

    @Value("${spring.elasticsearch.api-key}")
    private String apiKey;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
            .connectedTo(elasticsearchUrls)
            .usingSsl()
            .withHeaders(() -> {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Authorization", "ApiKey " + apiKey);
                return headers;
            })
            .withConnectTimeout(Duration.ofSeconds(30))
            .withSocketTimeout(Duration.ofSeconds(30))
            .build();
    }
}
