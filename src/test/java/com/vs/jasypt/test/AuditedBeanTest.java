package com.vs.jasypt.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.vs.jasypt.beans.AuditedBean;
import com.vs.jasypt.modal.AbstractAudited;
import com.vs.jasypt.modal.AbstractNamedAuditedEntity;
import com.vs.jasypt.modal.Auditable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
public class AuditedBeanTest {

	@Test
	public void testCopyAuditData(){
		final Auditable<String,Integer, Date> from = newAuditable();
		final AbstractNamedAuditedEntity<Integer> to = new AbstractNamedAuditedEntity<Integer>() {
			@Override
			public Date getLastModifiedByDate() {
				return null;
			}
		};
		to.setName("BEAN_NAME");
		AuditedBean.copyAuditData(from,to);
		assertThat("VishabSingh", is(equalTo(to.getCreatedBy())));
		assertThat(new Date(0), is(equalTo(to.getCreatedDate())));
		assertTrue(to.toString().endsWith("(name=BEAN_NAME)"));
	}

	private Auditable<String, Integer, Date> newAuditable() {
		final Auditable<String,Integer,Date> from = new AbstractAudited<Integer>() {
			@Override
			public Date getLastModifiedByDate() {
				return new Date();
			}
		};
		from.setCreatedBy("VishabSingh");
		from.setCreatedDate(new Date(0));
		from.setLastModifiedDate(new Date(1));
		from.setLastModifiedBy("Rohini");
		return from;
	}
}
