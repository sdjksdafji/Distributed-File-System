package cs5412.project.distributed_file_system.dao;

import cs5412.project.distributed_file_system.pojo.User;

public interface UserDAO {
	public User getUserByUid(int uid);

	public int createUser(User user);

	public boolean updateUser(User user);

	public boolean deleteUser(User user);

	public boolean userLogin(String username, String hashPassword);
}
