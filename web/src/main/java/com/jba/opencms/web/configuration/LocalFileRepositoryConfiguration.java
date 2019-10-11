package com.jba.opencms.web.configuration;

import com.jba.opencms.file.FileService;
import com.jba.opencms.web.repository.DatabaseBackedFileRepository;
import com.jba.opencms.web.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
@Slf4j
public class LocalFileRepositoryConfiguration {
    @Bean
    public FileRepository fileRepository(FileService fileService) {
        return new DatabaseBackedFileRepository(fileService);
    }
}
