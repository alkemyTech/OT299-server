package com.alkemy.ong.domain.cloud;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface AmazonGateway {

    String uploadFile(MultipartFile file);

}
