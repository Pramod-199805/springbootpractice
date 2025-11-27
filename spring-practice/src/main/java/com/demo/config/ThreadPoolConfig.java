package com.demo.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {
	/* Springbean used this for @Async class */
	@Bean
	Executor threadPoolExecutor() {

		ThreadPoolTaskExecutor tp = new ThreadPoolTaskExecutor();
		tp.setCorePoolSize(2);
		tp.setMaxPoolSize(4);
		tp.setQueueCapacity(6);
		tp.setThreadNamePrefix("asyncthread- ");

		return tp;
	}
}
