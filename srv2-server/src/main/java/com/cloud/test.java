package com.cloud;

import com.cloud.services.Service_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liujia on 2017/5/25.
 */
@RestController
public class test {
    @Autowired
    Service_info i;
    @RequestMapping("/call_test")
    public String test123(@RequestParam String name){
        return  i.info(name);
    }
}
