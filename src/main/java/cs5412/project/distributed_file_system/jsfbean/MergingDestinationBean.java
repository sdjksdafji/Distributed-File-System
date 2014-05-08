package cs5412.project.distributed_file_system.jsfbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;

import cs5412.project.distributed_file_system.dao.FileDAO;
import cs5412.project.distributed_file_system.pojo.File;
import cs5412.project.distributed_file_system.service.CookieService;
import cs5412.project.distributed_file_system.service.UserAccountService;

@Named
@Scope("request")
public class MergingDestinationBean implements Serializable {
	private List<File> branches;
	private int userId;

	@Inject
	private FileDAO fileDao;
	@Inject
	private CookieService cookieService;
	@Inject
	private UserAccountService userAccountService; // should be cookie service
													// after connected to jdbc

	@PostConstruct
	public void init() {
		getLoginCookie();
		readBranchListForUser();
	}

	private void getLoginCookie() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		this.userId = this.userAccountService.getUidFromCookie(request);
	}

	private void readBranchListForUser() {
		if (this.userId >= 0) {
			this.branches = this.fileDao.getBranchesForUser(userId);
		}
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		int selectedBranchId = this.cookieService.getBranchFid(request);
		int index = -1;
		if (this.branches != null) {
			index = 0;
			for (File branch : branches) {
				if (branch.getFid() == selectedBranchId) {
					break;
				}
				index++;
			}
		}
		if (index >= 0) {
			this.branches.remove(index);
		}
	}

	public void selectBranchFromDialog(File file) {
		RequestContext.getCurrentInstance().closeDialog(file);
	}

	public List<File> getBranches() {
		return branches;
	}

	public void setBranches(List<File> branches) {
		this.branches = branches;
	}

}
