package cs5412.project.distributed_file_system.service.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs5412.project.distributed_file_system.dao.FileDAO;
import cs5412.project.distributed_file_system.dao.UserDAO;
import cs5412.project.distributed_file_system.pojo.User;
import cs5412.project.distributed_file_system.service.CookieService;
import cs5412.project.distributed_file_system.service.UserAccountService;

//@Named
public class UserAccountServiceImpl implements UserAccountService {

	@Inject
	private UserDAO userDao;

	@Inject
	private FileDAO fileDao;

	@Inject
	private CookieService cookieService;

	@Override
	public int getUidFromCookie(HttpServletRequest request) {
		int uid = this.cookieService.getUidFromCookie(request);
		if (this.cookieService.verifyUid(uid, request)) {
			return uid;
		} else {
			return -1;
		}
	}

	@Override
	public boolean login(User user, HttpServletResponse response) {
		if (this.userDao
				.userLogin(user.getUsername(), user.getHashedPassword())) {
			this.cookieService.storeUid(user.getUid(), response);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean register(User user) {
		int uid = this.userDao.createUser(user);
		user.setUid(uid);
		if (uid >= 0) {
			return true;
		} else {
			return false;
		}
	}

}
