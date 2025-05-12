package com.hrm.app.config;

import jakarta.annotation.PostConstruct;
import java.time.Duration;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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

    private String elasticsearchUrls1 = "localhost";

    //    @Value("${spring.elasticsearch.api-key}")
    //    private String apiKey;

    @Value("${spring.elasticsearch.username}")
    private String username;

    @Value("${spring.elasticsearch.password}")
    private String password;

    //    @Override
    //    public ClientConfiguration clientConfiguration() {
    //        return ClientConfiguration.builder()
    //            .connectedTo(elasticsearchUrls)
    //            //.usingSsl()
    //            .withHeaders(() -> {
    //                HttpHeaders headers = new HttpHeaders();
    //                headers.add("Authorization", "ApiKey " + apiKey);
    //                return headers;
    //            })
    //            .withConnectTimeout(Duration.ofSeconds(30))
    //            .withSocketTimeout(Duration.ofSeconds(30))
    //            .build();
    //    }
    @Override
    public ClientConfiguration clientConfiguration() {
        try {
            SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial(null, (chain, authType) -> true).build();
            return ClientConfiguration.builder()
                .connectedTo("localhost:9200") // Thay bằng "https://localhost:9200" nếu cần
                .usingSsl(sslContext) // Kích hoạt SSL
                .withClientConfigurer((HttpAsyncClientBuilder clientBuilder) -> {
                    clientBuilder.setSSLContext(sslContext);
                    clientBuilder.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);
                    return clientBuilder;
                })
                .withBasicAuth("elastic", "123456") // Dùng thông tin xác thực từ Python
                .withConnectTimeout(Duration.ofSeconds(30))
                .withSocketTimeout(Duration.ofSeconds(30))
                .build();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi cấu hình client Elasticsearch", e);
        }
    }
}
