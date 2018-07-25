package com.arthursong.demo.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/7/23.
 */
@Data
@Component
@ConfigurationProperties(prefix = "author")
public class Author {
    private String name;
    private Integer port;
    private Integer favoriteNumber;
    private String email;
    private String website;
    private String github;
    private String greeting;
}
