package com.demo.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class AsyncService {

	@Autowired
	Executor tp;

	/**
	 * Async annotation by default uses SimpleAsyncTaskExecutor Thread pool To use
	 * customized thread pool we need to create the bean of TaskPoolExecutor then
	 * spring will use customized executor
	 */
	@Async
	public CompletableFuture<String> getEmployeeDataFromDb() {

		return CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(5000);

			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println("Async Task Completed " + Thread.currentThread().getName());
			return "Employee data retrieved";
		}, tp);
	}

}
