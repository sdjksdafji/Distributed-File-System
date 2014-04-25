package cs5412.project.distributed_file_system.dao;

import cs5412.project.distributed_file_system.pojo.History;

public interface HistoryDAO {
	public History getHistoryByHid(int hid);

	public int createHistory(History history);

	public boolean updateHistory(History history);

	public boolean deleteHisotry(History history);
}
