package com.vs.jasypt.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vs.jasypt.modal.Auditable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import org.joda.time.DateTime;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

/**
 * Class for Audited Objects
 *
 * @param <U> the type of identifier
 * @param <K> type of author
 */
@Getter
@Setter
@ToString(of = "id")
public class AuditedBean<U extends Serializable, K extends Serializable> {

	private K id;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date createdDate;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date lastModifiedDate;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private U createdBy;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private U lastModifiedBy;

	public <T extends Auditable<U, K, Date>> void copyAuditData(final T from) {
		copyAuditData(from, Function.identity());
	}

	/**
	 * Define the modifiable state of a valued object.
	 *
	 * @param from          The source object to copy to current one.
	 * @param userConverter the user converter.
	 * @param <S>           Bean type of source parameter.
	 * @param <T>           User type of source parameter.
	 */
	private <T, S extends Auditable<T, K, Date>> void copyAuditData(S from, final Function<T, ? extends U> userConverter) {
		if (from != null) {
			// copy audit Date
			this.createdDate = from.getCreatedDate();
			this.createdBy = userConverter.apply(from.getCreatedBy());
			this.lastModifiedDate = from.getLastModifiedByDate();
			this.lastModifiedBy = userConverter.apply(from.getLastModifiedBy());

		}
	}

	public static Date toDate(final DateTime date) {
		return date != null ? date.toDate() : null;
	}

	public static Date toDate(Instant instant) {
		return instant != null ? Date.from(instant) : null;
	}

	public static DateTime toDatetime(final Date date) {
		return date == null ? null : new DateTime(date);
	}

	/**
	 * Copy auditable data .
	 *
	 * @param <T>
	 *            Bean source type.
	 * @param <U>
	 *            Bean target type.
	 * @param <S>
	 *            "Source" type.
	 * @param <D>
	 *            "Destination" type.
	 * @param <L>
	 *            Date type.
	 * @param from
	 *            The source object.
	 * @param to
	 *            The target object.
	 */
	public static <L, U extends Serializable, T extends Serializable, S extends Auditable<U, T, L>,
		D extends Auditable<U, T, L>> void copyAuditData(final S from, final D to) {
		if (from != null) {
			// Copy audit properties
			to.setCreatedDate(from.getCreatedDate());
			to.setLastModifiedDate(from.getLastModifiedByDate());
			to.setCreatedBy(from.getCreatedBy());
			to.setLastModifiedBy(from.getLastModifiedBy());
		}
	}
}
