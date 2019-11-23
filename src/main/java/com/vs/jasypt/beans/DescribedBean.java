package com.vs.jasypt.beans;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
public class DescribedBean <K extends Serializable> extends NamedBean<K> implements IDescribableBean<K>{

	@Length(max = 250)
	private String description;

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	public static <T extends Serializable> void copy( final IDescribableBean<T> from , final IDescribableBean<T> to){
		NamedBean.copy(from,to);
		to.setDescription(from.getDescription());
	}


	public static <T extends Serializable> DescribedBean<T> clone(final IDescribableBean<T> from) {
		if(from == null) return null;
		final DescribedBean describedBean = new DescribedBean();
		copy(from, describedBean);
		return describedBean;
	}
}
