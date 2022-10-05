package com.alkemy.ong.domain.cloud;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
public interface AmazonGateway {

    String uploadMultipartFile(MultipartFile file);
    String uploadFile(File file);
    String deleteFile(String fileUrl);
    File getImageFromBase64(String base64String);
}
