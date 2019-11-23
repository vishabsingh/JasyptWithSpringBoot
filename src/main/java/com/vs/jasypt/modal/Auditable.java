package com.vs.jasypt.modal;

import org.springframework.data.domain.Persistable;

/**
 * Interface for auditable entities. Allows storing and retrieving creation and modification information. The changing
 * instance (typically some user) is to be defined by a generics definition.
 *
 * @param <U> the auditing type. Typically some kind of user.
 * @param <K> the type of the audited type's identifier.
 * @param <D> the type of the audit date.
 */
public interface Auditable<U,K,D> extends Persistable<K> {

	/**
	 * Returns the user who created this entity.
	 *
	 * @return the createdBy
	 */
	U getCreatedBy();

	void setCreatedBy(U createdBy);

	D getCreatedDate();

	void setCreatedDate(D createdDate);

	U getLastModifiedBy();

	/**
	 *
	 * @param modifiedBy
	 */
	void setLastModifiedBy(U modifiedBy);

	/**
	 * Returns the date of the last modification.
	 * @return the last ModifedByDate
	 */
	D getLastModifiedByDate();

	/**
	 * Sets the date of the last modification.
	 *
	 * @param lastModifiedDate the date of the last modification to set
	 */
	void setLastModifiedDate(D lastModifiedDate);

}
