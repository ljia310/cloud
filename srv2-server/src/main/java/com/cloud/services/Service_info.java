package com.cloud.services;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liujia on 2017/5/25.
 */
@FeignClient(name ="srv1-server")
public interface Service_info {
    @RequestMapping(value = "/sysinfo")
    String info(@RequestParam(name = "name") String name);
}
