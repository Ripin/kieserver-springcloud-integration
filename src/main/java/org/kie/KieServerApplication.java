package org.kie;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by RipinYan on 2017/10/11.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class KieServerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(KieServerApplication.class);
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(KieServerApplication.class).web(true).run(args);
		/*if(1==1){
			throw new RuntimeException("方法水电费");
		}*/
	}


}