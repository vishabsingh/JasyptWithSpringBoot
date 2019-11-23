package com.vs.jasypt.system.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "S_USER")
@Getter
@Setter
@EqualsAndHashCode(of = "login")
@ToString(of = "login")
public class SystemUser implements Serializable {
	/**
	 * SID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Corporate user login.
	 */
	@Id
	@NotEmpty
	private String login;

	/**
	 * Last known connection.
	 */
	@Temporal(TemporalType.DATE)
	private Date lastConnection;

	/**
	 * Associated roles
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private Set<SystemRoleAssignment> roles;
}
