package com.vs.jasypt.system.modal;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.vs.jasypt.modal.AbstractAudited;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Role.
 */
@Entity
@Getter
@Setter
@Table(name = "S_ROLE", uniqueConstraints = @UniqueConstraint(columnNames = { "name" }))
@ToString(of = "name")
public class SystemRole extends AbstractAudited<Integer> implements GrantedAuthority {

	/**
	 * The default role, all authenticated users get it.
	 */
	public static final String DEFAULT_ROLE = "USER";

	/**
	 * SID
	 */
	private static final long serialVersionUID = -7118550223873607955L;

	/**
	 * Role name.
	 */
	@NotNull
	@NotEmpty
	@Length(max = 200)
	private String name;

	@JsonIgnore
	public String getAuthority() {
		return getName();
	}

	@Override
	public Date getLastModifiedByDate() {
		return super.getLastModifiedDate();
	}
}
