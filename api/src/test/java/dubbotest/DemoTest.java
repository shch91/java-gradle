package dubbotest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shch91.Shch91Application;
import shch91.inter.DemoService;
import shch91.inter.enums.Type;
import shch91.inter.request.User;
import shch91.repo.daoentity.Salary;
import shch91.repo.mapper.employees.SalaryMapper;
import shch91.repo.mapper.sakila.ActorMapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Shch91Application.class)
public class DemoTest {
    @Resource
    private ActorMapper actorMapper;

    @Resource
    private SalaryMapper salaryMapper;

    @Autowired
    private DemoService demoService;

    @Test
    public void dtuy(){
       /* Actor ae= actorMapper.select(32);
        String str= JSONUtils.toJSONString(ae);
        log.info(str);*/

        List<Salary> lists=salaryMapper.getByEmpNo();

    }

    @Test
    public void fdsa() {
        User str=demoService.sayHello(Type.ONE);


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