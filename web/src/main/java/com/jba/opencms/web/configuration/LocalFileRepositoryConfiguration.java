package com.jba.opencms.web.configuration;

import com.jba.opencms.web.repository.FileRepository;
import com.jba.opencms.web.repository.MockFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
@Slf4j
public class LocalFileRepositoryConfiguration {
    @Bean
    public FileRepository fileRepository() {
        return new MockFileRepository();
    }
}
