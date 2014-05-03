package cs5412.project.distributed_file_system.service.mock;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import cs5412.project.distributed_file_system.service.HdfsFileService;

@Named
public class HdfsFileServiceMock implements HdfsFileService {

	private FileSystem fileSystem = null;
	public static final String theFilename = "/user/ubuntu/test04.txt";

	@PostConstruct
	private void init() {
		Configuration conf = new Configuration();
		conf.set("fs.default.name", "hdfs://54.187.180.230:9000");
		System.out.println(conf.toString());
		System.out.println(conf.get("fs.default.name"));
		try {
			this.fileSystem = FileSystem.get(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public InputStream getFile(String location) {
		Path filenamePath = new Path(theFilename);

		try {
			// if (fs.exists(filenamePath)) {
			// // remove the file first
			// fs.delete(filenamePath);
			// }
			//
			// FSDataOutputStream out = fs.create(filenamePath);
			// out.writeUTF(message);
			// out.close();

			FSDataInputStream hadoopInputStream = this.fileSystem
					.open(filenamePath);
			return hadoopInputStream;
			// InputStream inputStream = new FileInputStream(hadoopInputStream.)
//			String messageIn;
//			while ((messageIn = hadoopInputStream.readLine()) != null) {
//				System.out.println("message: " + messageIn);
//			}
//			hadoopInputStream.close();
		} catch (IOException ioe) {
			System.err.println("IOException during operation: "
					+ ioe.toString());
		}
		return null;
	}

}
