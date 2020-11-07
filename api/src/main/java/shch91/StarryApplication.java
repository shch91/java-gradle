package shch91;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import java.io.IOException;

@EnableAsync
@EnableCaching
@EnableScheduling
@SpringBootApplication
@ImportResource(locations = {"classpath:spring/spring.xml"})
@Import(value = {ServiceApplication.class})
public class StarryApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarryApplication.class, args);
    }

    @Bean
    @Qualifier(value = "zk")
    public ZooKeeper createZookeeper(@Value("${zookeeper.server}") String servers) throws IOException {
        return new ZooKeeper(servers, 5000, new Watcher() {
            // 监控所有被触发的事件
            @Override
            public void process(WatchedEvent event) {
            }
        });
    }

}
