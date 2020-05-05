package dubbotest;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shch91.StarryApplication;
import shch91.enums.Type;
import shch91.inter.DemoService;
import shch91.repo.daoentity.Salary;
import shch91.repo.mapper.employees.SalaryMapper;
import shch91.repo.mapper.sakila.ActorMapper;
import shch91.request.User;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StarryApplication.class)
public class DemoTest {
    @Resource
    private ActorMapper actorMapper;

    @Resource
    private SalaryMapper salaryMapper;

    @Autowired
    DemoService demoService;

    @Test
    public void dtuy(){
       /* Actor ae= actorMapper.select(32);
        String str= JSONUtils.toJSONString(ae);
        log.info(str);*/

        List<Salary> lists=salaryMapper.getByEmpNo(10006);
        log.info(JSONUtils.toJSONString(lists));
    }

    @Test
    public void fdsa() {
        User str=demoService.sayHello(Type.ONE);
        log.info(JSON.toJSONString(str));

    }

    @Test
    public  void fgfdsds() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<String> f = future.thenCompose( i -> {
            return CompletableFuture.supplyAsync(() -> {
                return (i * 10) + "";
            });
        });

        System.out.println(f.get());

    }
}