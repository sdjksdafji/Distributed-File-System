package cs5412.project.distributed_file_system.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs5412.project.distributed_file_system.pojo.User;

public interface UserAccountService {

	// return uid if valid login cookie found
	// return -1 if there is no login cookie or it is invalid
	public int getUidFromCookie(HttpServletRequest request);

	public boolean login(User user, HttpServletResponse response);

	public boolean register(User user);
}
