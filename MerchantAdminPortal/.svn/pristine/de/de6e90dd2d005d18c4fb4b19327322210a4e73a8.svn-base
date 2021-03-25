package com.npst.upi.portal.merchant.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
/**
 * 
 * @author Rahul Chaudhary
 *
 */
@Configuration
@EnableScheduling
public class ThreadExecutorConfig {
	@Value("${task_queueCapacity}")
	private int queueCapacity;
	@Value("${task_corePoolSize}")
	private int corePoolSize;
	@Value("${task_maxPoolSize}")
	private int maxPoolSize;

	@Bean
	public TaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadNamePrefix("Merchant-Switch");
		executor.setThreadGroupName("Merchant-Switch-Engine");
		executor.initialize();
		return executor;
	}
}
