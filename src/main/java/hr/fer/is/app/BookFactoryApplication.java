package hr.fer.is.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("hr.fer.is.app.domain")
@EnableJpaRepositories
@EnableJpaAuditing
public class BookFactoryApplication {
    private static final Logger log = LoggerFactory.getLogger(BookFactoryApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BookFactoryApplication.class, args);
    }
}
