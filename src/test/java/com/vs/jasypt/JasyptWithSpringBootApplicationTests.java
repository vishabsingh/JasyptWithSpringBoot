package com.vs.jasypt;

import com.vs.jasypt.encryption.EncryptionUtil;
import com.vs.jasypt.service.JyscryptService;
import org.hamcrest.CoreMatchers;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = JasyptWithSpringBootApplication.class)
//@ContextConfiguration(classes = {JasyptWithSpringBootApplication.class})
public class JasyptWithSpringBootApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private EncryptionUtil encryptionUtil;

	@MockBean
	@Qualifier("jasyptStringEncryptor")
	StringEncryptor stringEncryptor;

	@Test
	public void test(){
		System.setProperty("jasypt.encryptor.password", "localPassword");
		JyscryptService service = applicationContext.getBean(JyscryptService.class);
		Assert.assertThat("JAIDEV", CoreMatchers.is(CoreMatchers.equalTo(service.getProperty()
		)));
		Environment env = applicationContext.getBean(Environment.class);
		Assert.assertEquals("JAIDEV", service.getPassword(env));
	}

	@Test
	public void getApplicationContext() {
		Assert.assertNotNull(applicationContext);
		Assert.assertNotNull(applicationContext.getBean(org.jasypt.encryption.pbe.StandardPBEStringEncryptor.class));
	}

	@Test
	public void encryptedTest(){
		Mockito.when(stringEncryptor.decrypt("test")).thenThrow(EncryptionOperationNotPossibleException.class);
		Mockito.when(stringEncryptor.encrypt("test")).thenReturn("encrypted");
		encryptionUtil.setApplicationDataEncryptor(stringEncryptor);
		Assert.assertThat("encrypted",CoreMatchers.is(CoreMatchers.equalTo(encryptionUtil.encryptForApplicationData("test"))));

	}

	@Test
	public void decryptedTest(){
		Mockito.when(stringEncryptor.decrypt("encrypted")).thenReturn("decrypted");
		encryptionUtil.setApplicationDataEncryptor(stringEncryptor);
		String result = encryptionUtil.decryptForApplicationData("encrypted");
		Assert.assertThat("decrypted",CoreMatchers.is(CoreMatchers.equalTo(result)));
	}
}
