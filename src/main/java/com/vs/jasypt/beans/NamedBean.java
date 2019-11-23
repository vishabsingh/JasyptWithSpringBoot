package com.vs.jasypt.beans;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@ToString(of = "name")
@AllArgsConstructor
@NoArgsConstructor
public  class NamedBean <K extends Serializable> implements INameAbleBean<K> {

	/**
	 * Identifer of this bean
	 */
	private K id;

	@NotBlank
	@NotNull
	private String name;

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name =  name;
	}

	@Override
	public K getId() {
		return this.id;
	}

	@Override
	public void setId(K id) {
		this.id = id;
	}

	public static <T extends  Serializable> void copy(final INameAbleBean<T> from, final INameAbleBean<T> to){
		to.setId(from.getId());
		to.setName(from.getName());
	}
}
