package org.kie;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by RipinYan on 2017/10/11.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class KieServerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(KieServerApplication.class);
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(KieServerApplication.class).web(true).run(args);
	}


}
