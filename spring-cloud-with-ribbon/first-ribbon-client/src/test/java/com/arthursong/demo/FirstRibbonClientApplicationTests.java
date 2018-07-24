package com.arthursong.demo;

import com.arthursong.demo.ping.MyPing;
import com.arthursong.demo.rule.MyRule;
import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.client.http.RestClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstRibbonClientApplicationTests {

	@Test
	public void testRestClient() throws Exception{
		// 设置请求的服务器
		ConfigurationManager.getConfigInstance().setProperty(
				"my-client.ribbon.listOfServers",
				"localhost:8080,localhost:8081");
		// 获取REST请求客户端
		RestClient client = (RestClient) ClientFactory
				.getNamedClient("my-client");
		// 创建请求实例
		HttpRequest request = HttpRequest.newBuilder().uri("/hello").build();
		// 发送10次请求到服务器中
		for (int i = 0; i < 10; i++) {
			HttpResponse response = client.executeWithLoadBalancer(request);
			String result = response.getEntity(String.class);
			log.info(result);
		}
	}

	@Test
	public void testChooseServer() throws Exception{
		// 创建负载均衡器
		BaseLoadBalancer lb = new BaseLoadBalancer();
		// 添加服务器
		List<Server> servers = new ArrayList<Server>();
		servers.add(new Server("localhost", 8080));
		servers.add(new Server("localhost", 8081));
		lb.addServers(servers);
		// 进行10次服务器选择
		for(int i = 0; i < 10; i++) {
			Server s = lb.chooseServer(null);
			log.info(s.toString());
		}
	}

	@Test
	public void testMyRule() throws Exception{
		// 创建负载均衡器
		BaseLoadBalancer lb = new BaseLoadBalancer();
		// 设置自定义的负载规则
		lb.setRule(new MyRule(lb));
		// 添加服务器
		List<Server> servers = new ArrayList<Server>();
		servers.add(new Server("localhost", 8080));
		servers.add(new Server("localhost", 8081));
		lb.addServers(servers);
		// 进行10次服务器选择
		for(int i = 0; i < 10; i++) {
			Server s = lb.chooseServer(null);
			log.info(s.toString());
		}
	}

	@Test
	public void testMyRule2() throws Exception{
		//设置请求的服务器
		ConfigurationManager
				.getConfigInstance() //
				.setProperty("my-client.ribbon.listOfServers","localhost:8080,localhost:8081");
		//配置规则处理类
		ConfigurationManager
				.getConfigInstance() .setProperty(
				"my-client.ribbon.NFLoadBalancerRuleClassName",
				MyRule.class.getName());
		//获取REST请求客户端
		// 获取REST请求客户端
		RestClient client = (RestClient) ClientFactory
				.getNamedClient("my-client");
		// 创建请求实例
		HttpRequest request = HttpRequest.newBuilder().uri("/hello").build();
		// 发送10次请求到服务器中
		for (int i = 0; i < 10; i++) {
			HttpResponse response = client.executeWithLoadBalancer(request);
			String result = response.getEntity(String.class);
			log.info(result);
		}
	}

	@Test
	public void testPingUrl() throws Exception{
		// 创建负载均衡器
		BaseLoadBalancer lb = new BaseLoadBalancer();
		// 添加服务器
		List<Server> servers = new ArrayList<Server>();
		// 8080端口连接正常
		servers.add(new Server("localhost", 8080));
		// 一个不存在的端口
		servers.add(new Server("localhost", 8888));
		lb.addServers(servers);
		// 设置IPing实现类
		lb.setPing(new PingUrl());
		// 设置Ping时间间隔为2秒
		lb.setPingInterval(2);
		Thread.sleep(6000);
		for(Server s : lb.getAllServers()) {
			log.info(s.getHostPort() + " 状态：" + s.isAlive());
		}
	}

	@Test
	public void testPingUrl2() throws Exception{
		// 设置请求的服务器
		ConfigurationManager.getConfigInstance().setProperty(
				"my-client.ribbon.listOfServers",
				"localhost:8080,localhost:8888");
		// 配置Ping处理类
		ConfigurationManager.getConfigInstance().setProperty(
				"my-client.ribbon.NFLoadBalancerPingClassName",
				PingUrl.class.getName());
		// 配置Ping时间间隔
		ConfigurationManager.getConfigInstance().setProperty(
				"my-client.ribbon.NFLoadBalancerPingInterval",
				2);
		// 获取REST请求客户端
		RestClient client = (RestClient) ClientFactory
				.getNamedClient("my-client");
		Thread.sleep(6000);
		// 获取全部服务器
		List<Server> servers = client.getLoadBalancer().getAllServers();
		System.out.println(servers.size());
		// 输出状态
		for(Server s : servers) {
			log.info(s.getHostPort() + " 状态：" + s.isAlive());
		}
	}

	@Test
	public void testMyPing() throws Exception{
		// 设置请求的服务器
		ConfigurationManager.getConfigInstance().setProperty(
				"my-client.ribbon.listOfServers",
				"localhost:8080,localhost:8888");
		// 配置Ping处理类
		ConfigurationManager.getConfigInstance().setProperty(
				"my-client.ribbon.NFLoadBalancerPingClassName",
				MyPing.class.getName());
		// 配置Ping时间间隔
		ConfigurationManager.getConfigInstance().setProperty(
				"my-client.ribbon.NFLoadBalancerPingInterval",
				2);
		// 获取REST请求客户端
		RestClient client = (RestClient) ClientFactory
				.getNamedClient("my-client");
		Thread.sleep(6000);
		// 获取全部服务器
		List<Server> servers = client.getLoadBalancer().getAllServers();
		log.info("size:"+servers.size());
		// 输出状态
		for(Server s : servers) {
			log.info(s.getHostPort() + " 状态：" + s.isAlive());
		}
	}

}
