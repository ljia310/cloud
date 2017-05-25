package com.cloud.controllers;

import com.cloud.services.Service_info;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by liujia on 2017/5/19.
 */
@RestController
public class info {
    @Autowired
    Service_info i;
    @ApiOperation(value="获取系统信息", notes="获取系统信息")// 使用该注解描述接口方法信息
    @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String",paramType = "query")
    @RequestMapping(value = "/sysinfo")
    public  String sysinfo(Principal user,@RequestParam String name){
        return  name+user.getName()+i.info();
    }
}
