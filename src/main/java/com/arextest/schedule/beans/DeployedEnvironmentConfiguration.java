package com.arextest.schedule.beans;

import com.arextest.schedule.model.deploy.DeploymentEnvironmentProvider;
import com.arextest.schedule.service.DefaultDeployedEnvironmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * created by xinyuan_wang on 2023/1/12
 * <p>
 * Expose the entrance for users to inherit. Selection for expanding playback environment
 */
@Slf4j
@Configuration
@ConditionalOnMissingBean(DefaultDeployedEnvironmentService.class)
public class DeployedEnvironmentConfiguration {
    @Bean
    public DefaultDeployedEnvironmentService deployedEnvironmentService(
            List<DeploymentEnvironmentProvider> deploymentEnvironmentProviders
    ) {
        return new DefaultDeployedEnvironmentService(deploymentEnvironmentProviders);
    }
}