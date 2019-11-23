package com.vs.jasypt.processor;

import java.util.Map;

public class MapProcessor<T> extends BeanProcessor<T> {

	private final Map<?,?> map;

	public MapProcessor(final Map<?,?> map,Class<T> beanType, String property) {
		super(beanType, property);
		this.map = map;
	}

	@Override
	public Object getValue(final T context) {
		return getMapValue(super.getValue(context));
	}

	/**
	 * Return the {@link Map} value corresponding to given key.
	 *
	 * @param key
	 *            The {@link Map} key.
	 * @return
	 * 		The {@link Map} value.
	 */
	protected  Object getMapValue(Object key) {
		return map.get(key);
	}
}
