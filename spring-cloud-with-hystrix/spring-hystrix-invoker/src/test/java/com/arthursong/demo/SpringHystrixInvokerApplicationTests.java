package com.arthursong.demo;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringHystrixInvokerApplicationTests {

	@Test
	public void test() throws Exception{
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		Logger logger = loggerContext.getLogger("root");
		logger.setLevel(Level.toLevel("INFO"));
		// 创建默认的HttpClient
		final CloseableHttpClient httpclient = HttpClients.createDefault();
		// 调用多次服务并输出结果
		for(int i = 0; i < 6; i++) {
			// 建立线程访问接口
			Thread t = new Thread() {
				public void run() {
					try {
						String url = "http://localhost:9000/feign/hello";
						// 调用 GET 方法请求服务
						HttpGet httpget = new HttpGet(url);
						// 获取响应
						HttpResponse response = httpclient.execute(httpget);
						// 根据 响应解析出字符串
						log.info(EntityUtils.toString(response.getEntity()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			t.start();
		}
		// 等待完成
		Thread.sleep(15000);
	}

}
