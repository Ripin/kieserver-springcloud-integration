package org.kie.server.springboot.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 配置属性
 * @author RipinYan
 * @Date
 *
 */
@Configuration
public class KieServerConfig {

	@Value("${deploy.releaseIds}")
	private String releaseIds;

	public String getReleaseIds() {
		return releaseIds;
	}

	public void setReleaseIds(String releaseIds) {
		this.releaseIds = releaseIds;
	}
}
