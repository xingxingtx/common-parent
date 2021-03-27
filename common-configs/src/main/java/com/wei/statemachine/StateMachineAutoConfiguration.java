package com.wei.statemachine;

import com.wei.properties.StateMachineProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineModelConfigurer;
import org.springframework.statemachine.config.model.StateMachineModelFactory;
import org.springframework.statemachine.uml.UmlStateMachineModelFactory;

/**
 * @author wei.peng
 * @descripe
 * @Date 2019/7/1 0001.
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(StateMachineProperties.class)
@ComponentScan(basePackages = {"com.wei"})
public class StateMachineAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(StateMachineAutoConfiguration.class);

    @Autowired
    private StateMachineProperties stateMachineProperties;

    @Bean
    public StateMachineModelFactory<String, String> modelFactory1() {
        log.info("state uml path:{}",stateMachineProperties.getUmlFilePath1());
        ClassPathResource resource = new ClassPathResource(stateMachineProperties.getUmlFilePath1());
        return new UmlStateMachineModelFactory(resource);
    }

    @Bean
    public StateMachineModelFactory<String, String> modelFactory2() {
        log.info("state uml path:{}",stateMachineProperties.getUmlFilePath2());
        ClassPathResource resource = new ClassPathResource(stateMachineProperties.getUmlFilePath2());
        return new UmlStateMachineModelFactory(resource);
    }

    @Bean
    public StateMachineModelFactory<String, String> modelFactory3() {
        log.info("state uml path:{}", stateMachineProperties.getUmlFilePath3());
        ClassPathResource resource = new ClassPathResource(stateMachineProperties.getUmlFilePath3());
        return new UmlStateMachineModelFactory(resource);
    }

    @Bean
    public StateMachineService getStateMachineService(){
        return new StateMachineService();
    }

    @Bean
    public IStateMachineMappingConfig<EnumStateMachineType,String> getStateMachineMappingConfig(){
        return new DefaultStateMachineMappingConfig();
    }

    @Configuration
    @EnableStateMachineFactory(name = {"stateMachineFactory1"})
    static class config1 extends StateMachineConfigurerAdapter<String,String>{
        @Autowired
        private StateMachineModelFactory<String,String> modelFactory1;

        public config1() {
        }

        @Override
        public void configure(StateMachineConfigurationConfigurer<String, String> config) throws Exception {
          config.withConfiguration().autoStartup(true);
        }

        @Override
        public void configure(StateMachineModelConfigurer<String, String> model) throws Exception {
            model.withModel().factory(modelFactory1);
        }
    }

    @Configuration
    @EnableStateMachineFactory(name = {"stateMachineFactory2"})
    static class config2 extends StateMachineConfigurerAdapter<String,String>{
        @Autowired
        private StateMachineModelFactory<String,String> modelFactory2;

        public config2() {
        }

        @Override
        public void configure(StateMachineConfigurationConfigurer<String, String> config) throws Exception {
            config.withConfiguration().autoStartup(true);
        }

        @Override
        public void configure(StateMachineModelConfigurer<String, String> model) throws Exception {
            model.withModel().factory(modelFactory2);
        }
    }
    @Configuration
    @EnableStateMachineFactory(name = {"stateMachineFactory3"})
    static class config3 extends StateMachineConfigurerAdapter<String,String>{
        @Autowired
        private StateMachineModelFactory<String,String> modelFactory3;

        public config3() {
        }

        @Override
        public void configure(StateMachineConfigurationConfigurer<String, String> config) throws Exception {
            config.withConfiguration().autoStartup(true);
        }

        @Override
        public void configure(StateMachineModelConfigurer<String, String> model) throws Exception {
            model.withModel().factory(modelFactory3);
        }
    }
/*
    @Bean
    public CuratorFramework curatorFramework(){
        CuratorFramework client = CuratorFrameworkFactory.builder().defaultData(new byte[0])
                .retryPolicy(new ExponentialBackoffRetry(100,3))
                .connectString("localhost:2181").build();
        client.start();
        return client;
    }

    @Bean
    public StateMachineEnsemble<String,String> stateMachineEnsemble(){
        return new ZookeeperStateMachineEnsemble<String, String>(curatorFramework(),"/foo");
    }*/
}
