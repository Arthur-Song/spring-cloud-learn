package com.arthursong.demo;

import com.arthursong.demo.collapser.MyHystrixCollapser;
import com.arthursong.demo.command.*;
import com.arthursong.demo.entity.Person;
import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandMetrics;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rx.Observable;
import rx.Observer;

import java.util.concurrent.Future;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstHystrixClientApplicationTests {

	@Test
	public void testHealthHello() throws Exception{
		//请求正常的服务
		String url = "http://localhost:8080/healthHello";
		HelloCommand command= new HelloCommand(url);
		String result = command.execute() ;
		log.info("”请求正常的服务，结果："+result);
	}

	@Test
	public void testErrorHello() throws Exception{
		//请求异常的服务
		String url = "http://localhost:8080/errorHello";
		HelloCommand command= new HelloCommand(url);
		String result = command.execute() ;
		log.info("”请求异常的服务，结果："+result);
	}

	@Test
	public void testExecute4() throws Exception{
		//使用execute 方法
		RunCommand command1 = new RunCommand("使用execute 方法执行命令");
		command1.execute();

		//使用queue 方法
		RunCommand command2 = new RunCommand("使用queue 方法执行命令");
		command2.queue();

		//使用observe 方法
		RunCommand command3 = new RunCommand("使用observe 方法执行命令");
		command3.observe();

		//使用toObservable  方法
		RunCommand command4 = new RunCommand("使用toObservable  方法执行命令");
		//调用toObservable 方法后， 命令不会马上执行
		Observable<String> observable = command4.toObservable();
		//进行订阅，此时会执行命令
		observable.subscribe(new Observer<String>() {
			@Override
			public void onCompleted() {
				log.info("命令执行完成");
			}

			@Override
			public void onError(Throwable throwable) {

			}

			@Override
			public void onNext(String s) {
				log.info("命令执行的结果："+s);
			}
		});
	}

	@Test
	public void testTimeoutCommand() throws Exception{
		//设置超时时间为500毫秒
		TimeoutCommand timeoutCommand = new TimeoutCommand(500);
		String result = timeoutCommand.execute();
		log.info("执行结果："+result);
	}

	@Test
	public void testGlobleTimeout() throws Exception{
		//全局设置默认超时时间
		ConfigurationManager //
				.getConfigInstance() //
				.setProperty( //
						"hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds",
						500);
		TimeoutCommand timeoutCommand = new TimeoutCommand();
		String result = timeoutCommand.execute();
		log.info("执行结果："+result);
	}

	@Test
	public void testFallback() throws Exception{
		//断路器被强制打开
		ConfigurationManager //
				.getConfigInstance() //
				.setProperty("hystrix.command.default.circuitBreaker.forceOpen","true");
		FallbackCommand fallbackCommand1 = new FallbackCommand();
		String result1 = fallbackCommand1.execute();
		log.info("结果1："+result1);
		//创建第二个命令，断路器关闭
		ConfigurationManager //
				.getConfigInstance() //
				.setProperty("hystrix.command.default.circuitBreaker.forceOpen","false");
		FallbackCommand fallbackCommand2 = new FallbackCommand();
		String result2 = fallbackCommand2.execute();
		log.info("结果2："+result2);
	}

	@Test
	public void testChain() throws Exception{
		CommandA command = new CommandA();
		command.execute();
	}

	@Test
	public void testOpen() throws Exception{
		//10秒内有10个请求，则符合第一个
		ConfigurationManager.getConfigInstance().setProperty(
				"hystrix.command.default.metrics.rollingStats.timeInMilliseconds",10000);
		ConfigurationManager.getConfigInstance().setProperty(
				"hystrix.command.default.circuitBreaker.requestVolumeThreshold",10);
		ConfigurationManager.getConfigInstance().setProperty(
				"hystrix.command.default.circuitBreaker.errorThresholdPercentage",50);
		for (int i=0;i<15;i++){
			//执行命令全部都会超时
			OpenCommand command = new OpenCommand();
			command.execute();
			//断路器打开后输出信息
			if(command.isCircuitBreakerOpen()){
				log.info("断路器被打开，执行第"+(i+1)+"个命令");
			}
		}
	}

	@Test
	public void testClose() throws Exception{
		// 10秒内有3个请求就满足第一个开启断路器的条件
		ConfigurationManager.getConfigInstance().setProperty(
				"hystrix.command.default.metrics.rollingStats.timeInMilliseconds", 10000);
		ConfigurationManager.getConfigInstance().setProperty(
				"hystrix.command.default.circuitBreaker.requestVolumeThreshold", 3);
		// 请求的失败率，默认值为50%
		ConfigurationManager.getConfigInstance().setProperty(
				"hystrix.command.default.circuitBreaker.errorThresholdPercentage", 50);
		// 设置休眠期，断路器打开后，这段时间不会再执行命令，默认值为5秒，此处设置为3秒
		ConfigurationManager.getConfigInstance().setProperty(
				"hystrix.command.default.circuitBreaker.sleepWindowInMilliseconds", 3000);
		// 该值决定是否执行超时
		boolean isTimeout = true;
		for(int i = 0; i < 10; i++) {
			// 执行的命令全部都会超时
			CloseCommand command = new CloseCommand(isTimeout);
			command.execute();
			// 输出健康状态等信息
			HystrixCommandMetrics.HealthCounts healthCounts = command.getMetrics().getHealthCounts();
			log.info("断路器状态：" + command.isCircuitBreakerOpen() +
					", 请求总数：" + healthCounts.getTotalRequests());
			if(command.isCircuitBreakerOpen()) {
				// 断路器打开，让下一次循环成功执行命令
				isTimeout = false;
				log.info("=====  断路器打开了，等待休眠期结束   =====");
				// 休眠期会在3秒后结束，此处等待4秒，确保休眠期结束
				Thread.sleep(4000);
			}
		}
	}

	@Test
	public void testThreadIso() throws Exception{
		// 配置线程池大小为3
		ConfigurationManager.getConfigInstance().setProperty(
				"hystrix.threadpool.default.coreSize", 3);

		for(int i = 0; i < 6; i++) {
			IsoCommand command = new IsoCommand(i);
			command.queue();
		}
		Thread.sleep(5000);
	}

	@Test
	public void testSemaphoreIso() throws Exception{
		// 配置使用信号量的策略进行隔离
		ConfigurationManager.getConfigInstance().setProperty(
				"hystrix.command.default.execution.isolation.strategy",
				HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE);
		// 设置最大并发数，默认值为10
		ConfigurationManager
				.getConfigInstance()
				.setProperty(
						"hystrix.command.default.execution.isolation.semaphore.maxConcurrentRequests",
						2);
		// 设置执行回退方法的最大并发，默认值为10
		ConfigurationManager
				.getConfigInstance()
				.setProperty(
						"hystrix.command.default.fallback.isolation.semaphore.maxConcurrentRequests",
						20);
		for (int i = 0; i < 6; i++) {
			final int index = i;
			Thread t = new Thread() {
				public void run() {
					IsoCommand command = new IsoCommand(index);
					command.execute();
				}
			};
			t.start();
		}
		Thread.sleep(5000);
	}

	@Test
	public void testCollapse() throws Exception{
		// 收集 1 秒内发生的请求，合并为一个命令执行
		ConfigurationManager.getConfigInstance().setProperty(
				"hystrix.collapser.default.timerDelayInMilliseconds", 1000);
		// 请求上下文
		HystrixRequestContext context = HystrixRequestContext
				.initializeContext();
		// 创建请求合并处理器
		MyHystrixCollapser c1 = new MyHystrixCollapser("Angus");
		MyHystrixCollapser c2 = new MyHystrixCollapser("Crazyit");
		MyHystrixCollapser c3 = new MyHystrixCollapser("Sune");
		MyHystrixCollapser c4 = new MyHystrixCollapser("Paris");
		// 异步执行
		Future<Person> f1 = c1.queue();
		Future<Person> f2 = c2.queue();
		Future<Person> f3 = c3.queue();
		Future<Person> f4 = c4.queue();
		log.info(f1.get().toString());
		log.info(f2.get().toString());
		log.info(f3.get().toString());
		log.info(f4.get().toString());
		context.shutdown();
	}

	@Test
	public void testCache() throws Exception{
		// 初始化请求上下文
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		// 请求正常的服务
		String key = "cache-key";
		CacheCommand c1 = new CacheCommand(key);
		CacheCommand c2 = new CacheCommand(key);
		CacheCommand c3 = new CacheCommand(key);
		// 输出结果
		log.info(c1.execute() + "c1 是否读取缓存: " + c1.isResponseFromCache());
		log.info(c2.execute() + "c2 是否读取缓存: " + c2.isResponseFromCache());
		log.info(c3.execute() + "c3 是否读取缓存: " + c3.isResponseFromCache());
		// 获取缓存实例
		HystrixRequestCache cache = HystrixRequestCache.getInstance(
				HystrixCommandKey.Factory.asKey("MyCommandKey"),
				HystrixConcurrencyStrategyDefault.getInstance());
		// 清空缓存
		cache.clear(key);
		// 重新执行命令
		CacheCommand c4 = new CacheCommand(key);
		log.info(c4.execute() + "c4 是否读取缓存: " + c4.isResponseFromCache());

		context.shutdown();
	}
}
