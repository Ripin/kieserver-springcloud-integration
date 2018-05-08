package org.kie.server.springboot.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author RipinYan
 * @ClassName: JmsHealthIndicator
 * @Description: 替代eureka健康检查中JMS连接检查
 * @date 2018/4/19 下午6:29
 */
@Component
public class JmsHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		return Health.up().build();
	}
}