package cs5412.project.distributed_file_system.service.mock;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs5412.project.distributed_file_system.pojo.User;
import cs5412.project.distributed_file_system.service.CookieService;
import cs5412.project.distributed_file_system.service.UserAccountService;

@Named
public class UserAccountServiceMock implements UserAccountService {

	@Inject
	private CookieService cookieService;

	@Override
	public int getUidFromCookie(HttpServletRequest request) {
		return 1;
	}

	@Override
	public boolean login(User user, HttpServletResponse response) {
		this.cookieService.storeUid(user.getUid(), response);
		return true;
	}

	@Override
	public boolean register(User user) {
		return true;
	}

}
