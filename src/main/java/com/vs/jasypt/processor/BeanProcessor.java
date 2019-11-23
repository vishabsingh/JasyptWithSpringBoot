package com.vs.jasypt.processor;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;

public class BeanProcessor<T> extends Processor<T> {

	private final Field field;


	/**
	 * Property bean access constructor.
	 *
	 * @param beanType
	 *            the source bean type.
	 * @param property
	 *            the property name.
	 * @param data
	 *            the context data or another {@link Processor} instance.
	 */
	public BeanProcessor(Class<T> beanType,String property,Object data){
		super(data);
		field = FieldUtils.getField(beanType, property,true);
		if(field ==null)
			throw new IllegalStateException("Unknown property '" + property + "' for class " + beanType);
	}

	public BeanProcessor(Class<T> beanType,String property){
		this(beanType,property,null);
	}

	@Override
	public Object getValue(final T context) {
		return getFieldValue(context);
	}

	/**
	 * Return the field value.
	 *
	 * @param context
	 *            the current context (root or loop item).
	 * @return the raw value. <code>null</code> when the context is <code>null</code>.
	 */
	private Object getFieldValue(T context) {
		try {
			return context==null ? null : field.get(context);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException("Unable to access to property " + field.getName() + " of " + context, e);
		}
	}
}
