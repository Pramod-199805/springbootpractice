package com.demo.exception;

import java.lang.reflect.Method;

import org.jspecify.annotations.Nullable;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

@Component
public class SimpleAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

	@Override
	public void handleUncaughtException(Throwable ex, Method method, @Nullable Object... params) {
		System.out.println("Default Custom Async Exception " + method);

	}

}
