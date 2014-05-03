package cs5412.project.distributed_file_system.service.impl;


import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cs5412.project.distributed_file_system.service.Sha1HashService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class Sha1HashServiceImplTest {
	
	@Inject
	private Sha1HashService sha1HashService;

	@Test
	public void test() {
		System.out.println(this.sha1HashService.sha1("thisIsApassowrd dsafdsf"));
		System.out.println(this.sha1HashService.sha1("helloUser"));
	}

}
