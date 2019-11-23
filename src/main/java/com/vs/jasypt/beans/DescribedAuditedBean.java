package com.vs.jasypt.beans;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import java.io.Serializable;


/**
 * An audited and described bean
 *
 * @param <K>
 *            the type of the identifier
 * @param <U>
 *            the type of the author
 */
@Setter
@Getter
public class DescribedAuditedBean <U extends Serializable, K extends Serializable>
	extends NamedAuditedBean<U,K> implements IDescribableBean<K>{

	@Length(max= 250)
	private String description;
}
