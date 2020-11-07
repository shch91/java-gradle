package shch91.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import shch91.inter.DemoService;
import shch91.inter.enums.Type;
import shch91.inter.request.User;
import shch91.repo.daoentity.Actor;
import shch91.repo.daoentity.Salary;
import shch91.repo.mapper.employees.TmpMapper;
import shch91.repo.mapper.sakila.ActorMapper;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Slf4j
@RestController
public class HelloController {

    @Resource
    private ActorMapper actorMapper;

    @Resource
    private TmpMapper tmpMapper;

    @Autowired
    private DemoService demoService;

    @RequestMapping("/hello/id")
    @Transactional(rollbackFor = {Exception.class})
    public int add() {
        Actor actor = actorMapper.select(23);
        actor.setLastName("shch91/app");
        actorMapper.insertOrUpdate(actor);
        return 0;
    }


    @RequestMapping("/first")
    public String first() {
        Vector ver = new Vector<String>();
        Actor actor = actorMapper.select(32);
        return "first controller";
    }

    @RequestMapping("/doError")
    public Object error() {
        return 1 / 0;
    }

    @RequestMapping("/kk")
    public void test() {

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
    public User testfdsa() {

        User str = demoService.sayHello(Type.ONE);

        return str;

    }

    @RequestMapping("/set")
    @ResponseBody
    public Set<Integer> fds() {

        Set<Integer> ret = demoService.getSetInteger();

        return ret;

    }

    @PostConstruct
    public void fdsa() {
        int no = 10001;
        int t=0;
        while (t < 10) {
            try {
                Actor list = actorMapper.select(1);
            } catch (Exception e) {
                log.error("insert  salary error ", e);
            }finally{
                t++;
            }

        }
    }

}