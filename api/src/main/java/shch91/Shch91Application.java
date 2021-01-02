package shch91;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
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
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
@ImportResource(locations = {"classpath:spring/spring.xml"})
@Import(value = {ServiceApplication.class})
public class Shch91Application {

    public static void main(String[] args) {
        SpringApplication.run(Shch91Application.class, args);
    }


    @Bean(value = "zk")
    public ZooKeeper createZookeeper() throws IOException {
        return new ZooKeeper("localhost:2181,localhost:2182,localhost:2183", 5000, new Watcher() {
            // 监控所有被触发的事件
            @Override
            public void process(WatchedEvent event) {

            }
        });
    }

}
