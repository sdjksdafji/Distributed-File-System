package cs5412.project.distributed_file_system.jsfbean;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import cs5412.project.distributed_file_system.service.GetLocalIPService;

@Named
@Scope("request")
public class DisplayIpBean {
	private String ip;

	@Inject
	private GetLocalIPService getLocalIpService;

	public String getIp() {
		return this.getLocalIpService.getLocalIP().toString();
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
