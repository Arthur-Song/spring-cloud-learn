package com.arthursong.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/8/10.
 */
@RestController
public class HealthController {
    // 标识当前数据库是否可以访问
    public static Boolean canVisitDb = false;

    @GetMapping("/db/{canVisitDb}")
    public String setConnectState(@PathVariable("canVisitDb") Boolean canVisitDb) {
        this.canVisitDb = canVisitDb;
        return "当前数据库是否正常: " + this.canVisitDb;
    }
}
