package org.kie.server.springboot.conf;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 配置属性
 * @author RipinYan
 * @Date
 *
 */
@Configuration
@ConfigurationProperties(prefix = "deploy")
public class KieServerConfig {

	private List<String> releaseIds = null;

	public List<String> getReleaseIds() {
		return releaseIds;
	}

	public void setReleaseIds(List<String> releaseIds) {
		this.releaseIds = releaseIds;
	}
}
