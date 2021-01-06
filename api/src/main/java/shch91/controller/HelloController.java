package shch91.controller;

import com.alibaba.dubbo.rpc.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import shch91.inter.DemoService;
import shch91.inter.enums.Type;
import shch91.inter.request.User;

import java.util.*;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    @Qualifier("demoGenericService")
    private GenericService demoService;

    @RequestMapping("/doError")
    public Object error() {
        return 1 / 0;
    }

    @RequestMapping("/kk")
    public void test() {

    }


    @RequestMapping("/dubbo")
    @ResponseBody
    public User testfdsa() {

        Object str = demoService.$invoke("sayHello", new String[]{"shch91.inter.enums"}, new Object[]{Type.ONE});

        return (User) str;

    }

    @RequestMapping("/set")
    @ResponseBody
    public Set<Integer> fds() {

        //Set<Integer> ret = demoService.getSetInteger();

        return null;

    }


}