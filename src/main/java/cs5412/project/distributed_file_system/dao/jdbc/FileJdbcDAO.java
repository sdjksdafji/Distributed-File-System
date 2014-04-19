package cs5412.project.distributed_file_system.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import cs5412.project.distributed_file_system.dao.FileDAO;
import cs5412.project.distributed_file_system.pojo.File;

@Named
public class FileJdbcDAO implements FileDAO {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final class FileMapper implements RowMapper<File> {
		public File mapRow(ResultSet rs, int rowNum) throws SQLException {
			File file = new File();
			file.setfId(rs.getInt("fid"));
			file.setName(rs.getString("fname"));
			file.setLocation(rs.getString("location"));
			file.setParentDir(rs.getString("directory"));
			file.setHash(rs.getString("hash"));
			file.setVersion(rs.getInt("version"));
			file.setuId(rs.getInt("uid"));
			file.setDir(rs.getBoolean("uid"));
			return file;
		}
	}

	@Override
	public int createFile(File file) {
		// fid is an autoincrement integer
		this.jdbcTemplate
				.update("insert into File (fname, location, directory, hash, version, uid, isdir) values (?, ?, ?, ?, ?, ?, ?)",
						new Object[] { file.getName(), file.getLocation(),
								file.getParentDir(), file.getHash(),
								file.getVersion(), file.getuId(), file.isDir() });
		int ret = this.jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
				Integer.class);
		return ret;
	}

	@Override
	public boolean updateFile(File file) {
		try {
			this.jdbcTemplate
					.update("update File set fname = ?, location=?, directory=?, hash=?, version=?, uid=?, isdir=? where fid = ?",
							new Object[] { file.getName(), file.getLocation(),
									file.getParentDir(), file.getHash(),
									file.getVersion(), file.getuId(),
									file.isDir(), file.getfId() });
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public File getFileByFid(int fid) {
		File ret = (File) this.jdbcTemplate
				.queryForObject(
						"select fid, fname, location, directory, hash, version, uid, isdir from File where fid = ?",
						new Object[] { fid }, new FileMapper());
		return ret;
	}

	@Override
	public List<File> getFileByParentDir(File parentDir) {
		List<File> files = this.jdbcTemplate
				.query("select fid, fname, location, directory, hash, version, uid, isdir from File where directory = ?",
						new Object[] { parentDir.getName() }, new FileMapper());
		return files;
	}

	@Override
	public boolean deleteFile(File file) {
		try {
			this.jdbcTemplate.update("delete from File where fid = ?",
					new Object[] { file.getfId() });
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public File getRootDirForUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
