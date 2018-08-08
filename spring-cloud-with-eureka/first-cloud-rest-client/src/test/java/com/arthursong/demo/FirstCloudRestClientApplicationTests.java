package com.arthursong.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstCloudRestClientApplicationTests {

	@Test
	public void test() throws Exception{
		// 创建默认的HttpClient
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 调用6次服务并输出结果
		for(int i = 0; i < 6; i++) {
			// 调用 GET 方法请求服务
			HttpGet httpget = new HttpGet("http://localhost:9000/router");
			// 获取响应
			HttpResponse response = httpclient.execute(httpget);
			// 根据 响应解析出字符串
			log.info(EntityUtils.toString(response.getEntity()));
		}
	}

}
