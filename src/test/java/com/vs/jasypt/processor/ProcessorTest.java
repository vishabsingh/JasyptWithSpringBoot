package com.vs.jasypt.processor;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import com.vs.jasypt.system.modal.SystemRoleAssignment;
import com.vs.jasypt.system.modal.SystemUser;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Processor.class})
public class ProcessorTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorTest.class);
	@Test
	public void testProcessor(){
		final Deque<Object> contextData = new LinkedList<>();
		contextData.add(3);
		assertThat(new Integer(3), is(equalTo(new Processor<>().getValue(contextData))));
		assertThat(new Integer(3), is(equalTo(new Processor<>(3).getValue(contextData))));
	}

	@Test
	public void testProcessor_1(){
		final Deque<Object> contextData = new LinkedList<>();
		contextData.add(4);
		assertThat(new Integer(3), is(equalTo(new Processor<>(3).getValue(contextData))));
	}
	@Test
	public void testProcessor_2_getWrappedValue() {
		final SystemUser systemUser = new SystemUser();
		systemUser.setLogin("any");

		final SystemRoleAssignment roleAssignment = new SystemRoleAssignment();
		roleAssignment.setUser(systemUser);

		final Deque<Object> contextData = new LinkedList<>();
		contextData.add(roleAssignment);

		assertThat("any", is(
			equalTo(new BeanProcessor<>(SystemUser.class, "login",new BeanProcessor<>(SystemRoleAssignment.class, "user")).getValue(contextData))));
	}

	@Test
	public void testProcessor_3_beanprocessor_get_value() {
		final Deque<Object> contextData = new LinkedList<>();
		final SystemUser systemUser = new SystemUser();
		systemUser.setLogin("any");
		contextData.add(systemUser);
		String result = (String) new BeanProcessor<>(SystemUser.class, "login").getValue(contextData);
		LOGGER.info("get Value Bean Processor  {} ",result);
		assertThat("any",is(equalTo(result)) );
	}

	@Test
	public void getValueNull() {
		Assert.assertNull("any", new BeanProcessor<>(SystemUser.class, "login").getValue((SystemUser) null));
	}
	@Test(expected = IllegalStateException.class)
	public void getValueInvalidProperty() {
		new BeanProcessor<>(SystemUser.class, "_any").getClass();
	}

	@Test(expected = IllegalStateException.class)
	public void getValueInvalidBean() {
		final Deque<Object> contextData = new LinkedList<>();
		contextData.add(3);
		Assert.assertEquals(new Integer(3), new BeanProcessor<>(SystemUser.class, "login").getValue(contextData));
	}

	@Test
	public void testGetValue() {
		final Deque<Object> contextData = new LinkedList<>();
		final SystemUser systemUser = new SystemUser();
		systemUser.setLogin("any");
		contextData.add(systemUser);
		final Map<String, String> map = new HashMap<>();
		map.put("any", "value");
		Assert.assertEquals("value", new MapProcessor<>(map, SystemUser.class, "login").getValue(contextData));
	}
}
