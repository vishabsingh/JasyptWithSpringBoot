package com.vs.jasypt.beans;

import java.io.Serializable;


/**
 * A named and identified bean
 *
 * @param <K> The type of the identifier
 */
public interface INameAbleBean<K extends Serializable> extends Comparable<INameAbleBean<K>> {

	/**
	 * Bean Name
	 * Human Bean readable Name
	 * @return
	 */
	String getName();

	void setName(String name);

	/**
	 * Bean identifier
	 * @return identifier
	 */
	K getId();

	/**
	 * Set the bean identifier.
	 * @param id The new identifier.
	 */
	void setId(K id);

	@Override
	default int compareTo(final INameAbleBean<K> o){
		return getName().compareToIgnoreCase(o.getName());
	}
}
