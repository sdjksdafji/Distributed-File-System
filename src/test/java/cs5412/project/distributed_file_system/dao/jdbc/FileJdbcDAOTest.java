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

	// @Test
	// public void testCreateHistory() {
	// boolean isSuccess = fileDao.createHistory(1,0,0,4);
	// if (!isSuccess) {
	// fail("create history fail");
	// }
	// }

	@Test
	public void testCreateFile() {
		File file = new File("file3", false);
		file.setUid(1);
		file.setParentDir(3);
		file.setPublic(true);
		file.setHash("hjkl");
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
		} else {
			System.out.println("removed fid " + tmpfid);
		}
	}

	@Test
	public void testMergeBranch() {
		File srcfile = new File("srcfolder", true);
		File dstfile = new File("dstfolder", true);
		srcfile.setParentDir(1);
		srcfile.setUid(1);
		dstfile.setParentDir(1);
		dstfile.setUid(1);
		int srcfid = fileDao.createFile(srcfile);
		int dstfid = fileDao.createFile(dstfile);
		File file1 = new File("file1", false);
		file1.setHash("asdf");
		file1.setParentDir(srcfid);
		file1.setUid(1);
		File file1dup = new File("file1dup", false);
		file1dup.setHash("asdf");
		file1dup.setParentDir(dstfid);
		file1dup.setUid(1);
		File file2 = new File("file2", false);
		file2.setHash("fdsa");
		file2.setParentDir(srcfid);
		file2.setUid(1);
		File file3 = new File("file3", false);
		file3.setHash("hjkl");
		file3.setParentDir(dstfid);
		file3.setUid(1);
		fileDao.createFile(file1);
		fileDao.createFile(file1dup);
		fileDao.createFile(file2);
		fileDao.createFile(file3);

		File srcsubfolder1 = new File("subfolder1", true);
		srcsubfolder1.setParentDir(srcfid);
		srcsubfolder1.setUid(1);
		int srcsubfolder1fid = fileDao.createFile(srcsubfolder1);
		File srcsubfolder2 = new File("subfolder2", true);
		srcsubfolder2.setParentDir(srcfid);
		srcsubfolder2.setUid(1);
		int srcsubfolder2fid = fileDao.createFile(srcsubfolder2);
		File srcsubfolder3 = new File("subfolder3", true);
		srcsubfolder3.setParentDir(srcfid);
		srcsubfolder3.setUid(1);
		int srcsubfolder3fid = fileDao.createFile(srcsubfolder3);
		File dstsubfolder1 = new File("subfolder1", true);
		dstsubfolder1.setParentDir(dstfid);
		dstsubfolder1.setUid(1);
		int dstsubfolder1fid = fileDao.createFile(dstsubfolder1);
		File dstsubfolder2 = new File("subfolder2", true);
		dstsubfolder2.setParentDir(dstfid);
		dstsubfolder2.setUid(1);
		int dstsubfolder2fid = fileDao.createFile(dstsubfolder2);

		File file4 = new File("file4", false);
		file4.setHash("4444");
		file4.setParentDir(srcsubfolder1fid);
		file4.setUid(1);

		File file4dup = new File("file4dup", false);
		file4dup.setHash("4444");
		file4dup.setParentDir(dstsubfolder1fid);
		file4dup.setUid(1);

		File file5 = new File("file5", false);
		file5.setHash("5555");
		file5.setParentDir(srcsubfolder2fid);
		file5.setUid(1);

		File file6 = new File("file6", false);
		file6.setHash("6666");
		file6.setParentDir(dstsubfolder2fid);
		file6.setUid(1);

		File file7 = new File("file7", false);
		file7.setHash("7777");
		file7.setParentDir(srcsubfolder3fid);
		file7.setUid(1);

		fileDao.createFile(file4);
		fileDao.createFile(file4dup);
		fileDao.createFile(file5);
		fileDao.createFile(file6);
		fileDao.createFile(file7);

		File srcbranch = fileDao.getFileByFid(srcfid);
		File dstbranch = fileDao.getFileByFid(dstfid);
		boolean isSuccess = fileDao.mergeBranch(srcbranch, dstbranch);
		if (!isSuccess) {
			fail("merge branch fail");
		}
	}

	@Test
	public void testForkBrank() {
		File srcfile = new File("srcfolder", true);
		srcfile.setParentDir(1);
		srcfile.setUid(1);

		int srcfid = fileDao.createFile(srcfile);
		srcfile.setFid(srcfid);
		File file1 = new File("file1", false);
		file1.setHash("asdf");
		file1.setParentDir(srcfid);
		file1.setUid(1);

		File file2 = new File("file2", false);
		file2.setHash("fdsa");
		file2.setParentDir(srcfid);
		file2.setUid(1);

		fileDao.createFile(file1);
		fileDao.createFile(file2);

		File srcsubfolder1 = new File("subfolder1", true);
		srcsubfolder1.setParentDir(srcfid);
		srcsubfolder1.setUid(1);
		int srcsubfolder1fid = fileDao.createFile(srcsubfolder1);

		File file4 = new File("file4", false);
		file4.setHash("4444");
		file4.setParentDir(srcsubfolder1fid);
		file4.setUid(1);
		fileDao.createFile(file4);

		File forkdir = fileDao.forkBrank(srcfile, "srcfolder_forked");
		if (forkdir == null) {
			fail("fork branch fail");
		}
	}

}
