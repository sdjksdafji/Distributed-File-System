package cs5412.project.distributed_file_system.dao.jdbc;

import static org.junit.Assert.*;

import java.util.List;

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
	private int tmpfid;

	@Test
	public void test() {
		System.out.println("Hello Test");
		fail("Not yet implemented");
	}

	@Test
	public void testCreateFile() {
		File file = new File("testdir", true);
		file.setuId(1);
		tmpfid = fileDao.createFile(file);
		if (tmpfid <= 0) {
			fail("create fail");
		} else {
			System.out.println("created fid " + tmpfid);
		}
	}

	@Test
	public void testUpdateFile() {
		tmpfid = 1;
		File file = new File("testdir_Updated", true);
		file.setuId(1);
		file.setfId(tmpfid);
		boolean isSuccess = fileDao.updateFile(file);
		if (!isSuccess) {
			fail("update fail");
		}
	}

	@Test
	public void testGetFileByFid() {
		tmpfid = 1;
		File file = fileDao.getFileByFid(tmpfid);
		String filename = file.getName();
		if (filename == null || filename.length() == 0) {
			fail("update fail");
		} else {
			System.out.println("filename: " + filename);
		}
	}

	@Test
	public void testGetFileByParentDir() {
		File dir = new File("testdir", true);
		List<File> files = fileDao.getFileByParentDir(dir);
		if (files == null || files.size() == 0) {
			fail("update fail");
		} else {
			for (File f : files) {
				System.out.println("filename: " + f.getName());
			}
		}
	}
	
	@Test
	public void testDeleteFile() {
		File file = new File("testdir", true);
		file.setuId(1);
		tmpfid = fileDao.createFile(file);
		boolean isSuccess = fileDao.deleteFile(file);
		if (!isSuccess) {
			fail("delete fail");
		}
	}
	
	

}
