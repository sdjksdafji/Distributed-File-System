package cs5412.project.distributed_file_system.service;

import javax.servlet.http.HttpServletRequest;

public interface UserAccountService {
	
//	return uid if valid login cookie found 
//	return -1 if there is no login cookie or it is invalid
	int getUidFromCookie(HttpServletRequest request);
}
