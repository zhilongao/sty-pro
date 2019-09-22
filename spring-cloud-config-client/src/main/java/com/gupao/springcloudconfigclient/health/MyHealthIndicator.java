package com.gupao.springcloudconfigclient.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

/**
 * @Author long
 * @Date 2019/7/27 17:05
 */
public class MyHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.down().withDetail("MyHealthIndicator","Down");
    }
}
