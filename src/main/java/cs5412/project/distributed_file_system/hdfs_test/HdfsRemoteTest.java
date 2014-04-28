//package cs5412.project.distributed_file_system.hdfs_test;
//
//import java.io.IOException;
//import java.net.URI;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FSDataInputStream;
//import org.apache.hadoop.fs.FSDataOutputStream;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.hdfs.HftpFileSystem;
//
//public class HdfsRemoteTest {
//
//	public static final String theFilename = "hello.txt";
//	public static final String message = "Hello, world!\n";
//
//	public static void main(String[] args) throws IOException {
//		Configuration conf = new Configuration();
//		System.out.println(conf.toString());
//		System.out.println(conf.get("fs.default.name"));
//		Path p = new Path("hftp://localhost:2750/foo");
//		HftpFileSystem fs = (HftpFileSystem) p.getFileSystem(conf);
//
//		Path filenamePath = new Path(theFilename);
//
//		try {
//			if (fs.exists(filenamePath)) {
//				// remove the file first
//				fs.delete(filenamePath);
//			}
//
//			FSDataOutputStream out = fs.create(filenamePath);
//			out.writeUTF(message);
//			out.close();
//
//			FSDataInputStream in = fs.open(filenamePath);
//			String messageIn = in.readUTF();
//			System.out.print(messageIn);
//			in.close();
//		} catch (IOException ioe) {
//			System.err.println("IOException during operation: "
//					+ ioe.toString());
//			System.exit(1);
//		}
//
//	}
//}
