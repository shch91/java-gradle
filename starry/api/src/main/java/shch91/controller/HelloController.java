package shch91.controller;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import shch91.inter.DemoService;
import shch91.repo.daoentity.Actor;
import shch91.repo.daoentity.Salary;
import shch91.repo.mapper.employees.SalaryMapper;
import shch91.repo.mapper.employees.TmpMapper;
import shch91.repo.mapper.sakila.ActorMapper;


import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
@RestController
public class HelloController {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private static final LocalDate beginDate = LocalDate.of(2018, 1, 1);

    @Resource
    private ActorMapper actorMapper;

    @Resource
    private SalaryMapper salaryMapper;

    @Resource
    private TmpMapper tmpMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    DemoService demoService;


    @RequestMapping("/hello/id")
    @Transactional(rollbackFor = {Exception.class})
    public int add() {
        Actor actor = actorMapper.select(23);


        log.info(JSON.toJSONString(actor));

        actor.setLastName("shch91/app");
        actorMapper.insertOrUpdate(actor);
        return 0;
        //throw new RuntimeException("insert");

    }




    @RequestMapping("/first")
    public String first() {

        //readResource.getResourceByClassOrClassLoader();
        boolean fda = Strings.isNullOrEmpty("");

        Vector ver = new Vector<String>();
        Actor actor = actorMapper.select(32);
        log.info(JSON.toJSONString(actor));
        redisTemplate.opsForValue().set("userToJson", JSON.toJSONString(actor));


        return "first controller";
    }

    @RequestMapping("/doError")
    public Object error() {
        return 1 / 0;
    }

    @RequestMapping("/kk")
    public void test() {
        List<Salary> res = salaryMapper.getAll();
        tmpMapper.add(res.get(0));
        List<List<Salary>> part = Lists.partition(res, 20000);

        for (List<Salary> item : part) {
            threadPoolTaskExecutor.execute(new CacheTask(item));
        }
        return;
    }

    private class CacheTask implements Runnable {


        public CacheTask(List<Salary> list) {
            salaryList = list;
        }

        List<Salary> salaryList;

        @Override
        public void run() {
            batchAdd(salaryList);
        }
    }

    private void batchAdd(List<Salary> list) {
        tmpMapper.addBatch(list);
    }


    @RequestMapping("/dubbo")
    @ResponseBody
    public String testfdsa() throws ExecutionException, InterruptedException {

           String fd=fdsa();


        return "fsdas";

    }

    @Async
    public String fdsa() throws ExecutionException, InterruptedException {
        CompletableFuture<String> str=demoService.sayHello("dubbo");
        return str.get();
    }
}