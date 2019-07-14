package com.jba.opencms.web.configuration;

import com.jba.opencms.image.ImageService;
import com.jba.opencms.web.repository.DatabaseImageRepository;
import com.jba.opencms.web.repository.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ImageRepositoryConfiguration {
    @Bean
    public ImageRepository imageRepository(ImageService imageService) {
        log.info("Initializing " + ImageRepository.class.getSimpleName() + " with: " + imageService.toString());
        return new DatabaseImageRepository(imageService);
    }
}
