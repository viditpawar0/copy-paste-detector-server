package org.lsrv.copypastedetector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CopyPasteDetectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CopyPasteDetectorApplication.class, args);
    }

}
