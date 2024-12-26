package com.hrm.app.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MinioService {

    private MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;

    public void FileStorageService(
        @Value("${minio.url}") String url,
        @Value("${minio.accessKey}") String accessKey,
        @Value("${minio.secretKey}") String secretKey
    ) {
        this.minioClient = MinioClient.builder().endpoint(url).credentials(accessKey, secretKey).build();
    }

    public String uploadFile(String fileName, InputStream fileStream, String contentType) throws Exception {
        minioClient.putObject(
            PutObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .stream(fileStream, fileStream.available(), -1)
                .contentType(contentType)
                .build()
        );
        return "File uploaded successfully.";
    }
}
