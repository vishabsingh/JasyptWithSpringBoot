package com.vs.jasypt.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.vs.jasypt.beans.DescribedAuditedBean;
import com.vs.jasypt.beans.DescribedBean;
import com.vs.jasypt.beans.IDescribableBean;
import com.vs.jasypt.beans.NamedBean;
import com.vs.jasypt.modal.AbstractNamedAuditedEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@RunWith(SpringRunner.class)
public class DescribedBeanTest {

	//@Autowired
	//@Qualifier("describedAuditedBean")
	private IDescribableBean describedAuditedBean;


	@Test
	public void testCopyAudited(){
		final IDescribableBean from = new DescribedAuditedBean();
		from.setId(101);
		from.setName("IDescribableBean");
		from.setDescription("TYPE_OF_DescribedAuditedBean");

		final IDescribableBean to = new DescribedAuditedBean();
		DescribedBean.copy(from, to);
		assertThat(101, is(equalTo(to.getId())));
	}
	@Test
	public void testCompareTo(){
		final Set<NamedBean<Integer>> beans = new TreeSet<>();
		beans.add(new NamedBean<>(3, "VALUE3"));
		beans.add(new NamedBean<>(1, "VALUE1"));
		beans.add(new NamedBean<>(1, "value4"));
		beans.add(new NamedBean<>(1, "value0"));
		beans.add(new NamedBean<>(2, "VALUE2"));
		final List<NamedBean<Integer>> beansList = new ArrayList<>(beans);
		assertThat("value0", is(equalTo(beansList.get(0).getName())));
	}
}
