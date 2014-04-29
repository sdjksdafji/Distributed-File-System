package cs5412.project.distributed_file_system.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CookieService {
	public void storeFolderFid(int fid, HttpServletResponse response);
	public int getFolderFid(HttpServletRequest request);
}
