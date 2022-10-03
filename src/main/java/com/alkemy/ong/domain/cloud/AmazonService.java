package com.alkemy.ong.domain.cloud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@AllArgsConstructor
@Service
public class AmazonService {

    AmazonGateway amazonGateway;

    public String uploadMultipartFile(MultipartFile file) {
        return amazonGateway.uploadMultipartFile(file);
    }

    public String uploadFile(File file) {
        return amazonGateway.uploadFile(file);
    }

    public File getImageFromBase64(String base64String) {
        return amazonGateway.getImageFromBase64(base64String);
    }

    public String deleteFile(String fileUrl) {
        return amazonGateway.deleteFile(fileUrl);
    }

}
