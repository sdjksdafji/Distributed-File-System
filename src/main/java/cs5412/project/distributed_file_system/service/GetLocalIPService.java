package cs5412.project.distributed_file_system.service;

import java.net.InetAddress;

public interface GetLocalIPService {
	public InetAddress getLocalIP();

	public String moveFirstSlash(String ip);
}
