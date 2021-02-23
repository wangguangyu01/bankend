package com.smart119.activiti.config;

import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
//import org.activiti.spring.ProcessEngineFactoryBean;
//import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
@Configuration
public class ActivitiConfig {
    //流程配置，与spring整合采用SpringProcessEngineConfiguration这个实现
//    @Bean
//    public ProcessEngineConfiguration processEngineConfiguration(DataSource dataSource, PlatformTransactionManager transactionManager){
//        SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
//        processEngineConfiguration.setDataSource(dataSource);
//        processEngineConfiguration.setDatabaseSchemaUpdate("false");
//        processEngineConfiguration.setDatabaseType("mysql");
//        processEngineConfiguration.setTransactionManager(transactionManager);
//        processEngineConfiguration.setCreateDiagramOnDeploy(false);
//        processEngineConfiguration.setAsyncExecutorActivate(false);
//        processEngineConfiguration.setJobExecutorActivate(false);
//        processEngineConfiguration.setEnableDatabaseEventLogging(false);
//        processEngineConfiguration.setEnableConfiguratorServiceLoader(false);
//        processEngineConfiguration.setAsyncExecutorEnabled(false);
//        //流程图字体
//        processEngineConfiguration.setActivityFontName("宋体");
//        processEngineConfiguration.setAnnotationFontName("宋体");
//        processEngineConfiguration.setLabelFontName("宋体");
//
//        return processEngineConfiguration;
//    }

    //流程引擎，与spring整合使用factoryBean
//    @Bean
//    public ProcessEngineFactoryBean processEngine(ProcessEngineConfiguration processEngineConfiguration){
//        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
//        processEngineFactoryBean.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
//        return processEngineFactoryBean;
//    }

    //八大接口
//    @Bean
//    @ConditionalOnBean(ProcessEngine.class)
//    public RepositoryService repositoryService(ProcessEngine processEngine){
//        return processEngine.getRepositoryService();
//    }
//
//    @Bean
//    @ConditionalOnBean(ProcessEngine.class)
//    public RuntimeService runtimeService(ProcessEngine processEngine){
//        return processEngine.getRuntimeService();
//    }
//
//    @Bean
//    @ConditionalOnBean(ProcessEngine.class)
//    public TaskService taskService(ProcessEngine processEngine){
//        return processEngine.getTaskService();
//    }
//
//    @Bean
//    @ConditionalOnBean(ProcessEngine.class)
//    public HistoryService historyService(ProcessEngine processEngine){
//        return processEngine.getHistoryService();
//    }
//
//    @Bean
//    @ConditionalOnBean(ProcessEngine.class)
//    public FormService formService(ProcessEngine processEngine){
//        return processEngine.getFormService();
//    }
//
//    @Bean
//    @ConditionalOnBean(ProcessEngine.class)
//    public IdentityService identityService(ProcessEngine processEngine){
//        return processEngine.getIdentityService();
//    }
//
//    @Bean
//    @ConditionalOnBean(ProcessEngine.class)
//    public ManagementService managementService(ProcessEngine processEngine){
//        return processEngine.getManagementService();
//    }
//
//    @Bean
//    @ConditionalOnBean(ProcessEngine.class)
//    public DynamicBpmnService dynamicBpmnService(ProcessEngine processEngine){
//        return processEngine.getDynamicBpmnService();
//    }

    //八大接口 end
}
