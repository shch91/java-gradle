package shch91;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableAsync
@EnableCaching
@EnableScheduling
@SpringBootApplication
@ImportResource(locations={"classpath:spring/spring.xml"})
@Import(value = {ServiceApplication.class})
public class StarryApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarryApplication.class, args);
    }

    @Bean
    @Qualifier(value = "restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }



}
