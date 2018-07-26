package com.arthursong.demo.collapser;

import com.arthursong.demo.command.CollapserCommand;
import com.arthursong.demo.entity.Person;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/26.
 */
public class MyHystrixCollapser extends HystrixCollapser<Map<String, Person>, Person, String> {
    private String personName;

    public MyHystrixCollapser(String personName) {
        this.personName = personName;
    }

    @Override
    public String getRequestArgument() {
        return personName;
    }

    @Override
    protected HystrixCommand<Map<String, Person>> createCommand(
            Collection<CollapsedRequest<Person, String>> requests) {
        return new CollapserCommand(requests);
    }

    @Override
    protected void mapResponseToRequests(Map<String, Person> batchResponse,
                                         Collection<CollapsedRequest<Person, String>> requests) {
        // 让结果与请求进行关联
        for (CollapsedRequest<Person, String> request : requests) {
            // 获取单个响应返回的结果
            Person singleResult = batchResponse.get(request.getArgument());
            // 关联到请求中
            request.setResponse(singleResult);
        }
    }
}
