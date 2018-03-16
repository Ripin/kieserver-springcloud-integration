package org.kie.server.springboot;

import org.kie.server.springboot.listener.AutoDeployListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * kie-server启动类
 *
 * @author RipinYan on 2017/10/11.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class KieServerApplication extends SpringBootServletInitializer {

	private static final Logger logger = LoggerFactory.getLogger(KieServerApplication.class);
	protected static final String PERSISTENCE_UNIT_NAME = "org.jbpm.domain";


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		builder.listeners(new AutoDeployListener());
		return builder.sources(KieServerApplication.class);
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(KieServerApplication.class).web(true).run(args);
	}

/*

	@Bean
	@ConditionalOnMissingBean(name = "definitionService")
	public DefinitionService definitionService() {

		return new BPMN2DataServiceImpl();
	}

	@Bean
	@ConditionalOnMissingBean(name = "formService")
	public FormManagerService formService() {

		return new FormManagerServiceImpl();
	}
*/

/*	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaProperties jpaProperties){
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
		factoryBean.setPersistenceXmlLocation(PERSISTENCE_XML_LOCATION);
		factoryBean.setJtaDataSource(dataSource);
		factoryBean.setJpaPropertyMap(jpaProperties.getHibernateProperties(dataSource));


		return factoryBean;
	}*/

	/*@Bean
	@ConditionalOnMissingBean(name = "deploymentService")
	public DeploymentService deploymentService(DefinitionService definitionService, RuntimeManagerFactory runtimeManagerFactory, FormManagerService formService, EntityManagerFactory entityManagerFactory, IdentityProvider identityProvider) {
		EntityManagerFactoryManager.get().addEntityManagerFactory(PERSISTENCE_UNIT_NAME, entityManagerFactory);

		KModuleDeploymentService deploymentService = new KModuleDeploymentService();
		deploymentService.setBpmn2Service(definitionService);
		deploymentService.setEmf(entityManagerFactory);
		deploymentService.setIdentityProvider(identityProvider);
		deploymentService.setManagerFactory(runtimeManagerFactory);
		deploymentService.setFormManagerService(formService);

		deploymentService.addListener((BPMN2DataServiceImpl) definitionService);

		return deploymentService;
	}
*/

	/*@Bean
	CommandLineRunner deployAndValidate() {
		return new CommandLineRunner() {

		*//*	@Autowired
			private DeploymentService deploymentService;

			@Autowired
			private RuntimeDataService runtimeDataService;

			@Autowired
			private ProcessService processService;*//*

			@Override
			public void run(String... strings) throws Exception {

				ServiceRegistry serviceRegistry = ServiceRegistry.get();
				KModuleDeploymentService deploymentService = new KModuleDeploymentService();

				KModuleDeploymentUnit unit = null;
				if (strings.length > 0) {
					String arg = strings[0];
					logger.info("About to deploy : {}", arg);

					String[] gav = arg.split(":");

					unit = new KModuleDeploymentUnit(gav[0], gav[1], gav[2]);
					deploymentService.deploy(unit);
					logger.info("{} successfully deployed", arg);
				}
				logger.info("Available processes:");
				*//*Collection<ProcessDefinition> processes = runtimeDataService.getProcesses(new QueryContext());
				for (ProcessDefinition def : processes) {
					logger.info("\t{} (with id '{})", def.getName(), def.getId());
				}

				if (unit != null && !processes.isEmpty()) {
					String processId = processes.iterator().next().getId();
					logger.info("About to start process with id {}", processId);
					long processInstanceId = processService.sta、、rtProcess(unit.getIdentifier(), processId);
					logger.info("Started instance of {} process with id {}", processId, processInstanceId);

					processService.abortProcessInstance(processInstanceId);
					logger.info("Aborted instance with id {}", processInstanceId);
				}*//*
				logger.info("========= Verification completed successfully =========");
			}
		};
	}*/
}
