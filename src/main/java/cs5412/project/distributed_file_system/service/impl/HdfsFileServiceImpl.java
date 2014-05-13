package cs5412.project.distributed_file_system.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import cs5412.project.distributed_file_system.service.HdfsFileService;

@Named
public class HdfsFileServiceImpl implements HdfsFileService {

	private FileSystem fileSystem = null;
	public static final String theFilename = "/user/ubuntu/test04.txt";
	public static final String path = "/user/ubuntu/";

	@PostConstruct
	public void init() {
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
		Path filenamePath = new Path(location);

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
			// String messageIn;
			// while ((messageIn = hadoopInputStream.readLine()) != null) {
			// System.out.println("message: " + messageIn);
			// }
			// hadoopInputStream.close();
		} catch (IOException ioe) {
			System.err.println("IOException during operation: "
					+ ioe.toString());
		}
		return null;
	}

	@Override
	public String setFile(String filename, InputStream is) {
		String filename2 = Math.round(Math.random() * 10) + filename;
		Path filenamePath = new Path(path + filename2);

		try {
			while (fileSystem.exists(filenamePath)) {
				filename2 = Math.round(Math.random() * 10) + filename2;
				filenamePath = new Path(path + filename2);
			}

			OutputStream out = fileSystem.create(filenamePath);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = is.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			is.close();
			out.flush();
			out.close();

			System.out.println("New file created!");
		} catch (IOException ioe) {
			System.err.println("IOException during operation: "
					+ ioe.toString());
		}
		return filenamePath.toString();
	}
}
