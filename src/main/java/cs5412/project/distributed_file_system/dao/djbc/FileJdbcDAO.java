package cs5412.project.distributed_file_system.dao.djbc;

import java.util.List;

import cs5412.project.distributed_file_system.dao.FileDAO;
import cs5412.project.distributed_file_system.pojo.File;

public class FileJdbcDAO implements FileDAO {

	@Override
	public int createFile(File file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateFile(File file) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public File getFileByFid(int fid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<File> getFileByParentFir(File parentDir) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteFile(File file) {
		// TODO Auto-generated method stub
		return false;
	}

}
