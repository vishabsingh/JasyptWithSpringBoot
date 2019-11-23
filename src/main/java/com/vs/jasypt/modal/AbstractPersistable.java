package com.vs.jasypt.modal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = "new")
public abstract class AbstractPersistable<K extends Serializable> extends org.springframework.data.jpa.domain.AbstractPersistable<K> {

	@Override
	public void setId(final K id) {
		super.setId(id);
	}
}
