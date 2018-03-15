package org.kie.server.springboot;

import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.ReleaseId;
import org.kie.server.services.impl.KieServerImpl;
import org.kie.server.services.impl.KieServerLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Created by RipinYan on 2018/3/12.
 */

public class AutoDeployHandler implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(AutoDeployHandler.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

		KieServerImpl kieServer = KieServerLocator.getInstance();

		/*KModuleDeploymentService deploymentService = (KModuleDeploymentService) contextRefreshedEvent.getApplicationContext().getBean
				("KModuleDeploymentService");*/
		String[] strings = new String[]{"com.myteam:notification:1.1"};
		if (strings.length > 0) {
			String arg = strings[0];
			logger.info("About to deploy : {}", arg);

			String[] gav = arg.split(":");

			String containerId = gav[1] + "_" + gav[2];
			ReleaseId releaseId = new ReleaseId(gav[0], gav[1], gav[2]);
			KieContainerResource container = new KieContainerResource(containerId, releaseId);
			container.setContainerAlias(gav[1]);

			kieServer.createContainer(containerId, container);
			/*KModuleDeploymentUnit unit = new KModuleDeploymentUnit(gav[0], gav[1], gav[2]);
			deploymentService.deploy(unit);*/
			logger.info("{} successfully deployed", arg);
		}
		logger.info("Available processes:");
	}
}
