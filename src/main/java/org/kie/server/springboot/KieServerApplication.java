package org.kie.server.springboot;

import org.kie.server.springboot.listener.AutoDeployListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * kie-server启动类
 *
 * @author Ripin Yan
 * @ClassName: KieServerApplication
 * @Description: kie-server启动类
 * @date 2017/10/11 上午9:21
 */
@SpringBootApplication
@EnableDiscoveryClient
public class KieServerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		builder.listeners(new AutoDeployListener());
		return builder.sources(KieServerApplication.class);
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(KieServerApplication.class).web(true).run(args);
	}

}
