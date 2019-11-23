package com.vs.jasypt.beans;

import java.io.Serializable;

/**
 * A common contract for entity and business object.
 *
 * @param <K>
 *            The type of the identifier
 */
public interface IDescribableBean<K extends Serializable> extends INameAbleBean<K> {

	/**
	 * Bean description.
	 * @return The description.
	 */
	String getDescription();

	/**
	 * Set the bean description.
	 * @param description
	 *            The new description.
	 */
	void setDescription(String description);
}
