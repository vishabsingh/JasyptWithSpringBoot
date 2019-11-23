package com.vs.jasypt.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@ToString(of = "name")
public class NamedAuditedBean<U extends Serializable, K extends  Serializable> extends  AuditedBean<U,K> implements INameAbleBean<K>{

	@NotBlank
	@NotNull
	private String name;
}
