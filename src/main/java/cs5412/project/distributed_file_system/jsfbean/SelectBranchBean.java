package cs5412.project.distributed_file_system.jsfbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import cs5412.project.distributed_file_system.dao.FileDAO;
import cs5412.project.distributed_file_system.pojo.File;
import cs5412.project.distributed_file_system.service.UserAccountService;

@Named
@Scope("request")
@URLBeanName("selectBranchBean")
@URLMapping(id = "selectBranch", pattern = "/branch", viewId = "/views/FileBrowser/SelectBranch.xhtml")
public class SelectBranchBean {
	private List<File> branches = null;
	private File selectedBranch;// only fid is valid in selectedItem, other
								// fields
								// are all default value
	private int userId;

	@Inject
	private FileDAO fileDao;
	@Inject
	private UserAccountService userAccountService;

	@URLAction
	public void init() {
		checkLoginCookie();
		readBranchListForUser();
		System.out.println(this.branches.size());
	}

	private void checkLoginCookie() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		this.userId = this.userAccountService.getUidFromCookie(request);
	}

	private void readBranchListForUser() {
		if (this.userId >= 0) {
			this.branches = this.fileDao.getBranchesForUser(userId);
		}
	}

	public String select(ActionEvent e) {
		return "";
	}

	public List<File> getBranches() {
		if (this.branches != null) {
			return branches;
		} else {
			return new ArrayList<File>();
		}
	}

	public void setBranches(List<File> branches) {
		this.branches = branches;
	}

	public File getSelectedBranch() {
		return selectedBranch;
	}

	public void setSelectedBranch(File selectedBranch) {
		this.selectedBranch = selectedBranch;
	}

}
