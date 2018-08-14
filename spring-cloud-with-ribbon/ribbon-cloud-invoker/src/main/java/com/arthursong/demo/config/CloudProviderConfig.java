package com.arthursong.demo.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * Created by Administrator on 2018/8/14.
 */
@RibbonClient(name = "ribbon-cloud-provider",configuration = MyConfig.class)
public class CloudProviderConfig {
}
