package com.demo.service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.management.RuntimeErrorException;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.demo.exception.ApplicationException;

@Service
public class DemoService {

	private AsyncService async;

	public DemoService(AsyncService async) {
		System.out.println("DemoService");
		this.async = async;
	}

	ThreadPoolExecutor tp = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(4));

	public CompletableFuture<String> greetings() {
		CompletableFuture<String> asyncTask = asyncTask(); // 5 sec
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
	 * 
	 * Using CompletableFuture we can achieve the async mechanism
	 */
//	@Async
	public CompletableFuture<String> asyncTask() {
		return CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(5000);
				System.out.println("AsyncTask: " + Thread.currentThread().getName());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return "Hello";
		}, tp);

	}

	public String greeting() {
		System.out.println(Thread.currentThread().getName());
		return "processed";

	}

	/* ====================================== */
	public CompletableFuture<String> getEmployee() {
		System.out.println("getEmployee " + Thread.currentThread().getName());
		CompletableFuture<String> employeeDataFromDb = null;
		employeeDataFromDb = async.getEmployeeDataFromDb();

		// Invoking asyncmethod from same class wont tigger async mechansim
		// String employeeDataFromDb = getEmployeeDataFromDb();

		/**
		 * Note: thenApply will always return new CompletableFuture so store it in
		 * common local variable or else use store in new variable and return the new
		 * completeablefuture
		 * 
		 * 
		 * Exception thrown in asyncThread i.e compleatble future then apply it will not
		 * be handled by the @Rest/ControllerAdvice because exceptions not on request
		 * thread
		 */

		employeeDataFromDb = employeeDataFromDb.thenApply((data) -> {
			System.out.println("DATA " + data);
			if (!data.isBlank()) {
				throw new ApplicationException("Data Not found");
			}
			return data;
		});

		updateDb();

		return employeeDataFromDb;
	}

	private void updateDb() {
		System.out.println("updateDb" + Thread.currentThread().getName());
		System.out.println("DB Updated");

	}

	/**
	 * Async marks the method to run asynchronously The method run on the new thread
	 * without blocking main thread
	 * 
	 * To enable async mechanism we need to use annotation @EnableAsync in main
	 * application
	 * 
	 * Async methods should not be called from same class methods Spring uses
	 * proxy-based AOP So we need to inject the Async method class and call the
	 * method When the same bean calls its own @Async method → async FAILS
	 * 
	 * @Async methods must be public
	 * 
	 * @Async methods must return: -void, or -Future<T>, or -CompletableFuture<T>
	 * 
	 * @Async method MUST be inside a different bean
	 */
	@Async
	private String getEmployeeDataFromDb() {
		System.out.println("getEmployeeDataFromDb" + Thread.currentThread().getName());
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "Employee data";
	}

}

/**
 * CompletableFuture<String> employeeDataFromDb =
 * async.getEmployeeDataFromDb().thenApply(data -> { System.out.println("DATA "
 * + data); if (!data.isBlank()) { throw new ApplicationException("Data Not
 * found"); } return data; }).handle((result, ex) -> { if (ex != null) {
 * Throwable cause = ex.getCause(); return
 * CompletableFuture.<String>failedFuture(new
 * ApplicationException(cause.getMessage())).join(); } return result; });
 */
