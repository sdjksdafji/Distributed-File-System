package cs5412.project.distributed_file_system.hdfs_test;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cs5412.project.distributed_file_system.dao.FileDAO;
import cs5412.project.distributed_file_system.dao.HistoryDAO;
import cs5412.project.distributed_file_system.pojo.File;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class HdfsCleanTest {
	public static final String theFilename = "/user/ubuntu/test04.txt";
	public static final String coresite = "~/Developer/cornell/cs5300/hadoop/conf/core-site.xml";
	public static final String message = "Hello, world!\n";
	public static final int uid = 4;
	public static final int historySize = 10;

	@Inject
	private HistoryDAO historyDao;

	@Inject
	private FileDAO fileDao;

	@Test
	public void testClean() {

		Configuration conf = new Configuration();
		conf.set("fs.default.name", "hdfs://master:9000");
		conf.set("fs.default.name", "hdfs://54.187.180.230:9000");
		System.out.println(conf.toString());
		System.out.println(conf.get("fs.default.name"));
		// Path p = new Path("hdfs://54.187.180.230:9000/");
		FileSystem fs = null;
		try {
			fs = FileSystem.get(conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Path filenamePath = new Path(theFilename);

		boolean isSuccess = historyDao.keepLatestNHistoryForUser(uid,
				historySize);

		if (isSuccess) {
			System.out.println("history clean success");
		}

		List<File> removeList = fileDao.getRemovingFile();
		System.out.println("removeList: " + removeList.size());

		try {
			for (File f : removeList) {
				System.out.println("removing: " + f.getLocation());
				filenamePath = new Path(f.getLocation());
				if (fs.exists(filenamePath)) {
					// remove the file first
					boolean delete = fs.delete(filenamePath);
					if (delete) {
						System.out.println("\tremoved");
						boolean dbremove = fileDao.realRemoveFile(f.getFid());
						if (dbremove) {
							System.out.println("\tremoved in db");
						} else {
							System.out.println("\tnot removed in db");
						}
					} else {
						System.out.println("\tnot removed");
					}
				}
			}

		} catch (IOException ioe) {
			System.err.println("IOException during operation: "
					+ ioe.toString());
			System.exit(1);
		}

	}
}
