package cs5412.project.distributed_file_system.service.mock;

import javax.servlet.http.HttpServletRequest;

import cs5412.project.distributed_file_system.service.UserAccountService;

public class UserAccountServiceMock implements UserAccountService {

	@Override
	public int getUidFromCookie(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return 1;
	}

}
