package org.kie.server.springboot.listener;

import java.util.List;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.ReleaseId;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.services.impl.KieServerImpl;
import org.kie.server.services.impl.KieServerLocator;
import org.kie.server.springboot.conf.KieServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 自动部署监听器
 *
 * @author Ripin Yan
 * @ClassName: AutoDeployListener
 * @Description: 自动部署监听器
 * @date 2018/3/12 上午9:21
 */
public class AutoDeployListener implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(AutoDeployListener.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

		KieServerImpl kieServer = KieServerLocator.getInstance();
		List<String> releaseIds = contextRefreshedEvent.getApplicationContext().getBean(KieServerConfig.class).getReleaseIds();
		logger.info("KieServer auto deploy start!");

		if (releaseIds != null && releaseIds.size() > 0) {
			for (int i = 0; i < releaseIds.size(); i++) {

				String releaseIdString = releaseIds.get(i);
				String[] gav = releaseIdString.split(":");
				String containerId = gav[1] + "_" + gav[2];
				ReleaseId releaseId = new ReleaseId(gav[0], gav[1], gav[2]);
				KieContainerResource container = new KieContainerResource(containerId, releaseId);
				container.setContainerAlias(gav[1]);
				ServiceResponse<KieContainerResource> serviceResponse = kieServer.createContainer(containerId, container);
				logger.info("***BPM-LOG***" + serviceResponse.getMsg());
			}
		}

		logger.info("KieServer auto deploy end!");
	}
}
