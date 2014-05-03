package cs5412.project.distributed_file_system.service.mock;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cs5412.project.distributed_file_system.service.HdfsFileService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class HdfsFileServiceMockTest {
	
	@Inject
	private HdfsFileService hdfsFileService;

	@Test
	public void test() {
		this.hdfsFileService.getFile("21341234");
	}

}
