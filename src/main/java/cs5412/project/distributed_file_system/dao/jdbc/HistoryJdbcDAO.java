package cs5412.project.distributed_file_system.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import cs5412.project.distributed_file_system.dao.HistoryDAO;
import cs5412.project.distributed_file_system.pojo.History;

@Named
public class HistoryJdbcDAO implements HistoryDAO {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final class HistoryMapper implements RowMapper<History> {
		public History mapRow(ResultSet rs, int rowNum) throws SQLException {
			History history = new History();
			history.setHid(rs.getInt("hid"));
			history.setTs(rs.getTimestamp("timestamp"));
			history.setUid(rs.getInt("uid"));
			history.setOldFid(rs.getInt("File_fid_old"));
			history.setNewFid(rs.getInt("File_fid_new"));
			history.setOperationType(rs.getInt("type"));
			return history;
		}
	}

	@Override
	public History getHistoryByHid(int hid) {
		History ret = (History) this.jdbcTemplate
				.queryForObject(
						"select hid, timestamp, uid, File_fid_old, File_fid_new, type from History where hid = ?",
						new Object[] { hid }, new HistoryMapper());
		return ret;
	}

	@Override
	public int createHistory(History history) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateHistory(History history) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteHisotry(History history) {
		if (history.getHid() <= 0) {
			return false;
		}
		try {
			if (history.getOldFid() > 0) {
				this.jdbcTemplate
						.update("update File set hiscount = hiscount - 1 where fid = ?	",
								new Object[] { history.getOldFid() });
			}
			if (history.getNewFid() > 0) {
				this.jdbcTemplate
						.update("update File set hiscount = hiscount - 1 where fid = ?	",
								new Object[] { history.getNewFid() });
			}
			this.jdbcTemplate.update("delete from History where Hid = ?",
					new Object[] { history.getHid() });

		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean keepLatestNHistoryForUser(int uid, int n) {
		List<History> histories = this.jdbcTemplate
				.query("select hid, timestamp, uid, File_fid_old, File_fid_new, type from History where uid = ?",
						new Object[] { uid }, new HistoryMapper());

		Collections.sort(histories, new Comparator<History>() {
			@Override
			public int compare(History h1, History h2) {

				return h2.getTs().compareTo(h1.getTs());
			}
		});
		if (histories.size() > n) {
			boolean isSuccess = true;
			for (int i = n; i < histories.size(); i++) {
				isSuccess = isSuccess && deleteHisotry(histories.get(i));
			}
			return isSuccess;
		} else {
			return true;
		}
	}

	@Override
	public List<History> getLatestNHistoryForUser(int uid, int n) {
		List<History> histories = this.jdbcTemplate
				.query("select hid, timestamp, uid, File_fid_old, File_fid_new, type from History where uid = ?",
						new Object[] { uid }, new HistoryMapper());

		Collections.sort(histories, new Comparator<History>() {
			@Override
			public int compare(History h1, History h2) {

				return h2.getTs().compareTo(h1.getTs());
			}
		});
		if (histories.size() > n) {
			return histories.subList(0, n);
		} else {
			return histories;
		}
	}

	@Override
	public boolean revertHistory(History history) {
		// TODO Auto-generated method stub
		return false;
	}

}
