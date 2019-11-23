package com.vs.jasypt.modal;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractNamedValue<K extends Serializable> extends AbstractNamedAuditedEntity<K> {

	/**
	 * Value as string.
	 */
	@NotEmpty
	@NotNull
	@Size(max = 1023)
	private String value;
}

