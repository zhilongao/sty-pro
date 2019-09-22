package com.gupao.spring.cloud.feign.client.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * 自定义负载均衡实现
 * @Author long
 * @Date 2019/8/1 18:03
 */
public class FirstServerForeverRule extends AbstractLoadBalancerRule {

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        ILoadBalancer loadBalancer = getLoadBalancer();
        List<Server> allServers = loadBalancer.getAllServers();
        return allServers.get(0);
    }
}
