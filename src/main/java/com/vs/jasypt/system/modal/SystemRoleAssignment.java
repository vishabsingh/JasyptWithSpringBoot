package com.vs.jasypt.system.modal;

import com.vs.jasypt.modal.AbstractAudited;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * An assignment. Currently is only managing a many-to-many (2 many-to-one) relationship, but should handle assignment
 * on a specific organization unit, as described in ORBAC.
 */
@Entity
@Getter
@Setter
@Table(name = "S_ROLE_ASSIGNMENT")
@ToString(of = { "user", "role" })
public class SystemRoleAssignment extends AbstractAudited<Integer> {

	/**
	 * Associated role.
	 */
	@ManyToOne
	@NotNull
	private SystemRole role;

	/**
	 * Associated user
	 */
	@ManyToOne
	@NotNull
	private SystemUser user;

	@Override
	public Date getLastModifiedByDate() {
		return super.getLastModifiedDate();
	}
}
