package com.vs.jasypt.test;

import com.vs.jasypt.utils.GlobalPropertyUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@RunWith(SpringRunner.class)
public class GlobalPropertiesTest {

	/**
	 * No provided locations.
	 * @throws IOException Read issue occurred
	 */
	@Test
	public void testNoLocations() throws IOException {
		new GlobalPropertyUtils().setLocations(new Resource[0]);
		new GlobalPropertyUtils().loadProperties(new Properties());
		Assert.assertNull(GlobalPropertyUtils.getProperty("key"));
	}

	/**
	 * Location does not exit
	 * @throws IOException
	 *
	 */
	@Test
	public void testLocationNoInput() throws IOException{
		Resource[] resources = new Resource[] {Mockito.mock(Resource.class)};
		Mockito.when(resources[0].getInputStream()).thenReturn(null);
		new GlobalPropertyUtils().setLocations(new Resource[0]);
		new GlobalPropertyUtils().loadProperties(new Properties());
		Assert.assertNull(GlobalPropertyUtils.getProperty("key"));
	}

	/**
	 * Resource read causes error.
	 *
	 * @throws IOException
	 *             Read issue occurred.
	 */
	@Test
	public void testLocationInputError() throws IOException {
		final Resource[] resources = new Resource[1];
		final Resource resource = Mockito.mock(Resource.class);
		resources[0] = resource;
		Mockito.doThrow(new IOException()).when(resource).getInputStream();
		new GlobalPropertyUtils().setLocations(resources);
	}

	@Test
	public void testLocation() throws IOException {
		final Resource[] resources = new Resource[1];
		final Resource resource = Mockito.mock(Resource.class);
		InputStream input = new ByteArrayInputStream("key=value".getBytes());
		Mockito.when(resource.getInputStream()).thenReturn(input);
		resources[0] = resource;
		new GlobalPropertyUtils().setLocations(resources);
		new GlobalPropertyUtils().loadProperties(new Properties());
		Assert.assertThat("value", CoreMatchers.is(CoreMatchers.equalTo(GlobalPropertyUtils.getProperty("key"))));

	}
}
