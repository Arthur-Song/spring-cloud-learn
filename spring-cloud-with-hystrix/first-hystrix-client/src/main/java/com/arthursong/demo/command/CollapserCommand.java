package com.arthursong.demo.command;

import com.arthursong.demo.entity.Person;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Created by Administrator on 2018/7/26.
 */
@Slf4j
public class CollapserCommand extends HystrixCommand<Map<String, Person>> {
    // 请求集合，第一个类型是单个请求返回的数据类型，第二是请求参数的类型
    Collection<HystrixCollapser.CollapsedRequest<Person, String>> requests;

    public CollapserCommand(
            Collection<HystrixCollapser.CollapsedRequest<Person, String>> requests) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory
                .asKey("ExampleGroup")));
        this.requests = requests;
    }

    @Override
    protected Map<String, Person> run() throws Exception {
        log.info("收集参数后执行命令，参数数量：" + requests.size());
        // 处理参数
        List<String> personNames = new ArrayList<String>();
        for(HystrixCollapser.CollapsedRequest<Person, String> request : requests) {
            personNames.add(request.getArgument());
        }
        // 调用服务（此处模拟调用），根据名称获取Person的Map
        Map<String, Person> result = callService(personNames);
        return result;
    }

    // 模拟服务返回
    private Map<String, Person> callService(List<String> personNames) {
        Map<String, Person> result = new HashMap<String, Person>();
        for(String personName : personNames) {
            Person person = new Person();
            person.setId(UUID.randomUUID().toString());
            person.setName(personName);
            person.setAge(new Random().nextInt(30));
            result.put(personName, person);
        }
        return result;
    }
}
