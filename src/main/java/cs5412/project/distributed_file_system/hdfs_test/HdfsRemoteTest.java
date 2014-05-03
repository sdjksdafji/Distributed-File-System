package cs5412.project.distributed_file_system.hdfs_test;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsRemoteTest {

	public static final String theFilename = "/user/ubuntu/test04.txt";
	public static final String coresite = "~/Developer/cornell/cs5300/hadoop/conf/core-site.xml";
	public static final String message = "Hello, world!\n";

	public static void main(String[] args) throws IOException {
		Configuration conf = new Configuration();
		conf.set("fs.default.name", "hdfs://master:9000");
		conf.set("fs.default.name", "hdfs://54.187.180.230:9000");
		System.out.println(conf.toString());
		System.out.println(conf.get("fs.default.name"));
		// Path p = new Path("hdfs://54.187.180.230:9000/");
		FileSystem fs = FileSystem.get(conf);

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

			FSDataInputStream in = fs.open(filenamePath);
			String messageIn;
			while ((messageIn = in.readLine()) != null) {
				System.out.println("message: " + messageIn);
			}
			in.close();
		} catch (IOException ioe) {
			System.err.println("IOException during operation: "
					+ ioe.toString());
			System.exit(1);
		}

	}
}