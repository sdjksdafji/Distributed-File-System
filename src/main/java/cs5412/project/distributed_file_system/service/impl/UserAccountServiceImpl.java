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

@Named
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
		System.out.println("getUidFromCookie uid=" + uid);
		if (this.cookieService.verifyUid(uid, request)) {
			return uid;
		} else {
			return -1;
		}
	}

	@Override
	public boolean login(User user, HttpServletResponse response) {
		User u = this.userDao.userLogin(user.getUsername(),
				user.getHashedPassword());
		if (u != null) {
			this.cookieService.storeUid(u.getUid(), response);
			System.out.println("login complete uid=" + u.getUid());
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
