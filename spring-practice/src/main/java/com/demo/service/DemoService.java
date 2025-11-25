package com.demo.service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

	public DemoService() {
		System.out.println("DemoService");
	}

	ThreadPoolExecutor tp = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(4));

	public CompletableFuture<String> greetings() {
		CompletableFuture<String> asyncTask = asyncTask();
		String h = greeting();
		System.out.println(h);
		return asyncTask;

	}

	/**
	 * Async marks the method to run asynchronously The method run on the new thread
	 * without blocking main thread
	 * 
	 * To enable async mechanism we need to use annotation @EnableAsync is main
	 * application
	 */
//	@Async
	public CompletableFuture<String> asyncTask() {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(5000);
				System.out.println("AsyncTask: " + Thread.currentThread().getName());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return "Hello";
		}, tp);
		return future;
	}

//		

//		tp.execute(() -> {
//			try {
//				Thread.sleep(5000);
//				System.out.println("AsyncTask: " + Thread.currentThread().getName());
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//
//		});

	public String greeting() {
		System.out.println(Thread.currentThread().getName());
		return "processed";
	}
}
