package cs5412.project.distributed_file_system.service.mock;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs5412.project.distributed_file_system.pojo.User;
import cs5412.project.distributed_file_system.service.UserAccountService;

@Named
public class UserAccountServiceMock implements UserAccountService {

	@Override
	public int getUidFromCookie(HttpServletRequest request) {
		return 1;
	}

	@Override
	public boolean login(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean login(User user, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean register(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
