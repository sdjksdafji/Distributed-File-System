package cs5412.project.distributed_file_system.dao.jdbc;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cs5412.project.distributed_file_system.dao.FileDAO;
import cs5412.project.distributed_file_system.pojo.File;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class FileJdbcDAOTest {
	
	@Inject
	private FileDAO fileDao;

	@Test
	public void test() {
		System.out.println("Hello Test");
		fail("Not yet implemented");
	}
	
	@Test
    public void testCreate(){
        File file = new File("testdir",true);
        file.setuId(1);
		fileDao.createFile(file);
    }

}
