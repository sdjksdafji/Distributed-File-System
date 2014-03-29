package cs5412.project.distributed_file_system.dao.jdbc;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.JdbcTemplate;

import cs5412.project.distributed_file_system.dao.FileDAO;
import cs5412.project.distributed_file_system.pojo.File;

@Named
public class FileJdbcDAO implements FileDAO {
	
	@Inject
	private JdbcTemplate jdbcTemplate;

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
