package shch91.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private DemoService demoService;

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

        User str = demoService.sayHello(Type.ONE);

        return str;

    }

    @RequestMapping("/set")
    @ResponseBody
    public Set<Integer> fds() {

        Set<Integer> ret = demoService.getSetInteger();

        return ret;

    }


}