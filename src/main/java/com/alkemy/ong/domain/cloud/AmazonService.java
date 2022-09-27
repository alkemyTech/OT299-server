package com.alkemy.ong.domain.cloud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Service
public class AmazonService {

    AmazonGateway amazonGateway;

    public String uploadFile(MultipartFile file) {
        return amazonGateway.uploadFile(file);
    }

}
