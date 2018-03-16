package org.kie.server.springboot.listener;

import org.apache.commons.lang3.StringUtils;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.ReleaseId;
import org.kie.server.services.impl.KieServerImpl;
import org.kie.server.services.impl.KieServerLocator;
import org.kie.server.springboot.conf.KieServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Created by RipinYan on 2018/3/12.
 */

public class AutoDeployListener implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(AutoDeployListener.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

		KieServerImpl kieServer = KieServerLocator.getInstance();
		String releaseIds = contextRefreshedEvent.getApplicationContext().getBean(KieServerConfig.class).getReleaseIds();
		String[] releaseIdArray = null;
		if (StringUtils.isNotBlank(releaseIds)) {
			releaseIdArray = releaseIds.split(",");
			if (releaseIdArray != null && releaseIdArray.length > 0) {
				for (int i = 0; i < releaseIdArray.length; i++) {

					String releaseIdString = releaseIdArray[i];
					logger.info("About to deploy : {}", releaseIdString);
					String[] gav = releaseIdString.split(":");
					String containerId = gav[1] + "_" + gav[2];
					ReleaseId releaseId = new ReleaseId(gav[0], gav[1], gav[2]);
					KieContainerResource container = new KieContainerResource(containerId, releaseId);
					container.setContainerAlias(gav[1]);
					kieServer.createContainer(containerId, container);
					logger.info("{} successfully deployed", releaseIdString);
				}
			}
		}
		logger.info("Auto deploy over!");
	}
}
