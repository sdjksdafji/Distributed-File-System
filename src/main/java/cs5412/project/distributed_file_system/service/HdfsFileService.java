package cs5412.project.distributed_file_system.service;

import java.io.InputStream;
import java.io.OutputStream;

public interface HdfsFileService {
	public InputStream getFile(String location);

	public String setFile(String filename, InputStream is);
}
