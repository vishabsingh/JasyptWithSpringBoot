package com.vs.jasypt.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	@Autowired
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		setSharedApplicationContext(applicationContext);
	}

	public static void setSharedApplicationContext(final ApplicationContext applicationContext) {
		SpringUtils.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * Get a bean in the spring factory by name and class.
	 * Return an instance, which may be shared or independent, of the specified bean.
	 * @param beanClass
	 * @param <T>the bean type.
	 * @return the initialized spring bean.
	 */
	public static <T> T findBean(final Class<T> beanClass){
		return  applicationContext.getBean(beanClass);
	}
}
