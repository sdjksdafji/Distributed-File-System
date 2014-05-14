package cs5412.project.distributed_file_system.dao;

import java.util.List;

import cs5412.project.distributed_file_system.pojo.History;

public interface HistoryDAO {
	public History getHistoryByHid(int hid);

	public int createHistory(History history);

	public boolean updateHistory(History history);

	public boolean deleteHisotry(History history);
	
	public List<History> getLatestNHistoryForUser(int uid, int n);
	
	public boolean revertHistory(History history);

	public boolean keepLatestNHistoryForUser(int uid, int n);
}
