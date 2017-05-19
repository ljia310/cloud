package com.cloud.services;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liujia on 2017/5/19.
 */
@FeignClient(name ="config-server")
public interface Service_info {
    @RequestMapping(value = "/info")
    String info();
}
