package com.vs.jasypt.modal;

import com.vs.jasypt.beans.INameAbleBean;
import lombok.*;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Named and audited entity.
 *
 * @param <K>
 *            Identifier type.
 */
@Getter
@Setter
@MappedSuperclass
@ToString(of = "name")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractNamedAuditedEntity<K extends Serializable> extends AbstractAudited<K> implements INameAbleBean<K> {

	/**
	 * Object name
	 */
	@NotBlank
	@NotNull
	private String name;

}