package cs5412.project.distributed_file_system.service.impl;

import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs5412.project.distributed_file_system.service.CookieService;

@Named
public class CookieServiceImpl implements CookieService {
	
	private static final String FID = "FidByThisProject";
	private static final int COOKIE_TIMEOUT = 120;

	@Override
	public void storeFolderFid(int fid, HttpServletResponse response) {
		Cookie cookie = new Cookie(FID, Integer.toString(fid));
		cookie.setMaxAge(COOKIE_TIMEOUT);
		response.addCookie(cookie);
	}

	@Override
	public int getFolderFid(HttpServletRequest request) {
		String fidStr = this.getCookieVal(FID, request);
		if(fidStr!=null){
			return Integer.parseInt(fidStr);
		}
		return -1;
	}
	
	private String getCookieVal(String cookieName, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				String value = cookie.getValue();
				if (name.equalsIgnoreCase(cookieName)) {
					return value;
				}
			}
		}
		return null;
	}


}
