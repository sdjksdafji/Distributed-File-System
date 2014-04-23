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

//	@Test
//	public void testCreateHistory() {
//		boolean isSuccess = fileDao.createHistory(1,0,0,4);
//		if (!isSuccess) {
//			fail("create history fail");
//		}
//	}

	@Test
	public void testCreateFile() {
		File file = new File("testdir", true);
		file.setUid(1);
		file.setParentDir(1);
		tmpfid = fileDao.createFile(file);
		if (tmpfid <= 0) {
			fail("create fail");
		} else {
			System.out.println("created fid " + tmpfid);
		}
	}

	@Test
	public void testUpdateFile() {
		tmpfid = 2;
		File file = new File("testdir_Updated", true);
		file.setUid(1);
		file.setFid(tmpfid);
		file.setHidden(false);
		file.setParentDir(1);
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
		dir.setFid(1);
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
		file.setUid(1);
		file.setParentDir(1);
		tmpfid = fileDao.createFile(file);
		System.out.println("created fid " + tmpfid);
		file.setFid(tmpfid);
		boolean isSuccess = fileDao.deleteFile(file);
		if (!isSuccess) {
			fail("delete fail");
		}else{
			System.out.println("removed fid " + tmpfid);
		}
	}
	
	

}
