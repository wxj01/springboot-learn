package com.wxj.springboot.specificfeature.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.wxj.springboot.specificfeature.FeatureContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 自定义轮询策略
 */
//@Component
//@Primary
public class CustomerRoundRobinRule extends AbstractLoadBalancerRule {

    private AtomicInteger nextServerCyclicCounter;
    private static final boolean AVAILABLE_ONLY_SERVERS = true;
    private static final boolean ALL_SERVERS = false;

    private static Logger log = LoggerFactory.getLogger(com.netflix.loadbalancer.RoundRobinRule.class);

    //初始化nextServerCyclicCounter
    public CustomerRoundRobinRule() {
        nextServerCyclicCounter = new AtomicInteger(0);
    }

    public CustomerRoundRobinRule(ILoadBalancer lb) {
        this();
        setLoadBalancer(lb);
    }

    // 选择服务进行访问
    public Server choose(ILoadBalancer lb, Object key) {
        System.out.println("进入自定义的轮询 CustomerRoundRobinRule");
        if (lb == null) {
            log.warn("no load balancer");
            return null;
        }

        Server server = null;
        int count = 0;
        while (server == null && count++ < 10) {
            List<Server> reachableServers = lb.getReachableServers();
            List<Server> allServers = lb.getAllServers();
            int upCount = reachableServers.size();
            int serverCount = allServers.size();

            //这里进行特殊处理 特定分支名参数 选择特定分支实例sever进行请求
            // 这是通过特定参数分支获取对应的服务
            String specificBranches = FeatureContext.get();
            Optional<Server> specificServer = allServers.stream().filter(server1 -> server1.getMetaInfo().getInstanceId().equals(specificBranches)).findAny();
            if (specificServer.isPresent()) {
                Server specificFeatureServer = specificServer.get();
                if (specificFeatureServer != null) {
                    return specificFeatureServer;
                }
            }


            if ((upCount == 0) || (serverCount == 0)) {
                log.warn("No up servers available from load balancer: " + lb);
                return null;
            }

            int nextServerIndex = incrementAndGetModulo(serverCount);
            server = allServers.get(nextServerIndex);

            if (server == null) {
                /* Transient. */
                Thread.yield();
                continue;
            }

            if (server.isAlive() && (server.isReadyToServe())) {
                return (server);
            }

            // Next.
            server = null;
        }

        if (count >= 10) {
            log.warn("No available alive servers after 10 tries from load balancer: "
                    + lb);
        }
        return server;
    }

    /**
     * Inspired by the implementation of {@link AtomicInteger#incrementAndGet()}.
     *
     * @param modulo The modulo to bound the value of the counter.
     * @return The next value.
     */
    private int incrementAndGetModulo(int modulo) {
        for (; ; ) {
            int current = nextServerCyclicCounter.get();
            int next = (current + 1) % modulo;
            if (nextServerCyclicCounter.compareAndSet(current, next))
                return next;
        }
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
