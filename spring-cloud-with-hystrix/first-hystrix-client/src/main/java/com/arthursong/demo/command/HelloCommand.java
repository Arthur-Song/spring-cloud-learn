package com.arthursong.demo.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by Administrator on 2018/7/26.
 */
@Slf4j
public class HelloCommand extends HystrixCommand<String> {

    private String url;

    private CloseableHttpClient httpclient;

    public HelloCommand(String url) {
        // 调用父类的构造器，设置命令组的key，默认用来作为线程池的key
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        // 创建HttpClient客户端
        this.httpclient = HttpClients.createDefault();
        this.url = url;
    }

    @Override
    protected String run() throws Exception {
        try {
            // 调用 GET 方法请求服务
            HttpGet httpget = new HttpGet(url);
            // 得到服务响应
            HttpResponse response = httpclient.execute(httpget);
            // 解析并返回命令执行结果
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected String getFallback() {
        log.warn("执行 HelloCommand 的回退方法");
        return "error";
    }
}
