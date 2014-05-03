package cs5412.project.distributed_file_system.service;

import java.io.InputStream;

public interface HdfsFileService {
	public InputStream getFile(String location);
}
