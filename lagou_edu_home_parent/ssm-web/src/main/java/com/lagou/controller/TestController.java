package com.lagou.controller;

import com.lagou.domain.Test;
import com.lagou.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController     // @Controller + @Responsbody(转换成json的形式直接返回数据)
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    /*
        查询所有
     */
    @RequestMapping("/findAllTest")
    public List<Test> findAllTest(){
        List<Test> testList = testService.findAllTest();
        return testList;
    }

}
